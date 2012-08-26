package scs.municipio;

import java.util.List;

import scs.segmento.Segmento;

public interface MunicipioDAO {

	public void salvar(Municipio municipio);
	public List<Municipio> listar();
	
}