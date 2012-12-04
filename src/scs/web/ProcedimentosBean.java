package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import scs.procedimentos.Procedimentos;
import scs.procedimentos.ProcedimentosRN;
import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;
import scs.usuario.Usuario;

@ManagedBean(name="procedimentosBean")
@RequestScoped
public class ProcedimentosBean {
	
	private Procedimentos procedimentos = new Procedimentos();
	private List<Procedimentos> lista;
	private List<SelectItem> profissionalSelect;
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		ProcedimentosRN procRN = new ProcedimentosRN();
		Integer codigo = procedimentos.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+procedimentos.getDataFormtada(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+procedimentos.getDataFormtada(), ""));
			
		}
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			procedimentos.setUnidade(usuario.getArea().getUnidade());
		}
		
		procRN.salvar(this.procedimentos);
		
		return "/restrito/lista_procedimentos.xhtml";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		return true;
		
	}
	
	public List<Procedimentos> getLista() {
		if(this.lista==null){
			ProcedimentosRN procedRN = new ProcedimentosRN();
			this.lista = procedRN.listar();
		}
		return lista;
	}

	public void setLista(List<Procedimentos> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.procedimentos = new Procedimentos();
		this.procedimentos.setData_cadastro(new Date()); 
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			procedimentos.setUnidade(usuario.getArea().getUnidade());
		}
		
		return "/restrito/procedimentos";
	}
	
	public boolean getDisableProc() {
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
		return "/restrito/procedimentos";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+procedimentos.getDataFormtada(), ""));
		ProcedimentosRN procedRN = new ProcedimentosRN();
		procedRN.excluir(this.procedimentos);
		this.lista = null;
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((procedimentos == null) ? 0 : procedimentos.hashCode());
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
		ProcedimentosBean other = (ProcedimentosBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (procedimentos == null) {
			if (other.procedimentos != null)
				return false;
		} else if (!procedimentos.equals(other.procedimentos))
			return false;
		return true;
	}

	public Procedimentos getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Procedimentos procedimentos) {
		this.procedimentos = procedimentos;
	}

	public List<SelectItem> getProfissionalSelect() {
		if (this.profissionalSelect == null) {
			this.profissionalSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			ProfissionalRN profRn = new ProfissionalRN();
			List<Profissional> categorias = profRn.listar();
			this.montaDadosSelectProfissional(this.profissionalSelect,
					categorias, "");
		}

		return profissionalSelect;
	}

	private void montaDadosSelectProfissional(List<SelectItem> select, List<Profissional> profs, String prefixo) {

		SelectItem item = null;
		if (profs != null) {
			for (Profissional prof : profs) {
				item = new SelectItem(prof, prefixo + "Nome: "+prof.getNome()+" | "+prof.getTipo()+" | Especialidade: "+prof.getEspecialidade());
				item.setEscape(false);
				if(procedimentos.getUnidade()!=null){
					if(procedimentos.getUnidade().equals(prof.getUnidade())){
						select.add(item);
					}
				} else {
					select.add(item);
				}
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	
	
}
