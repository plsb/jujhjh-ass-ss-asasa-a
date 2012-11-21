package scs.profissional;

import java.util.List;

import scs.bairro.Bairro;

public interface ProfissionalDAO {
	
	public void salvar(Profissional prof);
	public void atualizar(Profissional prof);
	public void excluir(Profissional prof);
	public Profissional carregar(Integer codigo);
	public List<Profissional> listar();

}
