package com.amogh.affirm.basic.main;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LoanAssignmentMainTest {
    @Test
    public void testSmall () throws URISyntaxException, IOException, CsvValidationException {
        LoanAssignmentMain.main(new String []{"-p", "small"});
        assert (compare ("assignments.csv") == 0);
        assert (compare ("yields.csv") == 0);
    }

    @Test
    public void testLarge () throws URISyntaxException, IOException, CsvValidationException {
        //Test if the allocation is greater than the facility amount
        LoanAssignmentMain.main(new String []{"-p", "large"});
        Map <Integer, Double> loanMap = getIdAmountMap ("large/loans.csv");
        Map <Integer, Double> facilityMap = getIdAmountMap ("large/facilities.csv");
        Map <Integer, Double> facilityAssignedMap = getFacilityAssignedMap (loanMap);

        Set <Map.Entry<Integer, Double>> facilityAssignedEntrySet = facilityAssignedMap.entrySet();
        for (Map.Entry<Integer, Double> entry: facilityAssignedEntrySet) {
            assert (entry.getValue() < facilityMap.get(entry.getKey()));
        }
    }

    private Map<Integer, Double> getFacilityAssignedMap(Map <Integer, Double> loanMap) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReader(new BufferedReader(new FileReader("assignments.csv")));
        String [] headerLine = reader.readNext();
        int loanIdIndex = -1;
        int facilityIdIndex = -1;
        for (int i = 0 ; i < headerLine.length; i++) {
            if ("loan_id".equalsIgnoreCase(headerLine [i])) {
                loanIdIndex = i;
            }

            if ("facility_id".equalsIgnoreCase(headerLine [i])) {
                facilityIdIndex = i;
            }
        }

        String [] nextLine;
        Map <Integer, Double> facilityAssignedMap = new HashMap<>();
        while ((nextLine = reader.readNext()) != null) {
            facilityAssignedMap.put(Integer.valueOf(nextLine[facilityIdIndex]),
                    facilityAssignedMap.getOrDefault(Integer.valueOf(nextLine[facilityIdIndex]), 0.0)
                            + loanMap.get(Integer.valueOf(nextLine[loanIdIndex])));
        }

        reader.close();
        return facilityAssignedMap;
    }

    private Map<Integer, Double> getIdAmountMap(String fileName) throws URISyntaxException, CsvValidationException, IOException {
        Map<Integer, Double> idAmountMap = new HashMap<>();
        CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource( fileName).toURI())));
        String [] headerLine = reader.readNext();
        int idIndex = -1;
        int amountIndex = -1;
        for (int i = 0 ; i < headerLine.length; i++) {
            if ("id".equalsIgnoreCase(headerLine [i])) {
                idIndex = i;
            }

            if ("amount".equalsIgnoreCase(headerLine [i])) {
                amountIndex = i;
            }
        }

        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            idAmountMap.put(Integer.valueOf(nextLine[idIndex]), Double.valueOf(nextLine[amountIndex]));
        }
        reader.close();
        return idAmountMap;
    }

    private int compare(String fileName) throws URISyntaxException, IOException, CsvValidationException {
        Map<String, Integer> expectedMap = getLineMap (Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource("small/" + fileName).toURI())));
        Map<String, Integer> actualMap = getLineMap (new BufferedReader(new FileReader(fileName)));
        Set <Map.Entry<String, Integer>> entries = actualMap.entrySet();
        int count = 0;
        for (Map.Entry<String, Integer> entry:entries) {
            if (!entry.getValue().equals(expectedMap.get(entry.getKey()))) {
                count ++;
            }
        }
        return count;
    }

    private Map<String, Integer> getLineMap(BufferedReader newBufferedReader) throws IOException, CsvValidationException {

        CSVReader reader = new CSVReader(newBufferedReader);
        String [] headerLine = reader.readNext();
        String [] nextLine;
        Map <String, Integer> integerMap = new HashMap<>();
        while ((nextLine = reader.readNext()) != null) {
            Map<String, String> line = new TreeMap<>();
            for (int i = 0; i < nextLine.length; i++) {
                line.put(headerLine [i], nextLine[i]);
            }

            Set <Map.Entry <String, String>> entries = line.entrySet();
            StringBuilder builder = new StringBuilder();
            for (Map.Entry <String, String> entry: entries) {
                builder.append(entry.getValue()).append(",");
            }
            integerMap.put(builder.toString(), integerMap.getOrDefault(line, 0) + 1);
        }
        newBufferedReader.close();
        return integerMap;
    }
}

