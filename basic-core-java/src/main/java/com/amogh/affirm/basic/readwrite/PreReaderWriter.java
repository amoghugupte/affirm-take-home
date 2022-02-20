package com.amogh.affirm.basic.readwrite;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PreReaderWriter implements ReaderWriter {
    private String folderName = null;

    public PreReaderWriter(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public Reader getReader(String fileName) throws URISyntaxException, IOException {
        return Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(folderName + "/" + fileName).toURI()));
    }

    @Override
    public Writer getWriter(String fileName) throws IOException {
        return new BufferedWriter(new FileWriter(fileName));
    }
}
