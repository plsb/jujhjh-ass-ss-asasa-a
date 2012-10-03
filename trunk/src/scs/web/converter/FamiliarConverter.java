package scs.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import scs.familiar.Familiar;
import scs.familiar.FamiliarRN;

@FacesConverter(forClass= Familiar.class)
public class FamiliarConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				FamiliarRN familiarRN = new FamiliarRN();
				return familiarRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar o familiar de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Familiar familiar = (Familiar) value;
			return familiar.getId().toString();
		}
		return "";
	}

}
