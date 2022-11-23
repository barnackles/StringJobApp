package com.barnackles.StringJobApp.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class StringJobDto {

    @NotNull(message = "You have to provide a value.")
    @Min(value = 1, message = "You have to provide at least one character.")
    private Integer requiredStringMinLength;
    @NotNull
    @Min(value = 1, message = "You have to provide at least one character.")
    @Max(value = Integer.MAX_VALUE, message = "This is maximum value.")
    private Integer requiredStringMaxLength;
    @NotEmpty
    private List<Character> baseCharacters;
    @NotNull
    @Min(value = 1, message = "Minimum number of results must be at least 1.")
    @Max(value = Integer.MAX_VALUE, message = "This is maximum value.")
    private Long numberOfStrings;

}
