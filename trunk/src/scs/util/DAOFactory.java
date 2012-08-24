package scs.util;

import scs.municipio.MunicipioDAO;
import scs.municipio.MunicipioDAOHibernate;
import scs.segmento.SegmentoDAO;
import scs.segmento.SegmentoDAOHibernate;
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
	
	public static SegmentoDAO criarSegmentoDAO(){
		SegmentoDAOHibernate segmentoDAO = new SegmentoDAOHibernate();
		segmentoDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return segmentoDAO;
	}
	
	public static MunicipioDAO criarMunicipioDAO() {
		MunicipioDAOHibernate municipioDAO = new MunicipioDAOHibernate();
		municipioDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return municipioDAO;
	}
	
}
