package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import scs.consultasMedicas.ConsultasMedicas;
import scs.consultasMedicas.ConsultasMedicasRN;
import scs.encamMedicos.EncaminhamentosMedicos;
import scs.encamMedicos.EncaminhamentosMedicosRN;
import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiro;
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiroRN;
import scs.usuario.Usuario;
import scs.visitasDomiciliares.VisitasDomiciliares;
import scs.visitasDomiciliares.VisitasDomiciliaresRN;

@ManagedBean(name="consultasMedicasBean")
@RequestScoped
public class ConsultasMedicasBean {
	
	private ConsultasMedicas consultasMedicas = new ConsultasMedicas();
	private List<ConsultasMedicas> lista;
		
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		ConsultasMedicasRN consulRN = new ConsultasMedicasRN();
		Integer codigo = consultasMedicas.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+consultasMedicas.getTipoFormatada().toString(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+consultasMedicas.getTipoFormatada().toString(), ""));
			
		}
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			consultasMedicas.setUnidade(usuario.getArea().getUnidade());
		}
		
		consulRN.salvar(this.consultasMedicas);
		
		return "/restrito/lista_consultasMedicas.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<ConsultasMedicas> getLista() {
		if(this.lista==null){
			ConsultasMedicasRN consuRN = new ConsultasMedicasRN();
			this.lista = consuRN.listar();
		}
		return lista;
	}

	public void setLista(List<ConsultasMedicas> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.consultasMedicas = new ConsultasMedicas();
		this.consultasMedicas.setDtcadastro(new Date()); 
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			consultasMedicas.setUnidade(usuario.getArea().getUnidade());
		}
		
		return "/restrito/consultasMedicas";
	}
	
	public boolean getDisableConsultas() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		boolean result=true;
		for (int i = 0; i < usuario.getPermissao().size(); i++) {
			if(usuario.getPermissao().get(i).equals("ROLE_ADMIN")){
				result=false;
			}
		}		
		return result;
	}
	
	
	public String editar(){
		return "/restrito/consultasMedicas";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+consultasMedicas.getTipo(), ""));
		ConsultasMedicasRN consuRN = new ConsultasMedicasRN();
		consuRN.excluir(this.consultasMedicas);
		this.lista = null;
		return null;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((consultasMedicas == null) ? 0 : consultasMedicas.hashCode());
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
		ConsultasMedicasBean other = (ConsultasMedicasBean) obj;
		if (consultasMedicas == null) {
			if (other.consultasMedicas != null)
				return false;
		} else if (!consultasMedicas.equals(other.consultasMedicas))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	public ConsultasMedicas getConsultasMedicas() {
		return consultasMedicas;
	}

	public void setConsultasMedicas(ConsultasMedicas consultasMedicas) {
		this.consultasMedicas = consultasMedicas;
	}

		
	
}
