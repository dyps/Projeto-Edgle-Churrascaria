package br.com.churrascaria.beans.formasDePagamento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.BandeiraDeCartao;
import br.com.churrascaria.entities.TipoDePagamento;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;

@Named
@ViewScoped
public class ManageFormasDePagamento extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CRUDService<TipoDePagamento> tipoDePagamentoService;

	@Inject
	private CRUDService<BandeiraDeCartao> bandeiraDeCartaoService;

	private List<TipoDePagamento> tiposDePagamentos;

	private List<BandeiraDeCartao> bandeirasDeCartao;
	private List<BandeiraDeCartao> bandeirasDeCartaoSemPrimeiro;

	private BandeiraDeCartao novaBandeira;

	private TipoDePagamento novoTipo;

	@PostConstruct
	private void init() {
		atualizarTipos();
		atualizarBandeiras();
		novaBandeira = new BandeiraDeCartao();
		novoTipo = new TipoDePagamento();
	}

	private void atualizarTipos() {
		tiposDePagamentos = new ArrayList<TipoDePagamento>();
		TipoDePagamento primeiroTipo = new TipoDePagamento();
		primeiroTipo.setPrimeiro(true);
		tiposDePagamentos.add(primeiroTipo);
		try {
			tiposDePagamentos.addAll(tipoDePagamentoService.getAll());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}

	}

	private void atualizarBandeiras() {
		bandeirasDeCartao = new ArrayList<BandeiraDeCartao>();
		bandeirasDeCartaoSemPrimeiro = new ArrayList<BandeiraDeCartao>();
		BandeiraDeCartao primeiraBandeira = new BandeiraDeCartao();
		primeiraBandeira.setPrimeiro(true);
		bandeirasDeCartao.add(primeiraBandeira);
		try {
			bandeirasDeCartao.addAll(bandeiraDeCartaoService.getAll());
			bandeirasDeCartaoSemPrimeiro.addAll(bandeiraDeCartaoService.getAll());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public List<TipoDePagamento> getTiposDePagamentos() {
		return tiposDePagamentos;
	}

	public void setTiposDePagamentos(List<TipoDePagamento> tiposDePagamentos) {
		this.tiposDePagamentos = tiposDePagamentos;
	}

	public List<BandeiraDeCartao> getBandeirasDeCartao() {
		return bandeirasDeCartao;
	}

	public void setBandeirasDeCartao(List<BandeiraDeCartao> bandeirasDeCartao) {
		this.bandeirasDeCartao = bandeirasDeCartao;
	}

	public BandeiraDeCartao getNovaBandeira() {
		return novaBandeira;
	}

	public void setNovaBandeira(BandeiraDeCartao novaBandeira) {
		this.novaBandeira = novaBandeira;
	}

	public TipoDePagamento getNovoTipo() {
		return novoTipo;
	}

	public void setNovoTipo(TipoDePagamento novoTipo) {
		this.novoTipo = novoTipo;
	}

	public List<BandeiraDeCartao> getBandeirasDeCartaoSemPrimeiro() {
		return bandeirasDeCartaoSemPrimeiro;
	}

	public void setBandeirasDeCartaoSemPrimeiro(List<BandeiraDeCartao> bandeirasDeCartaoSemPrimeiro) {
		this.bandeirasDeCartaoSemPrimeiro = bandeirasDeCartaoSemPrimeiro;
	}

	public void salvarNovoTipo() {
		try {
			tipoDePagamentoService.save(novoTipo);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		atualizarTipos();
	}

	public void salvarNovaBandeira() {
		try {
			bandeiraDeCartaoService.save(novaBandeira);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		atualizarBandeiras();
	}

	public boolean podeExcluir(TipoDePagamento tipoDePagamento) {
		return tipoDePagamentoService.podeSerApagada(tipoDePagamento);
	}

	public String excluir(TipoDePagamento tipoDePagamento) {
		try {
			tipoDePagamentoService.delete(tipoDePagamento);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_FORMAS_DE_PAGAMENTO;
	}

	public boolean podeExcluir(BandeiraDeCartao tipoDePagamento) {
		return bandeiraDeCartaoService.podeSerApagada(tipoDePagamento);
	}

	public String excluir(BandeiraDeCartao tipoDePagamento) {
		try {
			bandeiraDeCartaoService.delete(tipoDePagamento);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_FORMAS_DE_PAGAMENTO;
	}
}
