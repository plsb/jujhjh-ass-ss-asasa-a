package scs.procedimentos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.area.Area;
import scs.encamMedicos.EncaminhamentosMedicos;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class ProcedimentosDAOHibernate implements ProcedimentosDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Procedimentos procedimentos) {
		try {
			this.session.save(procedimentos);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Procedimentos procedimentos) {
		this.session.update(procedimentos);

	}

	@Override
	public void excluir(Procedimentos procedimentos) {
		this.session.delete(procedimentos);

	}

	@Override
	public Procedimentos carregar(Integer codigo) {
		return (Procedimentos) this.session.get(Procedimentos.class, codigo);
	}

	@Override
	public List<Procedimentos> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(Procedimentos.class);
		
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
