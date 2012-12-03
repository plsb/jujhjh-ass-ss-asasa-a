package scs.web;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import com.sun.xml.internal.ws.util.UtilException;

import scs.web.ContextoBean;

import scs.area.Area;
import scs.area.AreaRN;
import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.util.ContextoUtil;
import scs.util.HibernateUtil;


@ManagedBean(name="unidadeBean")
@RequestScoped
public class UnidadeBean {
	
	private Unidade unidade = new Unidade();
	private List<Unidade> lista;
	//private StreamedContent arquivoRetorno;
	private int tipoRelatorio;
	private List<SelectItem> unidadeSelect;
		
	
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + tipoRelatorio;
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime * result
				+ ((unidadeSelect == null) ? 0 : unidadeSelect.hashCode());
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
		UnidadeBean other = (UnidadeBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (tipoRelatorio != other.tipoRelatorio)
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (unidadeSelect == null) {
			if (other.unidadeSelect != null)
				return false;
		} else if (!unidadeSelect.equals(other.unidadeSelect))
			return false;
		return true;
	}
	
	public String novo(){
		this.unidade = new Unidade();
		return "/restrito/unidade";
	}
	
	public String editar(){
		return "/restrito/unidade";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		UnidadeRN unidadeRN = new UnidadeRN();
		Integer codigo = unidade.getCodigo_unidade();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir: "+unidade.getCodigo_sia_sus(), ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar: "+unidade.getCodigo_sia_sus(), ""));
			
		}	
		
		unidadeRN.salvar(this.unidade);
		
		return "/restrito/lista_unidade";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a;
		FacesContext context = FacesContext.getCurrentInstance();
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.codigo_sia_sus from unidade u where u.codigo_sia_sus= '"
						+ unidade.getCodigo_sia_sus()+"'");
		List unida = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (unida.isEmpty()) {				
			a= true;
			
			query = session
					.createSQLQuery("select u.coordenador from unidade u where u.coordenador= '"
							+ unidade.getFuncionario().getCodigo().toString()+"'");
			unida = query.list();
			// query.setParameter("idfunc", codigo).uniqueResult();
			if (unida.isEmpty()) {					
				
				a= true;
			} else {
				context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Coordenador Já Cadastrado Para Unidades, Informe Outro Coordenador!", ""));
				a= false;
			}
		} else {
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Código SIA/SUS Ja Cadastrada, Informe Outro Código!", ""));
			a= false;		
		} 
		
		return a;
		
	}
	
	
	
	public void setLista(List<Unidade> lista) {
		this.lista = lista;
	}

	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir: "+unidade.getCodigo_sia_sus(), ""));
		UnidadeRN unidadeRN = new UnidadeRN();
		unidadeRN.excluir(this.unidade);
		this.lista = null;
		return null;
	}
	
	public List<Unidade> getLista(){
		if(this.lista==null){
			UnidadeRN unidadeRN = new UnidadeRN();
			this.lista = unidadeRN.listar();
		}
		return this.lista;
	}
/*	public StreamedContent getArquivoRetorno() throws scs.util.UtilException {
		FacesContext context = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		String usuario = contextoBean.getUsuarioLogado().getLogin();
		String nomeRelatorioJasper = "unidade";
		String nomeRelatorioSaida = "unidade";
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		HashMap parametrosRelatorio = new HashMap();
		parametrosRelatorio.put("codigoUsuario", contextoBean.getUsuarioLogado().getCodigo());
		try {
			this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, 
						nomeRelatorioSaida, this.tipoRelatorio);
		} catch (UtilException e) {
			context.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		} 
		return this.arquivoRetorno;
	}*/
	
	public int getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	
	public List<SelectItem> getUnidadeSelect() {
		if (this.unidadeSelect == null) {
			this.unidadeSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();

			UnidadeRN unidadeRN = new UnidadeRN();
			List<Unidade> categorias = unidadeRN.listar();
			this.montaDadosSelectUnidade(this.unidadeSelect, categorias, "");
		}
		
		return unidadeSelect;
	}

	private void montaDadosSelectUnidade(List<SelectItem> select, List<Unidade> unidades, String prefixo) {

		SelectItem item = null;
		if (unidades != null) {
			for (Unidade unidade : unidades) {
				item = new SelectItem(unidade, "Código SIA/SUS: " + unidade.getCodigo_sia_sus()+" | Tipo: "+unidade.getTipounidade()+" | Coordenador: "+unidade.getFuncionario().getNome());
				item.setEscape(false);
				select.add(item);
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
		
}
