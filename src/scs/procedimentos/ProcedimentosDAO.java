package scs.procedimentos;

import java.util.List;

public interface ProcedimentosDAO {
	
	public void salvar(Procedimentos procedimentos);
	public void atualizar(Procedimentos procedimentos);
	public void excluir(Procedimentos procedimentos);
	public Procedimentos carregar(Integer codigo);
	public List<Procedimentos> listar();

}
