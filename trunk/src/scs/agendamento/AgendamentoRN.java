package scs.agendamento;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class AgendamentoRN {
	
	private AgendamentoDAO agendamentoDAO;

	public AgendamentoRN() {
		this.agendamentoDAO = DAOFactory.criarAgendamentoDAO();
	}

	public Agendamento carregar(Integer codigo) {
		return this.agendamentoDAO.carregar(codigo);
	}

	public void salvar(Agendamento agendamento) {
		Integer codigo = agendamento.getId();
		if (codigo == null || codigo == 0) {
			this.agendamentoDAO.salvar(agendamento);
		} else {
			this.agendamentoDAO.atualizar(agendamento);
		}
	}

	public void excluir(Agendamento agendamento) {
		this.agendamentoDAO.excluir(agendamento);
	}

	public List<Agendamento> listar() {
		return this.agendamentoDAO.listar();
	}

}
