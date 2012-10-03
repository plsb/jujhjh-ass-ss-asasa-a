package scs.hanseniase;

import java.util.List;

import scs.area.Area;

public interface HanseniaseDAO {
	
	public void salvar(Hanseniase hanseniase);
	public void atualizar(Hanseniase hanseniase);
	public void excluir(Hanseniase hanseniase);
	public Hanseniase carregar(Integer codigo);
	public List<Hanseniase> listar();

}
