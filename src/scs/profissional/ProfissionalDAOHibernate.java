package scs.profissional;

import java.util.List;

import org.hibernate.Session;

import scs.bairro.Bairro;

public class ProfissionalDAOHibernate implements ProfissionalDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Profissional prof) {
		try {
			this.session.merge(prof);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Profissional prof) {
		this.session.merge(prof);

	}

	@Override
	public void excluir(Profissional prof) {
		this.session.delete(prof);

	}

	@Override
	public Profissional carregar(Integer codigo) {
		return (Profissional) this.session.get(Profissional.class, codigo);
	}

	@Override
	public List<Profissional> listar() {
		return this.session.createCriteria(Profissional.class).list();
	}

}
