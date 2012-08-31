package scs.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;

@FacesConverter(forClass= Segmento.class)
public class SegmentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				SegmentoRN segmentooRN = new SegmentoRN();
				return segmentooRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar o bairro de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Segmento segmento = (Segmento) value;
			return segmento.getCodigo_segmento().toString();
		}
		return "";
	}

}
