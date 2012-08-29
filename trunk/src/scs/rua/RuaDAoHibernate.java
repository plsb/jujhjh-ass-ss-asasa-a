package scs.rua;

import java.util.List;

import org.hibernate.Session;

import scs.bairro.Bairro;

public class RuaDAoHibernate implements RuaDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Rua rua) {
		try {
			this.session.save(rua);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Rua rua) {
		this.session.update(rua);

	}

	@Override
	public void excluir(Rua rua) {
		this.session.delete(rua);
	}

	@Override
	public Rua carregar(Integer codigo) {
		return (Rua) this.session.get(Rua.class, codigo);
	}

	@Override
	public List<Rua> listar() {
		return this.session.createCriteria(Rua.class).list();
	}

}
