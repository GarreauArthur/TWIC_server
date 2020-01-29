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

	/**
	 * Get all the villes
	 * @return
	 */
	public ArrayList<Ville> getVilles() {
		try {
			ArrayList<Ville> villes = new ArrayList<Ville>();
			Connection con = JDBCConfigurationSol1.getConnection();
			Statement stmt;
			stmt = con.createStatement();
		
		    ResultSet rs = stmt.executeQuery("SELECT * FROM ville_france WHERE estSupprimee = 0");
		    
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
	
	/**
	 * Get ville en fonction de leur code INSEE.
	 * @param codeINSEE
	 * @return
	 */
	public Ville getVilleFromCodeInsee(String codeINSEE) {
		try {
			
			Connection con = JDBCConfigurationSol1.getConnection();
			
			String query = "SELECT * FROM ville_france WHERE estSupprimee = 0 AND Code_commune_INSEE = ? ";
		
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
	
	/**
	 * Supprimer une ville.
	 * @param codeCommuneINSEE
	 */
	public void supprimer(String codeCommuneINSEE) {		
		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			String query = "UPDATE ville_france SET estSupprimee = 1 WHERE Code_commune_INSEE = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, codeCommuneINSEE);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get une ville en fonction d'informations partielles.
	 * @param villeAtrouver
	 * @return
	 */
	public ArrayList<Ville> getVille(Ville villeAtrouver) {
		try {
			
			Connection con = JDBCConfigurationSol1.getConnection();
			
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
			
			PreparedStatement stmt = con.prepareStatement(query);
			
			int index = 0;
			if (codeCommuneINSEE != null) {
				index++;
				stmt.setString(index, codeCommuneINSEE);
			}
			if (nomCommune != null) {
				index++;
				stmt.setString(index, nomCommune);
			}
			if (codePostal != null) {
				index++;
				stmt.setString(index, codePostal);
			}
			if (libelleAcheminement != null) {
				index++;
				stmt.setString(index, libelleAcheminement);
			}
			if (ligne5 != null) {
				index++;
				stmt.setString(index, ligne5);
			}
			if (latitude != null) {
				index++;
				stmt.setString(index, latitude);
			}
			if (longitude != null) {
				index++;
				stmt.setString(index, longitude);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			
			ArrayList<Ville> villes = new ArrayList<Ville>();
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
	
	public void creerVille(Ville nouvelleVille) {
		
		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			String query = "INSERT INTO ville_france VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, nouvelleVille.getCodeCommuneINSEE());
			preparedStatement.setString(2, nouvelleVille.getNomCommune());
			preparedStatement.setString(3, nouvelleVille.getCodePostal());
			preparedStatement.setString(4, nouvelleVille.getLibelleAcheminement());
			preparedStatement.setString(5, nouvelleVille.getLigne5());
			preparedStatement.setString(6, nouvelleVille.getLatitude());
			preparedStatement.setString(7, nouvelleVille.getLongitude());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sélectionne 50 villes, ]50*page,50*(page+1)]
	 * @param page
	 * @return
	 */
	public ArrayList<Ville> getVillesPagination(int page) {
		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			String query = "SELECT * FROM ville_france WHERE estSupprimee = 0 LIMIT 50 OFFSET ?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, 50*page);

			ResultSet rs = preparedStatement.executeQuery();
			
			
			ArrayList<Ville> villes = new ArrayList<Ville>();
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
	
	public void modifier(Ville ville) {
		
		try {
			Connection con = JDBCConfigurationSol1.getConnection();
			String query = "UPDATE ville_france SET Nom_commune = ?, Code_postal = ?,"
					+ " Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ?"
					+ " WHERE Code_commune_INSEE = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, ville.getNomCommune());
			preparedStatement.setString(2, ville.getCodePostal());
			preparedStatement.setString(3, ville.getLibelleAcheminement());
			preparedStatement.setString(4, ville.getLigne5());
			preparedStatement.setString(5, ville.getLatitude());
			preparedStatement.setString(6, ville.getLongitude());
			preparedStatement.setString(7, ville.getCodeCommuneINSEE());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
