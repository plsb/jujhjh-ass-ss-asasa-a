package scs.visitas;

import java.util.List;

public interface VisitasDAO {
	
	public void salvar(Visitas visitasDAO);
	public void atualizar(Visitas visitasDAO);
	public void excluir(Visitas visitasDAO);
	public Visitas carregar(Integer codigo);
	public List<Visitas> listar();

}
