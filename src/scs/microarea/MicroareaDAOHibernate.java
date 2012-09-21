package scs.microarea;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class MicroareaDAOHibernate implements MicroareaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Microarea microarea) {
		try {
			this.session.merge(microarea);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Microarea microarea) {
		this.session.merge(microarea);

	}

	@Override
	public void excluir(Microarea microarea) {
		this.session.delete(microarea);

	}

	@Override
	public Microarea carregar(Integer microarea) {
		return (Microarea) this.session.get(Microarea.class, microarea);
	}

	@Override
	public List<Microarea> listar() {
		return this.session.createCriteria(Microarea.class).list();
	}

}
