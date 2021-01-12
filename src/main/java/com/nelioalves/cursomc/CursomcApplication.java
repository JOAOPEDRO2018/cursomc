package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	CategoriaRepository categRepo;
	
	@Autowired
	ProdutoRepository produRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	//Alimentando o banco de dados atraves do spring
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutoList().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutoList().addAll(Arrays.asList(p2));
		
		p1.getCategoriaList().addAll(Arrays.asList(cat1));
		p2.getCategoriaList().addAll(Arrays.asList(cat1, cat2));
		p3.getCategoriaList().addAll(Arrays.asList(cat1));
		
		categRepo.saveAll(Arrays.asList(cat1, cat2));
		produRepository.saveAll(Arrays.asList(p1,p2,p3)); 
		
	}
	
	

}
