package com.company.springapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "anagrafica")
public class Anagrafica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	public final static String NOME_FIELD = "nome";
	
	@Column(name = "cognome", nullable = false)
	private String cognome;
	public final static String COGNOME_FIELD = "cognome";
	
	@Column(name = "data_nascita", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
}
