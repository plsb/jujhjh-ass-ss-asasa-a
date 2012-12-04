package scs.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import scs.area.Area;
import scs.area.AreaRN;

import scs.microarea.Microarea;
import scs.profissional.Profissional;
import scs.profissional.ProfissionalRN;
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.util.HibernateUtil;

@ManagedBean(name = "profissionalBean")
@RequestScoped
public class ProfissionalBean {

	private Profissional profissioal = new Profissional();
	private List<Profissional> lista;
	private List<SelectItem> profissionalSelect;

	public Profissional getProfissioal() {
		return profissioal;
	}

	public void setProfissioal(Profissional profissioal) {
		this.profissioal = profissioal;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ProfissionalRN profRN = new ProfissionalRN();
		Integer codigo = profissioal.getId();
		if (codigo == null || codigo == 0) {
			if (verificaUnique()) {
				context.addMessage(null, new FacesMessage(
						"Sucesso ao Inserir: " + profissioal.getNome(), ""));

			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "
					+ profissioal.getNome(), ""));

		}

		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		
		if (usuario.getUnidade() != null) {
			profissioal.setUnidade(usuario.getUnidade());
		}
		
		profRN.salvar(this.profissioal);

		return "/restrito/lista_profissional";// this.destinoSalvar;
	}

	public boolean verificaUnique() {
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.id from profissional u where u.nome= '"
						+ profissioal.getNome() + "'");
		List ar = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (ar.isEmpty()) {
			a = true;
		} else {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Nome Ja Cadastrado, Informe Outro Nome!", ""));
			a = false;

		}

		return a;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((profissioal == null) ? 0 : profissioal.hashCode());
		result = prime
				* result
				+ ((profissionalSelect == null) ? 0 : profissionalSelect
						.hashCode());
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
		ProfissionalBean other = (ProfissionalBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (profissioal == null) {
			if (other.profissioal != null)
				return false;
		} else if (!profissioal.equals(other.profissioal))
			return false;
		if (profissionalSelect == null) {
			if (other.profissionalSelect != null)
				return false;
		} else if (!profissionalSelect.equals(other.profissionalSelect))
			return false;
		return true;
	}

	public List<Profissional> getLista() {
		if (this.lista == null) {
			ProfissionalRN profRN = new ProfissionalRN();
			this.lista = profRN.listar();
		}
		return lista;
	}

	public void setLista(List<Profissional> lista) {
		this.lista = lista;
	}

	public String novo() {
		this.profissioal = new Profissional();
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		
		if (usuario.getUnidade() != null) {
			profissioal.setUnidade(usuario.getUnidade());
		}
		
		return "/restrito/profissional";
	}
	
	public boolean getDisableUnidade() {
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
		return "/restrito/profissional";
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "
				+ profissioal.getNome(), ""));
		ProfissionalRN profRN = new ProfissionalRN();
		profRN.excluir(this.profissioal);
		this.lista = null;
		return null;
	}

	public List<SelectItem> getProfissionalSelect() {
		if (this.profissionalSelect == null) {
			this.profissionalSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			ProfissionalRN profRN = new ProfissionalRN();
			List<Profissional> categorias = profRN.listar();
			this.montaDadosSelectArea(this.profissionalSelect, categorias, "");
		}
		return profissionalSelect;
	}

	private void montaDadosSelectArea(List<SelectItem> select,
			List<Profissional> profs, String prefixo) {

		SelectItem item = null;
		if (profs != null) {
			for (Profissional prof : profs) {
				item = new SelectItem(prof, "Nome: " + prof.getNome() + " - "
						+ prof.getTipo() + "| Especialidade:"
						+ prof.getEspecialidade());
				item.setEscape(false);
				select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}
}
