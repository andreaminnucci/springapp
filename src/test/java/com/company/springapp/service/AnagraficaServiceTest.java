package com.company.springapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.springapp.model.Anagrafica;
import com.company.springapp.repository.AnagraficaRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties ")
public class AnagraficaServiceTest {
//https://github.com/veerrajukakarla434/Vkakarla9-SpringBoot-SpringData-Junits-InMemoryH2DB
	@Autowired
	private AnagraficaRepository anagraficaRepository;
	
	@Autowired
	AnagraficaService anagraficaService;

	@Test
    public void A_testListAnagrafica() {
		List<Anagrafica> results = anagraficaService.findAll(null, 0, 10, "nome", "desc");
		assertEquals(results.size(), 0);
	}
	/*
	@Test
    public void B_testInserisciAnagrafica() {
	
	}
	
	@Test
    public void C_testEliminaAnagrafica() {
	
	}
	
	@Test
    public void D_testListAnagrafica() {
		anagraficaService.listAnagrafica();
	}*/
}
