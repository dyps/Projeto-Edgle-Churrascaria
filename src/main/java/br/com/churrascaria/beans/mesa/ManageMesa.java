package br.com.churrascaria.beans.mesa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Mesa;
import br.com.churrascaria.filter.MesaFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.MesaServiceImplementacao;

@Named
@ViewScoped
public class ManageMesa extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private MesaServiceImplementacao mesaService;

	private List<Mesa> mesas;

	private MesaFilter mesaFilter;
	
	private Mesa novaMesa;

	public List<Mesa> getMesas() {
		return mesas;
	}

	public MesaFilter getMesaFilter() {
		return mesaFilter;
	}

	public void setMesaFilter(MesaFilter mesaFilter) {
		this.mesaFilter = mesaFilter;
	}
	
	public Mesa getNovaMesa() {
		return novaMesa;
	}

	public void setNovaMesa(Mesa novaMesa) {
		this.novaMesa = novaMesa;
	}

	@PostConstruct
	public void init() {
		limpar();
//		setNovaMesa(new Mesa());
//		mesaFilter = new MesaFilter();
		filtrar();
	}

	public String filtrar() {
		ArrayList<Mesa> array = new ArrayList<Mesa>();
		Mesa mesa = new Mesa();
		mesa.setPrimeiro(true);
		array.add(mesa);
		mesas = array;
		try {
			array.addAll(mesaService.findBy(mesaFilter));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String delete(Mesa mesa) {
		try {
			mesaService.delete(mesa);
			reportarMensagemDeSucesso("Mesa '" + mesa.getNumero() + "' excluída");
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_MESA;

	}
	
	public String saveMesa() {
		try {
			mesaService.save(novaMesa);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		
		reportarMensagemDeSucesso("Mesa '" + novaMesa.getNumero() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_MESA;
	}
	
	public Object limpar() {
		this.mesaFilter = new MesaFilter();
		return null;
	}

}
