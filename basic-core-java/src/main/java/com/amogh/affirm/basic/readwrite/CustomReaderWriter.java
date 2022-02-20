package com.amogh.affirm.basic.readwrite;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomReaderWriter implements ReaderWriter {
    private String folderName = null;

    public CustomReaderWriter(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public Reader getReader(String fileName) throws IOException {
        return new BufferedReader(new FileReader(folderName + "/" + fileName));
    }

    @Override
    public Writer getWriter(String fileName) throws IOException {
        return new BufferedWriter(new FileWriter(folderName + "/" + fileName));
    }
}
