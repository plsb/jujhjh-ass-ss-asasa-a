package scs.acompcrianca;

import java.util.List;

import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoDAO;
import scs.util.DAOFactory;

public class AcompCriancaRN {
	
	private AcompCriancaDAO acompCricancaDAO;

	public AcompCriancaRN() {
		this.acompCricancaDAO = DAOFactory.criarAcompCriancaDAO();
	}

	public AcompCrianca carregar(Integer codigo) {
		return this.acompCricancaDAO.carregar(codigo);
	}

	public void salvar(AcompCrianca acompcrianca) {
		Integer codigo = acompcrianca.getId();
		if (codigo == null || codigo == 0) {
			this.acompCricancaDAO.salvar(acompcrianca);
		} else {
			this.acompCricancaDAO.atualizar(acompcrianca);
		}
	}

	public void excluir(AcompCrianca acompcrianca) {
		this.acompCricancaDAO.excluir(acompcrianca);
	}

	public List<AcompCrianca> listar() {
		return this.acompCricancaDAO.listar();
	}

}
