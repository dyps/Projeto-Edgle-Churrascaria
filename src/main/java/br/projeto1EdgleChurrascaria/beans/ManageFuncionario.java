package br.projeto1EdgleChurrascaria.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.projeto1EdgleChurrascaria.entities.Funcionario;
import br.projeto1EdgleChurrascaria.filter.FuncionarioFilter;
import br.projeto1EdgleChurrascaria.services.FuncionarioService;
import br.projeto1EdgleChurrascaria.services.ServiceEdgleChurrascariaException;
import br.projeto1EdgleChurrascaria.services.implementacao.FuncionarioServiceImplementacao;

@RequestScoped
@ManagedBean
public class ManageFuncionario extends AbstractBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FuncionarioService funcionarioService = new FuncionarioServiceImplementacao();
	
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
