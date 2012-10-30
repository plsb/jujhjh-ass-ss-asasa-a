package scs.tipoAtendimentoMedicoEnfermeiro;

import java.util.List;

import org.hibernate.Session;

import scs.solicExamesComplementares.SolicExamesComplem;

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
		return this.session.createCriteria(TipoAtendimentoMedicoEnfermeiro.class).list();
	}

}
