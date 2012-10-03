package scs.diabetes;

import java.util.List;

import scs.hanseniase.Hanseniase;
import scs.hanseniase.HanseniaseDAO;
import scs.util.DAOFactory;

public class DiabetesRN {
	
	private DiabetesDAO diabetesDAO;

	public DiabetesRN() {
		this.diabetesDAO = DAOFactory.criarDiabetesDAO();
	}

	public Diabetes carregar(Integer codigo) {
		return this.diabetesDAO.carregar(codigo);
	}

	public void salvar(Diabetes diabetes) {
		Integer codigo = diabetes.getId();
		if (codigo == null || codigo == 0) {
			this.diabetesDAO.salvar(diabetes);
		} else {
			this.diabetesDAO.atualizar(diabetes);
		}
	}

	public void excluir(Diabetes diabetes) {
		this.diabetesDAO.excluir(diabetes);
	}

	public List<Diabetes> listar() {
		return this.diabetesDAO.listar();
	}

}
