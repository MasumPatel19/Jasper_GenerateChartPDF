package com.example.demo;

import com.example.demo.model.nutrientData;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println("Run successfully...");

		String filePath = "C:\\Users\\Masum Patel\\Downloads\\demo (1)\\demo\\src\\main\\resources\\templates\\report.jrxml";


		nutrientData calciumNutrient = new nutrientData("calcium",75);
		nutrientData magnesiumNutrient = new nutrientData("magnesium",50);
		nutrientData zincNutrient = new nutrientData("zinc",80);


		List<nutrientData> nutrientDataList = new ArrayList<>();
		nutrientDataList.add(calciumNutrient);
		nutrientDataList.add(magnesiumNutrient);
		nutrientDataList.add(zincNutrient);

		JRBeanCollectionDataSource nutrientCollectionDataSource = new JRBeanCollectionDataSource(nutrientDataList);


		Map<String,Object> parameter = new HashMap<>();
		parameter.put("firstName","Masum");
		parameter.put("age",23);
		parameter.put("nutrientDataSet",nutrientCollectionDataSource);

		JasperReport report = JasperCompileManager.compileReport(filePath);
		JasperPrint print = JasperFillManager.fillReport(report,parameter,new JREmptyDataSource());

		JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\Masum Patel\\Downloads\\demo (1)\\demo\\src\\main\\resources\\static\\generatedreport.pdf");
		System.out.println("Report generated successfully...");

	}

}
