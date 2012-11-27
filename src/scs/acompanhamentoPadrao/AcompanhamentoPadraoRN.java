package scs.acompanhamentoPadrao;

import java.util.List;

import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoDAO;
import scs.util.DAOFactory;

public class AcompanhamentoPadraoRN {
	
	private AcompanhamentoPadraoDAO acompPadroDAO;

	public AcompanhamentoPadraoRN() {
		this.acompPadroDAO = DAOFactory.criarAcompanhamentoDAO();
	}

	public AcompanhamentoPadrao carregar(Integer codigo) {
		return this.acompPadroDAO.carregar(codigo);
	}

	public void salvar(AcompanhamentoPadrao acomp) {
		Integer codigo = acomp.getId();
		if (codigo == null || codigo == 0) {
			this.acompPadroDAO.salvar(acomp);
		} else {
			this.acompPadroDAO.atualizar(acomp);
		}
	}

	public void excluir(AcompanhamentoPadrao acomp) {
		this.acompPadroDAO.excluir(acomp);
	}

	public List<AcompanhamentoPadrao> listar() {
		return this.acompPadroDAO.listar();
	}

}
