package scs.bairro;

import java.util.List;

import scs.segmento.Segmento;
import scs.segmento.SegmentoDAO;
import scs.unidade.Unidade;
import scs.util.DAOFactory;

public class BairroRN {

	private BairroDAO bairroDAO;
	
	public BairroRN(){
		this.bairroDAO = DAOFactory.criarBairroDAO();
	}
	
	public Bairro carregar(Integer codigo){
		return this.bairroDAO.carregar(codigo);
	}
	
	public void salvar(Bairro bairro){
		Integer codigo = bairro.getCodigo_bairro();
		if(codigo==null || codigo == 0){
			this.bairroDAO.salvar(bairro);
		} else {
			this.bairroDAO.atualizar(bairro);
		}
	}
	
	public void excluir(Bairro bairro){
		this.bairroDAO.excluir(bairro);
	}
	
	public List<Bairro> listar(){
		return this.bairroDAO.listar();
	}

}
