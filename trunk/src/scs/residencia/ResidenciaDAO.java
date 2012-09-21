package scs.residencia;

import java.util.List;


public interface ResidenciaDAO {
	
	public void salvar(Residencia residencia);
	public void atualizar(Residencia residencia);
	public void excluir(Residencia residencia);
	public Residencia carregar(Integer codigo);
	public List<Residencia> listar();

}
