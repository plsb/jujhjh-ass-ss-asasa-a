package scs.hipertensao;

import java.util.List;

import org.hibernate.Session;

import scs.diabetes.Diabetes;

public class HipertensaoDAOHibernate implements HipertensaoDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Hipertesao hipertensao) {
		try {
			this.session.save(hipertensao);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}


	}

	@Override
	public void atualizar(Hipertesao hipertensao) {
		this.session.update(hipertensao);

	}

	@Override
	public void excluir(Hipertesao hipertensao) {
		this.session.delete(hipertensao);

	}

	@Override
	public Hipertesao carregar(Integer codigo) {
		return (Hipertesao) this.session.get(Hipertesao.class, codigo);
	}

	@Override
	public List<Hipertesao> listar() {
		return this.session.createCriteria(Hipertesao.class).list();
	}

}
