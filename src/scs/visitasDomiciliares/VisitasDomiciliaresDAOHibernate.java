package scs.visitasDomiciliares;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiro;
import scs.usuario.Usuario;
import scs.visitas.Visitas;
import scs.web.ContextoBean;

public class VisitasDomiciliaresDAOHibernate implements VisitasDomiciliaresDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(VisitasDomiciliares visitasDAO) {
		try {
			this.session.save(visitasDAO);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(VisitasDomiciliares visitasDAO) {
		this.session.update(visitasDAO);

	}

	@Override
	public void excluir(VisitasDomiciliares visitasDAO) {
		this.session.delete(visitasDAO);

	}

	@Override
	public VisitasDomiciliares carregar(Integer codigo) {
		return (VisitasDomiciliares) this.session.get(VisitasDomiciliares.class, codigo);
	}

	@Override
	public List<VisitasDomiciliares> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(VisitasDomiciliares.class);
		
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
