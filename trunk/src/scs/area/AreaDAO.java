package scs.area;

import java.util.List;
import scs.area.Area;

public interface AreaDAO {

	public void salvar(Area area);
	public List<Area> listar();
	
}
