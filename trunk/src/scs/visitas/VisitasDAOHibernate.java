package scs.visitas;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import scs.agendamento.Agendamento;

public class VisitasDAOHibernate implements VisitasDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Visitas visitasDAO) {
		try {
			this.session.save(visitasDAO);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Visitas visitasDAO) {
		this.session.update(visitasDAO);

	}

	@Override
	public void excluir(Visitas visitasDAO) {
		this.session.delete(visitasDAO);

	}

	@Override
	public Visitas carregar(Integer codigo) {
		return (Visitas) this.session.get(Visitas.class, codigo);
	}
	
	@Override
	public List<Visitas> listar() {
		Criteria crit = this.session.createCriteria(Visitas.class);
		//crit.add(Restrictions.eq("dtagendamento", null));
		return crit.list();
	}

}
