package scs.tuberculose;

import java.util.List;

import scs.hipertensao.HipertensaoDAO;
import scs.hipertensao.Hipertesao;
import scs.util.DAOFactory;

public class TuberculoseRN {
	
	private TuberculoseDAO tuberculoseDAO;

	public TuberculoseRN() {
		this.tuberculoseDAO = DAOFactory.criarTuberculoseDAO();
	}

	public Tuberculose carregar(Integer codigo) {
		return this.tuberculoseDAO.carregar(codigo);
	}

	public void salvar(Tuberculose tuberculose) {
		Integer codigo = tuberculose.getId();
		if (codigo == null || codigo == 0) {
			this.tuberculoseDAO.salvar(tuberculose);
		} else {
			this.tuberculoseDAO.atualizar(tuberculose);
		}
	}

	public void excluir(Tuberculose tuberculose) {
		this.tuberculoseDAO.excluir(tuberculose);
	}

	public List<Tuberculose> listar() {
		return this.tuberculoseDAO.listar();
	}

}
