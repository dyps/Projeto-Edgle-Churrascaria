package br.com.churrascaria.beans.entregador;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Entregador;
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
	
	public String init() {
		try {
			if (entregador == null) {
				entregador = new Entregador();
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
	
	public boolean isEdicaoDeEntregador() {
		return entregador != null && entregador.getId() != null;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

}
