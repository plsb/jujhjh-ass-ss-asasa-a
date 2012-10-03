package scs.familiar;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class FamiliarRN {
	
	private FamiliarDAO familiarDAO;

	public FamiliarRN() {
		this.familiarDAO = DAOFactory.criarFamiliarDAO();
	}

	public Familiar carregar(Integer codigo) {
		return this.familiarDAO.carregar(codigo);
	}

	public void salvar(Familiar familiar) {
		Integer codigo = familiar.getId();
		if (codigo == null || codigo==0) {
			this.familiarDAO.salvar(familiar);
		} else {
			this.familiarDAO.atualizar(familiar);
		}
	}

	public void excluir(Familiar familiar) {
		this.familiarDAO.excluir(familiar);
	}

	public List<Familiar> listar() {
		return this.familiarDAO.listar();
	}

}
