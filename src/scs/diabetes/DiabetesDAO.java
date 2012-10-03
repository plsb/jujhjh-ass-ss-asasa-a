package scs.diabetes;

import java.util.List;

import scs.hanseniase.Hanseniase;

public interface DiabetesDAO {
	
	public void salvar(Diabetes diabetes);
	public void atualizar(Diabetes diabetes);
	public void excluir(Diabetes diabetes);
	public Diabetes carregar(Integer codigo);
	public List<Diabetes> listar();

}
