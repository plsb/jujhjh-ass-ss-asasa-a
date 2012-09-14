package scs.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;
import scs.util.HibernateUtil;

@ManagedBean(name="bairroBean")
@RequestScoped
public class BairroBean {
	
	private Bairro bairro = new Bairro();
	private List<Bairro> lista;
	private List<SelectItem> bairroSelect;
	
	public String novo(){
		this.bairro = new Bairro();
		return "/restrito/bairro";
	}
	
	public String editar(){
		return "/restrito/bairro";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance(); 
		BairroRN bairroRN = new BairroRN();
			Integer codigo = bairro.getCodigo_bairro();
			if(codigo==null || codigo == 0){
				if (verificaUnique()){
					context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+bairro.getDescricao(), null));
				} else {
					return "";
				}
			} else {
				context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+bairro.getDescricao(), null));
			}
			
			bairroRN.salvar(this.bairro);
			return "/restrito/lista_bairro";
	}
	
	public boolean verificaUnique(){
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.descricao from bairro u where u.descricao= '"
						+ bairro.getDescricao()+"'");
		List bairros = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (bairros.isEmpty()) {					
			
			a= true;
		} else {
			context.addMessage(null, new FacesMessage("Descrição Ja Cadastrada, Informe Outra Descrição.", null));
			a= false;
		}	
		
		query = session
				.createSQLQuery("select u.cep from bairro u where u.cep= '"
						+ bairro.getCep()+"'");
		bairros = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (bairros.isEmpty()) {					
			
			a= true;
		} else {
			context.addMessage(null, new FacesMessage("CEP Ja Cadastrado, Informe Outro CEP.", null));
			a= false;
		}
		
		return a;
		
	}
	
	public void setLista(List<Bairro> lista) {
		this.lista = lista;
	}

	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		BairroRN bairroRN = new BairroRN();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+bairro.getDescricao(), null));
		bairroRN.excluir(this.bairro); 
		this.lista = null;
		return null;
	}
	
	public List<Bairro> getLista(){
		if(this.lista==null){
			BairroRN bairroRN = new BairroRN();
			this.lista = bairroRN.listar();
		}
		return this.lista;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((bairroSelect == null) ? 0 : bairroSelect.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BairroBean other = (BairroBean) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bairroSelect == null) {
			if (other.bairroSelect != null)
				return false;
		} else if (!bairroSelect.equals(other.bairroSelect))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}
	
	public List<SelectItem> getBairroSelect() {
		if (this.bairroSelect == null) {
			this.bairroSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();

			BairroRN bairroRN = new BairroRN();
			List<Bairro> categorias = bairroRN.listar();
			this.montaDadosSelectBairro(this.bairroSelect, categorias, "");
		}
		
		return bairroSelect;
	}

	private void montaDadosSelectBairro(List<SelectItem> select, List<Bairro> bairros, String prefixo) {

		SelectItem item = null;
		if (bairros != null) {
			for (Bairro bairro : bairros) {
				item = new SelectItem(bairro, prefixo + bairro.getDescricao());
				item.setEscape(false);
				select.add(item);
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public void setBairroSelect(List<SelectItem> bairroSelect) {
		this.bairroSelect = bairroSelect;
	}

	
	

}
