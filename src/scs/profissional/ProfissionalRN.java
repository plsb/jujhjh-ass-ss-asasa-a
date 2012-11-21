package scs.profissional;

import java.util.List;

import scs.bairro.Bairro;
import scs.bairro.BairroDAO;
import scs.util.DAOFactory;

public class ProfissionalRN {
	
	private ProfissionalDAO profissionalDAO;
	
	public ProfissionalRN(){
		this.profissionalDAO = DAOFactory.criarProfissionalDAO();
	}
	
	public Profissional carregar(Integer codigo){
		return this.profissionalDAO.carregar(codigo);
	}
	
	public void salvar(Profissional prof){
		Integer codigo = prof.getId();
		if(codigo==null || codigo == 0){
			this.profissionalDAO.salvar(prof);
		} else {
			this.profissionalDAO.atualizar(prof);
		}
	}
	
	public void excluir(Profissional medico){
		this.profissionalDAO.excluir(medico);
	}
	
	public List<Profissional> listar(){
		return this.profissionalDAO.listar();
	}

}
