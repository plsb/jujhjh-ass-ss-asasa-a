package scs.familiar;

import java.util.List;

public interface FamiliarDAO {
	
	public void salvar(Familiar familiar);
	public void atualizar(Familiar familiar);
	public void excluir(Familiar familiar);
	public Familiar carregar(Integer codigo);
	public List<Familiar> listar();
}
