package com.amogh.affirm.ms.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer keyId;

    @Column (columnDefinition = "DATE default sysdate", insertable = false)
    private Date effectiveFrom;

    @Column (columnDefinition = "DATE default to_date ('31-12-9999', 'DD-MM-YYYY')", insertable = false)
    private Date effectiveTo;

    @Column (columnDefinition = "DATE default sysdate", insertable = false)
    private Date createdOn;

    @Column
    private Date updatedOn;

}
