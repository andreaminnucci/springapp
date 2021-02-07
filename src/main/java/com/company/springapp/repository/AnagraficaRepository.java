package com.company.springapp.repository;

import com.company.springapp.model.Anagrafica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

// https://docs.spring.io/spring-data/jpa/docs/2.4.2/reference/html/#reference
public interface AnagraficaRepository extends PagingAndSortingRepository<Anagrafica, Long>, JpaSpecificationExecutor<Anagrafica> {

	public List<Anagrafica> findAll();
	
	
}
