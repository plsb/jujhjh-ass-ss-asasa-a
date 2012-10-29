package scs.encamMedicos;

import java.util.List;

import org.hibernate.Session;

public class EncaminhamentosMedicosDAOHibernate implements
		EncaminhamentosMedicosDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(EncaminhamentosMedicos encamMedicos) {
		try {
			this.session.save(encamMedicos);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(EncaminhamentosMedicos encamMedicos) {
		this.session.update(encamMedicos);

	}

	@Override
	public void excluir(EncaminhamentosMedicos encamMedicos) {
		this.session.delete(encamMedicos);

	}

	@Override
	public EncaminhamentosMedicos carregar(Integer codigo) {
		return (EncaminhamentosMedicos) this.session.get(EncaminhamentosMedicos.class, codigo);
	}

	@Override
	public List<EncaminhamentosMedicos> listar() {
		return this.session.createCriteria(EncaminhamentosMedicos.class).list();
	}

}
