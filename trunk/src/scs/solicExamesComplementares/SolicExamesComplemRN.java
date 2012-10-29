package scs.solicExamesComplementares;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class SolicExamesComplemRN {

	private SolicExamesComplemDAO soliExamCompDAO;

	public SolicExamesComplemRN() {
		this.soliExamCompDAO = DAOFactory.criarSolicExamComplDAO();
	}

	public SolicExamesComplem carregar(Integer codigo) {
		return this.soliExamCompDAO.carregar(codigo);
	}

	public void salvar(SolicExamesComplem solExam) {
		Integer codigo = solExam.getId();
		if (codigo == null || codigo == 0) {
			this.soliExamCompDAO.salvar(solExam);
		} else {
			this.soliExamCompDAO.atualizar(solExam);
		}
	}

	public void excluir(SolicExamesComplem solExam) {
		this.soliExamCompDAO.excluir(solExam);
	}

	public List<SolicExamesComplem> listar() {
		return this.soliExamCompDAO.listar();
	}

}
