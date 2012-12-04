package scs.visitasDomiciliares;

import java.util.List;

import scs.util.DAOFactory;
import scs.visitas.Visitas;
import scs.visitas.VisitasDAO;

public class VisitasDomiciliaresRN {
	
	private VisitasDomiciliaresDAO visitasDAO;

	public VisitasDomiciliaresRN() {
		this.visitasDAO = DAOFactory.criarVisitasDomiciliaresDAO();
	}

	public VisitasDomiciliares carregar(Integer codigo) {
		return this.visitasDAO.carregar(codigo);
	}

	public void salvar(VisitasDomiciliares visi) {
		Integer codigo = visi.getId();
		if (codigo == null || codigo == 0) {
			this.visitasDAO.salvar(visi);
		} else {
			this.visitasDAO.atualizar(visi);
		}
	}

	public void excluir(VisitasDomiciliares visi) {
		this.visitasDAO.excluir(visi);
	}

	public List<VisitasDomiciliares> listar() {
		return this.visitasDAO.listar();
	}

}
