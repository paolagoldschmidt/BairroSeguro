package br.com.gavazzoni.bairroseguro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "crime")
public class Crime {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "bou_year")
    private Integer bouYear;

    @Setter
    @Getter
    @Column(name = "external_id")
    private String externalId;

    @Setter
    @Getter
    @Column(name = "year")
    private Integer year;

    @Setter
    @Getter
    @Column(name = "month")
    private Integer month;

    @Setter
    @Getter
    @Column(name = "day")
    private Integer day;

    @Setter
    @Getter
    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Setter
    @Getter
    @Column(name = "hour")
    private Integer hour;

    @Setter
    @Getter
    @Column(name = "aisp")
    private String aisp;

    @Setter
    @Getter
    @Column(name = "municipality")
    private String municipality;

    @Setter
    @Getter
    @Column(name = "neighborhood")
    private String neighborhood;

    @Setter
    @Getter
    @Column(name = "nature_type")
    private String natureType;

    @Setter
    @Getter
    @Column(name = "nature")
    private String nature;

    @Setter
    @Getter
    @Column(name = "environment")
    private String environment;
}
