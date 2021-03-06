package com.hospital.medico;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.medico.entities.Medico;
import com.hospital.medico.repository.IMedicoJasper;
import com.hospital.medico.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
@RestController
public class MedicoApplication {

	@Autowired
	private IMedicoJasper repository;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/getMedicos")
	public List<Medico> getMedico(){
		return repository.findAll();
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException{
		return service.exportReport(format);
	}

	public static void main(String[] args) {
		SpringApplication.run(MedicoApplication.class, args);
	}

}
