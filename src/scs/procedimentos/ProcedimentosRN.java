package scs.procedimentos;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class ProcedimentosRN {
	
	private ProcedimentosDAO procedimentosDAO;

	public ProcedimentosRN() {
		this.procedimentosDAO = DAOFactory.criarProcedimentosDAO();
	}

	public Procedimentos carregar(Integer codigo) {
		return this.procedimentosDAO.carregar(codigo);
	}

	public void salvar(Procedimentos procedimentos) {
		Integer codigo = procedimentos.getId();
		if (codigo == null || codigo == 0) {
			this.procedimentosDAO.salvar(procedimentos);
		} else {
			this.procedimentosDAO.atualizar(procedimentos);
		}
	}

	public void excluir(Procedimentos procedimentos) {
		this.procedimentosDAO.excluir(procedimentos);
	}

	public List<Procedimentos> listar() {
		return this.procedimentosDAO.listar();
	}


}
