//package com.barnackles.StringJobApp.controller;
//
//import com.barnackles.StringJobApp.dto.StringJobDto;
//import com.barnackles.StringJobApp.service.JobService;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Test;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.job.SimpleJob;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JobRestControllerTest {
//    @Test
//
//    void newJob() {
//        JobService jobService = new JobService();
//        JobLauncher jobLauncher = new SimpleJobLauncher();
//        Job job = new SimpleJob();
//
//        JobRestController jobRestController = new JobRestController(jobService, jobLauncher, job);
//// given request dto
//        StringJobDto stringJobDto1 = new StringJobDto();
//        StringJobDto stringJobDto2 = new StringJobDto();
//        StringJobDto stringJobDto3 = new StringJobDto();
//        StringJobDto stringJobDto4 = new StringJobDto();
//
//
//
//        stringJobDto1.setRequiredStringMinLength(1);
//        stringJobDto1.setRequiredStringMaxLength(1);
//        // required number of strings is greater than maximum permutations
//        stringJobDto1.setNumberOfStrings(5L);
//        List<Character> characters1 = List.of('a');
//        stringJobDto1.setBaseCharacters(characters1);
//
//        //min is greater than max
//        stringJobDto2.setRequiredStringMinLength(10);
//        stringJobDto2.setRequiredStringMaxLength(2);
//        stringJobDto2.setNumberOfStrings(2L);
//        List<Character> characters2 = List.of('a','b');
//        stringJobDto2.setBaseCharacters(characters2);
//
//        //ok
//        stringJobDto3.setRequiredStringMinLength(1);
//        stringJobDto3.setRequiredStringMaxLength(2);
//        stringJobDto3.setNumberOfStrings(2L);
//        List<Character> characters3 = List.of('a','b');
//        stringJobDto3.setBaseCharacters(characters3);
//
//        //  required length of string is greater than number of characters
//        stringJobDto4.setRequiredStringMinLength(1);
//        stringJobDto4.setRequiredStringMaxLength(10);
//        stringJobDto4.setNumberOfStrings(6L);
//        List<Character> characters4 = List.of('a','b','c'); //max is 3
//        stringJobDto4.setBaseCharacters(characters4);
//
//
////responses
//
//        String requestedTooManyStrings = String.format("Maximum number of unique strings from given characters is: %d. You have requested: %d",
//                jobService.checkNumberOfUniqueStrings(stringJobDto1), stringJobDto1.getNumberOfStrings());
//
//        String minGreaterThanMaxResponse = String.format("Minimum: %d value is grater than Maximum %d",
//                stringJobDto2.getRequiredStringMinLength(), stringJobDto2.getRequiredStringMaxLength());
//
//        String okResponse = String.format("Job to create %d strings accepted.", stringJobDto3.getNumberOfStrings());
//        HttpStatus status = HttpStatus.OK;
//
//        String maxLengthGreaterThanNumberOfChars = ("String length cannot be greater than the number of the characters.");
//
////request
//
//        jobRestController.newJob(stringJobDto1);
//        jobRestController.newJob(stringJobDto2);
//        jobRestController.newJob(stringJobDto3);
//        jobRestController.newJob(stringJobDto4);
//
////assert
//
//        assertEquals(new ResponseEntity<>(requestedTooManyStrings, status), jobRestController.newJob(stringJobDto1));
//        assertEquals(new ResponseEntity<>(minGreaterThanMaxResponse, status), jobRestController.newJob(stringJobDto2));
//        assertEquals(new ResponseEntity<>(okResponse, status), jobRestController.newJob(stringJobDto3));
//        assertEquals(new ResponseEntity<>(maxLengthGreaterThanNumberOfChars, status), jobRestController.newJob(stringJobDto4));
//
//    }
//}