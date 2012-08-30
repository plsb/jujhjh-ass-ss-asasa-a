package scs.area;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;


public class AreaRN {

	private AreaDAO areaDAO;

	public AreaRN() {
		this.areaDAO = DAOFactory.criarAreaDAO();
	}

	public void salvar(Area area) {
		this.areaDAO.salvar(area);
	}
	
	public List<Area> listar(){
		return this.areaDAO.listar();
	}
	
}
