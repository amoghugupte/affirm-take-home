package com.amogh.affirm.basic.csv.repository;

import com.amogh.affirm.basic.csv.entity.YieldEntity;
import com.amogh.affirm.basic.bean.Yield;
import com.amogh.affirm.basic.readwrite.ReaderWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YieldRepository {
    private ReaderWriter readerWriter = null;

    private List<Yield> yieldList = new ArrayList<>();

    public YieldRepository(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public void push(Yield yield) {
        yieldList.add(yield);
    }

    public List <YieldEntity> getYieldEntities () {
        return yieldList.stream()
                .collect(Collectors.groupingBy(Yield::getFacilityId, Collectors.summingLong(Yield::getExpectedYield)))
                .entrySet()
                .stream()
                .flatMap(integerLongEntry -> Stream.of(new YieldEntity(integerLongEntry.getKey(), integerLongEntry.getValue())))
                .collect(Collectors.toList());
    }

    public void persist () {
        Writer writer  = null;
        try {
            writer = readerWriter.getWriter("yields.csv");
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(getYieldEntities ());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                //ignore
            }
        }
    }
}
