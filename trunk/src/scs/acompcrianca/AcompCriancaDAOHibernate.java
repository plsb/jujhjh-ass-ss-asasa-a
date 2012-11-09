package scs.acompcrianca;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import scs.agendamento.Agendamento;

public class AcompCriancaDAOHibernate implements AcompCriancaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(AcompCrianca acompcrianca) {
		try {
			this.session.save(acompcrianca);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(AcompCrianca acompcrianca) {
		this.session.update(acompcrianca);

	}

	@Override
	public void excluir(AcompCrianca acompcrianca) {
		this.session.delete(acompcrianca);

	}

	@Override
	public AcompCrianca carregar(Integer codigo) {
		return (AcompCrianca) this.session.get(AcompCrianca.class, codigo);
	}

	@Override
	public List<AcompCrianca> listar() {
		Criteria crit = this.session.createCriteria(AcompCrianca.class);
		//crit.add(Restrictions.eq("dtagendamento", null));
		return crit.list();
	}

}
