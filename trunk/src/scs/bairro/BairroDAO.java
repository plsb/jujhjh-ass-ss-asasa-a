package scs.bairro;

import java.util.List;

import scs.segmento.Segmento;

public interface BairroDAO {
	
	public void salvar(Bairro bairro);
	public void atualizar(Bairro bairro);
	public void excluir(Bairro bairro);
	public Bairro carregar(Integer codigo);
	public List<Bairro> listar();
}
