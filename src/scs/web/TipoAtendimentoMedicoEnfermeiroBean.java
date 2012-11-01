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
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiro;
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiroRN;

@ManagedBean(name="tipoAtendMedicoEnfermeiroBean")
@RequestScoped
public class TipoAtendimentoMedicoEnfermeiroBean {
	
	private TipoAtendimentoMedicoEnfermeiro tipoAtend = new TipoAtendimentoMedicoEnfermeiro();
	private List<TipoAtendimentoMedicoEnfermeiro> lista;
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		TipoAtendimentoMedicoEnfermeiroRN tipoAtenRN = new TipoAtendimentoMedicoEnfermeiroRN();
		Integer codigo = tipoAtend.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+tipoAtend.getId(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+tipoAtend.getId(), ""));
			
		}
		
		tipoAtenRN.salvar(this.tipoAtend);
		
		return "/restrito/lista_tipoatendimento.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<TipoAtendimentoMedicoEnfermeiro> getLista() {
		if(this.lista==null){
			TipoAtendimentoMedicoEnfermeiroRN tipoAtenRN = new TipoAtendimentoMedicoEnfermeiroRN();
			this.lista = tipoAtenRN.listar();
		}
		return lista;
	}

	public void setLista(List<TipoAtendimentoMedicoEnfermeiro> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.tipoAtend = new TipoAtendimentoMedicoEnfermeiro();
		this.tipoAtend.setData_cadastro(new Date()); 
		return "/restrito/tipoatendimento";
	}
	
	public String editar(){
		return "/restrito/tipoatendimento";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+tipoAtend.getId(), ""));
		TipoAtendimentoMedicoEnfermeiroRN tipoAtendRN = new TipoAtendimentoMedicoEnfermeiroRN();
		tipoAtendRN.excluir(this.tipoAtend);
		this.lista = null;
		return null;
	}

	public TipoAtendimentoMedicoEnfermeiro getTipoAtend() {
		return tipoAtend;
	}

	public void setTipoAtend(TipoAtendimentoMedicoEnfermeiro tipoAtend) {
		this.tipoAtend = tipoAtend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((tipoAtend == null) ? 0 : tipoAtend.hashCode());
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
		TipoAtendimentoMedicoEnfermeiroBean other = (TipoAtendimentoMedicoEnfermeiroBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (tipoAtend == null) {
			if (other.tipoAtend != null)
				return false;
		} else if (!tipoAtend.equals(other.tipoAtend))
			return false;
		return true;
	}

	
	
	
}
