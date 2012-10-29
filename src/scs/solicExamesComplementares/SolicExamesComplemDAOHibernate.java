package scs.solicExamesComplementares;

import java.util.List;

import org.hibernate.Session;


public class SolicExamesComplemDAOHibernate implements SolicExamesComplemDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(SolicExamesComplem solExameCompl) {
		try {
			this.session.save(solExameCompl);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(SolicExamesComplem solExameCompl) {
		this.session.update(solExameCompl);

	}

	@Override
	public void excluir(SolicExamesComplem solExameCompl) {
		this.session.delete(solExameCompl);

	}

	@Override
	public SolicExamesComplem carregar(Integer codigo) {
		return (SolicExamesComplem) this.session.get(SolicExamesComplem.class, codigo);
	}

	@Override
	public List<SolicExamesComplem> listar() {
		return this.session.createCriteria(SolicExamesComplem.class).list();
	}

}
