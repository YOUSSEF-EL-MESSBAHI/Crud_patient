package com.mesbahi.patientcrud;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mesbahi.patientcrud.entities.Patient;
import com.mesbahi.patientcrud.repo.PatientRepo;

@SpringBootApplication
public class PatientCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientCrudApplication.class, args);
	}
	
	//@Bean
	CommandLineRunner commandLineRunner(PatientRepo patientRepo) {
		return args -> {
			patientRepo.save(new Patient(0, "Youssef",new Date(),false,12));
			patientRepo.save(new Patient(0,"Amine",new Date(),true,13));
			patientRepo.save(new Patient(0,"Ayman",new Date(),false,14));
			patientRepo.save(new Patient(0,"Mouad",new Date(),true,15));
			
			patientRepo.findAll().forEach(p->{
				System.out.println(p.getNom());
			});
		};
		
	}
}
