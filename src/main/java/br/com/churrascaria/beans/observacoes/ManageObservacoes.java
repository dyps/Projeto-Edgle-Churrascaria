package br.com.churrascaria.beans.observacoes;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.ObservacaoPadrao;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ObservacoesPadraoServiceImplementacao;

@Named
@ViewScoped
public class ManageObservacoes extends AbstractBean {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ObservacoesPadraoServiceImplementacao observacoesService;

	private List<ObservacaoPadrao> observacoes;

	private ObservacaoPadrao novaObservacao;

	@PostConstruct
	public void init() {
		novaObservacao = new ObservacaoPadrao();
		try {
			observacoes = observacoesService.getAll();
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public ObservacaoPadrao getNovaObservacao() {
		return novaObservacao;
	}

	public void setNovaObservacao(ObservacaoPadrao novaObservacao) {
		this.novaObservacao = novaObservacao;
	}

	public String saveObservacao() {
		try {
			observacoesService.save(novaObservacao);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Observação '" + novaObservacao.getDescricao() + "' salva");

		return EnderecoPaginas.PAGINA_PRINCIPAL_OBSERVACAO;

	}
	public String delete(ObservacaoPadrao obs){
		try {
			if (observacoesService.aProdutosUsando(obs)) {
				reportarMensagemDeErro("Observação '" + obs.getDescricao() + "' está sendo usada em algum produto");
				return null;
			} else {
				observacoesService.delete(obs);
				reportarMensagemDeSucesso("Observação '" + obs.getDescricao() + "' excluída");
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_OBSERVACAO;
		
	}

	public List<ObservacaoPadrao> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<ObservacaoPadrao> observacoes) {
		this.observacoes = observacoes;
	}
	

}
