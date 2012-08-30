package scs.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.equipe.Equipe;
import scs.equipe.EquipeRN;

@ManagedBean(name="equipeBean")
@RequestScoped
public class EquipeBean {

	private Equipe equipe = new Equipe();
	private List<Equipe> lista;
	
	
	
	public Equipe getEquipe() {
		return equipe;
	}



	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}



	public List<Equipe> getLista() {
		return lista;
	}



	public void setLista(List<Equipe> lista) {
		this.lista = lista;
	}



	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		EquipeRN equipeRN = new EquipeRN();
		equipeRN.salvar(this.equipe);
		
		return "/restrito/principal";//this.destinoSalvar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((equipe == null) ? 0 : equipe.hashCode());
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
		EquipeBean other = (EquipeBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (equipe == null) {
			if (other.equipe != null)
				return false;
		} else if (!equipe.equals(other.equipe))
			return false;
		return true;
	}
	
}
