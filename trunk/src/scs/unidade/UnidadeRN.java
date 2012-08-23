package scs.unidade;

import java.util.List;
import scs.util.DAOFactory;

public class UnidadeRN {

	private UnidadeDAO unidadeDAO;
	
	public UnidadeRN(){
		this.unidadeDAO = DAOFactory.criarUnidadeDAO();
	}
	
	public Unidade carregar(Integer codigo){
		return this.unidadeDAO.carregar(codigo);
	}
	
	public void salvar(Unidade unidade){
		Integer codigo = unidade.getCodigo_unidade();
		if(codigo==null || codigo == 0){
			this.unidadeDAO.salvar(unidade);
		} else {
			this.unidadeDAO.atualizar(unidade);
		}
	}
	
	public void excluir(Unidade unidade){
		this.unidadeDAO.excluir(unidade);
	}
	
	public List<Unidade> listar(){
		return this.unidadeDAO.listar();
	}
}
