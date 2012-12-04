package scs.microarea;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.area.Area;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class MicroareaDAOHibernate implements MicroareaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Microarea microarea) {
		try {
			this.session.merge(microarea);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Microarea microarea) {
		this.session.merge(microarea);

	}

	@Override
	public void excluir(Microarea microarea) {
		this.session.delete(microarea);

	}

	@Override
	public Microarea carregar(Integer microarea) {
		return (Microarea) this.session.get(Microarea.class, microarea);
	}

	@Override
	public List<Microarea> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(Microarea.class);
		
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
				crit.add(Restrictions.eq("area", null));
			}
		}
		return crit.list();
	}

}
