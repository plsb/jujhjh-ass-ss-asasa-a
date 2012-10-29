package scs.encamMedicos;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class EncaminhamentosMedicosRN {
	
	private EncaminhamentosMedicosDAO encamDAO;

	public EncaminhamentosMedicosRN() {
		this.encamDAO = DAOFactory.criarEncaminhamentosMedicosDAO();
	}

	public EncaminhamentosMedicos carregar(Integer codigo) {
		return this.encamDAO.carregar(codigo);
	}

	public void salvar(EncaminhamentosMedicos encamMedi) {
		Integer codigo = encamMedi.getId();
		if (codigo == null || codigo == 0) {
			this.encamDAO.salvar(encamMedi);
		} else {
			this.encamDAO.atualizar(encamMedi);
		}
	}

	public void excluir(EncaminhamentosMedicos encamMedi) {
		this.encamDAO.excluir(encamMedi);
	}

	public List<EncaminhamentosMedicos> listar() {
		return this.encamDAO.listar();
	}

}
