package scs.util;

import scs.usuario.UsuarioDAO;
import scs.usuario.UsuarioDAOHibernate;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
}
