package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class RegionDTO {
    private Integer id;
    private String key;
    private LocalDateTime created_date;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private Boolean visible;
}
