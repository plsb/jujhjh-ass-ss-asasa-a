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

@ManagedBean(name="tipoAtendMedicoEnfermeiroBean")
@RequestScoped
public class TipoAtendimentoMedicoEnfermeiroBean {
	
	private TipoAtendimentoMedicoEnfermeiro tipoAtend = new TipoAtendimentoMedicoEnfermeiro();
	private List<TipoAtendimentoMedicoEnfermeiro> lista;
	private List<SelectItem> profissionalSelect;
	
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
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			tipoAtend.setUnidade(usuario.getArea().getUnidade());
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
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			tipoAtend.setUnidade(usuario.getArea().getUnidade());
		}
		
		return "/restrito/tipoatendimento";
	}
	
	public boolean getDisableTpAtend() {
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
				if(tipoAtend.getUnidade()!=null){
					if(tipoAtend.getUnidade().equals(prof.getUnidade())){
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
