package scs.tipoAtendimentoMedicoEnfermeiro;

import java.util.List;

import scs.solicExamesComplementares.SolicExamesComplem;
import scs.solicExamesComplementares.SolicExamesComplemDAO;
import scs.util.DAOFactory;

public class TipoAtendimentoMedicoEnfermeiroRN {
	
	private TipoAtendimentoMedicoEnfermeiroDAO tipoAtendiMedicoDAO;

	public TipoAtendimentoMedicoEnfermeiroRN() {
		this.tipoAtendiMedicoDAO = DAOFactory.criarTipoAtendimentoMedicoEnfermeiroDAO();
	}

	public TipoAtendimentoMedicoEnfermeiro carregar(Integer codigo) {
		return this.tipoAtendiMedicoDAO.carregar(codigo);
	}

	public void salvar(TipoAtendimentoMedicoEnfermeiro tpAtend) {
		Integer codigo = tpAtend.getId();
		if (codigo == null || codigo == 0) {
			this.tipoAtendiMedicoDAO.salvar(tpAtend);
		} else {
			this.tipoAtendiMedicoDAO.atualizar(tpAtend);
		}
	}

	public void excluir(TipoAtendimentoMedicoEnfermeiro tpAtend) {
		this.tipoAtendiMedicoDAO.excluir(tpAtend);
	}

	public List<TipoAtendimentoMedicoEnfermeiro> listar() {
		return this.tipoAtendiMedicoDAO.listar();
	}


}
