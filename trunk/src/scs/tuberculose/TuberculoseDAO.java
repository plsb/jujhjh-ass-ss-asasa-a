package scs.tuberculose;

import java.util.List;

import scs.hipertensao.Hipertesao;

public interface TuberculoseDAO {
	
	public void salvar(Tuberculose tuberculose);
	public void atualizar(Tuberculose tuberculose);
	public void excluir(Tuberculose tuberculose);
	public Tuberculose carregar(Integer codigo);
	public List<Tuberculose> listar();

}
