package scs.familiar;

import java.util.List;

import org.hibernate.Session;

public class FamiliarDAOHibernate implements FamiliarDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Familiar familiar) {
		try {
			this.session.save(familiar);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Familiar familiar) {
		this.session.update(familiar);

	}

	@Override
	public void excluir(Familiar familiar) {
		this.session.delete(familiar);

	}

	@Override
	public Familiar carregar(Integer codigo) {
		return (Familiar) this.session.get(Familiar.class, codigo);
	}

	@Override
	public List<Familiar> listar() {
		return this.session.createCriteria(Familiar.class).list();
	}

}
