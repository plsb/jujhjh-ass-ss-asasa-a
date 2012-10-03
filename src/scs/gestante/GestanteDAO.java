package scs.gestante;

import java.util.List;

import scs.diabetes.Diabetes;

public interface GestanteDAO {
	
	public void salvar(Gestante gestante);
	public void atualizar(Gestante gestante);
	public void excluir(Gestante gestante);
	public Gestante carregar(Integer codigo);
	public List<Gestante> listar();
}
