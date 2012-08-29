package scs.rua;

import java.util.List;

import scs.bairro.Bairro;

public interface RuaDAO {
	
	public void salvar(Rua rua);
	public void atualizar(Rua rua);
	public void excluir(Rua rua);
	public Rua carregar(Integer codigo);
	public List<Rua> listar();

}
