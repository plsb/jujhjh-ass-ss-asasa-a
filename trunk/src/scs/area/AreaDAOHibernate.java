package scs.area;

import java.util.List;

import org.hibernate.Session;

import scs.municipio.Municipio;

public class AreaDAOHibernate implements AreaDAO{

	private Session session;

	public void setSesson(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Area area) {
		try {
			this.session.save(area);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Area> listar() {
		return this.session.createCriteria(Municipio.class).list();
	}

}
