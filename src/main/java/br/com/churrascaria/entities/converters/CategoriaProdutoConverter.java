package br.com.churrascaria.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.churrascaria.entities.CategoriaProduto;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.CategoriaProdutoServiceImplementacao;

@FacesConverter(forClass = CategoriaProduto.class)
public class CategoriaProdutoConverter implements Converter<CategoriaProduto> {
	
	@Inject
	private CategoriaProdutoServiceImplementacao categoriaProdutoService;

	@Override
	public CategoriaProduto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			long id = Long.parseLong(value);
			return categoriaProdutoService.getByID(id);
		} catch (ServiceEdgleChurrascariaException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, CategoriaProduto value) {
		if (!(value instanceof CategoriaProduto)) {
			return null;
		}

		Long id = ((CategoriaProduto) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
