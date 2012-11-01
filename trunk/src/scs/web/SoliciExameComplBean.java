package scs.web;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;

@ManagedBean(name="solicExameComp")
@RequestScoped
public class SoliciExameComplBean {
	
	private SolicExamesComplem solicExamCompl = new SolicExamesComplem();
	private List<SolicExamesComplem> lista;
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		SolicExamesComplemRN soliRN = new SolicExamesComplemRN();
		Integer codigo = solicExamCompl.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+solicExamCompl.getDataFormtada(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+solicExamCompl.getDataFormtada(), ""));
			
		}
		
		soliRN.salvar(this.solicExamCompl);
		
		return "/restrito/lista_solicExamCompl.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<SolicExamesComplem> getLista() {
		if(this.lista==null){
			SolicExamesComplemRN soliRN = new SolicExamesComplemRN();
			this.lista = soliRN.listar();
		}
		return lista;
	}

	public void setLista(List<SolicExamesComplem> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.solicExamCompl = new SolicExamesComplem();
		this.solicExamCompl.setData_cadastro(new Date()); 
		return "/restrito/solicExamesComplem";
	}
	
	public String editar(){
		return "/restrito/solicExamesComplem";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+solicExamCompl.getDataFormtada(), ""));
		SolicExamesComplemRN solicRN = new SolicExamesComplemRN();
		solicRN.excluir(this.solicExamCompl);
		this.lista = null;
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((solicExamCompl == null) ? 0 : solicExamCompl.hashCode());
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
		SoliciExameComplBean other = (SoliciExameComplBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (solicExamCompl == null) {
			if (other.solicExamCompl != null)
				return false;
		} else if (!solicExamCompl.equals(other.solicExamCompl))
			return false;
		return true;
	}

	public SolicExamesComplem getSolicExamCompl() {
		return solicExamCompl;
	}

	public void setSolicExamCompl(SolicExamesComplem solicExamCompl) {
		this.solicExamCompl = solicExamCompl;
	}
	
	
	
}
