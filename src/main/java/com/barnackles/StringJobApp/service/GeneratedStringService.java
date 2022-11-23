package com.barnackles.StringJobApp.service;


import com.barnackles.StringJobApp.model.GeneratedString;
import com.barnackles.StringJobApp.model.StringJob;
import com.barnackles.StringJobApp.repository.GeneratedStringRepository;
import com.barnackles.StringJobApp.repository.StringJobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class GeneratedStringService {

    private final GeneratedStringRepository generatedStringRepository;
    private final StringJobRepository stringJobRepository;


    public void saveAll(List<String> listOfGeneratedStrings, StringJob stringJob) {

        List<GeneratedString> generatedStringObjectList = new ArrayList<>();

        for (String gs : listOfGeneratedStrings) {
            GeneratedString generatedString = new GeneratedString();
            generatedString.setResult(gs);
            generatedString.setStringJob(stringJob);

            generatedStringObjectList.add(generatedString);
        }

        generatedStringRepository.saveAll(generatedStringObjectList);
        stringJob.setGeneratedString(generatedStringObjectList);
        stringJobRepository.save(stringJob);
    }

    public List<String> findAllGeneratedStringObjectsStrings(StringJob stringJob) {
        return generatedStringRepository.findAllByStringJobEquals(stringJob)
                .stream()
                .map(GeneratedString::getResult)
                .toList();
    }

    public void deleteAllGeneratedStringObjects(StringJob stringJob) {
        log.info("All deleted");
        generatedStringRepository.deleteAllByStringJobEquals(stringJob);
    }

}
