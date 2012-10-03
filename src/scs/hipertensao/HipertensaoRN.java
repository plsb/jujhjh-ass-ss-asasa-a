package scs.hipertensao;

import java.util.List;

import scs.diabetes.Diabetes;
import scs.diabetes.DiabetesDAO;
import scs.util.DAOFactory;

public class HipertensaoRN {

	private HipertensaoDAO hipertensaoDAO;

	public HipertensaoRN() {
		this.hipertensaoDAO = DAOFactory.criarHipertensaoDAO();
	}

	public Hipertesao carregar(Integer codigo) {
		return this.hipertensaoDAO.carregar(codigo);
	}

	public void salvar(Hipertesao hipertesao) {
		Integer codigo = hipertesao.getId();
		if (codigo == null || codigo == 0) {
			this.hipertensaoDAO.salvar(hipertesao);
		} else {
			this.hipertensaoDAO.atualizar(hipertesao);
		}
	}

	public void excluir(Hipertesao hipertesao) {
		this.hipertensaoDAO.excluir(hipertesao);
	}

	public List<Hipertesao> listar() {
		return this.hipertensaoDAO.listar();
	}
	
}
