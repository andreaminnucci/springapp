package com.company.springapp.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.company.springapp.model.Anagrafica;

@Component
public class AnagraficaDTOConverter {

	public AnagraficaDTO entityToDto(Anagrafica anagrafica) {
		AnagraficaDTO dto = new AnagraficaDTO();
		
		dto.setNome(anagrafica.getNome());
		dto.setCognome(anagrafica.getCognome());
		dto.setDataNascita(anagrafica.getDataNascita());
		
		return dto;
	}
	
	public List<AnagraficaDTO> entityToDto(List<Anagrafica> anagrafiche) {
		return anagrafiche
				.stream()
				.map(anagrafica -> entityToDto(anagrafica))
				.collect(Collectors.toList());
	}
	
	public 	Anagrafica dtoToEntity(AnagraficaDTO anagraficaDTO) {
		Anagrafica anagrafica = new Anagrafica();
		
		anagrafica.setNome(anagraficaDTO.getNome());
		anagrafica.setCognome(anagraficaDTO.getCognome());
		anagrafica.setDataNascita(anagraficaDTO.getDataNascita());
		
		return anagrafica;
	}
	
	public List<Anagrafica> dtoToEntity(List<AnagraficaDTO> dtos) {
		return dtos
				.stream()
				.map(dto -> dtoToEntity(dto))
				.collect(Collectors.toList());
	}
}
