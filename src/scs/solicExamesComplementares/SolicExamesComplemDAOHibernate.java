package scs.solicExamesComplementares;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.encamMedicos.EncaminhamentosMedicos;
import scs.usuario.Usuario;
import scs.web.ContextoBean;


public class SolicExamesComplemDAOHibernate implements SolicExamesComplemDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(SolicExamesComplem solExameCompl) {
		try {
			this.session.save(solExameCompl);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(SolicExamesComplem solExameCompl) {
		this.session.update(solExameCompl);

	}

	@Override
	public void excluir(SolicExamesComplem solExameCompl) {
		this.session.delete(solExameCompl);

	}

	@Override
	public SolicExamesComplem carregar(Integer codigo) {
		return (SolicExamesComplem) this.session.get(SolicExamesComplem.class, codigo);
	}

	@Override
	public List<SolicExamesComplem> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(SolicExamesComplem.class);
		
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
