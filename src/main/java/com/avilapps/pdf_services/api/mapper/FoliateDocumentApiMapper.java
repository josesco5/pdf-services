package com.avilapps.pdf_services.api.mapper;

import com.avilapps.pdf_services.api.model.FoliateDocumentRequest;
import com.avilapps.pdf_services.api.model.FoliateDocumentResponse;
import com.avilapps.pdf_services.domain.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoliateDocumentApiMapper {

    @Mapping(target = "originalFile.url", source = "originPath")
    @Mapping(target = "processedFile.url", source = "destinationPath")
    @Mapping(target = "initialFolio", source = "initialFolio")
    Document mapApiRequestToDomainModel(FoliateDocumentRequest foliateDocumentRequest);

    @Mapping(target = "url", source = "processedFile.url")
    FoliateDocumentResponse mapDomainModelToApiResponse(Document document);
}
