package com.metro.newborns.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "metro_newborns")
public class NewBorn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unique_identifier;

    @Column(name = "child_name")
    private String child_name;

    @Column(name = "child_place_of_birth")
    private String child_place_of_birth;

    @Column(name = "child_registration_number")
    private String child_registration_number;

    @Column(name = "child_date_of_birth")
    private LocalDate child_date_of_birth;

    @Column(name = "child_gender")
    private String child_gender;

    @Column(name = "child_weight")
    private Double child_weight;

    @Column(name = "mothers_name")
    private String mothers_name;

    @Column(name = "mothers_age")
    private Long mothers_age;

    @Column(name = "mothers_occupation")
    private String mothers_occupation;

    @Column(name = "mothers_martial_status")
    private String mothers_martial_status;


    @Column(name = "created_at")
    private Instant created_at;


}
