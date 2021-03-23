package br.com.churrascaria.entities.converters;

import java.io.Serializable;

import javax.enterprise.inject.Default;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.churrascaria.entities.ItemDeConfiguracao;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.ProdutoServiceImplementacao;

@Default
@FacesConverter(forClass = ItemDeConfiguracao.class)
public class ItemDeConfiguracaoConverter implements Serializable, Converter<ItemDeConfiguracao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ProdutoServiceImplementacao produtoServiceImplementacao;

	@Override
	public ItemDeConfiguracao getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			long id = Long.parseLong(value);
			return produtoServiceImplementacao.getItemByID(id);
		} catch (ServiceEdgleChurrascariaException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	public String getAsString(FacesContext context, UIComponent component, ItemDeConfiguracao value) {
		if (value instanceof ItemDeConfiguracao && value != null) {
			ItemDeConfiguracao itemDeConfiguracao = (ItemDeConfiguracao) value;
			return itemDeConfiguracao.toString();//adaptacao para nao da erro em alguns componentes jsf
		}
		return null;
	}

}
