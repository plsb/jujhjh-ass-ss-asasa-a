package scs.rua;

import java.util.List;

import scs.bairro.Bairro;
import scs.bairro.BairroDAO;
import scs.util.DAOFactory;

public class RuaRN {
	
	private RuaDAO ruaDAO;
	
	public RuaRN(){
		this.ruaDAO = DAOFactory.criarRuaDAO();
	}
	
	public Rua carregar(Integer codigo){
		return this.ruaDAO.carregar(codigo);
	}
	
	public void salvar(Rua rua){
		Integer codigo = rua.getCodigo_rua();
		if(codigo==null || codigo == 0){
			this.ruaDAO.salvar(rua);
		} else {
			this.ruaDAO.atualizar(rua);
		}
	}
	
	public void excluir(Rua rua){
		this.ruaDAO.excluir(rua);
	}
	
	public List<Rua> listar(){
		return this.ruaDAO.listar();
	}

}
