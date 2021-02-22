package br.com.churrascaria.beans.entregador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.filter.EntregadorFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.EntregadorServiceImplementacao;

@Named
@ViewScoped
public class ManageEntregador extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntregadorServiceImplementacao entregadorService;

	private List<Entregador> entregadores;

	private EntregadorFilter entregadorFilter;

	private Entregador novoEntregador;

	public List<Entregador> getEntregadores() {
		return entregadores;
	}

	public EntregadorFilter getEntregadorFilter() {
		return entregadorFilter;
	}

	public void setEntregadorFilter(EntregadorFilter entregadorFilter) {
		this.entregadorFilter = entregadorFilter;
	}

	public Entregador getNovoEntregador() {
		return novoEntregador;
	}

	public void setNovoEntregador(Entregador novoEntregador) {
		this.novoEntregador = novoEntregador;
	}

	@PostConstruct
	public void init() {
		setNovoEntregador(new Entregador());
		entregadorFilter = new EntregadorFilter();
		filtrar();
	}

	public String filtrar() {
		ArrayList<Entregador> array = new ArrayList<Entregador>();
		Entregador entregador = new Entregador();
		entregador.setPrimeiro(true);
		array.add(entregador);
		entregadores = array;
		try {
			array.addAll(entregadorService.getAll());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String delete(Entregador entregador) {
		try {
			entregadorService.delete(entregador);
			reportarMensagemDeSucesso("Entregador '" + entregador.getNome() + "' excluído");
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_ENTREGADOR;

	}

	public String saveCategoriaProduto() {
		try {
			entregadorService.save(novoEntregador);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("entregador '" + novoEntregador.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_ENTREGADOR;
	}

}