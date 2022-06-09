package com.company.entity;

import com.company.enums.ProfileStatus;
import com.company.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "created_date")
    private LocalDateTime created_date;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProfileStatus status = ProfileStatus.ACTIVE;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProfileRole role = ProfileRole.USER;
    @Column
    private Boolean visible;

}
