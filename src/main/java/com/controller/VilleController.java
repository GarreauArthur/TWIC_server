package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/ville/{codeCommuneINSEE}")
	public Ville getVille(@PathVariable String codeCommuneINSEE) {
		VilleDAO vdao = new VilleDAO();
		return vdao.getVilleFromCodeInsee(codeCommuneINSEE);
	}
	
	
	@GetMapping("/ville")
	public ArrayList<Ville> getVilleFiltre(
			@RequestParam(required = false, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required = false, value="nomCommune") String nomCommune,
			@RequestParam(required = false, value="codePostal") String codePostal,
			@RequestParam(required = false, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value="ligne5") String ligne5,
			@RequestParam(required = false, value="latitude") String latitude,
			@RequestParam(required = false, value="longitude") String longitude
	) {
		VilleDAO vdao = new VilleDAO();
		Ville atrouver = new Ville();
		atrouver.setCodeCommuneINSEE(codeCommuneINSEE);
		atrouver.setNomCommune(nomCommune);
		atrouver.setCodePostal(codePostal);
		atrouver.setLibelleAcheminement(libelleAcheminement);
		atrouver.setLigne5(ligne5);
		atrouver.setLongitude(longitude);
		atrouver.setLatitude(latitude);
		
		return vdao.getVille(atrouver);
	}
	
	@DeleteMapping(value = "/ville/{codeCommuneINSEE}")
	public void deleteVille(@PathVariable String codeCommuneINSEE) {
		VilleDAO vdao = new VilleDAO();
		vdao.supprimer(codeCommuneINSEE);
	}
	
	@PostMapping("/ville")
	public void nouvelleVille(
			@RequestParam(required=true, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required=true, value="nomCommune") String nomCommune,
			@RequestParam(required=true, value="codePostal") String codePostal,
			@RequestParam(required=true, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required=true, value="ligne5") String ligne5,
			@RequestParam(required=true, value="latitude") String latitude,
			@RequestParam(required=true, value="longitude") String longitude
	) {
		VilleDAO vdao = new VilleDAO();
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(codeCommuneINSEE);
		ville.setNomCommune(nomCommune);
		ville.setCodePostal(codePostal);
		ville.setLibelleAcheminement(libelleAcheminement);
		ville.setLigne5(ligne5);
		ville.setLongitude(longitude);
		ville.setLatitude(latitude);
		ville.setEstSupprime(false);
		
		vdao.creerVille(ville);
	}
	
	// from https://stackoverflow.com/a/12600225/6417344
	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	public int calculateDistanceInKilometer(double userLat, double userLng, double venueLat, double venueLng) {

	    double latDistance = Math.toRadians(userLat - venueLat);
	    double lngDistance = Math.toRadians(userLng - venueLng);

	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
	      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	}
	
	@GetMapping("/distance/v1/{codeVille1}/v2/{codeVille2}")
	public int getVille(@PathVariable String codeVille1, @PathVariable String codeVille2) {
		VilleDAO vdao = new VilleDAO();
		Ville v1 = vdao.getVilleFromCodeInsee(codeVille1);
		Ville v2 = vdao.getVilleFromCodeInsee(codeVille2);
		if ( v1 == null || v2 == null ) {
			return -1;
		}
		double lat1 = Double.parseDouble(v1.getLatitude());
		double lon1 = Double.parseDouble(v1.getLongitude());
		double lat2 = Double.parseDouble(v2.getLatitude());
		double lon2 = Double.parseDouble(v2.getLongitude());
		
		return calculateDistanceInKilometer(lat1,lon1,lat2,lon2);
	}
	
	@GetMapping("/villes/{page}")
	public ArrayList<Ville> getVillePaginee(@PathVariable int page) {
		VilleDAO vdao = new VilleDAO();
		return vdao.getVillesPagination(page);
	}
	
	@PutMapping("/ville")
	public void modifierVille(
			@RequestParam(required=true, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required=true, value="nomCommune") String nomCommune,
			@RequestParam(required=true, value="codePostal") String codePostal,
			@RequestParam(required=true, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required=true, value="ligne5") String ligne5,
			@RequestParam(required=true, value="latitude") String latitude,
			@RequestParam(required=true, value="longitude") String longitude
	) {
		VilleDAO vdao = new VilleDAO();
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(codeCommuneINSEE);
		ville.setNomCommune(nomCommune);
		ville.setCodePostal(codePostal);
		ville.setLibelleAcheminement(libelleAcheminement);
		ville.setLigne5(ligne5);
		ville.setLongitude(longitude);
		ville.setLatitude(latitude);
		ville.setEstSupprime(false);
		
		vdao.modifier(ville);
	}
	
}
