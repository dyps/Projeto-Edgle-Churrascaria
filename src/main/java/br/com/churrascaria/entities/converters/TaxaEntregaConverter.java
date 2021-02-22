package br.com.churrascaria.entities.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.churrascaria.entities.TaxaEntrega;
import br.com.churrascaria.services.ServiceEdgleChurrascariaException;
import br.com.churrascaria.services.implementacao.TaxaEntregaServiceImplementacao;

@FacesConverter(forClass = TaxaEntrega.class)
public class TaxaEntregaConverter implements Converter<TaxaEntrega> {

	@Inject
	private TaxaEntregaServiceImplementacao taxaEntregaService;

	@Override
	public TaxaEntrega getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			long id = Long.parseLong(value);
			return taxaEntregaService.getByID(id);
		} catch (ServiceEdgleChurrascariaException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TaxaEntrega value) {
		if (!(value instanceof TaxaEntrega)) {
			return null;
		}

		Long id = ((TaxaEntrega) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
