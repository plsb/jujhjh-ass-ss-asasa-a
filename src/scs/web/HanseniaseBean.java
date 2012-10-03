package scs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
import scs.hanseniase.Hanseniase;
import scs.hanseniase.HanseniaseRN;
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name="hanseniaseBean")
@RequestScoped

public class HanseniaseBean {

	private Hanseniase hanseniase = new Hanseniase();
	private List<Hanseniase> lista;
	//private String novo;
	public static String idMD5;
	public String nome;
	public String rua;
	public String numero;
	public String ocupacao;
	public String dtNascimento;
	
	public void setValuesPanel(){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Familiar where idMD5='"
						+idMD5+"'");
		List<Familiar> famil = query.list();
		nome = famil.get(0).getNome();
		rua = famil.get(0).getRuaFamilia().getDescricao();
		numero = famil.get(0).getNumero().toString();
		ocupacao = famil.get(0).getOcupacao();
		dtNascimento = famil.get(0).getDataFormtada();
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		HanseniaseRN hanseniaseRN = new HanseniaseRN();
		Integer codigo = hanseniase.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Acompanhamento de Hanseniase ", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Acompanhamento de Hanseniase", ""));
			
		}
		hanseniase.setIdMD5familiar(idMD5);
		hanseniase.setDtVisita(new Date());
		hanseniaseRN.salvar(this.hanseniase);
		
		
		return "/restrito/acompanhamentoFamiliar";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		//FacesContext context = FacesContext.getCurrentInstance();
		boolean a=true;
		/*if(hanseniase.getTmMedicacaoDiaria()==null){
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe", ""));
			a=false;
		} else if(hanseniase.getDtUtDoseSupervisionada()==null){ 
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;
	    } else if(hanseniase.getFzAutosCuidados()==null){
	    	context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;
		} else if(hanseniase.getComExaminados()==null){
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;
		} else if(hanseniase.getCmRecebBCG()==null){
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;
		} else if(hanseniase.getObservacoes()==null){
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;
		} else if(hanseniase.getDtUltCOnsulta()==null){
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Descrição Ja Cadastrada, Informe Outra Descrição!", ""));
			a=false;			
		}*/
			
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hanseniase == null) ? 0 : hanseniase.hashCode());
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
		HanseniaseBean other = (HanseniaseBean) obj;
		if (hanseniase == null) {
			if (other.hanseniase != null)
				return false;
		} else if (!hanseniase.equals(other.hanseniase))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	public List<Hanseniase> getLista() {
		if(this.lista==null){
			Session session;
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Query qry = session.createQuery("From Hanseniase where idMD5familiar='"+
					idMD5+"' order by dtVisita desc");	
			this.lista = qry.list();
			System.out.println(String.valueOf(qry.list().size()));
		}
		return lista;
	}

	public void setLista(List<Hanseniase> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.hanseniase = new Hanseniase();
		setValuesPanel();
		return "/restrito/hanseniase";
	}
	
	public String editar(){
		setValuesPanel();
		return "/restrito/hanseniase";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Acompanhamento de Hanseniase", ""));
		HanseniaseRN hanseniaseRN = new HanseniaseRN();
		hanseniaseRN.excluir(this.hanseniase);
		this.lista = null;
		return null;
	}

	public Hanseniase getHanseniase() {
		return hanseniase;
	}

	public void setHanseniase(Hanseniase hanseniase) {
		this.hanseniase = hanseniase;
	}

	public static String getIdMD5() {
		return idMD5;
	}

	public static void setIdMD5(String idMD5) {
		HanseniaseBean.idMD5 = idMD5;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	
	
		
	
}

