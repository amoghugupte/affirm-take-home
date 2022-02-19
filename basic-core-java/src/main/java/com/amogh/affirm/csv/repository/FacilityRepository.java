package com.amogh.affirm.csv.repository;

import com.amogh.affirm.bean.Facility;
import com.amogh.affirm.csv.entity.BankEntity;
import com.amogh.affirm.csv.entity.FacilityEntity;
import com.amogh.affirm.csv.parser.BankParser;
import com.amogh.affirm.csv.parser.CovenantParser;
import com.amogh.affirm.csv.parser.FacilityParser;
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
    private String folder = null;
    private List<Facility> facilities = null;

    public FacilityRepository load () {
        try {
            List <BankEntity> bankList = BankParser
                    .builder()
                    .fileName(folder + "/banks.csv")
                    .build()
                    .toBeans();

            List<FacilityEntity> facilityList = FacilityParser
                    .builder()
                    .fileName(folder + "/facilities.csv")
                    .build()
                    .toBeans(bankList);

            CovenantParser
                    .builder()
                    .fileName(folder + "/covenants.csv")
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
