package scs.residencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.area.Area;
import scs.microarea.Microarea;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class ResidenciaDAOHibernate implements ResidenciaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Residencia residencia) {
		try {
			this.session.save(residencia);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Residencia residencia) {
		this.session.update(residencia);
	}

	@Override
	public void excluir(Residencia residencia) {
		this.session.delete(residencia);

	}

	@Override
	public Residencia carregar(Integer codigo) {
		return (Residencia) this.session.get(Residencia.class, codigo);
	}

	@Override
	public List<Residencia> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(Residencia.class);
		
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
