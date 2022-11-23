package com.barnackles.StringJobApp.task;

import com.barnackles.StringJobApp.dto.StringJobDto;
import com.barnackles.StringJobApp.model.StringJob;
import com.barnackles.StringJobApp.service.CombinationService;
import com.barnackles.StringJobApp.service.GeneratedStringService;
import com.barnackles.StringJobApp.service.StringJobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Slf4j
public class Task implements Runnable {

    private final CombinationService combinationService;
    private final StringJobService stringJobService;
    private final GeneratedStringService generatedStringService;
    private StringJobDto stringJobDto;
    private Long stringJobId;


    public void run() {

        LocalDateTime startTime = LocalDateTime.now();
        log.info("Task: for job {} is running. Start time: {}", stringJobId, startTime);
        List<String> listOfGeneratedStrings = combinationService.getListOfCombinations(stringJobDto);
        log.info("Task: for job {} is running - string generation is complete. Persisting data.", stringJobId);
        StringJob persistedStringJob = stringJobService.findById(stringJobId);
        generatedStringService.saveAll(listOfGeneratedStrings, persistedStringJob);
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        log.info("Task: for job {} is complete. Finished: {}. Processing Time: {} s", stringJobId, endTime, duration.toSeconds());
    }

}
