package com.avilapps.pdf_services.services;

import java.io.IOException;

public interface PageCounter {
    public int count(String fileUrl) throws IOException;
}
