package scs.hipertensao;

import java.util.List;


public interface HipertensaoDAO {
	
	public void salvar(Hipertesao hipertensao);
	public void atualizar(Hipertesao hipertensao);
	public void excluir(Hipertesao hipertensao);
	public Hipertesao carregar(Integer codigo);
	public List<Hipertesao> listar();

}
