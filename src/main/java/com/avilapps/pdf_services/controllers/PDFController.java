package com.avilapps.pdf_services.controllers;

import com.avilapps.pdf_services.dto.PageCountResponse;
import com.avilapps.pdf_services.services.PageCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {
    private static Logger LOG = LoggerFactory.getLogger(PDFController.class);

    @Autowired
    private PageCounter pageCounter;

    @GetMapping("/pages-count")
    public PageCountResponse countPDFPages(@RequestParam(value="url") String fileUrl) throws IOException {
        LOG.info("Counting pages for " + fileUrl);
        int pagesTotal = pageCounter.count(fileUrl);
        return new PageCountResponse(fileUrl, pagesTotal);
    }
}
