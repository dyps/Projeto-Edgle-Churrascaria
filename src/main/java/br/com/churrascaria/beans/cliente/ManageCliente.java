package br.com.churrascaria.beans.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.churrascaria.beans.AbstractBean;
import br.com.churrascaria.beans.EnderecoPaginas;
import br.com.churrascaria.entities.Cliente;
import br.com.churrascaria.filter.ClienteFilter;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ClienteServiceImplementacao;

@Named
@ViewScoped
public class ManageCliente extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteServiceImplementacao clienteService;

	private List<Cliente> clientes;

	private ClienteFilter clienteFilter;

	private Cliente novoCliente;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public ClienteFilter getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(ClienteFilter clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public Cliente getNovoCliente() {
		return novoCliente;
	}

	public void setNovoCliente(Cliente novoCliente) {
		this.novoCliente = novoCliente;
	}

	@PostConstruct
	public void init() {
		setNovoCliente(new Cliente());
		clienteFilter = new ClienteFilter();
		filtrar();
	}

	public String filtrar() {
		ArrayList<Cliente> array = new ArrayList<Cliente>();
//		Cliente cliente = new Cliente();
//		cliente.setPrimeiro(true);
//		array.add(cliente);
		clientes = array;
		try {
			array.addAll(clienteService.findBy(clienteFilter));
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return null;
	}

	public String delete(Cliente cliente) {
		try {
			clienteService.delete(cliente);
			reportarMensagemDeSucesso("Cliente '" + cliente.getNome() + "' excluído");
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}
		return EnderecoPaginas.PAGINA_PRINCIPAL_CLIENTE;
	}

	public String saveCliente() {
		try {
			clienteService.save(novoCliente);
		} catch (ServiceEdgleChurrascariaException e) {
			reportarMensagemDeErro(e.getMessage());
			return null;
		}

		reportarMensagemDeSucesso("Cliente '" + novoCliente.getNome() + "' salvo");

		return EnderecoPaginas.PAGINA_PRINCIPAL_CLIENTE;
	}

}
