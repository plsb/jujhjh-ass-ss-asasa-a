package scs.acompcrianca;

import java.util.List;

import scs.agendamento.Agendamento;

public interface AcompCriancaDAO {
	
	public void salvar(AcompCrianca acompcrianca);
	public void atualizar(AcompCrianca acompcrianca);
	public void excluir(AcompCrianca acompcrianca);
	public AcompCrianca carregar(Integer codigo);
	public List<AcompCrianca> listar();

}
