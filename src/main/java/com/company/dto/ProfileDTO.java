package com.company.dto;

import com.company.enums.ProfileStatus;
import com.company.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDateTime created_date;
    private ProfileStatus status = ProfileStatus.ACTIVE;
    private ProfileRole role = ProfileRole.USER;
    private Boolean visible;
    private String jwt_token;
}
