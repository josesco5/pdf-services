package com.avilapps.pdf_services.controllers;

import com.avilapps.pdf_services.dto.FoliationRequest;
import com.avilapps.pdf_services.dto.FoliationResponse;
import com.avilapps.pdf_services.dto.PageCountResponse;
import com.avilapps.pdf_services.services.Foliator;
import com.avilapps.pdf_services.services.PageCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {
    private static Logger LOG = LoggerFactory.getLogger(PDFController.class);

    @Autowired
    private PageCounter pageCounter;

    @Autowired
    private Foliator foliator;

    @GetMapping("/pages-count")
    public PageCountResponse countPDFPages(@RequestParam(value="url") String fileUrl) throws IOException {
        LOG.info("Counting pages for " + fileUrl);
        int pagesTotal = pageCounter.count(fileUrl);
        return new PageCountResponse(fileUrl, pagesTotal);
    }

    @PostMapping("/foliate")
    public FoliationResponse foliate(@RequestBody FoliationRequest request) throws IOException {
        LOG.info("Foliating PDF file at " + request.getFoliationSettings().getFileUrl());
        String fileUrl = request.getFoliationSettings().getFileUrl();
        File result = foliator.foliate(request.getFoliationSettings());
        result.deleteOnExit();
        return new FoliationResponse(fileUrl, fileUrl);
    }
}
