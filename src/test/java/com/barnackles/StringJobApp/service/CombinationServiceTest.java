package com.barnackles.StringJobApp.service;

import com.barnackles.StringJobApp.dto.StringJobDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CombinationServiceTest {

    @Test
    void checkNumberOfUniqueStrings() {

//        StringJobDto stringJobDto1 = new StringJobDto();
//        StringJobDto stringJobDto2 = new StringJobDto();
//        StringJobDto stringJobDto3 = new StringJobDto();
        StringJobDto stringJobDto4 = new StringJobDto();
//        StringJobDto stringJobDto5 = new StringJobDto();
//        stringJobDto1.setRequiredStringMinLength(1);
//        stringJobDto1.setRequiredStringMaxLength(10);
//        stringJobDto1.setNumberOfStrings(1L);
//        stringJobDto2.setRequiredStringMinLength(1);
//        stringJobDto2.setRequiredStringMaxLength(10);
//        stringJobDto2.setNumberOfStrings(1L);
//        stringJobDto3.setRequiredStringMinLength(1);
//        stringJobDto3.setRequiredStringMaxLength(10);
//        stringJobDto3.setNumberOfStrings(1L);
        stringJobDto4.setRequiredStringMinLength(1);
        stringJobDto4.setRequiredStringMaxLength(10);
        stringJobDto4.setNumberOfStrings(10000L);
//        stringJobDto5.setRequiredStringMinLength(1);
//        stringJobDto5.setRequiredStringMaxLength(10);
//        stringJobDto5.setNumberOfStrings(1L);

        List<Character> characters1 = List.of('a');
        List<Character> characters2 = List.of('a','b');
//        List<Character> characters3 = List.of('a','b','c');
        List<Character> characters3 = List.of('a','b','c', 'd', 'e', 'f', 'g', 'h');
        List<Character> characters4 = List.of('a','b','c','d', 'e', 'f', 'g');
        List<Character> characters5 = List.of('b','a','g', 'g');
//        stringJobDto1.setBaseCharacters(characters1);
//        stringJobDto2.setBaseCharacters(characters2);
//        stringJobDto3.setBaseCharacters(characters3);
        stringJobDto4.setBaseCharacters(characters3);
//        stringJobDto5.setBaseCharacters(characters5);

        CombinationService combinationService = new CombinationService();

//        Long result1 = combinationService.checkNumberOfUniqueStrings(stringJobDto1);
//        Long result2 = combinationService.checkNumberOfUniqueStrings(stringJobDto2);
//        Long result3 = combinationService.checkNumberOfUniqueStrings(stringJobDto3);
        Long result4 = combinationService.checkNumberOfUniqueStrings(stringJobDto4);
//        Long result5 = combinationService.checkNumberOfUniqueStrings(stringJobDto5);


//        assertEquals(1L, result1);
//        assertEquals(2L, result2);
//        assertEquals(6L, result3);
        assertEquals(8800L, result4);
//        assertEquals(12L, result5);


    }

    @Test
    void getListOfCombinations() {

//        StringJobDto stringJobDto1 = new StringJobDto();
//        StringJobDto stringJobDto2 = new StringJobDto();
//        StringJobDto stringJobDto3 = new StringJobDto();
        StringJobDto stringJobDto4 = new StringJobDto();
//        StringJobDto stringJobDto5 = new StringJobDto();
//        stringJobDto1.setRequiredStringMinLength(1);
//        stringJobDto1.setRequiredStringMaxLength(10);
//        stringJobDto1.setNumberOfStrings(1L);
//        stringJobDto2.setRequiredStringMinLength(1);
//        stringJobDto2.setRequiredStringMaxLength(10);
//        stringJobDto2.setNumberOfStrings(1L);
//        stringJobDto3.setRequiredStringMinLength(1);
//        stringJobDto3.setRequiredStringMaxLength(10);
//        stringJobDto3.setNumberOfStrings(1L);
        stringJobDto4.setRequiredStringMinLength(1);
        stringJobDto4.setRequiredStringMaxLength(5);
        stringJobDto4.setNumberOfStrings(10000L);
//        stringJobDto5.setRequiredStringMinLength(1);
//        stringJobDto5.setRequiredStringMaxLength(10);
//        stringJobDto5.setNumberOfStrings(1L);

        List<Character> characters1 = List.of('a');
        List<Character> characters2 = List.of('a','b');
//        List<Character> characters3 = List.of('a','b','c');
        List<Character> characters3 = List.of('a','b','c', 'd', 'e', 'f', 'g', 'h');
        List<Character> characters4 = List.of('a','b','c','d', 'e', 'f', 'g');
        List<Character> characters5 = List.of('b','a','g', 'g');
//        stringJobDto1.setBaseCharacters(characters1);
//        stringJobDto2.setBaseCharacters(characters2);
//        stringJobDto3.setBaseCharacters(characters3);
        stringJobDto4.setBaseCharacters(characters3);
//        stringJobDto5.setBaseCharacters(characters5);

        CombinationService combinationService = new CombinationService();

//        List<String> result1 = combinationService.getListOfCombinations(stringJobDto1);
//        List<String> result2 = combinationService.getListOfCombinations(stringJobDto2);
//        List<String> result3 = combinationService.getListOfCombinations(stringJobDto3);
        List<String> result4 = combinationService.getListOfCombinations(stringJobDto4);
//        List<String> result5 = combinationService.getListOfCombinations(stringJobDto5);


//        assertEquals(1L, result1);
//        assertEquals(2L, result2);
//        assertEquals(6L, result3);
        System.out.println(result4);
//        assertEquals(5040L, result4);
//        assertEquals(12L, result5);

    }
}