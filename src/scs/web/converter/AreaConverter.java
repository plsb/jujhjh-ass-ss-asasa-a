package scs.web.converter;

import scs.area.Area;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.area.AreaRN;

@FacesConverter(forClass= Area.class)
public class AreaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				AreaRN areaRN = new AreaRN();
				return areaRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("N�o foi poss�vel encontrar o bairro de c�digo " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Area area = (Area) value;
			return area.getCodigo_area().toString();
		}
		return "";
	}

}
