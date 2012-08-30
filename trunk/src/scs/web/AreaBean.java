package scs.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.area.Area;
import scs.area.AreaRN;

@ManagedBean(name="areaBean")
@RequestScoped

public class AreaBean {

	private Area area = new Area();
	private List<Area> lista;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		AreaRN areaRN = new AreaRN();
		areaRN.salvar(this.area);
		
		return "/restrito/principal";//this.destinoSalvar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((area == null) ? 0 : area.hashCode());
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
		AreaBean other = (AreaBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		return true;
	}

	public List<Area> getLista() {
		return lista;
	}

	public void setLista(List<Area> lista) {
		this.lista = lista;
	}
	
}
