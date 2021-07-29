package com.avilapps.pdf_services.api.mapper;

import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.domain.model.ContentFile;
import com.avilapps.pdf_services.domain.model.Document;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class FoliateDocumentApiMapperTest {

    private final FoliateDocumentApiMapper mapper = Mappers.getMapper(FoliateDocumentApiMapper.class);

    @Test
    public void whenMappingNullFoliateDocumentRequestThenReturnNull() {
        assertNull(mapper.mapApiRequestToDomainModel(null));
    }

    @Test
    public void shouldMapFoliateDocumentRequest() {
        FoliateDocumentRequest source = new FoliateDocumentRequest();
        source.setInitialFolio("10");
        source.setOriginPath("http://localhost/pdf");
        source.setDestinationPath("/document/1/attachment");

        Document destination = mapper.mapApiRequestToDomainModel(source);

        assertEquals(10, destination.getInitialFolio());
        assertEquals(source.getOriginPath(), destination.getOriginalFile().getUrl());
        assertEquals(source.getDestinationPath(), destination.getProcessedFile().getUrl());
    }

    @Test
    public void whenMappingNullDocumentResponseThenReturnNull() {
        assertNull(mapper.mapDomainModelToApiResponse(null));
    }

    @Test
    public void shouldMapResponse() {
        Document source = new Document();
        ContentFile contentFile = new ContentFile();
        contentFile.setUrl("http://localhost/pdf");
        source.setProcessedFile(contentFile);

        FoliateDocumentResponse destination = mapper.mapDomainModelToApiResponse(source);

        assertEquals(source.getProcessedFile().getUrl(), destination.getUrl());
    }
}