package br.com.churrascaria.beans.taxaEntrega;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.TaxaEntregaServiceImplementacao;

@Named
@ViewScoped
public class ManageTaxaEntrega extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TaxaEntregaServiceImplementacao taxaEntregaService;

	private List<TaxaEntrega> taxaEntregas;

	private TaxaEntrega novaTaxaEntrega;

	public List<TaxaEntrega> getTaxaEntregas() {
		return taxaEntregas;
	}

	public TaxaEntrega getNovaTaxaEntrega() {
		return novaTaxaEntrega;
	}

	public void setNovaTaxaEntrega(TaxaEntrega novaTaxaEntrega) {
		this.novaTaxaEntrega = novaTaxaEntrega;
	}

	@PostConstruct
	public void init() {
		novaTaxaEntrega = new TaxaEntrega();
		try {
			taxaEntregas = taxaEntregaService.getAll();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String delete(TaxaEntrega taxaEntrega) {
		try {
			taxaEntregaService.delete(taxaEntrega);
			reportarMensagemDeSucesso("Taxa de Entrega '" + taxaEntrega.getValor() + "' excluída");
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO; // onde redirecionar?

	}

	public String saveTaxaEntrega() {
		try {
			taxaEntregaService.save(novaTaxaEntrega);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Taxa de Entrega '" + novaTaxaEntrega.getValor() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CATEGORIAPRODUTO; // onde redirecionar?
	}

}
