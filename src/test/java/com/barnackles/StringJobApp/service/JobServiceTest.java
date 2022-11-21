package com.barnackles.StringJobApp.service;

import com.barnackles.StringJobApp.dto.CreateNewJobDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    @Test

    void checkNumberOfUniqueStrings() {

        CreateNewJobDto createNewJobDto1 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto2 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto3 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto4 = new CreateNewJobDto();
        CreateNewJobDto createNewJobDto5 = new CreateNewJobDto();
        createNewJobDto1.setRequiredStringMinLength(1);
        createNewJobDto1.setRequiredStringMaxLength(10);
        createNewJobDto1.setNumberOfStrings(1L);
        createNewJobDto2.setRequiredStringMinLength(1);
        createNewJobDto2.setRequiredStringMaxLength(10);
        createNewJobDto2.setNumberOfStrings(1L);
        createNewJobDto3.setRequiredStringMinLength(1);
        createNewJobDto3.setRequiredStringMaxLength(10);
        createNewJobDto3.setNumberOfStrings(1L);
        createNewJobDto4.setRequiredStringMinLength(1);
        createNewJobDto4.setRequiredStringMaxLength(10);
        createNewJobDto4.setNumberOfStrings(1L);
        createNewJobDto5.setRequiredStringMinLength(1);
        createNewJobDto5.setRequiredStringMaxLength(10);
        createNewJobDto5.setNumberOfStrings(1L);

        List<Character> characters1 = List.of('a');
        List<Character> characters2 = List.of('a','b');
        List<Character> characters3 = List.of('a','b','c');
        List<Character> characters4 = List.of('a','b','c','d', 'e', 'f', 'g');
        List<Character> characters5 = List.of('b','a','g', 'g');
        createNewJobDto1.setBaseCharacters(characters1);
        createNewJobDto2.setBaseCharacters(characters2);
        createNewJobDto3.setBaseCharacters(characters3);
        createNewJobDto4.setBaseCharacters(characters4);
        createNewJobDto5.setBaseCharacters(characters5);

        JobService jobService = new JobService();

        Long result1 = jobService.checkNumberOfUniqueStrings(createNewJobDto1);
        Long result2 = jobService.checkNumberOfUniqueStrings(createNewJobDto2);
        Long result3 = jobService.checkNumberOfUniqueStrings(createNewJobDto3);
        Long result4 = jobService.checkNumberOfUniqueStrings(createNewJobDto4);
        Long result5 = jobService.checkNumberOfUniqueStrings(createNewJobDto5);


        assertEquals(1L, result1);
        assertEquals(2L, result2);
        assertEquals(6L, result3);
        assertEquals(5040L, result4);
        assertEquals(12L, result5);


    }
}