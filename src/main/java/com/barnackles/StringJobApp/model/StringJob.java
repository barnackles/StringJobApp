package com.barnackles.StringJobApp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class StringJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "You have to provide a value.")
    @Min(value = 1, message = "You have to provide at least one character.")
    private Integer requiredStringMinLength;
    @NotNull
    @Min(value = 1, message = "You have to provide at least one character.")
    @Max(value = Integer.MAX_VALUE, message = "This is maximum value.")
    private Integer requiredStringMaxLength;
    @NotEmpty
    @ElementCollection
    private List<Character> baseCharacters;
    @NotNull
    @Min(value = 1, message = "Minimum number of results must be at least 1.")
    @Max(value = Integer.MAX_VALUE, message = "This is maximum value.")
    private Long numberOfStrings;
    @OneToMany
    private List<GeneratedString> generatedString;

    @Override
    public String toString() {
        return "StringJob{" +
                "id=" + id +
                ", requiredStringMinLength=" + requiredStringMinLength +
                ", requiredStringMaxLength=" + requiredStringMaxLength +
                ", numberOfStrings=" + numberOfStrings +
                '}';
    }
}
