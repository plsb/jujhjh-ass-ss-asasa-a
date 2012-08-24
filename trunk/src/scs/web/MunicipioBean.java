package scs.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.municipio.Municipio;
import scs.municipio.MunicipioRN;

@ManagedBean(name="municipioBean")
@RequestScoped
public class MunicipioBean {

	private Municipio municipio = new Municipio();

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		MunicipioRN municipioRN = new MunicipioRN();
		municipioRN.salvar(this.municipio);
		
		return "/restrito/principal";//this.destinoSalvar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		return result;
	}
	
}