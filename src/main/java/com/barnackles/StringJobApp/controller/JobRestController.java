package com.barnackles.StringJobApp.controller;

import com.barnackles.StringJobApp.dto.StatusResponseDto;
import com.barnackles.StringJobApp.dto.StringJobDto;
import com.barnackles.StringJobApp.model.StringJob;
import com.barnackles.StringJobApp.service.CombinationService;
import com.barnackles.StringJobApp.service.GeneratedStringService;
import com.barnackles.StringJobApp.service.StringJobService;
import com.barnackles.StringJobApp.task.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobRestController {

    private final CombinationService combinationService;
    private final GeneratedStringService generatedStringService;
    private final StringJobService stringJobService;

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(15);


    @PostMapping("/new")
    public ResponseEntity<String> newJob(@Valid @RequestBody StringJobDto stringJobDto) {

        String response;
        HttpStatus status = HttpStatus.OK;

        Long maxNumberOfUniqueStrings = combinationService.checkNumberOfUniqueStrings(stringJobDto);

        if (stringJobDto.getRequiredStringMinLength() > stringJobDto.getRequiredStringMaxLength()) {

            response = String.format("Minimum: %d value is grater than Maximum %d",
                    stringJobDto.getRequiredStringMinLength(), stringJobDto.getRequiredStringMaxLength());
            return new ResponseEntity<>(response, status);
        }

        if (stringJobDto.getRequiredStringMaxLength() > stringJobDto.getBaseCharacters().size()
                || stringJobDto.getRequiredStringMinLength() > stringJobDto.getBaseCharacters().size()) {

            response = ("String length cannot be greater than the number of the characters.");

            return new ResponseEntity<>(response, status);
        }


        if (maxNumberOfUniqueStrings < stringJobDto.getNumberOfStrings()) {

            response = String.format("Maximum number of unique strings from given characters is: %d. You have requested: %d",
                    maxNumberOfUniqueStrings, stringJobDto.getNumberOfStrings());
            return new ResponseEntity<>(response, status);
        }


        Long stringJobId = stringJobService.saveJob(stringJobDto);
        executor.execute(new Task(combinationService, stringJobService, generatedStringService, stringJobDto, stringJobId));

        response = String.format("Job with id: %d - %s - has been accepted.", stringJobId, stringJobDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/status")
    public ResponseEntity<StatusResponseDto> status() {

        StatusResponseDto statusResponseDto = new StatusResponseDto();

        statusResponseDto.setActiveTasksCount(executor.getActiveCount());
        statusResponseDto.setCompletedTaskCount(executor.getCompletedTaskCount());

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);

    }


    @GetMapping(value = "/export/{jobId}", produces = "text/csv")
    public ResponseEntity<InputStreamResource> exportCSV(@Digits(integer = 10000, fraction = 0, message = "Must be a digit")
                                                         @PathVariable String jobId) {


        CSVFormat csvFileFormat = CSVFormat.Builder.create().build();
        csvFileFormat.builder().setHeader("NUmber", "String");

        StringJob readyJob = stringJobService.findById(Long.parseLong(jobId));


        List<String> csvBody = generatedStringService.findAllGeneratedStringObjectsStrings(readyJob);
        ByteArrayInputStream byteArrayOutputStream;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), csvFileFormat);
        ) {

            for (String record : csvBody)
                csvPrinter.printRecord(record);

            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        // save by the id of the job
        String csvFileName = String.format("Strings_for_job_id_%s.csv", readyJob.getId());

        // setting HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }

//   optionally to check model
//    @GetMapping("/model")
//    public ResponseEntity<StringJobDto> model() {
//
//        StringJobDto exampleStringJobDto = new StringJobDto();
//        exampleStringJobDto.setRequiredStringMaxLength(1);
//        exampleStringJobDto.setRequiredStringMinLength(2);
//        exampleStringJobDto.setNumberOfStrings(2L);
//        exampleStringJobDto.setBaseCharacters(Arrays.asList('a','b'));
//
//        return new ResponseEntity<>(exampleStringJobDto, HttpStatus.OK);
//    }


}
