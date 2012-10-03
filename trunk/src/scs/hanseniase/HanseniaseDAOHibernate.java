package scs.hanseniase;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class HanseniaseDAOHibernate implements HanseniaseDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Hanseniase hanseniase) {
		try {
			this.session.save(hanseniase);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Hanseniase hanseniase) {
		this.session.update(hanseniase);

	}

	@Override
	public void excluir(Hanseniase hanseniase) {
		this.session.delete(hanseniase);

	}

	@Override
	public Hanseniase carregar(Integer codigo) {
		return (Hanseniase) this.session.get(Hanseniase.class, codigo);
	}

	@Override
	public List<Hanseniase> listar() {
		return this.session.createCriteria(Hanseniase.class).list();
	}

}
