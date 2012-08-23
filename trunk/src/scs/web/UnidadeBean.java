package scs.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;

@ManagedBean(name="unidadeBean")
@RequestScoped
public class UnidadeBean {
	
	private Unidade unidade = new Unidade();
	private List<Unidade> lista;
	
	
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		UnidadeBean other = (UnidadeBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	public String novo(){
		this.unidade = new Unidade();
		return "/restrito/unidade";
	}
	
	public String editar(){
		return "/restrito/unidade";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		UnidadeRN unidadeRN = new UnidadeRN();
		unidadeRN.salvar(this.unidade);
		
		return "/restrito/lista_unidade";//this.destinoSalvar;
	}
	
	public void setLista(List<Unidade> lista) {
		this.lista = lista;
	}

	public String excluir(){
		UnidadeRN unidadeRN = new UnidadeRN();
		unidadeRN.excluir(this.unidade);
		this.lista = null;
		return null;
	}
	
	public List<Unidade> getLista(){
		if(this.lista==null){
			UnidadeRN unidadeRN = new UnidadeRN();
			this.lista = unidadeRN.listar();
		}
		return this.lista;
	}
	
		
	
}
