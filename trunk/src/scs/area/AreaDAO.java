package scs.area;

import java.util.List;
import scs.area.Area;
import scs.bairro.Bairro;

public interface AreaDAO {

	public void salvar(Area area);
	public void atualizar(Area area);
	public void excluir(Area area);
	public Area carregar(Integer codigo);
	public List<Area> listar();
	
}
