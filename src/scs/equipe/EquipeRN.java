package scs.equipe;

import java.util.List;

import scs.municipio.Municipio;
import scs.municipio.MunicipioDAO;
import scs.util.DAOFactory;

public class EquipeRN {

	private EquipeDAO equipeDAO;

	public EquipeRN() {
		this.equipeDAO = DAOFactory.criarEquipeDAO();
	}

	public void salvar(Equipe equipe) {
		this.equipeDAO.salvar(equipe);
	}
	
	public List<Equipe> listar(){
		return this.equipeDAO.listar();
	}
	
}
