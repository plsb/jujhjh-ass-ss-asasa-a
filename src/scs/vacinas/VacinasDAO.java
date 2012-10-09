package scs.vacinas;

import java.util.List;

import scs.rua.Rua;

public interface VacinasDAO {
	
	public void salvar(Vacinas vacinas);
	public void atualizar(Vacinas vacinas);
	public void excluir(Vacinas vacinas);
	public Vacinas carregar(Integer codigo);
	public List<Vacinas> listar();

}
