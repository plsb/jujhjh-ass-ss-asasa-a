package scs.bairro;

import java.util.List;

import org.hibernate.Session;

import scs.segmento.Segmento;

public class BairroDAOHibernate implements BairroDAO {

	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}
	
	@Override
	public void salvar(Bairro bairro) {
		try {
			this.session.merge(bairro);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void atualizar(Bairro bairro) {
		this.session.merge(bairro);

	}

	@Override
	public void excluir(Bairro bairro) {
		this.session.delete(bairro);

	}

	@Override
	public Bairro carregar(Integer codigo) {
		return (Bairro) this.session.get(Bairro.class, codigo);
	}

	@Override
	public List<Bairro> listar() {
		return this.session.createCriteria(Bairro.class).list();
	}

}
