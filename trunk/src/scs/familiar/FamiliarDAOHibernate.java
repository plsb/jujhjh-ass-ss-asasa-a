package scs.familiar;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class FamiliarDAOHibernate implements FamiliarDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Familiar familiar) {
		try {
			this.session.save(familiar);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Familiar familiar) {
		this.session.update(familiar);

	}

	@Override
	public void excluir(Familiar familiar) {
		this.session.delete(familiar);

	}

	@Override
	public Familiar carregar(Integer codigo) {
		return (Familiar) this.session.get(Familiar.class, codigo);
	}

	@Override
	public List<Familiar> listar() {
		Criteria crit = session.createCriteria(Familiar.class);
		crit.add(Restrictions.eq("mudou_se", false));
		return crit.list();
	}
	
	@Override
	public List<Familiar> listarStandBy() {
		Criteria crit = session.createCriteria(Familiar.class);
		crit.add(Restrictions.eq("mudou_se", true));
		return crit.list();
	}

}
