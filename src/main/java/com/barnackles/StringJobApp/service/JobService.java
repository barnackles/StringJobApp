package com.barnackles.StringJobApp.service;

import com.barnackles.StringJobApp.dto.CreateNewJobDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class JobService {

    static final int MAX_CHAR = 26;
    public Long checkNumberOfUniqueStrings(CreateNewJobDto createNewJobDto) {

        return countDistinctPermutations(createNewJobDto.getBaseCharacters());
    }
    private Long countDistinctPermutations(List<Character> listOfChars) {
        int length = listOfChars.size();

        int[] frequency = new int[MAX_CHAR];

        // check frequency of character occurrences and save to an array
        // assuming that has 26 chars
        for (Character listOfChar : listOfChars) {
            if (listOfChar >= 'a')
                frequency[listOfChar - 'a']++;
        }


        //get factorial of char occurences
        // iterate through alphabet and multiply by factorial
        int factorial = 1;
        for (int i = 0; i < MAX_CHAR; i++) {
            factorial = factorial * findFactorialOfSizeOfString((frequency[i]));
        }

        // get factorial of size of string and divide by factorial resulting from multiplication
        return (Long) (long) (findFactorialOfSizeOfString(length) / factorial);
    }

    private int findFactorialOfSizeOfString(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; i++)
            factorial = factorial * i;
        return factorial;
    }

}
