package com.example.demo.controller;

import com.example.demo.service.NutrientDataService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
public class NutrientDataController {

    @Autowired
    private NutrientDataService nutrientDataService;

    @GetMapping("/report")
    public ResponseEntity getReport() throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = nutrientDataService.generateReport();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity(byteArrayOutputStream.toByteArray(), httpHeaders, HttpStatus.OK);
    }

}
