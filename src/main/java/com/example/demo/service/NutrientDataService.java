package com.example.demo.service;

import com.example.demo.Repository.NutrientDataRepository;
import com.example.demo.model.NutrientData;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutrientDataService {

    @Autowired
    private NutrientDataRepository nutrientDataRepository;

    public ByteArrayOutputStream generateReport() throws JRException {
        String filePath = "C:\\Users\\Masum Patel\\Downloads\\demo (1)\\Jasper_GenerateChartPDF\\src\\main\\resources\\templates\\report.jrxml";

        List<NutrientData> nutrientDataList = nutrientDataRepository.findAll();
        System.out.println("Size of nutrientDataList: " + nutrientDataList.size());

        JRBeanCollectionDataSource nutrientCollectionDataSource = new JRBeanCollectionDataSource(nutrientDataList);
        System.out.println("Number of records in dataSource: " + nutrientCollectionDataSource.getData().size());

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("nutrientDataSet",  new JRBeanCollectionDataSource(nutrientDataList));

        // Add a new parameter for the second pie chart
        JRBeanCollectionDataSource nutrientCollectionDataSource2 = new JRBeanCollectionDataSource(nutrientDataList);
        parameter.put("nutrientDataSet2", nutrientCollectionDataSource2);

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

