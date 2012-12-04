package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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

@ManagedBean(name="visitasDomiBean")
@RequestScoped
public class VisitasDomiciliaresBean {
	
	private VisitasDomiciliares visitas = new VisitasDomiciliares();
	private List<VisitasDomiciliares> lista;
		
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		VisitasDomiciliaresRN visitasRN = new VisitasDomiciliaresRN();
		Integer codigo = visitas.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+visitas.getTipovisita(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+visitas.getTipovisita(), ""));
			
		}
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			visitas.setUnidade(usuario.getArea().getUnidade());
		}
		
		visitasRN.salvar(this.visitas);
		
		return "/restrito/lista_visitasDomiciliares.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<VisitasDomiciliares> getLista() {
		if(this.lista==null){
			VisitasDomiciliaresRN visitasRN = new VisitasDomiciliaresRN();
			this.lista = visitasRN.listar();
		}
		return lista;
	}

	public void setLista(List<VisitasDomiciliares> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.visitas = new VisitasDomiciliares();
		this.visitas.setDtcadastro(new Date()); 
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			visitas.setUnidade(usuario.getArea().getUnidade());
		}
		
		return "/restrito/visitasDomiciliares";
	}
	
	public boolean getDisableVisitas() {
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
		return "/restrito/visitasDomiciliares";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+visitas.getId(), ""));
		VisitasDomiciliaresRN visitasRN = new VisitasDomiciliaresRN();
		visitasRN.excluir(this.visitas);
		this.lista = null;
		return null;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((visitas == null) ? 0 : visitas.hashCode());
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
		VisitasDomiciliaresBean other = (VisitasDomiciliaresBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (visitas == null) {
			if (other.visitas != null)
				return false;
		} else if (!visitas.equals(other.visitas))
			return false;
		return true;
	}

	public VisitasDomiciliares getVisitas() {
		return visitas;
	}

	public void setVisitas(VisitasDomiciliares visitas) {
		this.visitas = visitas;
	}

		
	
}
