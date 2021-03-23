package br.com.churrascaria.beans.pedidos.mesa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;

@Named
@ViewScoped
public class VendasMesa extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MesaServiceImplementacao mesaService;

	private List<Mesa> mesas;

	public List<Mesa> getMesas() {
		return mesas;
	}

	@PostConstruct
	public void init() {
		mesas = new ArrayList<Mesa>();
		try {
			mesas.addAll(mesaService.findBy(new MesaFilter()));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

}
