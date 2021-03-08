package br.com.churrascaria.beans.cliente;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.entities.Endereco;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ClienteServiceImplementacao;

@ViewScoped
@Named
public class ClienteEdit extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteServiceImplementacao clienteService;

	private Cliente cliente;

	private Endereco endereco;

	public String init() {
		try {
			endereco = new Endereco();
			if (cliente == null) {
				cliente = new Cliente();
				cliente.setEnderecos(new ArrayList<>());
			} else {
				cliente = clienteService.getByID(cliente.getId());
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}

	public String saveCliente() {
		try {
			if (isEdicaoDeCliente()) {
				clienteService.update(cliente);
			} else {
				clienteService.save(cliente);
			}
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + cliente.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CLIENTE;
	}

	public void saveEndereco() {
		try {
			clienteService.validarEndereco(endereco);
			cliente.getEnderecos().add(endereco);
			endereco.setCliente(cliente);
			endereco = new Endereco();
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
	}

	public String deleteEndereco(Endereco endereco) {
		cliente.getEnderecos().remove(endereco);
		reportarMensagemDeSucesso("Endereço '" + endereco.getNome() + "' excluída");
		return null;
	}

	public boolean isEdicaoDeCliente() {
		return cliente != null && cliente.getId() != null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
