package scs.consultasMedicas;

import java.util.List;

import scs.util.DAOFactory;
import scs.visitasDomiciliares.VisitasDomiciliares;
import scs.visitasDomiciliares.VisitasDomiciliaresDAO;

public class ConsultasMedicasRN {
	
	private ConsultasMedicasDAO consulDAO;

	public ConsultasMedicasRN() {
		this.consulDAO = DAOFactory.criarConsultasMedicasDAO();
	}

	public ConsultasMedicas carregar(Integer codigo) {
		return this.consulDAO.carregar(codigo);
	}

	public void salvar(ConsultasMedicas cons) {
		Integer codigo = cons.getId();
		if (codigo == null || codigo == 0) {
			this.consulDAO.salvar(cons);
		} else {
			this.consulDAO.atualizar(cons);
		}
	}

	public void excluir(ConsultasMedicas cons) {
		this.consulDAO.excluir(cons);
	}

	public List<ConsultasMedicas> listar() {
		return this.consulDAO.listar();
	}

}
