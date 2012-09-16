package scs.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.area.Area;
import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.microarea.Microarea;
import scs.microarea.MicroareaRN;
import scs.rua.Rua;
import scs.rua.RuaRN;

@ManagedBean(name="microareaBean")
@RequestScoped

public class MicroareaBean {

	private Microarea microarea = new Microarea();
	private List<Microarea> lista;
	private String mostraInsereRuas;
	
	public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();  
          
        //RuaRN ruaRN = new RuaRN();
        
      	//if(descri.equals("")||descri==null){
      		RuaRN ruaRN = new RuaRN();
      		List<Rua> ruas = ruaRN.listar();
      	//} else {
      	//	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //    String hql ="From Rua where descricao="+String.valueOf(descri);
        //  	Query query = session.createQuery(hql);		
       //   	ruas = query.list();	
      	//}  	
		
		if (ruas != null) {
			for (Rua rua : ruas) {
				 results.add(rua.getDescricao());
			}
		} 
          
        return results;  
    } 
	
	public String getMostraInsereRuas(){
		if (microarea.getCodigo_microarea()==null||microarea.getCodigo_microarea()==0){
			return "false";
		} else return "true";
	}

	public Microarea getArea() {
		return microarea;
	}

	public void setArea(Microarea area) {
		this.microarea = area;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		MicroareaRN microareaRN = new MicroareaRN();
		microareaRN.salvar(this.microarea);
		
		return "/restrito/lista_microarea";//this.destinoSalvar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((microarea == null) ? 0 : microarea.hashCode());
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
		MicroareaBean other = (MicroareaBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (microarea == null) {
			if (other.microarea != null)
				return false;
		} else if (!microarea.equals(other.microarea))
			return false;
		return true;
	}

	public List<Microarea> getLista() {
		if(this.lista==null){
			MicroareaRN microareaRN = new MicroareaRN();
			this.lista = microareaRN.listar();
		}
		return lista;
	}

	public void setLista(List<Microarea> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.microarea = new Microarea();
		return "/restrito/microarea";
	}
	
	public String editar(){
		return "/restrito/microarea";
	}
	
	public String excluir(){
		MicroareaRN microareaRN = new MicroareaRN();
		microareaRN.excluir(this.microarea);
		this.lista = null;
		return null;
	}

	public Microarea getMicroarea() {
		return microarea;
	}

	public void setMicroarea(Microarea microarea) {
		this.microarea = microarea;
	}
	
	
	
}
