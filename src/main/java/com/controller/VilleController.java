package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.VilleDAO;
import com.dto.Ville;

@RestController
public class VilleController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/villes")
	public ArrayList<Ville> getVilles() {
		VilleDAO vdao = new VilleDAO();
		return vdao.getVilles();
	}
	
	@GetMapping("/ville")
	public Ville getVille(@RequestParam(required = true, value="codeCommuneINSEE") String codeCommuneINSEE) {
		VilleDAO vdao = new VilleDAO();
		return vdao.getVilleFromCodeInsee(codeCommuneINSEE);
	}
	
	@DeleteMapping(value = "/ville/{codeCommuneINSEE}")
	public void deleteVille(@PathVariable String codeCommuneINSEE) {
		VilleDAO vdao = new VilleDAO();
		vdao.supprimer(codeCommuneINSEE);
	}
	
}
