package scs.consultasMedicas;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.usuario.Usuario;
import scs.visitasDomiciliares.VisitasDomiciliares;
import scs.web.ContextoBean;

public class ConsultasMedicasDAOHibernate implements ConsultasMedicasDAO {

	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}
	
	@Override
	public void salvar(ConsultasMedicas consDAO) {
		try {
			this.session.save(consDAO);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(ConsultasMedicas consDAO) {
		this.session.update(consDAO);

	}

	@Override
	public void excluir(ConsultasMedicas consDAO) {
		this.session.delete(consDAO);

	}

	@Override
	public ConsultasMedicas carregar(Integer codigo) {
		return (ConsultasMedicas) this.session.get(ConsultasMedicas.class, codigo);
	}

	@Override
	public List<ConsultasMedicas> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(ConsultasMedicas.class);
		
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
