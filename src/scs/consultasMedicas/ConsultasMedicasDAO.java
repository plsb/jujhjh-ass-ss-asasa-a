package scs.consultasMedicas;

import java.util.List;

import scs.visitasDomiciliares.VisitasDomiciliares;

public interface ConsultasMedicasDAO {
	
	public void salvar(ConsultasMedicas consDAO);
	public void atualizar(ConsultasMedicas consDAO);
	public void excluir(ConsultasMedicas consDAO);
	public ConsultasMedicas carregar(Integer codigo);
	public List<ConsultasMedicas> listar();

}
