package com.amogh.affirm.basic.readwrite;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;

public interface ReaderWriter {
    Reader getReader(String fileName) throws URISyntaxException, IOException;

    Writer getWriter(String fileName) throws IOException;
}
