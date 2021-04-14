package com.leonardo.lojinha;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardo.lojinha.entity.Categoria;
import com.leonardo.lojinha.entity.Cidade;
import com.leonardo.lojinha.entity.Cliente;
import com.leonardo.lojinha.entity.Endereco;
import com.leonardo.lojinha.entity.Estado;
import com.leonardo.lojinha.entity.ItemPedido;
import com.leonardo.lojinha.entity.Pagamento;
import com.leonardo.lojinha.entity.PagamentoComBoleto;
import com.leonardo.lojinha.entity.PagamentoComCartao;
import com.leonardo.lojinha.entity.Pedido;
import com.leonardo.lojinha.entity.Produto;
import com.leonardo.lojinha.entity.enums.EstadoPagamento;
import com.leonardo.lojinha.entity.enums.TipoCliente;
import com.leonardo.lojinha.repositories.CategoriaDAO;
import com.leonardo.lojinha.repositories.CidadeDAO;
import com.leonardo.lojinha.repositories.ClienteDAO;
import com.leonardo.lojinha.repositories.EnderecoDAO;
import com.leonardo.lojinha.repositories.EstadoDAO;
import com.leonardo.lojinha.repositories.ItemPedidoDAO;
import com.leonardo.lojinha.repositories.PagamentoDAO;
import com.leonardo.lojinha.repositories.PedidoDAO;
import com.leonardo.lojinha.repositories.ProdutoDAO;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaDAO dao;
	
	@Autowired
	private ProdutoDAO pdao;
	
	@Autowired
	private CidadeDAO cdao;
	
	@Autowired
	private EstadoDAO edao;
	
	@Autowired
	private ClienteDAO cliDao;
	
	@Autowired
	private EnderecoDAO eDao;
	
	@Autowired
	private PedidoDAO pedDao;
	
	@Autowired
	private PagamentoDAO pagDao;
	
	@Autowired
	private ItemPedidoDAO itemPedidoDao;

	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception{
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		dao.saveAll(Arrays.asList(cat1, cat2));
		pdao.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		edao.saveAll(Arrays.asList(est1, est2));
		cdao.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "123456789", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("997749254", "988010828"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220534", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 1", "Centro", "1111111", cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1, e2));
		
		cliDao.saveAll(Arrays.asList(cli1));
		eDao.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null );
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedDao.saveAll(Arrays.asList(ped1, ped2));
		pagDao.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoDao.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
