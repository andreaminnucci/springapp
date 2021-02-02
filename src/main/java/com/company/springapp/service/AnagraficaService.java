package com.company.springapp.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.company.springapp.model.Anagrafica;
import com.company.springapp.repository.AnagraficaRepository;

@Service
@Transactional
public class AnagraficaService {
	
	@Autowired
	private AnagraficaRepository anagraficaRepository;

	public List<Anagrafica> findAll(String query, int offset, int limit, String sortBy, String orderBy) {
		List<Anagrafica> results = new ArrayList<>();
		
		Sort sort = Sort.by(Anagrafica.NOME_FIELD).ascending();
        if (sortBy == Anagrafica.COGNOME_FIELD) {
            sort = Sort.by(sortBy).ascending();
        }
        if (orderBy != null && orderBy == "desc") {
        	sort = sort.descending();
        }
        if (query != null) {
        	anagraficaRepository.findAll((Root<Anagrafica> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> 
                cb.or(
                    cb.like(
                        cb.lower(root.get(Anagrafica.NOME_FIELD)),
                        "%" + query + "%"),
                    cb.like(
                        cb.lower(root.get(Anagrafica.COGNOME_FIELD)),
                        "%" + query + "%")	
                ),
                PageRequest.of(offset, limit, sort)
            ).forEach(results::add);
        } else {
        	anagraficaRepository.findAll(PageRequest.of(offset, limit, sort)).forEach(results::add);
        }
        
		return results;
	}
	
	public Long createAnagrafica(Anagrafica anagrafica) {
		Anagrafica p = anagraficaRepository.save(anagrafica);
		return p.getId();
	}
	
	public boolean eliminaAnagrafica(Long id) {
		anagraficaRepository.deleteById(id);
		return true;
	}
	
}
