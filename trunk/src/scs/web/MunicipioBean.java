package scs.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.municipio.Municipio;
import scs.municipio.MunicipioRN;
import scs.unidade.Unidade;
import scs.usuario.Usuario;

@ManagedBean(name="municipioBean")
@RequestScoped
public class MunicipioBean {

	private Municipio municipio = new Municipio();
	private List<Municipio> lista;

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public String nvEdMuni(){
		MunicipioRN municipioRN = new MunicipioRN();
		lista = municipioRN.listar();
		if (lista.size()!=0){
			municipio = lista.get(0);
		}
		return "/restrito/municipios.jsf";
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
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((municipio == null) ? 0 : municipio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MunicipioBean other = (MunicipioBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		return true;
	}

	public List<Municipio> getLista() {
		return lista;
	}

	public void setLista(List<Municipio> lista) {
		this.lista = lista;
	}
	
	
	
}