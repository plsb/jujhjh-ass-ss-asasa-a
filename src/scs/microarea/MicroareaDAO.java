package scs.microarea;

import java.util.List;

import scs.area.Area;

public interface MicroareaDAO {
	public void salvar(Microarea microarea);
	public void atualizar(Microarea microarea);
	public void excluir(Microarea microarea);
	public Microarea carregar(Integer microarea);
	public List<Microarea> listar();
}
