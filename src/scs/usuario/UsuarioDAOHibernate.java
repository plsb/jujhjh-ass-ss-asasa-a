package scs.usuario;

import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;

import scs.util.HibernateUtil;

public class UsuarioDAOHibernate implements UsuarioDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	public void salvar(Usuario usuario) {
		try {
			this.session.save(usuario);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		

	}

	public void atualizar(Usuario usuario) {
		if(usuario.getPermissao()==null||usuario.getPermissao().size()==0){
			Usuario usuarioPermissao = this.carregar(usuario.getCodigo());
			usuario.setPermissao(usuarioPermissao.getPermissao());
			this.session.evict(usuarioPermissao);
		}
		this.session.update(usuario);
	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);

	}

	public Usuario carregar(Integer codigo) {
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	public Usuario buscarPorLogin(String login) {
		Query consulta = this.session.createQuery("From Usuario u where u.login='"+login+"'");
		return (Usuario) consulta.list().get(0);
	}

	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}
	

}
