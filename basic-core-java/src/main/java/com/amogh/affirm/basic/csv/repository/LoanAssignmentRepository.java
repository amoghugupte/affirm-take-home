package com.amogh.affirm.basic.csv.repository;

import com.amogh.affirm.basic.bean.LoanAssignment;
import com.amogh.affirm.basic.csv.entity.LoanAssignmentEntity;
import com.amogh.affirm.basic.readwrite.ReaderWriter;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.Data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Data
public class LoanAssignmentRepository {
    private ReaderWriter readerWriter = null;

    private List<LoanAssignmentEntity> loanAssignmentEntities = new ArrayList<>();

    public LoanAssignmentRepository(ReaderWriter readerWriter) {
        this.readerWriter = readerWriter;
    }

    public void push (LoanAssignment loanAssignment) {
        loanAssignmentEntities.add (new LoanAssignmentEntity(loanAssignment.getLoan().getId(), loanAssignment.getFacility().getId()));
    }

    public void persist () {
        Writer writer  = null;
        try {
            writer = readerWriter.getWriter("assignments.csv");
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();

            sbc.write(loanAssignmentEntities);
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
