package scs.tipoAtendimentoMedicoEnfermeiro;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import scs.encamMedicos.EncaminhamentosMedicos;
import scs.solicExamesComplementares.SolicExamesComplem;
import scs.usuario.Usuario;
import scs.web.ContextoBean;

public class TipoAtendimentoMedicoEnfermeiroDAOHibernate implements
		TipoAtendimentoMedicoEnfermeiroDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(TipoAtendimentoMedicoEnfermeiro tpAtendMedico) {
		try {
			this.session.save(tpAtendMedico);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(TipoAtendimentoMedicoEnfermeiro tpAtendMedico) {
		this.session.update(tpAtendMedico);

	}

	@Override
	public void excluir(TipoAtendimentoMedicoEnfermeiro tpAtendMedico) {
		this.session.delete(tpAtendMedico);

	}

	@Override
	public TipoAtendimentoMedicoEnfermeiro carregar(Integer codigo) {
		return (TipoAtendimentoMedicoEnfermeiro) this.session.get(TipoAtendimentoMedicoEnfermeiro.class, codigo);
	}

	@Override
	public List<TipoAtendimentoMedicoEnfermeiro> listar() {
		Usuario usuario = new Usuario();
		ContextoBean cx = new ContextoBean();
		usuario = cx.getUsuarioLogado();
		Criteria crit = session.createCriteria(TipoAtendimentoMedicoEnfermeiro.class);
		
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
