package com.avilapps.pdf_services.services;

import com.avilapps.pdf_services.dto.FoliationSettings;

import java.io.File;
import java.io.IOException;

public interface Foliator {
    File foliate(FoliationSettings settings) throws IOException;
}
