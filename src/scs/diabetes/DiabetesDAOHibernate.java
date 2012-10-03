package scs.diabetes;

import java.util.List;

import org.hibernate.Session;

import scs.hanseniase.Hanseniase;

public class DiabetesDAOHibernate implements DiabetesDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Diabetes diabetes) {
		try {
			this.session.save(diabetes);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Diabetes diabetes) {
		this.session.update(diabetes);

	}

	@Override
	public void excluir(Diabetes diabetes) {
		this.session.delete(diabetes);

	}

	@Override
	public Diabetes carregar(Integer codigo) {
		return (Diabetes) this.session.get(Diabetes.class, codigo);
	}

	@Override
	public List<Diabetes> listar() {
		return this.session.createCriteria(Diabetes.class).list();
	}

}
