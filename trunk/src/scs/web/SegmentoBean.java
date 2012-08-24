package scs.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;


@ManagedBean(name="segmentoBean")
@RequestScoped
public class SegmentoBean {
	
	private Segmento segmento = new Segmento();
	private List<Segmento> lista;
	
	public String novo(){
		this.segmento = new Segmento();
		return "/restrito/segmento";
	}
	
	public String editar(){
		return "/restrito/segmento";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		SegmentoRN segmentoRN = new SegmentoRN();
		segmentoRN.salvar(this.segmento);
		
		return "/restrito/lista_segmento";//this.destinoSalvar;
	}
	
	public void setLista(List<Segmento> lista) {
		this.lista = lista;
	}

	public String excluir(){
		SegmentoRN segmentoRN = new SegmentoRN();
		segmentoRN.excluir(this.segmento);
		this.lista = null;
		return null;
	}
	
	public List<Segmento> getLista(){
		if(this.lista==null){
			SegmentoRN segmentoRN = new SegmentoRN();
			this.lista = segmentoRN.listar();
		}
		return this.lista;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
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
		SegmentoBean other = (SegmentoBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (segmento == null) {
			if (other.segmento != null)
				return false;
		} else if (!segmento.equals(other.segmento))
			return false;
		return true;
	}
	
	

}
