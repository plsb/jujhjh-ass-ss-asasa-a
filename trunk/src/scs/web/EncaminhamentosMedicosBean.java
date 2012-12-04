package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.encamMedicos.EncaminhamentosMedicos;
import scs.encamMedicos.EncaminhamentosMedicosRN;
import scs.microarea.Microarea;
import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemRN;
import scs.usuario.Usuario;

@ManagedBean(name = "encaminhamentoMedicosBean")
@RequestScoped
public class EncaminhamentosMedicosBean {

	private EncaminhamentosMedicos encamiMedico = new EncaminhamentosMedicos();
	private List<EncaminhamentosMedicos> lista;
	private List<SelectItem> profissionalSelect;

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		EncaminhamentosMedicosRN encaRN = new EncaminhamentosMedicosRN();
		Integer codigo = encamiMedico.getId();
		if (codigo == null || codigo == 0) {
			if (verificaUnique()) {
				context.addMessage(
						null,
						new FacesMessage("Sucesso ao Inserir: "
								+ encamiMedico.getDataFormtada(), ""));

			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "
					+ encamiMedico.getDataFormtada(), ""));

		}

		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			encamiMedico.setUnidade(usuario.getArea().getUnidade());
		}
		
		encaRN.salvar(this.encamiMedico);

		return "/restrito/lista_encaminhamentoMedico.xhtml";// this.destinoSalvar;
	}

	public boolean verificaUnique() {
		return true;

	}

	public List<EncaminhamentosMedicos> getLista() {
		if (this.lista == null) {
			EncaminhamentosMedicosRN encamMedicos = new EncaminhamentosMedicosRN();
			this.lista = encamMedicos.listar();
		}
		return lista;
	}

	public void setLista(List<EncaminhamentosMedicos> lista) {
		this.lista = lista;
	}

	public String novo() {
		this.encamiMedico = new EncaminhamentosMedicos();
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		if (usuario.getArea() != null) {
			encamiMedico.setUnidade(usuario.getArea().getUnidade());
		}
		
		
		this.encamiMedico.setData_cadastro(new Date());
		return "/restrito/encaminhamentoMedico";
	}
	
	public boolean getDisableEnca() {
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

	public String editar() {
		return "/restrito/encaminhamentoMedico";
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "
				+ encamiMedico.getDataFormtada(), ""));
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
				if(encamiMedico.getUnidade()!=null){
					if(encamiMedico.getUnidade().equals(prof.getUnidade())){
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
