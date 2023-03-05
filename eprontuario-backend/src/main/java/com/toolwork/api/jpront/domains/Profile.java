package com.toolwork.api.jpront.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="t_profiles")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long profileId;
    @Column(name="profileName", nullable = false, length = 40)
    private String profileName;
    //the accesses should be created in a JSON format.
    @Column(name="profileAccess", nullable = false, length = 8000)
    private String availableAccesses;
    private LocalDate createdDate;
    @Column(name="createdBy", length=100)
    private String createdBy;
    @Column(name="lastUpdateDate", columnDefinition = "DateTime")
    private LocalDate lastUpdateDate;
    @Column(name="lastUpdateBy", length=100)
    private String lastUpdateBy;
}
