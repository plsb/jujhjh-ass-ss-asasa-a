package scs.encamMedicos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.profissional.Profissional;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

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
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(EncaminhamentosMedicos.class);
		
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
