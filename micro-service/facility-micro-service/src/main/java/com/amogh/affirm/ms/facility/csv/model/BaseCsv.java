package com.amogh.affirm.ms.facility.csv.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseCsv {
    private Integer keyId;
    
    private Date effectiveFrom;

    private Date effectiveTo;

    private Date createdOn;

    private Date updatedOn;
}
