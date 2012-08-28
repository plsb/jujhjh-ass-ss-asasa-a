package scs.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;

@FacesConverter(forClass= Bairro.class)
public class BairroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			Integer codigo = Integer.valueOf(value);
			try {
				BairroRN bairroRN = new BairroRN();
				return bairroRN.carregar(codigo);
			} catch (Exception e) {
				throw new ConverterException("Não foi possível encontrar o usuário de código " + value + "." + e.getMessage());
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Bairro bairro = (Bairro) value;
			return bairro.getCodigo_bairro().toString();
		}
		return "";
	}

}
