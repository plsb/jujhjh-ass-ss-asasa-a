package scs.acompanhamentoPadrao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import scs.agendamento.Agendamento;

public class AcompanhamentoPadraoDAOHibernate implements
		AcompanhamentoPadraoDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(AcompanhamentoPadrao acompPadrao) {
		try {
			this.session.save(acompPadrao);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(AcompanhamentoPadrao acompPadrao) {
		this.session.update(acompPadrao);

	}

	@Override
	public void excluir(AcompanhamentoPadrao acompPadrao) {
		this.session.delete(acompPadrao);

	}

	@Override
	public AcompanhamentoPadrao carregar(Integer codigo) {
		return (AcompanhamentoPadrao) this.session.get(AcompanhamentoPadrao.class, codigo);
	}

	@Override
	public List<AcompanhamentoPadrao> listar() {
		Criteria crit = this.session.createCriteria(AcompanhamentoPadrao.class);
		//crit.add(Restrictions.eq("dtagendamento", null));
		return crit.list();
	}

}
