package com.avilapps.pdf_services.domain.services;

import java.io.File;
import java.io.IOException;

public interface Foliator {

    File foliate(String fileUrl, int initialFolio) throws IOException;
}
