package com.company.springapp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AnagraficaDTO {
	
	@NotNull(message = "{NotNull.AnagraficaDTO.nome.Validation}")
	private String nome;
	
	@NotNull(message = "{NotNull.AnagraficaDTO.cognome.Validation}")
	private String cognome;
	
	@NotNull(message = "{NotNull.AnagraficaDTO.dataNascita.Validation}")
	@Pattern(regexp = "^((19|20)\\\\d\\\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
	private Date dataNascita;
}
