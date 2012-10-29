package scs.encamMedicos;

import java.util.List;

import scs.area.Area;

public interface EncaminhamentosMedicosDAO {

	public void salvar(EncaminhamentosMedicos encamMedicos);
	public void atualizar(EncaminhamentosMedicos encamMedicos);
	public void excluir(EncaminhamentosMedicos encamMedicos);
	public EncaminhamentosMedicos carregar(Integer codigo);
	public List<EncaminhamentosMedicos> listar();
}
