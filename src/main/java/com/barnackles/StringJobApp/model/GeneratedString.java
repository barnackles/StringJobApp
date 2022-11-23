package com.barnackles.StringJobApp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "generated_strings")
public class GeneratedString {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String result;
    @ManyToOne
    private StringJob stringJob;

}
