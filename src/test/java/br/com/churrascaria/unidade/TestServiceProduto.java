package br.com.churrascaria.unidade;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.churrascaria.dao.CategoriaProdutoDAO;
import br.com.churrascaria.dao.PersistenciaEdgleChurrascariaException;
import br.com.churrascaria.dao.ProdutoDAO;
import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.entities.Opcao;
import br.com.churrascaria.entities.Produto;
import br.com.churrascaria.entities.ProdutoPadrao;
import br.com.churrascaria.entities.ProdutoPersonalizado;
import br.com.churrascaria.entities.TipoDeMedida;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceProduto {

	private ProdutoServiceImplementacao produtoServiceImplementacao;

	private CategoriaProdutoServiceImplementacao categoriaProdutoServiceImplementacao;

	@Mock
	private ProdutoDAO produtoDAO;

	@Mock
	private CategoriaProdutoDAO categoriaProdutoDAO;

	@Before
	public void setUp() {
		produtoServiceImplementacao = new ProdutoServiceImplementacao();
		produtoServiceImplementacao.setProdutoDAO(produtoDAO);

		categoriaProdutoServiceImplementacao = new CategoriaProdutoServiceImplementacao();
		categoriaProdutoServiceImplementacao.setCategoriaProdutoDAO(categoriaProdutoDAO);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test1CriarCategoriaDeProdutoSemValores() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoriaProdutoServiceImplementacao.save(categoria);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test2CriarCategoriaDeProdutoComNomeRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		List<CategoriaProduto> lista = new ArrayList<CategoriaProduto>();
		lista.add(categoria);
		when(categoriaProdutoDAO.getAll()).thenReturn(lista);

		categoriaProdutoServiceImplementacao.save(categoria);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test3CriarProdutoPersonalizadoComNomeRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Pizzas");
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setId(1l);
		produtoPersonalizado.setNome("Pizza");
		produtoPersonalizado.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produtoPersonalizado.setCategoriaProduto(categoria);
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		opcoes.add(opcao);
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);
		
		List<Produto> lista2 = new ArrayList<Produto>();
		lista2.add(produtoPersonalizado);
		when(produtoDAO.getAll()).thenReturn(lista2);
		
		ProdutoPersonalizado produto2 = new ProdutoPersonalizado();
		produto2.setNome(produtoPersonalizado.getNome());
		produto2.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produto2.setId(2l);

		produtoServiceImplementacao.save(produto2);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test4CriarProdutoPadraoComNomeRepetido()
			throws ServiceEdgleChurrascariaException, PersistenciaEdgleChurrascariaException {
		ProdutoPadrao produtoPadrao = new ProdutoPadrao();
		produtoPadrao.setId((long) 1);
		produtoPadrao.setNome("Coca-cola");
		produtoPadrao.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(produtoPadrao);
		when(produtoDAO.getAll()).thenReturn(lista);

		produtoServiceImplementacao.save(produtoPadrao);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test5ItemDeConfiguracaoSemValores() throws ServiceEdgleChurrascariaException {
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setId((long) 1);
		produtoPersonalizado.setNome("Pizza");
		produtoPersonalizado.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.save(produtoPersonalizado);
	}

	@Test(expected = ServiceEdgleChurrascariaException.class)
	public void test6OpcaoDeItemDeConfiguracaoSemValores() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Pizzas");
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setNome("Pizza");
		produtoPersonalizado.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produtoPersonalizado.setCategoriaProduto(categoria);
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.save(produtoPersonalizado);
	}

	@Test
	public void test7CriarCategoriaDeProduto() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		categoriaProdutoServiceImplementacao.save(categoria);
	}

	@Test
	public void test8NovoProdutoPadrao() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		ProdutoPadrao produtoPadrao = new ProdutoPadrao();
		produtoPadrao.setId((long) 1);
		produtoPadrao.setNome("Coca-cola 350ml");
		produtoPadrao.setValorDeVenda((float) 4);
		produtoPadrao.setMedida(TipoDeMedida.UNIDADE);
		produtoPadrao.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produtoPadrao.setCategoriaProduto(categoria);
		produtoServiceImplementacao.save(produtoPadrao);
	}

	@Test
	public void test9NovoProdutoPersonalizado() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Pizzas");
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setId((long) 1);
		produtoPersonalizado.setNome("Pizza");
		produtoPersonalizado.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produtoPersonalizado.setCategoriaProduto(categoria);
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		opcoes.add(opcao);
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.save(produtoPersonalizado);
	}

//	@Test
//	public void test10EditarProdutoPadrao() {
//
//	}
//
//	@Test
//	public void test11EditarProdutoPersonalizado() {
//
//	}
//
//	@Test
//	public void test12EditarItemDeConfiguracao() {
//
//	}

	@Test
	public void test13BuscarCategoriaDeProduto() throws Exception {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");
		List<CategoriaProduto> lista = new ArrayList<CategoriaProduto>();
		lista.add(categoria);
		when(categoriaProdutoDAO.getAll()).thenReturn(lista);

		List<CategoriaProduto> lista2 = categoriaProdutoServiceImplementacao.getAll();
		for (CategoriaProduto categoriaAux : lista2) {
			if (categoriaAux.getNome() == "Bebidas") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test14BuscarProdutoPadrao() throws Exception {
		ProdutoPadrao produtoPadrao = new ProdutoPadrao();
		produtoPadrao.setNome("Coca-cola 350ml");
		List<Produto> lista = new ArrayList<Produto>();
		lista.add(produtoPadrao);
		when(produtoDAO.getAll()).thenReturn(lista);

		List<Produto> lista2 = produtoServiceImplementacao.getAll();
		for (Produto produtoAux : lista2) {
			if (produtoAux.getNome() == "Coca-cola 350ml") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test15BuscarProdutoPersonalizado() throws Exception {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Pizzas");
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setNome("Pizza");
		produtoPersonalizado.setObservacoesPadrao(new ArrayList<ObservacaoPadrao>());
		produtoPersonalizado.setCategoriaProduto(categoria);
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		opcoes.add(opcao);
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		List<Produto> lista2 = new ArrayList<Produto>();
		lista2.add(produtoPersonalizado);
		when(produtoDAO.getAll()).thenReturn(lista2);

		List<Produto> lista3 = produtoServiceImplementacao.getAll();
		for (Produto produtoAux : lista3) {
			if (produtoAux.getNome() == "Pizza") {
				assertTrue(true);
				return;
			}
		}
		throw new Exception();
	}

	@Test
	public void test16ExcluirOpcaoDeItemDeConfiguracao() throws ServiceEdgleChurrascariaException {
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setNome("Pizza");
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.delete(opcao);
	}

	@Test
	public void test17ExcluirItemDeConfiguracao() throws ServiceEdgleChurrascariaException {
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setNome("Pizza");
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.delete(itemDeConfiguracao);
	}

	@Test
	public void test18ExcluirProdutoPadrao() throws ServiceEdgleChurrascariaException {
		ProdutoPadrao produtoPadrao = new ProdutoPadrao();
		produtoPadrao.setNome("Coca-cola 350ml");

		produtoServiceImplementacao.delete(produtoPadrao);
	}

	@Test
	public void test19ExcluirProdutoPersonalizado() throws ServiceEdgleChurrascariaException {
		ProdutoPersonalizado produtoPersonalizado = new ProdutoPersonalizado();
		produtoPersonalizado.setNome("Pizza");
		ItemDeConfiguracao itemDeConfiguracao = new ItemDeConfiguracao();
		itemDeConfiguracao.setNome("Sabor");
		itemDeConfiguracao.setQuantidadeMaxEscolhas(3);
		itemDeConfiguracao.setProdutoPersonalizado(produtoPersonalizado);
		Opcao opcao = new Opcao();
		opcao.setNome("Calabresa");
		opcao.setValorDeVenda((float) 15);
		opcao.setItemDeConfiguracao(itemDeConfiguracao);
		List<Opcao> opcoes = new ArrayList<Opcao>();
		itemDeConfiguracao.setOpcoes(opcoes);
		List<ItemDeConfiguracao> lista = new ArrayList<ItemDeConfiguracao>();
		lista.add(itemDeConfiguracao);
		produtoPersonalizado.setItensDeConfiguracao(lista);

		produtoServiceImplementacao.delete(produtoPersonalizado);
	}

	@Test
	public void test20ExcluirCategoriaDeProduto() throws ServiceEdgleChurrascariaException {
		CategoriaProduto categoria = new CategoriaProduto();
		categoria.setNome("Bebidas");

		categoriaProdutoServiceImplementacao.delete(categoria);
	}

}
