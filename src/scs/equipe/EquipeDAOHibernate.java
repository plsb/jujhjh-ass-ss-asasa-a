package scs.equipe;

import java.util.List;

import org.hibernate.Session;

public class EquipeDAOHibernate implements EquipeDAO {

	private Session session;

	public void setSesson(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Equipe equipe) {
		try {
			this.session.save(equipe);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Equipe> listar() {
		return this.session.createCriteria(Equipe.class).list();
	}

	
}
