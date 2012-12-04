package scs.visitasDomiciliares;

import java.util.List;

import scs.visitas.Visitas;

public interface VisitasDomiciliaresDAO {
	
	public void salvar(VisitasDomiciliares visitasDAO);
	public void atualizar(VisitasDomiciliares visitasDAO);
	public void excluir(VisitasDomiciliares visitasDAO);
	public VisitasDomiciliares carregar(Integer codigo);
	public List<VisitasDomiciliares> listar();

}
