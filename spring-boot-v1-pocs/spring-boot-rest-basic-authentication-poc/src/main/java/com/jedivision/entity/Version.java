package com.jedivision.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Version {
    private String uuid;
    private Date date;
    private String description;
}
