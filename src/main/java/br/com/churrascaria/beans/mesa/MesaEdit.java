package br.com.churrascaria.beans.mesa;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;

@ViewScoped
@Named
public class MesaEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MesaServiceImplementacao mesaService;
	
	private Mesa mesa;
	
	public String init() {
		try {
			if (mesa == null) {
				mesa = new Mesa();
			} else {
				mesa = mesaService.getByID(mesa.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}
	
	public String saveMesa() {
		try {
			if (isEdicaoDeMesa()) {
				mesaService.update(mesa);
			} else {
				mesaService.save(mesa);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Mesa '" + mesa.getNumero() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_MESA;
	}
	
	public boolean isEdicaoDeMesa() {
		return mesa != null && mesa.getId() != null;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
