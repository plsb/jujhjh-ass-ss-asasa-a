package scs.web.converter;

import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoRN;
import scs.area.Area;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.area.AreaRN;

@FacesConverter(forClass= Agendamento.class)
public class AgendamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				AgendamentoRN agendamentoRN = new AgendamentoRN();
				return agendamentoRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar o bairro de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Agendamento agendamento = (Agendamento) value;
			return agendamento.getId().toString();
		}
		return "";
	}
	

}
