package scs.tipoAtendimentoMedicoEnfermeiro;

import java.util.List;

import scs.solicExamesComplementares.SolicExamesComplem;

public interface TipoAtendimentoMedicoEnfermeiroDAO {
	
	public void salvar(TipoAtendimentoMedicoEnfermeiro tpAtendMedico);
	public void atualizar(TipoAtendimentoMedicoEnfermeiro tpAtendMedico);
	public void excluir(TipoAtendimentoMedicoEnfermeiro tpAtendMedico);
	public TipoAtendimentoMedicoEnfermeiro carregar(Integer codigo);
	public List<TipoAtendimentoMedicoEnfermeiro> listar();

}
