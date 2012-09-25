package scs.residencia;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class ResidenciaDAOHibernate implements ResidenciaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Residencia residencia) {
		try {
			this.session.save(residencia);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Residencia residencia) {
		this.session.update(residencia);
	}

	@Override
	public void excluir(Residencia residencia) {
		this.session.delete(residencia);

	}

	@Override
	public Residencia carregar(Integer codigo) {
		return (Residencia) this.session.get(Residencia.class, codigo);
	}

	@Override
	public List<Residencia> listar() {
		return this.session.createCriteria(Residencia.class).list();
	}

}
