package scs.visitas;

import java.util.List;

import scs.acompanhamentoPadrao.AcompanhamentoPadrao;
import scs.acompanhamentoPadrao.AcompanhamentoPadraoDAO;
import scs.util.DAOFactory;
import scs.vacinas.Vacinas;
import scs.vacinas.VacinasDAO;

public class VisitasRN {
	
	private VisitasDAO vaisitasDAO;

	public VisitasRN() {
		this.vaisitasDAO = DAOFactory.criarVisitasDAO();
	}

	public Visitas carregar(Integer codigo) {
		return this.vaisitasDAO.carregar(codigo);
	}

	public void salvar(Visitas visi) {
		Integer codigo = visi.getId();
		if (codigo == null || codigo == 0) {
			this.vaisitasDAO.salvar(visi);
		} else {
			this.vaisitasDAO.atualizar(visi);
		}
	}

	public void excluir(Visitas visi) {
		this.vaisitasDAO.excluir(visi);
	}

	public List<Visitas> listar() {
		return this.vaisitasDAO.listar();
	}

}
