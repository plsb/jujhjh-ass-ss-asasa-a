package scs.util;

import scs.area.AreaDAO;
import scs.area.AreaDAOHibernate;
import scs.bairro.BairroDAO;
import scs.bairro.BairroDAOHibernate;
import scs.equipe.EquipeDAO;
import scs.equipe.EquipeDAOHibernate;
import scs.microarea.MicroareaDAO;
import scs.microarea.MicroareaDAOHibernate;
import scs.municipio.MunicipioDAO;
import scs.municipio.MunicipioDAOHibernate;
import scs.rua.RuaDAO;
import scs.rua.RuaDAoHibernate;
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
	
	public static BairroDAO criarBairroDAO() {
		BairroDAOHibernate bairroDAO = new BairroDAOHibernate();
		bairroDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return bairroDAO;
	}
	
	public static RuaDAO criarRuaDAO() {
		RuaDAoHibernate ruaDAO = new RuaDAoHibernate();
		ruaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return ruaDAO;
	}

	public static AreaDAO criarAreaDAO() {
		AreaDAOHibernate areaDAO = new AreaDAOHibernate();
		areaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return areaDAO;
	}

	public static EquipeDAO criarEquipeDAO() {		
		EquipeDAOHibernate equipeDAO = new EquipeDAOHibernate();
		equipeDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return equipeDAO;		
	}
	
	public static MicroareaDAO criarMicroareaDAO() {		
		MicroareaDAOHibernate microareaDAO = new MicroareaDAOHibernate();
		microareaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return microareaDAO;		
	}
	
}
