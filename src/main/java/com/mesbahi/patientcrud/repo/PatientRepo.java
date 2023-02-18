package com.mesbahi.patientcrud.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mesbahi.patientcrud.entities.Patient;

public interface PatientRepo extends JpaRepository<Patient,Long> {
	Page<Patient> findByNomContains(String kw,Pageable pageable);
}
