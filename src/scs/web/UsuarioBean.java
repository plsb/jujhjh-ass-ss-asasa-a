package scs.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioDAO;
import scs.usuario.UsuarioDAOHibernate;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	private List<SelectItem> coordenadorSelect;
	private List<SelectItem> agenteSelect;

	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		java.util.List<String> permissoes = this.usuario.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String novo() {
		this.destinoSalvar = "usuarioSuceso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/restrito/funcionarios";// "usuario";
	}

	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		return "/restrito/funcionarios";
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			this.coordenadorSelect = null;
			return null;

		}
		if (((usuario.getCpf() != null) || (usuario.getCpf() != ""))) {
			if (verificaUnique()){
				if (validaCPF(usuario.getCpf()) == false) {
					FacesMessage facesMessage = new FacesMessage(
							"O CPF é Inválido!");
					context.addMessage(null, facesMessage);
					this.coordenadorSelect = null;
					return null;
				}
			} else {
				return "";
			}

		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		return "/admin/principal";// this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
		//Verifica se CPF ja foi cadastrado		
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.codigo from funcionario u where u.nome='"+usuario.getNome()+"'");
		List ar = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (ar.isEmpty()) {					
			
			a= true;
			query = session
					.createSQLQuery("select u.codigo from funcionario u where u.cpf='"+usuario.getCpf()+"'");
			ar.clear();
			ar = query.list();
			// query.setParameter("idfunc", codigo).uniqueResult();
			if (ar.isEmpty()) {					
				
				a= true;
				query = session
						.createSQLQuery("select u.codigo from funcionario u where u.login='"+usuario.getLogin()+"'");
				ar.clear();
				ar = query.list();
				// query.setParameter("idfunc", codigo).uniqueResult();
				if (ar.isEmpty()) {	
					a= true;
				} else {
					context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Já Cadastrado!", ""));
					a= false;
				}
			} else {
				context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"CPF Já Cadastrado!", ""));
				a= false;
			}
			
			
		} else {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Funcionário Já Cadastrado!", ""));
			a= false;
		
		}
		
		return a;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		this.coordenadorSelect = null;
		return null;
	}

	public String ativar() {
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmarSenha == null) ? 0 : confirmarSenha.hashCode());
		result = prime
				* result
				+ ((coordenadorSelect == null) ? 0 : coordenadorSelect
						.hashCode());
		result = prime * result
				+ ((destinoSalvar == null) ? 0 : destinoSalvar.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		UsuarioBean other = (UsuarioBean) obj;
		if (confirmarSenha == null) {
			if (other.confirmarSenha != null)
				return false;
		} else if (!confirmarSenha.equals(other.confirmarSenha))
			return false;
		if (coordenadorSelect == null) {
			if (other.coordenadorSelect != null)
				return false;
		} else if (!coordenadorSelect.equals(other.coordenadorSelect))
			return false;
		if (destinoSalvar == null) {
			if (other.destinoSalvar != null)
				return false;
		} else if (!destinoSalvar.equals(other.destinoSalvar))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	private static boolean validaCPF(String cpf) {
		cpf = (cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11) + cpf
				.substring(12, 14));
		if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
			return false;

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { // CPF não possui somente números
			return false;
		}

		if (!calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11)))
			return false;

		return true;
	}

	/**
	 * 
	 * @param cpf
	 *            String valor a ser testado
	 * @return boolean indicando se o usuário entrou com um CPF padrão
	 */
	private static boolean isCPFPadrao(String cpf) {
		if (cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999")) {

			return true;
		}

		return false;
	}

	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public void setCoordenadorSelect(List<SelectItem> coordenadorSelect) {
		this.coordenadorSelect = coordenadorSelect;
	}

	public List<SelectItem> getCoordenadorSelect() {
		if (this.coordenadorSelect == null) {
			this.coordenadorSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			UsuarioRN usuarioRN = new UsuarioRN();
			List<Usuario> categorias = usuarioRN.listar();
			this.montaDadosSelectCoordenador(this.coordenadorSelect,
					categorias, "");
		}
		return coordenadorSelect;
	}

	private void montaDadosSelectCoordenador(List<SelectItem> select,
			List<Usuario> usuarios, String prefixo) {

		SelectItem item = null;
		if (usuarios != null) {
			for (Usuario usuario : usuarios) {
				if (usuario.getTipofuncionario().equalsIgnoreCase("C")) {
					item = new SelectItem(usuario, prefixo + usuario.getNome());
					item.setEscape(false);
					select.add(item);
				}
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

	public List<SelectItem> getAgenteSelect() {
		if (this.agenteSelect == null) {
			this.agenteSelect = new ArrayList<SelectItem>();
			// ContextoBean contextoBean =
			// scs.util.ContextoUtil.getContextoBean();

			UsuarioRN usuarioRN = new UsuarioRN();
			List<Usuario> categorias = usuarioRN.listar();
			this.montaDadosSelectAgente(this.agenteSelect, categorias, "");
		}
		return agenteSelect;
	}

	private void montaDadosSelectAgente(List<SelectItem> select,
			List<Usuario> usuarios, String prefixo) {

		SelectItem item = null;
		if (usuarios != null) {
			for (Usuario usuario : usuarios) {
				if (usuario.getTipofuncionario().equalsIgnoreCase("A")) {
					item = new SelectItem(usuario, prefixo + usuario.getNome());
					item.setEscape(false);
					select.add(item);
				}
				// this.montaDadosSelect(select, usuario.getNome(), prefixo +
				// "&nbsp;&nbsp;");
			}
		}
	}

}
