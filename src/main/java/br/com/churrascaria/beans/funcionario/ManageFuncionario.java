package br.com.churrascaria.beans.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

@Named
@ViewScoped
public class ManageFuncionario extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioServiceImplementacao funcionarioService;

	private List<Funcionario> funcionarios;

	private FuncionarioFilter funcionarioFilter;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public FuncionarioFilter getFuncionarioFilter() {
		return funcionarioFilter;
	}

	public void setFuncionarioFilter(FuncionarioFilter funcionarioFilter) {
		this.funcionarioFilter = funcionarioFilter;
	}

	@PostConstruct
	public void init() {
		limpar();
		filtrar();
	}

	public String filtrar() {
		ArrayList<Funcionario> array = new ArrayList<Funcionario>();
		Funcionario func = new Funcionario();
		func.setPrimeiro(true);
		array.add(func);
		funcionarios = array;
		try {
			array.addAll(funcionarioService.findBy(funcionarioFilter));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String delete(Funcionario funcionario) {
		try {
			if (getFuncionarioLogado().getId().equals(funcionario.getId())) {
				reportarMensagemDeErro("Funcionario '" + funcionario.getNome() + "' está logado");
				return null;
			} else {
				funcionarioService.delete(funcionario);
				reportarMensagemDeSucesso("Funcionario '" + funcionario.getNome() + "' excluído");
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_FUNCIONARIO;

	}
	public boolean podeSerExcluido(Funcionario funcionario){
		if (getFuncionarioLogado().getId() == funcionario.getId()) {
			return false;
		}
		
		return true;
	}

	public Object limpar() {
		this.funcionarioFilter = new FuncionarioFilter();
		return null;
	}

}
