package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.config.JDBCConfigurationSol1;
import com.dto.Ville;

public class VilleDAO {

	public ArrayList<Ville> getVilles() {
		try {
			ArrayList<Ville> villes = new ArrayList<Ville>();
			Connection con = JDBCConfigurationSol1.getConnection();
			Statement stmt;
			stmt = con.createStatement();
		
		    ResultSet rs = stmt.executeQuery("SELECT * FROM ville_france");
		    
		    while(rs.next()){
		    	Ville ville = new Ville();
		    	ville.setCodeCommuneINSEE(rs.getString("Code_commune_INSEE"));
				ville.setNomCommune(rs.getString("Nom_commune"));
				ville.setCodePostal(rs.getString("Code_postal"));
				ville.setLibelleAcheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne5(rs.getString("Ligne_5"));
				ville.setLatitude(rs.getString("Latitude"));
				ville.setLongitude(rs.getString("Longitude"));
				villes.add(ville);
		    }
		    return villes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Ville getVilleFromCodeInsee(String codeINSEE) {
		try {
			
			Connection con = JDBCConfigurationSol1.getConnection();
			
			String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE = ? ";
		
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, codeINSEE);
			
			ResultSet rs =  stmt.executeQuery();
			
			Ville ville = null;
			
			if (rs.next()) {
				ville = new Ville();
				ville.setCodeCommuneINSEE(rs.getString("Code_commune_INSEE"));
				ville.setNomCommune(rs.getString("Nom_commune"));
				ville.setCodePostal(rs.getString("Code_postal"));
				ville.setLibelleAcheminement(rs.getString("Libelle_acheminement"));
				ville.setLigne5(rs.getString("Ligne_5"));
				ville.setLatitude(rs.getString("Latitude"));
				ville.setLongitude(rs.getString("Longitude"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			return ville;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public Ville getVille(Ville villeAtrouver) {
		try {
			
			Connection con = JDBCConfigurationSol1.getConnection();
			Statement stmt;
			
			String codeCommuneINSEE = villeAtrouver.getCodeCommuneINSEE();
			String nomCommune = villeAtrouver.getNomCommune();
			String codePostal = villeAtrouver.getCodePostal();
			String libelleAcheminement = villeAtrouver.getLibelleAcheminement();
			String ligne5 = villeAtrouver.getLigne5();
			String latitude = villeAtrouver.getLatitude();
			String longitude = villeAtrouver.getLongitude();
			
			String query = "SELECT * FROM ville_france WHERE Code_commune_INSEE IS NOT NULL"
					+ (codeCommuneINSEE == null ? "" : " AND Code_commune_INSEE = ? ")
					+ (nomCommune == null ? "" : " AND Nom_commune = ? ")
					+ (codePostal == null ? "" : " AND Code_postal	= ? ")
					+ (libelleAcheminement == null ? "" : " AND Libelle_acheminement = ? ")
					+ (ligne5 == null ? "" : " AND Ligne_5 = ? ")
					+ (latitude == null ? "" : " AND Latitude = ? ")
					+ (longitude == null ? "" : " AND Longitude = ? ");
			
			stmt = con.prepareStatement(query);
			
			// FINIR CA
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
}
