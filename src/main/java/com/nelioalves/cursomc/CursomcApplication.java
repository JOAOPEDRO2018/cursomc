package com.nelioalves.cursomc;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

import ch.qos.logback.core.net.server.Client;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	CategoriaRepository categRepo;	
	@Autowired
	ProdutoRepository produRepository;	
	@Autowired
	CidadeRepository cidadeRepository;	
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	//Alimentando o banco de dados atraves do spring
	@Override
	public void run(String... args) throws Exception {
		//instanciando categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		//instanciando produtos
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
		
		//instanciando os estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		//instanciando as cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//salvando as cidades que tem relação com o estado em questão
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		//salvando no banco
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		//instanciando clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		//telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		//instanciando endereco
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "centro", "38777012", cli1, c2);
		
		//fazendo o cliente conhecer os endereços dele
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//salvando no banco
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}
	
	

}






















