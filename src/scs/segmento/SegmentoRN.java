package scs.segmento;

import java.util.List;

import scs.unidade.Unidade;
import scs.util.DAOFactory;

public class SegmentoRN {
	
	private SegmentoDAO segmentoDAO;
	
	public SegmentoRN(){
		this.segmentoDAO = DAOFactory.criarSegmentoDAO();
	}
	
	public Segmento carregar(Integer codigo){
		return this.segmentoDAO.carregar(codigo);
	}
	
	public void salvar(Segmento segmento){
		Integer codigo = segmento.getCodigo_segmento();
		if(codigo==null || codigo == 0){
			this.segmentoDAO.salvar(segmento);
		} else {
			this.segmentoDAO.atualizar(segmento);
		}
	}
	
	public void excluir(Segmento segmento){
		this.segmentoDAO.excluir(segmento);
	}
	
	public List<Segmento> listar(){
		return this.segmentoDAO.listar();
	}

}
