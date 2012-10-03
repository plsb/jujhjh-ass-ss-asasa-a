package scs.vacinas;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;

public class VacinasRN {

	private VacinasDAO vacinasDAO;

	public VacinasRN() {
		this.vacinasDAO = DAOFactory.criarVacinasDAO();
	}

	public Vacinas carregar(Integer codigo) {
		return this.vacinasDAO.carregar(codigo);
	}

	public void salvar(Vacinas vacinas) {
		Integer codigo = vacinas.getId();
		if (codigo == null || codigo == 0) {
			this.vacinasDAO.salvar(vacinas);
		} else {
			this.vacinasDAO.atualizar(vacinas);
		}
	}

	public void excluir(Vacinas vacinas) {
		this.vacinasDAO.excluir(vacinas);
	}

	public List<Vacinas> listar() {
		return this.vacinasDAO.listar();
	}


}
