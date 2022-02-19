package com.amogh.affirm.facilityservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Base {
    private Integer keyId;

    private Date effectiveFrom;

    private Date effectiveTo;

    private Date createdOn;

    private Date updatedOn;
}
