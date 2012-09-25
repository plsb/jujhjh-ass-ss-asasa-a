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
import scs.rua.RuaRN;
import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;
import scs.util.HibernateUtil;


@ManagedBean(name="segmentoBean")
@RequestScoped
public class SegmentoBean {
	
	private Segmento segmento = new Segmento();
	private List<Segmento> lista;
	private List<SelectItem> segmentoSelect;
	
	public String novo(){
		this.segmento = new Segmento();
		return "/restrito/segmento";
	}
	
	public String editar(){
		return "/restrito/segmento";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		SegmentoRN segmentoRN = new SegmentoRN();
		Integer codigo = segmento.getCodigo_segmento();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+segmento.getCodigo(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+segmento.getCodigo(), ""));
			
		}
		
		
		segmentoRN.salvar(this.segmento);
		
		return "/restrito/lista_segmento";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.codigo from segmento u where u.codigo= '"
						+ segmento.getCodigo()+"'");
		List segmento = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (segmento.isEmpty()) {					
			
			a= true;
		} else {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Segmento Ja Cadastrado, Informe Outro Código!", ""));
			a= false;
		}	
		
		return a;
		
	}
	
	public void setLista(List<Segmento> lista) {
		this.lista = lista;
	}

	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+segmento.getCodigo_segmento(), ""));
		SegmentoRN segmentoRN = new SegmentoRN();
		segmentoRN.excluir(this.segmento);
		this.lista = null;
		return null;
	}
	
	public List<Segmento> getLista(){
		if(this.lista==null){
			SegmentoRN segmentoRN = new SegmentoRN();
			this.lista = segmentoRN.listar();
		}
		return this.lista;
	}

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
		result = prime * result
				+ ((segmentoSelect == null) ? 0 : segmentoSelect.hashCode());
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
		SegmentoBean other = (SegmentoBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (segmento == null) {
			if (other.segmento != null)
				return false;
		} else if (!segmento.equals(other.segmento))
			return false;
		if (segmentoSelect == null) {
			if (other.segmentoSelect != null)
				return false;
		} else if (!segmentoSelect.equals(other.segmentoSelect))
			return false;
		return true;
	}
	
	public List<SelectItem> getSegmentoSelect() {
		if (this.segmentoSelect == null) {
			this.segmentoSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();

			SegmentoRN segmentoRN = new SegmentoRN();
			List<Segmento> categorias = segmentoRN.listar();
			this.montaDadosSelectSegmento(this.segmentoSelect, categorias, "");
		}
		
		return segmentoSelect;
	}

	private void montaDadosSelectSegmento(List<SelectItem> select, List<Segmento> segmentos, String prefixo) {

		SelectItem item = null;
		if (segmentos != null) {
			for (Segmento segmento : segmentos) {
				item = new SelectItem(segmento, "Código: " + segmento.getCodigo()+" | Bairro: "+segmento.getBairro().getDescricao());
				item.setEscape(false);
				select.add(item);
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	

}
