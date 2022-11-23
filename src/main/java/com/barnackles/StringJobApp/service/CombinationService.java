package com.barnackles.StringJobApp.service;

import com.barnackles.StringJobApp.dto.StringJobDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class CombinationService {


    public List<String> getListOfCombinations(StringJobDto stringJobDto) {

        String[] input = stringJobDto.getBaseCharacters()
                .stream()
                .map(Object::toString)
                .toArray(String[]::new);

        int maxLength = stringJobDto.getRequiredStringMaxLength();
        int minLength = stringJobDto.getRequiredStringMinLength() - 1;
        Long stringNumber = stringJobDto.getNumberOfStrings();

        //set list with provided char values
        List<String> result = new ArrayList<>(Arrays.asList(input));

        int count = input.length;

        // traverse all possible lengths
        for (int z = 0; z < maxLength - 1; z++)
        {

            // stores all combinations of length z
            List<String> tmp = new ArrayList<>();

            // traverse the array
            for (int i = 0; i < stringJobDto.getBaseCharacters().size(); i++)
            {
                for (String s : input) {
                    String currentCharString = stringJobDto.getBaseCharacters().get(i).toString();

                    if (!currentCharString.equals(s)) {
                        // Generate all
                        // combinations of length z
                        // do not allow for duplicate characters
                            if (!(s.contains(currentCharString))) {
                                tmp.add(s + currentCharString);
                                count += 1;
                            }
                    }
                }
            }

            // add all combinations of length z
            result.addAll(tmp);

            // Replace all combinations of length z - 1
            // with all combinations of length z
            input = tmp.toArray(new String[0]);
        }
            List<String> resultingList = new ArrayList<>(result.stream().filter(t -> t.length() > minLength).limit(stringNumber).toList());
        System.out.println("resultListCount" + resultingList.size());
        Collections.shuffle(resultingList);

        return resultingList;
    }



    public Long checkNumberOfUniqueStrings(StringJobDto stringJobDto) {

        return countDistinctCombinations(stringJobDto);
    }
    private Long countDistinctCombinations(StringJobDto stringJobDto) {


        return (long) getListOfCombinations(stringJobDto).size();


    }


}
