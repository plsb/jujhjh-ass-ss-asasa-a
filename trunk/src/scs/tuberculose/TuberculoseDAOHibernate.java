package scs.tuberculose;

import java.util.List;

import org.hibernate.Session;

import scs.hipertensao.Hipertesao;

public class TuberculoseDAOHibernate implements TuberculoseDAO {
	
	private Session session;

	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Tuberculose tuberculose) {
		try {
			this.session.save(tuberculose);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Tuberculose tuberculose) {
		this.session.update(tuberculose);

	}

	@Override
	public void excluir(Tuberculose tuberculose) {
		this.session.delete(tuberculose);

	}

	@Override
	public Tuberculose carregar(Integer codigo) {
		return (Tuberculose) this.session.get(Tuberculose.class, codigo);
	}

	@Override
	public List<Tuberculose> listar() {
		return this.session.createCriteria(Tuberculose.class).list();
	}

}
