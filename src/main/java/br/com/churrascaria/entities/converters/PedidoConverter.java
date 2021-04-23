package br.com.churrascaria.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.churrascaria.entities.Pedido;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.PedidoServiceImplementacao;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter<Pedido> {
	
	@Inject
	private PedidoServiceImplementacao produtoServiceImplementacao;

	@Override
	public Pedido getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			long id = Long.parseLong(value);
			return produtoServiceImplementacao.getByID(id);
		} catch (ServiceEdgleChurrascariaException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pedido value) {
		if (!(value instanceof Pedido)) {
			return null;
		}

		Long id = ((Pedido) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
