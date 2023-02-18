package com.mesbahi.patientcrud.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mesbahi.patientcrud.entities.Patient;
import com.mesbahi.patientcrud.repo.PatientRepo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PatientController {
	private PatientRepo patientRepo;
	
	@GetMapping("/patients")
	public String patients(Model model,@RequestParam(name = "page",defaultValue = "0") int page
				,@RequestParam(name = "size",defaultValue = "5") int size
				,@RequestParam(name = "keyword",defaultValue = "") String keyword) {
		Page<Patient> Pagepatients=patientRepo.findByNomContains(keyword,PageRequest.of(page, size));
		model.addAttribute("ListPatients",Pagepatients.getContent());
		model.addAttribute("pages", new int[Pagepatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", keyword);
		return "patients";
	}
	
	@GetMapping("/delete")
	public String delete(long id,String keyword,int page) {
		patientRepo.deleteById(id);
		return "redirect:/patients?page="+page+"&keyword="+keyword ;
	}
	
	@GetMapping("/")
	public String home() {
		return "redirect:/patients";
	}
	
	@GetMapping("/formPatient")
	public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formPatient";
	}
	@PostMapping("/save")
	public String save(Model model,@Valid Patient patient,BindingResult bindingResult
			,@RequestParam(name = "page",defaultValue = "0") int page
			,@RequestParam(name = "keyword",defaultValue = "") String keyword) {
		if(bindingResult.hasErrors()) return "formPatient";
			patientRepo.save(patient);
		return "redirect:/patients?page="+page+"&keyword="+keyword;
	}
	
	@GetMapping("/editPatient")
	public String editPatient(Model model,long id,String keyword,int page) {
		Patient patient=patientRepo.findById(id).get();
		model.addAttribute("patient", patient);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		return "editPatient";
	}
}
