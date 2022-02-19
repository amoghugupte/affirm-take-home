package com.amogh.affirm.facilityservice.csv.util;

import com.amogh.affirm.facilityservice.csv.model.FacilityCsvEntity;
import com.amogh.affirm.facilityservice.csv.parser.FacilityParser;
import com.amogh.affirm.facilityservice.model.Facility;
import org.mapstruct.factory.Mappers;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FacilityUtil {

    public List<Facility> load (InputStream inputStream) {
            CsvMapper entityToBeanMapper = Mappers.getMapper(CsvMapper.class);

            List<FacilityCsvEntity> facilityList = FacilityParser
                    .builder()
                    .inputStream(inputStream)
                    .build()
                    .toBeans();

            List<Facility> facilities = facilityList
                    .stream()
                    .flatMap(facilityEntity -> Stream.of (getBean (entityToBeanMapper, facilityEntity)))
                    .collect(Collectors.toList());
            return facilities;
    }

    private Facility getBean(CsvMapper entityToBeanMapper, FacilityCsvEntity facilityEntity) {
        Facility facility = entityToBeanMapper.facilityEntityToBean(facilityEntity);
        facility.setCurrAmount(facility.getAmount());
        return facility;
    }
}
