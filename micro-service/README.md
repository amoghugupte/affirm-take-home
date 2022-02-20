# Read Me First
### Loan Facility assignment app
#### Architecture and Design

![](https://github.com/amoghugupte/affirm-take-home/blob/main/basic-core-java/images/process.png)

This is a [spring boot](https://spring.io/projects/spring-boot) application.
It used in memory [H2 Database Engine](https://www.h2database.com/html/main.html) to store the data.
It uses [Zuul](https://github.com/Netflix/zuul/wiki) and [netflix eureka](https://github.com/Netflix/eureka)
This is a very loosely coupled microservice implementation.

![](https://github.com/amoghugupte/affirm-take-home/blob/main/spring-boot-single-service/images/micro-service.png)

Api information is same as [](../spring-boot-single-service)

Few points - 
1. Banks, Facilities and Covenants create the Facility data.
2. Facility data is used to create the covenant filters.
3. Loans data is read one at a time and run through the Covenant filter, Covenants are filtered by the following criteria:
    1. If the loan meets the Max Default likelyhood criteria.
    2. If the loan does not originate from the banned states.
    3. If the Facility has enough balance to finance the loan.
4. The best matching facility is assigned based on two criteria:
    1. Interest Rate - the facility with the minimum interest rate is used.
    2. Pending amount - If the interest rates are the same then Facility with lower amount is chosen, to keep the bigger facilities for larger loans.
5. The loan assignment is captured with the Yield.
6. The application also captures the time and history for a facility and loan assignment, which can help in debugging. 
7. Loan assignment is exported as is, while the Yield is aggregated at Facility level and then exported.

##### Design

The application uses [OpenCsv](http://opencsv.sourceforge.net/) to read the csv files:

Facility data is used to create the covenant filters. Covenant filters are created:
1. If there is no banned state covenant for the facility, the bank banned state list is used.
2. If there is Max Default likelyhood covenant for the facility, the bank Max Default likelyhood is used.

![](https://github.com/amoghugupte/affirm-take-home/blob/main/basic-core-java/images/CovenantStrategy.png)

![](https://github.com/amoghugupte/affirm-take-home/blob/main/spring-boot-single-service/images/highDesign.png)

#### Deployment/run steps
##### Prerequisite
This utility was created with Java 11.
- Maven
- Java 11+.

##### To run
###### Build
- mvn clean package

###### Run
###### Start the application
1. Start com.amogh.affirm.ms.eureka.LoanEurekaServerApplication
2. Start com.amogh.affirm.ms.gateway.LoanGatewayApplication
3. Start com.amogh.affirm.ms.facility.FacilityServiceApplication
4. Start com.amogh.affirm.ms.loan.LoanServiceApplication

- Open the landing page [http://localhost:9700](http://localhost:9700)

- Upload banks.csv, facilities.csv, covenants.csv and loans.csv
- Hit Assign
- Click on the export links to down the csvs.
- Access [H2-Console](http://localhost:9700/facility-service/h2-console) - user: sa, password: password
- Access [H2-Console](http://localhost:9700/loan-service/h2-console) - user: sa, password: password
- Or Swagger link

The uploads can also be done through any rest client like postman or similar.

### Write Up:
1. How long did you spend working on the problem? What did you find to be the most
   difficult part?

I spent roughly 4 hours on top of the spring boot single application. The most time was spend on validating the results. The small data-set was really useful.

2. How would you modify your data model or code to account for an eventual introduction
   of new, as-of-yet unknown types of covenants, beyond just maximum default likelihood
   and state restrictions?

   The modification will be in the below:
    1. Bank and Facility model.
    2. New CovenantStrategy
    3. CovenantFactory for the new CovenantStrategy.

   Rest should not change.


3. How would you architect your solution as a production service wherein new facilities can
   be introduced at arbitrary points in time. Assume these facilities become available by the
   finance team emailing your team and describing the addition with a new set of CSVs.

   I would Build this as micro-services. Something with below components:
    1. Facility Poller which will keep checking the email for new facilities.
    2. Facility Service which will capture the current facility data and publish the updated data to a kafka topic.
    3. Loan Service - This will capture the loan details and publish them to a kafka topic.
    4. Loan Assignment Service to read from the kafka topics mentioned above and publish Assignment and Yield to new topics.

4. Your solution most likely simulates the streaming process by directly calling a method in
   your code to process the loans inside of a for loop. What would a REST API look like for
   this same service? Stakeholders using the API will need, at a minimum, to be able to
   request a loan be assigned to a facility, and read the funding status of a loan, as well as
   query the capacities remaining in facilities.

   The rest api will look like:
    1. new loan
        - Api - /loans/new
        - Input - *Loan { interestRate number, amount	number, id integer, defaultLikelihood number, state string }*
        - Output - Success message
    2. Assign Loan
        - Api - /loans/assign/{id}
        - Input - *id*  for the loan
        - Output - *LoanAssignment {loanId integer, facilityId integer}*
    3. Facilities
        - Api - /facility/get
        - Input - nothing
        - Output - List of *Facility {id integer, bankId integer, amount number, currAmount number, interestRate number, effectiveFrom date, effectiveTo date}*

        - This is broadly similar to what we have here.


5. How might you improve your assignment algorithm if you were permitted to assign loans
   in batch rather than streaming? We are not looking for code here, but pseudo code or
   description of a revised algorithm appreciated.

If this was a batch process then I would use the below two changes:
- Sort the loans by descending amounts, will allow maximum usage of available Facilities.
- Allow partial allocation so that the whole loan is not unfunded.
- Allow the loan to be funded by multiple facilities, this will allow to use the low interest facilities fully before using the higher interest facilities.
- Also check if new facilities have come up since last allocation, this will allow to change assignment to new facility, the newer facility has lower interest.

6. Discuss your solution’s runtime complexity.

The current solution's runtime complexity is O (L * F).
Where:
1. L - total number of loans
2. F - Number of Facilities
