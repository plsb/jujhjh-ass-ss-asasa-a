package scs.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import scs.residencia.Residencia;
import scs.residencia.ResidenciaRN;

@ManagedBean(name="residenciaBean")
@RequestScoped
public class ResidenciasBean {
	
	private Residencia residencia = new Residencia();
	private List<Residencia> lista;	

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

		
	public String novo(){
		this.residencia = new Residencia();
		return "/restrito/residencia";
	}
	
	public String editar(){
		return "/restrito/residencia";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance(); 
		ResidenciaRN residenciaRN = new ResidenciaRN();
						
		residenciaRN.salvar(this.residencia);
		return "/restrito/lista_residencias";
	}
	
	public void setLista(List<Residencia> lista) {
		this.lista = lista;
	}

	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		ResidenciaRN residenciaRN = new ResidenciaRN();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+residencia.getEndereco(), ""));
		residenciaRN.excluir(this.residencia); 
		this.lista = null;
		return null;
	}
	
	public List<Residencia> getLista(){
		if(this.lista==null){
			ResidenciaRN residenciaRN = new ResidenciaRN();
			this.lista = residenciaRN.listar();
		}
		return this.lista;
	}

	public Residencia getBairro() {
		return residencia;
	}

	public void setBairro(Residencia residencia) {
		this.residencia = residencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((residencia == null) ? 0 : residencia.hashCode());
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
		ResidenciasBean other = (ResidenciasBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (residencia == null) {
			if (other.residencia != null)
				return false;
		} else if (!residencia.equals(other.residencia))
			return false;
		return true;
	}

}
