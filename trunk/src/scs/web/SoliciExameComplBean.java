package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;
import scs.usuario.Usuario;

@ManagedBean(name="solicExameComp")
@RequestScoped
public class SoliciExameComplBean {
	
	private SolicExamesComplem solicExamCompl = new SolicExamesComplem();
	private List<SolicExamesComplem> lista;
	private List<SelectItem> profissionalSelect;
	
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
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			solicExamCompl.setUnidade(usuario.getArea().getUnidade());
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
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			solicExamCompl.setUnidade(usuario.getArea().getUnidade());
		}
		
		return "/restrito/solicExamesComplem";
	}
	
	public boolean getDisableSoli() {
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
				if(solicExamCompl.getUnidade()!=null){
					if(solicExamCompl.getUnidade().equals(prof.getUnidade())){
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
