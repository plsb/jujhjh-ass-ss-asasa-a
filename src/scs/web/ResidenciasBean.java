package scs.web;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import scs.area.Area;
import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.familiar.Familiar;
import scs.familiar.FamiliarRN;
import scs.hanseniase.Hanseniase;
import scs.hanseniase.HanseniaseRN;
import scs.microarea.Microarea;
import scs.microarea.MicroareaRN;
import scs.residencia.Residencia;
import scs.residencia.ResidenciaRN;
import scs.segmento.Segmento;
import scs.segmento.SegmentoRN;
import scs.util.HibernateUtil;

@ManagedBean(name="residenciaBean")
@RequestScoped
public class ResidenciasBean {
	
	
	private Residencia residencia = new Residencia();
	private List<Residencia> lista;	
	private boolean disableoutroTC=false;
	private boolean disableoutroCD=false;
	private List<SelectItem> areaSelect;
	private List<SelectItem> segmentoSelect;
	private List<SelectItem> microareaSelect;
	private List<SelectItem> residenciaSelect;
	
	public boolean getDisableoutroTC(){
		//if(residencia.getId()==null){
		//	return true;
		//}
		if(residencia.getTipocasa()!=null){
			if(residencia.getTipocasa().equals("Outro")){
				return false;			
			} else {
				return true;
			}
		} else {
			return true;
		}	
		
	}
	
	public boolean getDisableoutroCD(){
		//if(residencia.getId()==null){
		//	return true;
		//}
		if(residencia.getCasodoenca()!=null){
			if(residencia.getCasodoenca().equals("Outro")){
				return false;			
			} else {
				return true;
			}
		} else {
			return true;
		}
		//return disableoutroCD;
	}
	
	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

		
	public String novo(){
		this.residencia = new Residencia();
		return "/restrito/residencia";
	}
	
	public String editar(){
		return "/restrito/residencia";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance(); 
		ResidenciaRN residenciaRN = new ResidenciaRN();
		

		try {
			if(residencia.getTipocasa().equals("Outro")){
				if(residencia.getOutroTipoCasa().equals("")){
					context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Infrome o Outro tipo de Casa!", ""));
					return "";				
				}
			}
		} catch (Exception e) {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Infrome o Outro tipo de Casa!", ""));
			return "";
		}
		
		try {
			if(residencia.getCasodoenca().equals("Outro")){
				if(residencia.getOurtoCasoDoenca().equals("")){
					context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Infrome em Caso de Doença a Quem outro Procurar!", ""));
					return "";				
				}
			}
		} catch (Exception e) {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Infrome em Caso de Doença a Quem outro Procurar!", ""));
			return "";
		}
		
		
		Integer codigo = residencia.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+residencia.getEndereco().getDescricao()+", "+
										residencia.getNum_residencia().toString(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+residencia.getEndereco().getDescricao()+", "+
					residencia.getNum_residencia().toString(), ""));
			
		}
		
		residenciaRN.salvar(this.residencia);
		return "/restrito/lista_residencias";
	}
	
	public boolean verificaUnique(){
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.endereco from residencias u where u.endereco="
						+ residencia.getEndereco().getCodigo_rua().toString()+" and u.num_residencia="+
						residencia.getNum_residencia().toString());
		List resi = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (resi.isEmpty()) {				
			
			a=true;
			
		} else {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Residência Ja Cadastrado, Informe Outra Residência!", ""));
			a= false;
		
		}
		
		return a;
		
	}
	
	
	public void setLista(List<Residencia> lista) {
		this.lista = lista;
	}

	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		ResidenciaRN residenciaRN = new ResidenciaRN();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+residencia.getEndereco().getDescricao()+", "+
				residencia.getNum_residencia().toString(), ""));
		residenciaRN.excluir(this.residencia); 
		this.lista = null;
		return null;
	}
	
	public List<Residencia> getLista(){
		if(this.lista==null){
			ResidenciaRN residenciaRN = new ResidenciaRN();
			this.lista = residenciaRN.listar();
		}
		return this.lista;
	}

	public Residencia getBairro() {
		return residencia;
	}

	public void setBairro(Residencia residencia) {
		this.residencia = residencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result
				+ ((residencia == null) ? 0 : residencia.hashCode());
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
		ResidenciasBean other = (ResidenciasBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (residencia == null) {
			if (other.residencia != null)
				return false;
		} else if (!residencia.equals(other.residencia))
			return false;
		return true;
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
					item = new SelectItem(area, "Código: " + area.getCodigo()+" | Segmento: "+area.getSegmento().getCodigo());
					item.setEscape(false);
					if(residencia.getSegmento()==null){
						select.add(item);
					} else {
						if(area.getSegmento()==residencia.getSegmento()){
							select.add(item);
						}
					}
						
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
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
				
				if(residencia.getBairro()==null){
					select.add(item);
				} else {
					if(segmento.getBairro()==residencia.getBairro()){
						select.add(item);
					}
				}
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public List<SelectItem> getMicroareaSelect() {
		if (this.microareaSelect == null) {
			this.microareaSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();
	
			MicroareaRN microareaRN = new MicroareaRN();
			List<Microarea> categorias = microareaRN.listar();
			this.montaDadosSelectMicroArea(this.microareaSelect, categorias, "");
		}

		return microareaSelect;
	}
	
	private void montaDadosSelectMicroArea(List<SelectItem> select, List<Microarea> microareas, String prefixo) {

		SelectItem item = null;
		if (microareas != null) {
			for (Microarea microarea : microareas) {
					item = new SelectItem(microarea, microarea.getDescricao()+" | Agente: "+microarea.getAgente().getNome().toString()+" | Área: "+microarea.getArea().getCodigo().toString());
					item.setEscape(false);
					if(residencia.getArea()==null){
						select.add(item);
					} else {
						if(microarea.getArea().getCodigo_area()==residencia.getArea().getCodigo_area()){
							select.add(item);
						}
					}
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public List<SelectItem> getResidencaSelect() {
		if (this.residenciaSelect == null) {
			this.residenciaSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();
	
			ResidenciaRN residenciaRN = new ResidenciaRN();
			List<Residencia> categorias = residenciaRN.listar();
			this.montaDadosSelectResidencia(this.residenciaSelect, categorias, "");
		}

		return residenciaSelect;
	}
	
	private void montaDadosSelectResidencia(List<SelectItem> select, List<Residencia> residencias, String prefixo) {

		SelectItem item = null;
		if (residencias != null) {
			for (Residencia residencia : residencias) {
					item = new SelectItem(residencia, residencia.getEndereco().getDescricao() +", "+ residencia.getNum_residencia().toString());
					item.setEscape(false);
					select.add(item);
					
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	
	/*
	 * DAQUI PARA BAIXO É A PARTE DO ACOMPANHAMENTO
	 */
	private List<SelectItem> familiarSelect;
	private static Familiar familiarSelecionado;
	private String nomeFamiliar;
	private List<Hanseniase> listaHanse;
	private Hanseniase hanseniase;
	//private String novoHanseniase;
	private String idMD5Familiar;
	//public static String outroIDMd5;
	
	public Familiar getFamiliarSelecionado() {
		return familiarSelecionado;
	}

	public void setFamiliarSelecionado(Familiar familiarSelecionado) {
		if(familiarSelecionado!=null){
			idMD5Familiar=familiarSelecionado.getIdMD5();
		}
		HanseniaseBean.idMD5 = familiarSelecionado.getIdMD5();
		this.familiarSelecionado = familiarSelecionado;
	}
	
	public String getNomeFamiliar(){
		if(this.familiarSelecionado.getNome()==null){
			return "";
		}
		return familiarSelecionado.getNome();
	}

	public String acompanhamento(){
		
		return "/restrito/selecionaAcompanhamento";
	}
	
	public List<SelectItem> getFamiliarSelect() {
		if (this.familiarSelect == null) {
			this.familiarSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();
	
			FamiliarRN familiarRN = new FamiliarRN();
			List<Familiar> categorias = familiarRN.listar();
			this.montaDadosSelectFamiliar(this.familiarSelect, categorias, "");
		}

		return familiarSelect;
	}
	
	private void montaDadosSelectFamiliar(List<SelectItem> select, List<Familiar> familiares, String prefixo) {

		SelectItem item = null;
		if (familiares != null) {
			for (Familiar familiar : familiares) {
					item = new SelectItem(familiar, familiar.getNome());
					item.setEscape(false);
					if(residencia.getEndereco()==null){
						select.add(item);
					}else if((familiar.getRuaFamilia().getCodigo_rua().toString().equalsIgnoreCase(residencia.getEndereco().getCodigo_rua().toString()))&&
								(familiar.getNumero().toString().equalsIgnoreCase(residencia.getNum_residencia().toString()))){
						select.add(item);
							
					}
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public String acompanhamentoFamiliar(){
		if((familiarSelecionado==null)){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe o Familiar!", ""));
			return "";
		}
		return "/restrito/acompanhamentoFamiliar.jsf";
	}
	
	/*
	 * PARTE DE HANSENIASE
	 */
	/*public List<Hanseniase> getListaHanse(){
		if(this.listaHanse==null){
			if(familiarSelecionado!=null){
				Session session;
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				Query qry = session.createQuery("From Hanseniase where idMD5familiar='"+
						familiarSelecionado.getIdMD5()+"' order by dtVisita desc");	
				this.listaHanse = qry.list();
			}
		}
		return this.listaHanse;
	}*/

	public Hanseniase getHanseniase() {
		return hanseniase;
	}

	public void setHanseniase(Hanseniase hanseniase) {
		this.hanseniase = hanseniase;
	}
	
	
 

}
