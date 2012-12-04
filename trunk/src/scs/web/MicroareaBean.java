package scs.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.codehaus.groovy.tools.shell.commands.ShowCommand;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import scs.area.Area;
import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.microarea.Microarea;
import scs.microarea.MicroareaDAO;
import scs.microarea.MicroareaRN;
import scs.rua.Rua;
import scs.rua.RuaRN;
import scs.usuario.Usuario;
import scs.util.DAOFactory;
import scs.util.HibernateUtil;

@ManagedBean(name = "microareaBean")
@RequestScoped
public class MicroareaBean {

	private Microarea microarea = new Microarea();
	private List<Microarea> lista;
	private String mostraInsereRuas;
	private String RuaDigitada = "";
	private List<SelectItem> microareaSelect;
	private boolean painelAtivo = false;
	static List<Rua> listRuas = new ArrayList<Rua>();

	public List<String> complete(String query) {

		List<String> results = new ArrayList<String>();

		if (query != null) {
			RuaRN ruaRN = new RuaRN();
			List<Rua> ruas;// = ruaRN.listar();

			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			String hql = "SELECT i FROM Rua i WHERE i.descricao LIKE '%"
					+ query.toUpperCase() + "%'";
			Query qry = session.createQuery(hql);
			ruas = qry.list();

			if (ruas != null) {
				for (Rua rua : ruas) {
					results.add(rua.getDescricao());
				}
			}
		}
		return results;
	}

	public void InsereRuaMicroaria() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (getRuaDigitada().equals("")) {
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Digite o Nome da Rua!", ""));
		} else {
			Session session;
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Query query = session
					.createQuery("from Rua u where u.descricao=:descricao");
			System.out.println(getRuaDigitada());
			query.setParameter("descricao", getRuaDigitada());

			Rua rua = new Rua();
			rua = (Rua) query.uniqueResult();

			if (listRuas != null) {
				if (listRuas.size() == 0) {
					listRuas = microarea.retornaRuas();
				}
				boolean a = true;
				for (int i = 0; i < listRuas.size(); i++) {
					if (listRuas.get(i).getDescricao()
							.equalsIgnoreCase(rua.getDescricao())) {
						a = false;
					}
				}
				if (a) {
					try {
						listRuas.add(rua);
						microarea.setRuasLista(listRuas);
						context.addMessage(null, new FacesMessage(
								"Sucesso ao Inserir Rua: " + getRuaDigitada(),
								""));
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					microarea.setRuasLista(listRuas);
					context.addMessage(null, new FacesMessage(
							"Rua Já Foi Inserida Para Essa Microarea: "
									+ getRuaDigitada(), ""));
				}
			} else {
				try {
					listRuas = microarea.retornaRuas();
					listRuas.add(rua);
					microarea.setRuasLista(listRuas);
					context.addMessage(null, new FacesMessage(
							"Sucesso ao Inserir Rua: " + getRuaDigitada(), ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// MicroareaRN microareaRN = new MicroareaRN();
			// microareaRN.salvar(microarea);

			setRuaDigitada("");
		}

	}

	public void removeRuaLista(String nomeRua) {
		// Session session;
		// session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Rua> listaRuas;
		listaRuas = microarea.getRuasLista();

		// Query query =
		// session.createQuery("from Rua u where u.descricao=:descricao");
		// query.setParameter("descricao", nomeRua);

		// Rua rua = new Rua();
		// rua = (Rua) query.uniqueResult();
		System.out.println("Passou");
		for (int i = 0; i < listaRuas.size(); i++) {
			if (listaRuas.get(i).getDescricao().equalsIgnoreCase(nomeRua)) {
				listaRuas.remove(i);
				System.out.println("excluiu a rua");
			}
		}
		microarea.setRuasLista(listaRuas);
		MicroareaRN microareaRN = new MicroareaRN();
		microareaRN.salvar(microarea);

	}

	public String getRuaDigitada() {
		if ((RuaDigitada == "")) {
			RuaDigitada = "Digite Aqui o Nome da Rua...";
		}
		return RuaDigitada;
	}

	public void setRuaDigitada(String ruaDigitada) {
		RuaDigitada = ruaDigitada.toUpperCase();
	}

	public String getMostraInsereRuas() {
		if (microarea.getCodigo_microarea() == null
				|| microarea.getCodigo_microarea() == 0) {
			return "false";
		} else
			return "true";
	}

	public Microarea getArea() {
		return microarea;
	}

	public void setArea(Microarea area) {
		this.microarea = area;
	}

	public boolean getPanelAtivo() {
		Integer codigo = microarea.getCodigo_microarea();
		if (codigo == null || codigo == 0) {
			return false;
		} else
			return true;
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		MicroareaRN microareaRN = new MicroareaRN();

		Integer codigo = microarea.getCodigo_microarea();
		if (codigo == null || codigo == 0) {
			if (verificaUnique()) {
				context.addMessage(null, new FacesMessage(
						"Sucesso ao Inserir: " + microarea.getDescricao(), ""));

			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "
					+ microarea.getDescricao(), ""));

		}
		microarea.setRuasLista(listRuas);
		
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		this.microarea = new Microarea();
		if (usuario.getArea() != null) {
			microarea.setArea(usuario.getArea());
		}
		
		microareaRN.salvar(this.microarea);

		return "/restrito/lista_microarea";// this.destinoSalvar;
	}

	public boolean verificaUnique() {
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.idagente from microarea u where u.idagente= '"
						+ microarea.getAgente().getCodigo().toString() + "'");
		List micro = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (micro.isEmpty()) {

			a = true;

		} else {
			context.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Agente Ja Cadastrado para uma Microárea, Informe Outro Agente!",
							""));
			a = false;

		}

		return a;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((RuaDigitada == null) ? 0 : RuaDigitada.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((microarea == null) ? 0 : microarea.hashCode());
		result = prime * result
				+ ((microareaSelect == null) ? 0 : microareaSelect.hashCode());
		result = prime
				* result
				+ ((mostraInsereRuas == null) ? 0 : mostraInsereRuas.hashCode());
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
		MicroareaBean other = (MicroareaBean) obj;
		if (RuaDigitada == null) {
			if (other.RuaDigitada != null)
				return false;
		} else if (!RuaDigitada.equals(other.RuaDigitada))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (microarea == null) {
			if (other.microarea != null)
				return false;
		} else if (!microarea.equals(other.microarea))
			return false;
		if (microareaSelect == null) {
			if (other.microareaSelect != null)
				return false;
		} else if (!microareaSelect.equals(other.microareaSelect))
			return false;
		if (mostraInsereRuas == null) {
			if (other.mostraInsereRuas != null)
				return false;
		} else if (!mostraInsereRuas.equals(other.mostraInsereRuas))
			return false;
		return true;
	}

	public List<Microarea> getLista() {
		if (this.lista == null) {
			MicroareaRN microareaRN = new MicroareaRN();
			this.lista = microareaRN.listar();
		}
		return lista;
	}

	public void setLista(List<Microarea> lista) {
		this.lista = lista;
	}

	public boolean getDisableArea() {
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

	public String novo() {
		this.listRuas = null;

		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		this.microarea = new Microarea();
		if (usuario.getArea() != null) {
			microarea.setArea(usuario.getArea());
		}
		return "/restrito/microarea";
	}

	public String editar() {
		this.listRuas = microarea.retornaRuas();
		return "/restrito/microarea";
	}

	public String excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "
				+ microarea.getDescricao(), ""));
		MicroareaRN microareaRN = new MicroareaRN();
		microareaRN.excluir(this.microarea);
		this.lista = null;
		return null;
	}

	public Microarea getMicroarea() {
		return microarea;
	}

	public void setMicroarea(Microarea microarea) {
		this.microarea = microarea;
	}

	public List<SelectItem> getMicroareaSelect() {
		if (this.microareaSelect == null) {
			this.microareaSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			MicroareaRN microareaRN = new MicroareaRN();
			List<Microarea> categorias = microareaRN.listar();
			this.montaDadosSelectMicroArea(this.microareaSelect, categorias, "");
		}

		return microareaSelect;
	}

	public void setMicroareaSelect(List<SelectItem> microareaSelect) {
		this.microareaSelect = microareaSelect;
	}

	public void setMostraInsereRuas(String mostraInsereRuas) {
		this.mostraInsereRuas = mostraInsereRuas;
	}

	private void montaDadosSelectMicroArea(List<SelectItem> select,
			List<Microarea> microareas, String prefixo) {

		SelectItem item = null;
		if (microareas != null) {
			for (Microarea microarea : microareas) {
				item = new SelectItem(microarea, microarea.getDescricao()
						+ " | Agente: "
						+ microarea.getAgente().getNome().toString());
				item.setEscape(false);
				select.add(item);
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

}
