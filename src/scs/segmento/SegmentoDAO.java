package scs.segmento;

import java.util.List;

public interface SegmentoDAO {
	
	public void salvar(Segmento segmento);
	public void atualizar(Segmento segmento);
	public void excluir(Segmento segmento);
	public Segmento carregar(Integer codigo);
	public List<Segmento> listar();

}
