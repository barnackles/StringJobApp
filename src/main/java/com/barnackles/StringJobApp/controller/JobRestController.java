package com.barnackles.StringJobApp.controller;

import com.barnackles.StringJobApp.dto.CreateNewJobDto;
import com.barnackles.StringJobApp.dto.JobResponseDto;
import com.barnackles.StringJobApp.dto.StatusResponseDto;
import com.barnackles.StringJobApp.service.JobService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobRestController {

    private final JobService jobService;


    @PostMapping("/new")
    public ResponseEntity<String> newJob(@Valid @RequestBody CreateNewJobDto createNewJobDto) {

        String response = String.format("Job to create %d strings accepted.", createNewJobDto.getNumberOfStrings());
        HttpStatus status = HttpStatus.OK;

        Long maxNumberOfUniqueStrings = jobService.checkNumberOfUniqueStrings(createNewJobDto);

        if(createNewJobDto.getRequiredStringMinLength() > createNewJobDto.getRequiredStringMaxLength()) {

            response = String.format("Minimum: %d value is grater than Maximum %d",
                    createNewJobDto.getRequiredStringMinLength(), createNewJobDto.getRequiredStringMaxLength());
            return new ResponseEntity<>(response, status);
        }

        if(createNewJobDto.getRequiredStringMaxLength() > createNewJobDto.getBaseCharacters().size()
        || createNewJobDto.getRequiredStringMinLength() > createNewJobDto.getBaseCharacters().size()) {

            response = ("String length cannot be greater than the number of the characters.");

            return new ResponseEntity<>(response, status);
        }


        if(maxNumberOfUniqueStrings < createNewJobDto.getNumberOfStrings()) {

            response = String.format("Maximum number of unique strings from given characters is: %d. You have requested: %d",
                  maxNumberOfUniqueStrings, createNewJobDto.getNumberOfStrings());
            return new ResponseEntity<>(response, status);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/status")
    public ResponseEntity<StatusResponseDto> status() {

        StatusResponseDto statusResponseDto = new StatusResponseDto();

        return new ResponseEntity<>(statusResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/exportResult", produces = "text/csv")
    public ResponseEntity<InputStreamResource> exportCSV() {
        // replace this with your header (if required)
//        String[] csvHeader = {
//                "Number", "String"
//        };
        CSVFormat csvFileFormat = CSVFormat.Builder.create().build();
        csvFileFormat.builder().setHeader("NUmber", "String");


        // replace this with your data retrieving logic
        List<List<String>> csvBody = new ArrayList<>();
        csvBody.add(Arrays.asList("1", "dupa"));
        csvBody.add(Arrays.asList("2", "jeszcze jedna"));


        ByteArrayInputStream byteArrayOutputStream;

        // closing resources by using a try with resources
        // https://www.baeldung.com/java-try-with-resources
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // defining the CSV printer

                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), csvFileFormat);
        ) {
            // populating the CSV content
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            // writing the underlying stream
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        // save by thr number of the job?
        String csvFileName = "people.csv";

        // setting HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }


}
