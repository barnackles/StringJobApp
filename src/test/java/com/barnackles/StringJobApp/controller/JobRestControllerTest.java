package com.barnackles.StringJobApp.controller;

import com.barnackles.StringJobApp.dto.CreateNewJobDto;
import com.barnackles.StringJobApp.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRestControllerTest {

    @Test
    void newJob() {
        JobService jobService = new JobService();
        JobRestController jobRestController = new JobRestController(jobService);
// given request dto
        CreateNewJobDto createNewJobDto1 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto2 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto3 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto4 = new CreateNewJobDto();



        createNewJobDto1.setRequiredStringMinLength(1);
        createNewJobDto1.setRequiredStringMaxLength(1);
        // required number of strings is greater than maximum permutations
        createNewJobDto1.setNumberOfStrings(5L);
        List<Character> characters1 = List.of('a');
        createNewJobDto1.setBaseCharacters(characters1);

        //min is greater than max
        createNewJobDto2.setRequiredStringMinLength(10);
        createNewJobDto2.setRequiredStringMaxLength(2);
        createNewJobDto2.setNumberOfStrings(2L);
        List<Character> characters2 = List.of('a','b');
        createNewJobDto2.setBaseCharacters(characters2);

        //ok
        createNewJobDto3.setRequiredStringMinLength(1);
        createNewJobDto3.setRequiredStringMaxLength(2);
        createNewJobDto3.setNumberOfStrings(2L);
        List<Character> characters3 = List.of('a','b');
        createNewJobDto3.setBaseCharacters(characters3);

        //  required length of string is greater than number of characters
        createNewJobDto4.setRequiredStringMinLength(1);
        createNewJobDto4.setRequiredStringMaxLength(10);
        createNewJobDto4.setNumberOfStrings(6L);
        List<Character> characters4 = List.of('a','b','c'); //max is 3
        createNewJobDto4.setBaseCharacters(characters4);


//responses

        String requestedTooManyStrings = String.format("Maximum number of unique strings from given characters is: %d. You have requested: %d",
                jobService.checkNumberOfUniqueStrings(createNewJobDto1), createNewJobDto1.getNumberOfStrings());

        String minGreaterThanMaxResponse = String.format("Minimum: %d value is grater than Maximum %d",
                createNewJobDto2.getRequiredStringMinLength(), createNewJobDto2.getRequiredStringMaxLength());

        String okResponse = String.format("Job to create %d strings accepted.", createNewJobDto3.getNumberOfStrings());
        HttpStatus status = HttpStatus.OK;

        String maxLengthGreaterThanNumberOfChars = ("String length cannot be greater than the number of the characters.");

//request

        jobRestController.newJob(createNewJobDto1);
        jobRestController.newJob(createNewJobDto2);
        jobRestController.newJob(createNewJobDto3);
        jobRestController.newJob(createNewJobDto4);

//assert

        assertEquals(new ResponseEntity<>(requestedTooManyStrings, status), jobRestController.newJob(createNewJobDto1));
        assertEquals(new ResponseEntity<>(minGreaterThanMaxResponse, status), jobRestController.newJob(createNewJobDto2));
        assertEquals(new ResponseEntity<>(okResponse, status), jobRestController.newJob(createNewJobDto3));
        assertEquals(new ResponseEntity<>(maxLengthGreaterThanNumberOfChars, status), jobRestController.newJob(createNewJobDto4));

    }
}