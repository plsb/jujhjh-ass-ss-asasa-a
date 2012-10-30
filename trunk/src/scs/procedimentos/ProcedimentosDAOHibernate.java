package scs.procedimentos;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class ProcedimentosDAOHibernate implements ProcedimentosDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Procedimentos procedimentos) {
		try {
			this.session.save(procedimentos);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Procedimentos procedimentos) {
		this.session.update(procedimentos);

	}

	@Override
	public void excluir(Procedimentos procedimentos) {
		this.session.delete(procedimentos);

	}

	@Override
	public Procedimentos carregar(Integer codigo) {
		return (Procedimentos) this.session.get(Procedimentos.class, codigo);
	}

	@Override
	public List<Procedimentos> listar() {
		return this.session.createCriteria(Procedimentos.class).list();
	}

}
