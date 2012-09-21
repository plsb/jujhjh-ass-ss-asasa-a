package scs.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.microarea.Microarea;
import scs.microarea.MicroareaRN;

@FacesConverter(forClass= Microarea.class)
public class MicroareaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				MicroareaRN microareaRN = new MicroareaRN();
				return microareaRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar a microarea de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Microarea mciroarea = (Microarea) value;
			return mciroarea.getCodigo_microarea().toString();
		}
		return "";
	}

}
