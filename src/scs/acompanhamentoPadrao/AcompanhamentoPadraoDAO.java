package scs.acompanhamentoPadrao;

import java.util.List;


public interface AcompanhamentoPadraoDAO {

	public void salvar(AcompanhamentoPadrao acompPadrao);
	public void atualizar(AcompanhamentoPadrao acompPadrao);
	public void excluir(AcompanhamentoPadrao acompPadrao);
	public AcompanhamentoPadrao carregar(Integer codigo);
	public List<AcompanhamentoPadrao> listar();
	
}
