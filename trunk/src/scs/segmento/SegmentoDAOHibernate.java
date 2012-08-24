package scs.segmento;

import java.util.List;

import org.hibernate.Session;

import scs.unidade.Unidade;

public class SegmentoDAOHibernate implements SegmentoDAO {
	
	private Session session;
	
	public void setSesson(Session session){
		this.session = session;
	}

	@Override
	public void salvar(Segmento segmento) {
		try {
			this.session.save(segmento);
		} catch (Throwable e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}


	}

	@Override
	public void atualizar(Segmento segmento) {
		this.session.update(segmento);

	}

	@Override
	public void excluir(Segmento segmento) {
		this.session.delete(segmento);

	}

	@Override
	public Segmento carregar(Integer codigo) {
		return (Segmento) this.session.get(Segmento.class, codigo);
	}

	@Override
	public List<Segmento> listar() {
		return this.session.createCriteria(Segmento.class).list();
	}

}
