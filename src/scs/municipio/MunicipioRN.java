package scs.municipio;

import scs.util.DAOFactory;

public class MunicipioRN {

	private MunicipioDAO municipioDAO;

	public MunicipioRN() {
		this.municipioDAO = DAOFactory.criarMunicipioDAO();
	}

	public void salvar(Municipio municipio) {
		this.municipioDAO.salvar(municipio);
	}

}