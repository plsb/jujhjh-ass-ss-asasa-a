package scs.agendamento;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import scs.area.Area;
import scs.profissional.Profissional;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class AgendamentoDAOHibernate implements AgendamentoDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Agendamento agendamento) {
		try {
			this.session.save(agendamento);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Agendamento agendamento) {
		this.session.update(agendamento);

	}

	@Override
	public void excluir(Agendamento agendamento) {
		this.session.delete(agendamento);

	}

	@Override
	public Agendamento carregar(Integer codigo) {
		return (Agendamento) this.session.get(Agendamento.class, codigo);
	}

	@Override
	public List<Agendamento> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(Agendamento.class);
		
		boolean result=false;
		for (int i = 0; i < usuario.getPermissao().size(); i++) {
			if(usuario.getPermissao().get(i).equals("ROLE_ADMIN")){
				result=true;
			}
		}
		if(result==false){
			if(usuario.getArea()!=null){
				crit.add(Restrictions.eq("area", usuario.getArea()));
			} else {
				crit.add(Restrictions.eq("area",null));
			}
		}
		return crit.list();
	}

}
