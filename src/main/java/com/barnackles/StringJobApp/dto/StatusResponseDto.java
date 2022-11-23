package com.barnackles.StringJobApp.dto;

import lombok.Data;

@Data
public class StatusResponseDto {

    private int activeTasksCount;
    private Long completedTaskCount;

}
