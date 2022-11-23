package com.barnackles.StringJobApp.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

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
