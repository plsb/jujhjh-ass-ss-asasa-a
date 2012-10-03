package scs.vacinas;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class VacinasDAOHibernate implements VacinasDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Vacinas vacinas) {
		try {
			this.session.save(vacinas);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}


	}

	@Override
	public void atualizar(Vacinas vacinas) {
		this.session.update(vacinas);

	}

	@Override
	public void excluir(Vacinas vacinas) {
		this.session.delete(vacinas);

	}

	@Override
	public Vacinas carregar(Integer codigo) {
		return (Vacinas) this.session.get(Vacinas.class, codigo);
	}

	@Override
	public List<Vacinas> listar() {
		return this.session.createCriteria(Vacinas.class).list();
	}

}
