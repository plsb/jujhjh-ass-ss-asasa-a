package scs.gestante;

import java.util.List;

import scs.diabetes.Diabetes;
import scs.diabetes.DiabetesDAO;
import scs.util.DAOFactory;

public class GestanteRN {
	
	private GestanteDAO gestanteDAO;

	public GestanteRN() {
		this.gestanteDAO = DAOFactory.criarGestanteDAO();
	}

	public Gestante carregar(Integer codigo) {
		return this.gestanteDAO.carregar(codigo);
	}

	public void salvar(Gestante gestante) {
		Integer codigo = gestante.getId();
		if (codigo == null || codigo == 0) {
			this.gestanteDAO.salvar(gestante);
		} else {
			this.gestanteDAO.atualizar(gestante);
		}
	}

	public void excluir(Gestante gestante) {
		this.gestanteDAO.excluir(gestante);
	}

	public List<Gestante> listar() {
		return this.gestanteDAO.listar();
	}

}
