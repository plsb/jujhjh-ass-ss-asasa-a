package scs.gestante;

import java.util.List;

import org.hibernate.Session;

import scs.diabetes.Diabetes;

public class GestanteDAOHibernate implements GestanteDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Gestante gestante) {
		try {
			this.session.save(gestante);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Gestante gestante) {
		this.session.update(gestante);

	}

	@Override
	public void excluir(Gestante gestante) {
		this.session.delete(gestante);

	}

	@Override
	public Gestante carregar(Integer codigo) {
		return (Gestante) this.session.get(Gestante.class, codigo);
	}

	@Override
	public List<Gestante> listar() {
		return this.session.createCriteria(Gestante.class).list();
	}

}
