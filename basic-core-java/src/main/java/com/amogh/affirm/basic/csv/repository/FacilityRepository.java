package com.amogh.affirm.basic.csv.repository;

import com.amogh.affirm.basic.bean.Facility;
import com.amogh.affirm.basic.csv.entity.BankEntity;
import com.amogh.affirm.basic.csv.entity.FacilityEntity;
import com.amogh.affirm.basic.csv.parser.BankParser;
import com.amogh.affirm.basic.csv.parser.CovenantParser;
import com.amogh.affirm.basic.csv.parser.FacilityParser;
import com.amogh.affirm.basic.readwrite.ReaderWriter;
import lombok.Builder;
import lombok.Data;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Data
public class FacilityRepository {
    private ReaderWriter readerWriter = null;
    private List<Facility> facilities = null;

    public FacilityRepository load () {
        try {
            List <BankEntity> bankList = BankParser
                    .builder()
                    .fileName("banks.csv")
                    .readerWriter(readerWriter)
                    .build()
                    .toBeans();

            List<FacilityEntity> facilityList = FacilityParser
                    .builder()
                    .fileName("facilities.csv")
                    .readerWriter(readerWriter)
                    .build()
                    .toBeans(bankList);

            CovenantParser
                    .builder()
                    .fileName("covenants.csv")
                    .readerWriter(readerWriter)
                    .build()
                    .toBeans(bankList, facilityList);

            EntityToBeanMapper entityToBeanMapper = Mappers.getMapper(EntityToBeanMapper.class);
            facilities = facilityList.stream().flatMap(facilityEntity -> Stream.of (getBean (entityToBeanMapper, facilityEntity))).collect(Collectors.toList());
            facilities.sort((l, r) -> l.getAmount().compareTo(r.getAmount()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private Facility getBean(EntityToBeanMapper entityToBeanMapper, FacilityEntity facilityEntity) {
        Facility facility = entityToBeanMapper.facilityEntityToBean(facilityEntity);
        facility.setCurrAmount(facility.getAmount());
        return facility;
    }
}
