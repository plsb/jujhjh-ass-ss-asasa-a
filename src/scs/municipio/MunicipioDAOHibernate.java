package scs.municipio;

import java.util.List;

import org.hibernate.Session;

import scs.segmento.Segmento;

public class MunicipioDAOHibernate implements MunicipioDAO {

	private Session session;

	public void setSesson(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Municipio municipio) {
		try {
			this.session.merge(municipio);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Municipio> listar() {
		return this.session.createCriteria(Municipio.class).list();
	}

}