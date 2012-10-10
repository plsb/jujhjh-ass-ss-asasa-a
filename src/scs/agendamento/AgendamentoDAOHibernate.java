package scs.agendamento;

import java.util.List;

import org.hibernate.Session;

import scs.area.Area;

public class AgendamentoDAOHibernate implements AgendamentoDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Agendamento agendamento) {
		try {
			this.session.save(agendamento);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Agendamento agendamento) {
		this.session.update(agendamento);

	}

	@Override
	public void excluir(Agendamento agendamento) {
		this.session.delete(agendamento);

	}

	@Override
	public Agendamento carregar(Integer codigo) {
		return (Agendamento) this.session.get(Agendamento.class, codigo);
	}

	@Override
	public List<Agendamento> listar() {
		return this.session.createCriteria(Agendamento.class).list();
	}

}
