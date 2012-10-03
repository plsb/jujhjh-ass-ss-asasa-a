package scs.hanseniase;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class HanseniaseRN {
	
	private HanseniaseDAO hanseniaseDAO;

	public HanseniaseRN() {
		this.hanseniaseDAO = DAOFactory.criarHanseniaseDAO();
	}

	public Hanseniase carregar(Integer codigo) {
		return this.hanseniaseDAO.carregar(codigo);
	}

	public void salvar(Hanseniase hanseniase) {
		Integer codigo = hanseniase.getId();
		if (codigo == null || codigo == 0) {
			this.hanseniaseDAO.salvar(hanseniase);
		} else {
			this.hanseniaseDAO.atualizar(hanseniase);
		}
	}

	public void excluir(Hanseniase hanseniase) {
		this.hanseniaseDAO.excluir(hanseniase);
	}

	public List<Hanseniase> listar() {
		return this.hanseniaseDAO.listar();
	}

}
