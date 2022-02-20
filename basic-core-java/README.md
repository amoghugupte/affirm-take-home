### Loan Facility assignment utility
#### Architecture and Design

![](D:\git\ssh\affirm-take-home\basic-core-java\images\process.png)

This is a basic core java utility. The utility reads from four csv files:
- loans.csv
- covenants.csv
- facilities.csv
- banks.csv

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
6. Loan assignment is exported as is, while the Yield is aggregated at Facility level and then exported. 

##### Design

The application uses [OpenCsv](http://opencsv.sourceforge.net/) to read the csv files:

Facility data is used to create the covenant filters. Covenant filters are created:
    1. If there is no banned state covenant for the facility, the bank banned state list is used.
    2. If there is Max Default likelyhood covenant for the facility, the bank Max Default likelyhood is used.

![](D:\git\ssh\affirm-take-home\basic-core-java\images\CovenantStrategy.png)

#### Deployment/run steps
##### Prerequisite
This utility was created with Java 8+.
- Maven
- Java 8+.

##### To run

*java com.amogh.affirm.basic.main.LoanAssignmentMain [-help] [-p large|small] [-c <folder-path>]*

These are the options:
- -p - Pre-fixed mode, to use the test data provided with the assignment. The output assignments.csv and yields.csv, will be written in the current working dir.
- -c - Custom mode, to use new test data provide a folder path. it should contain the four csv - loans.csv, covenants.csv, facilities.csv and banks.csv. The output assignments.csv and yields.csv, will be written in the same dir

