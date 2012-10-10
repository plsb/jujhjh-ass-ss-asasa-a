package scs.agendamento;

import java.util.List;

import scs.area.Area;

public interface AgendamentoDAO {
	
	public void salvar(Agendamento agendamento);
	public void atualizar(Agendamento agendamento);
	public void excluir(Agendamento agendamento);
	public Agendamento carregar(Integer codigo);
	public List<Agendamento> listar();

}
