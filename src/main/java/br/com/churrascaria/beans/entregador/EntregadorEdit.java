package br.com.churrascaria.beans.entregador;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Entregador;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.EntregadorServiceImplementacao;

@ViewScoped
@Named
public class EntregadorEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntregadorServiceImplementacao entregadorService;

	private Entregador entregador;

	private TaxaEntrega taxaEntrega;

	public String init() {
		try {
			taxaEntrega = new TaxaEntrega();
			if (entregador == null) {
				entregador = new Entregador();
				entregador.setTaxas(new ArrayList<>());
			} else {
				entregador = entregadorService.getByID(entregador.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String saveEntregador() {
		try {
			if (isEdicaoDeEntregador()) {
				entregadorService.update(entregador);
			} else {
				entregadorService.save(entregador);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Entregador '" + entregador.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_ENTREGADOR;
	}

	public void saveTaxaEntrega() {
		try {
			entregadorService.validarTaxa(taxaEntrega);
			entregador.getTaxas().add(taxaEntrega);
			taxaEntrega.setEntregador(entregador);
			taxaEntrega = new TaxaEntrega();
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}
	
	public String deleteTaxa(TaxaEntrega taxaEntrega) {
		entregador.getTaxas().remove(taxaEntrega);
		reportarMensagemDeSucesso("Taxa de Entrega '" + taxaEntrega.getValor() + "' excluída");
		return null;
	}

	public boolean isEdicaoDeEntregador() {
		return entregador != null && entregador.getId() != null;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public TaxaEntrega getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(TaxaEntrega taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

}
