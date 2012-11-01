package scs.web;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import scs.encamMedicos.EncaminhamentosMedicos;
import scs.encamMedicos.EncaminhamentosMedicosRN;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;

@ManagedBean(name="encaminhamentoMedicosBean")
@RequestScoped
public class EncaminhamentosMedicosBean {
	
	private EncaminhamentosMedicos encamiMedico = new EncaminhamentosMedicos();
	private List<EncaminhamentosMedicos> lista;
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		EncaminhamentosMedicosRN encaRN = new EncaminhamentosMedicosRN();
		Integer codigo = encamiMedico.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+encamiMedico.getDataFormtada(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+encamiMedico.getDataFormtada(), ""));
			
		}
		
		encaRN.salvar(this.encamiMedico);
		
		return "/restrito/lista_encaminhamentoMedico.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<EncaminhamentosMedicos> getLista() {
		if(this.lista==null){
			EncaminhamentosMedicosRN encamMedicos = new EncaminhamentosMedicosRN();
			this.lista = encamMedicos.listar();
		}
		return lista;
	}

	public void setLista(List<EncaminhamentosMedicos> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.encamiMedico = new EncaminhamentosMedicos();
		this.encamiMedico.setData_cadastro(new Date()); 
		return "/restrito/encaminhamentoMedico";
	}
	
	public String editar(){
		return "/restrito/encaminhamentoMedico";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+encamiMedico.getDataFormtada(), ""));
		EncaminhamentosMedicosRN encamRN = new EncaminhamentosMedicosRN();
		encamRN.excluir(this.encamiMedico);
		this.lista = null;
		return null;
	}

	public EncaminhamentosMedicos getEncamiMedico() {
		return encamiMedico;
	}

	public void setEncamiMedico(EncaminhamentosMedicos encamiMedico) {
		this.encamiMedico = encamiMedico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encamiMedico == null) ? 0 : encamiMedico.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
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
		EncaminhamentosMedicosBean other = (EncaminhamentosMedicosBean) obj;
		if (encamiMedico == null) {
			if (other.encamiMedico != null)
				return false;
		} else if (!encamiMedico.equals(other.encamiMedico))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}
	
	
	
}
