package com.joaogcm.springbackend.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.joaogcm.springbackend.entities.enums.PerfilCliente;
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
		Produto prod12 = new Produto(null, "Produto 12", 10.00);
		Produto prod13 = new Produto(null, "Produto 13", 10.00);
		Produto prod14 = new Produto(null, "Produto 14", 10.00);
		Produto prod15 = new Produto(null, "Produto 15", 10.00);
		Produto prod16 = new Produto(null, "Produto 16", 10.00);
		Produto prod17 = new Produto(null, "Produto 17", 10.00);
		Produto prod18 = new Produto(null, "Produto 18", 10.00);
		Produto prod19 = new Produto(null, "Produto 19", 10.00);
		Produto prod20 = new Produto(null, "Produto 20", 10.00);
		Produto prod21 = new Produto(null, "Produto 21", 10.00);
		Produto prod22 = new Produto(null, "Produto 22", 10.00);
		Produto prod23 = new Produto(null, "Produto 23", 10.00);
		Produto prod24 = new Produto(null, "Produto 24", 10.00);
		Produto prod25 = new Produto(null, "Produto 25", 10.00);
		Produto prod26 = new Produto(null, "Produto 26", 10.00);
		Produto prod27 = new Produto(null, "Produto 27", 10.00);
		Produto prod28 = new Produto(null, "Produto 28", 10.00);
		Produto prod29 = new Produto(null, "Produto 29", 10.00);
		Produto prod30 = new Produto(null, "Produto 30", 10.00);
		Produto prod31 = new Produto(null, "Produto 31", 10.00);
		Produto prod32 = new Produto(null, "Produto 32", 10.00);
		Produto prod33 = new Produto(null, "Produto 33", 10.00);
		Produto prod34 = new Produto(null, "Produto 34", 10.00);
		Produto prod35 = new Produto(null, "Produto 35", 10.00);
		Produto prod36 = new Produto(null, "Produto 36", 10.00);
		Produto prod37 = new Produto(null, "Produto 37", 10.00);
		Produto prod38 = new Produto(null, "Produto 38", 10.00);
		Produto prod39 = new Produto(null, "Produto 39", 10.00);
		Produto prod40 = new Produto(null, "Produto 40", 10.00);
		Produto prod41 = new Produto(null, "Produto 41", 10.00);
		Produto prod42 = new Produto(null, "Produto 42", 10.00);
		Produto prod43 = new Produto(null, "Produto 43", 10.00);
		Produto prod44 = new Produto(null, "Produto 44", 10.00);
		Produto prod45 = new Produto(null, "Produto 45", 10.00);
		Produto prod46 = new Produto(null, "Produto 46", 10.00);
		Produto prod47 = new Produto(null, "Produto 47", 10.00);
		Produto prod48 = new Produto(null, "Produto 48", 10.00);
		Produto prod49 = new Produto(null, "Produto 49", 10.00);
		Produto prod50 = new Produto(null, "Produto 50", 10.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));
		cat8.getProdutos().addAll(Arrays.asList(prod9, prod10, prod11));
		cat9.getProdutos().addAll(Arrays.asList(prod3, prod8, prod11));
		cat1.getProdutos().addAll(Arrays.asList(prod12, prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20, prod21, prod22, prod23, prod24, prod25, prod26, prod27, prod28, prod29, prod30, prod31, prod32, prod34, prod35, prod36, prod37, prod38, prod39, prod40, prod41, prod42, prod43, prod44, prod45, prod46, prod47, prod48, prod49, prod50));
		
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
		prod12.getCategorias().add(cat1);
		prod13.getCategorias().add(cat1);
		prod14.getCategorias().add(cat1);
		prod15.getCategorias().add(cat1);
		prod16.getCategorias().add(cat1);
		prod17.getCategorias().add(cat1);
		prod18.getCategorias().add(cat1);
		prod19.getCategorias().add(cat1);
		prod20.getCategorias().add(cat1);
		prod21.getCategorias().add(cat1);
		prod22.getCategorias().add(cat1);
		prod23.getCategorias().add(cat1);
		prod24.getCategorias().add(cat1);
		prod25.getCategorias().add(cat1);
		prod26.getCategorias().add(cat1);
		prod27.getCategorias().add(cat1);
		prod28.getCategorias().add(cat1);
		prod29.getCategorias().add(cat1);
		prod30.getCategorias().add(cat1);
		prod31.getCategorias().add(cat1);
		prod32.getCategorias().add(cat1);
		prod33.getCategorias().add(cat1);
		prod34.getCategorias().add(cat1);
		prod35.getCategorias().add(cat1);
		prod36.getCategorias().add(cat1);
		prod37.getCategorias().add(cat1);
		prod38.getCategorias().add(cat1);
		prod39.getCategorias().add(cat1);
		prod40.getCategorias().add(cat1);
		prod41.getCategorias().add(cat1);
		prod42.getCategorias().add(cat1);
		prod43.getCategorias().add(cat1);
		prod44.getCategorias().add(cat1);
		prod45.getCategorias().add(cat1);
		prod46.getCategorias().add(cat1);
		prod47.getCategorias().add(cat1);
		prod48.getCategorias().add(cat1);
		prod49.getCategorias().add(cat1);
		prod50.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12, prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20, prod21, prod22, prod23, prod24, prod25, prod26, prod27, prod28, prod29, prod30, prod31, prod32, prod34, prod35, prod36, prod37, prod38, prod39, prod40, prod41, prod42, prod43, prod44, prod45, prod46, prod47, prod48, prod49, prod50));

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

		Cliente cli1 = new Cliente(null, "Maria Silva", "joaogabrielcm.jb@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Cliente cli2 = new Cliente(null, "Ana Costa", "joaogabriel36@hotmail.com", "18242237069", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("87365323", "12838399"));
		cli2.setPerfis(PerfilCliente.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida Floreano", "2600", null, "Centro", "77850451", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

		/*---------------------------------------//---------------------------------------*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("17/04/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/03/2020 09:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/04/2020 10:50"), null);
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