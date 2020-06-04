package com.joaogcm.springbackend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaogcm.springbackend.entities.Categoria;
import com.joaogcm.springbackend.entities.Cidade;
import com.joaogcm.springbackend.entities.Cliente;
import com.joaogcm.springbackend.entities.Endereco;
import com.joaogcm.springbackend.entities.Estado;
import com.joaogcm.springbackend.entities.ItemPedido;
import com.joaogcm.springbackend.entities.Pagamento;
import com.joaogcm.springbackend.entities.PagamentoComBoleto;
import com.joaogcm.springbackend.entities.PagamentoComCartao;
import com.joaogcm.springbackend.entities.Pedido;
import com.joaogcm.springbackend.entities.Produto;
import com.joaogcm.springbackend.entities.enums.EstadoPagamento;
import com.joaogcm.springbackend.entities.enums.TipoCliente;
import com.joaogcm.springbackend.repositories.CategoriaRepository;
import com.joaogcm.springbackend.repositories.CidadeRepository;
import com.joaogcm.springbackend.repositories.ClienteRepository;
import com.joaogcm.springbackend.repositories.EnderecoRepository;
import com.joaogcm.springbackend.repositories.EstadoRepository;
import com.joaogcm.springbackend.repositories.ItemPedidoRepository;
import com.joaogcm.springbackend.repositories.PagamentoRepository;
import com.joaogcm.springbackend.repositories.PedidoRepository;
import com.joaogcm.springbackend.repositories.ProdutoRepository;

@Service
public class DatabaseService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Livros");
		Categoria cat2 = new Categoria(null, "Bebidas");
		Categoria cat3 = new Categoria(null, "Escritório");
		Categoria cat4 = new Categoria(null, "Informática");
		Categoria cat5 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat6 = new Categoria(null, "Eletrônicos");
		Categoria cat7 = new Categoria(null, "Jardinagem");
		Categoria cat8 = new Categoria(null, "Decoração");
		Categoria cat9 = new Categoria(null, "Perfumaria");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Shampoo", 75.90);
		Produto prod4 = new Produto(null, "Monitor", 475.90);
		Produto prod5 = new Produto(null, "Hipercalórico", 96.00);
		Produto prod6 = new Produto(null, "Creatina", 65.00);
		Produto prod7 = new Produto(null, "Whey", 187.60);
		Produto prod8 = new Produto(null, "Albumina", 46.00);
		Produto prod9 = new Produto(null, "Maltodextrina", 23.90);
		Produto prod10 = new Produto(null, "Cartucho", 20.00);
		Produto prod11 = new Produto(null, "Alcool", 12.50);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));
		cat8.getProdutos().addAll(Arrays.asList(prod9, prod10, prod11));
		cat9.getProdutos().addAll(Arrays.asList(prod3, prod8, prod11));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1, cat4, cat9));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat5, cat9));
		prod9.getCategorias().addAll(Arrays.asList(cat6, cat8));
		prod10.getCategorias().addAll(Arrays.asList(cat6, cat8));
		prod11.getCategorias().addAll(Arrays.asList(cat7, cat8, cat9));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository
				.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		/*---------------------------------------//---------------------------------------*/

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		/*---------------------------------------//---------------------------------------*/

		Cliente cli1 = new Cliente(null, "Maria Silva", "joaogabrielcm.jb@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		/*---------------------------------------//---------------------------------------*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("17/04/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/03/2020 09:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/04/2020 10:50"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		/*---------------------------------------//---------------------------------------*/

		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}