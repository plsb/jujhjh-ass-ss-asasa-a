package scs.residencia;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class ResidenciaRN {
	
	private ResidenciaDAO residenciaDAO;

	public ResidenciaRN() {
		this.residenciaDAO = DAOFactory.criarResidenciaDAO();
	}

	public Residencia carregar(Integer codigo) {
		return this.residenciaDAO.carregar(codigo);
	}

	public void salvar(Residencia residencia) {
		Integer codigo = residencia.getId();
		if (codigo == null || codigo == 0) {
			this.residenciaDAO.salvar(residencia);
		} else {
			this.residenciaDAO.atualizar(residencia);
		}
	}

	public void excluir(Residencia residencia) {
		this.residenciaDAO.excluir(residencia);
	}

	public List<Residencia> listar() {
		return this.residenciaDAO.listar();
	}


}
