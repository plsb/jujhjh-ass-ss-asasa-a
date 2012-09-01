package scs.microarea;

import java.util.List;

import scs.util.DAOFactory;

public class MicroareaRN {
	
	private MicroareaDAO microareaDAO;
	
	public MicroareaRN(){
		this.microareaDAO = DAOFactory.criarMicroareaDAO();
	}
	
	public Microarea carregar(Integer codigo){
		return this.microareaDAO.carregar(codigo);
	}
	
	public void salvar(Microarea microarea){
		Integer codigo = microarea.getCodigo_microarea();
		if(codigo==null || codigo == 0){
			this.microareaDAO.salvar(microarea);
		} else {
			this.microareaDAO.atualizar(microarea);
		}
	}
	
	public void excluir(Microarea microarea){
		this.microareaDAO.excluir(microarea);
	}
	
	public List<Microarea> listar(){
		return this.microareaDAO.listar();
	}

}
