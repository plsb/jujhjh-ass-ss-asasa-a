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

import scs.area.Area;
import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name="areaBean")
@RequestScoped

public class AreaBean {

	private Area area = new Area();
	private List<Area> lista;
	private List<SelectItem> areaSelect;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		AreaRN areaRN = new AreaRN();
		Integer codigo = area.getCodigo_area();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+area.getCodigo(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+area.getCodigo(), ""));
			
		}
		
		areaRN.salvar(this.area);
		
		return "/restrito/lista_area";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.codigo from area u where u.codigo= '"
						+ area.getCodigo().toString()+"'");
		List ar = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (ar.isEmpty()) {					
			
			a= true;
			
			query = session
					.createSQLQuery("select u.idunidade from area u where u.idunidade= '"
							+ area.getUnidade().getCodigo_unidade().toString()+"'");
			
			ar = query.list();
			// query.setParameter("idfunc", codigo).uniqueResult();
			if (ar.isEmpty()) {					
				
				a= true;
			} else {
				context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unidade Ja Cadastrada para �rea, Informe Outra Unidade!", ""));
				a= false;
			}
		} else {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"C�digo Ja Cadastrado, Informe Outro C�digo!", ""));
			a= false;
		
		}
		
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
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
		AreaBean other = (AreaBean) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	public List<Area> getLista() {
		if(this.lista==null){
			AreaRN areaRN = new AreaRN();
			this.lista = areaRN.listar();
		}
		return lista;
	}

	public void setLista(List<Area> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.area = new Area();
		return "/restrito/area";
	}
	
	public String editar(){
		return "/restrito/area";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+area.getCodigo_area(), ""));
		AreaRN areaRN = new AreaRN();
		areaRN.excluir(this.area);
		this.lista = null;
		return null;
	}
	
	public List<SelectItem> getAreaSelect() {
		if (this.areaSelect == null) {
			this.areaSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();

			AreaRN areaRN = new AreaRN();
			List<Area> categorias = areaRN.listar();
			this.montaDadosSelectArea(this.areaSelect, categorias, "");
		}
		return areaSelect;
	}

	private void montaDadosSelectArea(List<SelectItem> select, List<Area> areas, String prefixo) {

		SelectItem item = null;
		if (areas != null) {
			for (Area area : areas) {
					item = new SelectItem(area, "C�digo: " + area.getCodigo()+" | Segmento: "+area.getSegmento().getCodigo());
					item.setEscape(false);
					select.add(item);
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
}
