package scs.area;

import java.util.List;

import scs.area.Area;
import scs.area.AreaDAO;
import scs.util.DAOFactory;


public class AreaRN {

	private AreaDAO areaDAO;
	
	public AreaRN(){
		this.areaDAO = DAOFactory.criarAreaDAO();
	}
	
	public Area carregar(Integer codigo){
		return this.areaDAO.carregar(codigo);
	}
	
	public void salvar(Area area){
		Integer codigo = area.getCodigo_area();
		if(codigo==null || codigo == 0){
			this.areaDAO.salvar(area);
		} else {
			this.areaDAO.atualizar(area);
		}
	}
	
	public void excluir(Area area){
		this.areaDAO.excluir(area);
	}
	
	public List<Area> listar(){
		return this.areaDAO.listar();
	}

	
}
