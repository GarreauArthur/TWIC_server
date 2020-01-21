package com.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ville  implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String codeCommuneINSEE;
	private String nomCommune;
	private String codePostal;
	private String libelleAcheminement;
	private String ligne5;
	private String latitude;
	private String longitude;
	private boolean isActive;
}
