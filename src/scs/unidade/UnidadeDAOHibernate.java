package scs.unidade;

import java.util.List;

import org.hibernate.Session;

import scs.usuario.Usuario;

public class UnidadeDAOHibernate implements UnidadeDAO {

	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}
	
	@Override
	public void salvar(Unidade unidade) {
		try {
			this.session.save(unidade);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Unidade unidade) {
		this.session.update(unidade);

	}

	@Override
	public void excluir(Unidade unidade) {
		this.session.delete(unidade);

	}

	@Override
	public Unidade carregar(Integer codigo) {
		return (Unidade) this.session.get(Unidade.class, codigo);
	}

	@Override
	public List<Unidade> listar() {
		return this.session.createCriteria(Unidade.class).list();
	}

	
}
