package scs.solicExamesComplementares;

import java.util.List;

import scs.area.Area;

public interface SolicExamesComplemDAO {

	public void salvar(SolicExamesComplem area);
	public void atualizar(SolicExamesComplem area);
	public void excluir(SolicExamesComplem area);
	public SolicExamesComplem carregar(Integer codigo);
	public List<SolicExamesComplem> listar();
	
}
