package scs.profissional;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.bairro.Bairro;
import scs.residencia.Residencia;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class ProfissionalDAOHibernate implements ProfissionalDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Profissional prof) {
		try {
			this.session.merge(prof);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Profissional prof) {
		this.session.merge(prof);

	}

	@Override
	public void excluir(Profissional prof) {
		this.session.delete(prof);

	}

	@Override
	public Profissional carregar(Integer codigo) {
		return (Profissional) this.session.get(Profissional.class, codigo);
	}

	@Override
	public List<Profissional> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(Profissional.class);
		
		boolean result=false;
		for (int i = 0; i < usuario.getPermissao().size(); i++) {
			if(usuario.getPermissao().get(i).equals("ROLE_ADMIN")){
				result=true;
			}
		}
		if(result==false){
			if(usuario.getArea()!=null){
				crit.add(Restrictions.eq("unidade", usuario.getUnidade()));
			} else {
				crit.add(Restrictions.eq("unidade",null));
			}
		}
		return crit.list();
	}

}
