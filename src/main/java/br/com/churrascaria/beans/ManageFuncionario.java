package br.com.churrascaria.beans;

import java.util.List;

//import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.churrascaria.entities.Funcionario;
import br.com.churrascaria.filter.FuncionarioFilter;
import br.com.churrascaria.services.CRUDService;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.FuncionarioServiceImplementacao;

@ManagedBean
@ViewScoped
public class ManageFuncionario extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CRUDService funcionarioService = new FuncionarioServiceImplementacao();
	
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
		try {
			funcionarios = funcionarioService.findBy(getFuncionarioFilter());
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String limpar() {
		this.funcionarioFilter = new FuncionarioFilter();
		return null;
	}

}
