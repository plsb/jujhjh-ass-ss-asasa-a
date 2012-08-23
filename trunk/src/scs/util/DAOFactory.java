package scs.util;

import scs.unidade.UnidadeDAO;
import scs.unidade.UnidadeDAOHibernate;
import scs.usuario.UsuarioDAO;
import scs.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static UnidadeDAO criarUnidadeDAO(){
		UnidadeDAOHibernate unidadeDAO = new UnidadeDAOHibernate();
		unidadeDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return unidadeDAO;
	}
	
}
