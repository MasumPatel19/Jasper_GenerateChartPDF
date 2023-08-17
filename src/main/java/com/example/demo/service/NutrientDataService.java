package com.example.demo.service;

import com.example.demo.model.nutrientData;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutrientDataService {

    public ByteArrayOutputStream generateReport() throws JRException {
        String filePath = "C:\\Users\\Masum Patel\\Downloads\\demo (1)\\Jasper_GenerateChartPDF\\src\\main\\resources\\templates\\report.jrxml";

        nutrientData calciumNutrient = new nutrientData("calcium", 75);
        nutrientData magnesiumNutrient = new nutrientData("magnesium", 50);
        nutrientData zincNutrient = new nutrientData("zinc", 80);


        List<nutrientData> nutrientDataList = new ArrayList<>();
        nutrientDataList.add(calciumNutrient);
        nutrientDataList.add(magnesiumNutrient);
        nutrientDataList.add(zincNutrient);

        JRBeanCollectionDataSource nutrientCollectionDataSource = new JRBeanCollectionDataSource(nutrientDataList);


        Map<String, Object> parameter = new HashMap<>();
        parameter.put("firstName", "Masum");
        parameter.put("age", 23);
        parameter.put("nutrientDataSet", nutrientCollectionDataSource);

        JasperReport report = JasperCompileManager.compileReport(filePath);
        JasperPrint print = JasperFillManager.fillReport(report, parameter, new JREmptyDataSource());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JRPdfExporter jrPdfExporter = new JRPdfExporter();
        SimplePdfExporterConfiguration simplePdfExporterConfiguration = new SimplePdfExporterConfiguration();
        simplePdfExporterConfiguration.setCompressed(true);
        jrPdfExporter.setConfiguration(simplePdfExporterConfiguration);
        jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
        jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        jrPdfExporter.exportReport();

        return byteArrayOutputStream;
    }

}
