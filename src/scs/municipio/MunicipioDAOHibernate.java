package scs.municipio;

import org.hibernate.Session;

public class MunicipioDAOHibernate implements MunicipioDAO {

	private Session session;

	public void setSesson(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Municipio municipio) {
		try {
			this.session.save(municipio);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}