package scs.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.rua.Rua;
import scs.rua.RuaRN;

@FacesConverter(forClass= Rua.class)
public class RuaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				RuaRN ruaRN = new RuaRN();
				return ruaRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar o bairro de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Rua rua = (Rua) value;
			return rua.getCodigo_rua().toString();
		}
		return "";
	}

}
