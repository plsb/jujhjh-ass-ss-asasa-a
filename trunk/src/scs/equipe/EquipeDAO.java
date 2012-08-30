package scs.equipe;

import java.util.List;

import scs.equipe.Equipe;

public interface EquipeDAO {

	public void salvar(Equipe equipe);
	public List<Equipe> listar();
	
}
