package com.barnackles.StringJobApp.service;

import com.barnackles.StringJobApp.dto.StringJobDto;
import com.barnackles.StringJobApp.model.StringJob;
import com.barnackles.StringJobApp.repository.StringJobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class StringJobService {

    private final StringJobRepository stringJobRepository;

    public Long saveJob(StringJobDto stringJobDto) {

        StringJob stringJob = new StringJob();
        stringJob.setRequiredStringMinLength(stringJobDto.getRequiredStringMinLength());
        stringJob.setRequiredStringMaxLength(stringJobDto.getRequiredStringMaxLength());
        stringJob.setBaseCharacters(stringJobDto.getBaseCharacters());
        stringJob.setNumberOfStrings(stringJobDto.getNumberOfStrings());

        return stringJobRepository.save(stringJob).getId();

    }

    public StringJob findById(Long id) throws EntityNotFoundException {
        return stringJobRepository.findById(id).orElseThrow(() -> {
            log.error("entity with id: {} not found", id);
                    throw new EntityNotFoundException("entity not found");
                }
        );
    }

    public void updateJob(StringJob stringJob) {
        stringJobRepository.save(stringJob);
    }



}
