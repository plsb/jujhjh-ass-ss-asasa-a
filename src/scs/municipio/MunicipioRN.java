package scs.municipio;

import java.util.List;

import scs.segmento.Segmento;
import scs.util.DAOFactory;

public class MunicipioRN {

	private MunicipioDAO municipioDAO;

	public MunicipioRN() {
		this.municipioDAO = DAOFactory.criarMunicipioDAO();
	}

	public void salvar(Municipio municipio) {
		this.municipioDAO.salvar(municipio);
	}
	
	public List<Municipio> listar(){
		return this.municipioDAO.listar();
	}
}