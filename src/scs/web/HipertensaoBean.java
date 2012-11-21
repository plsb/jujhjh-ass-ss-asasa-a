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
import scs.diabetes.Diabetes;
import scs.diabetes.DiabetesRN;
import scs.familiar.Familiar;
import scs.hanseniase.Hanseniase;
import scs.hanseniase.HanseniaseRN;
import scs.hipertensao.HipertensaoRN;
import scs.hipertensao.Hipertesao;
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name="hipertensaoBean")
@RequestScoped

public class HipertensaoBean {

	private Hipertesao hipertensao = new Hipertesao();
	private List<Hipertesao> lista;
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
		HipertensaoRN hipertensaoRN = new HipertensaoRN();
		Integer codigo = hipertensao.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Acompanhamento de Hipertensão.", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Acompanhamento de Hipertensão.", ""));
			
		}
		hipertensao.setIdMD5familiar(idMD5);
		hipertensao.setDtVisita(new Date());
		hipertensaoRN.salvar(this.hipertensao);
		
		
		return "/restrito/acompanhamentoFamiliar";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
		FacesContext context = FacesContext.getCurrentInstance();
		if(hipertensao.getPressaoArterial()==""){
			a=false;
			context.addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_ERROR,"Informe a Pressão Arterial!", ""));
		}
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result
				+ ((hipertensao == null) ? 0 : hipertensao.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((ocupacao == null) ? 0 : ocupacao.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
		HipertensaoBean other = (HipertensaoBean) obj;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
			return false;
		if (hipertensao == null) {
			if (other.hipertensao != null)
				return false;
		} else if (!hipertensao.equals(other.hipertensao))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (ocupacao == null) {
			if (other.ocupacao != null)
				return false;
		} else if (!ocupacao.equals(other.ocupacao))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}

	public List<Hipertesao> getLista() {
		if(this.lista==null){
			Session session;
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Query qry = session.createQuery("From Hipertesao where idMD5familiar='"+
					idMD5+"' order by dtVisita desc");	
			this.lista = qry.list();
		}
		return lista;
	}

	public void setLista(List<Hipertesao> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.hipertensao = new Hipertesao();
		setValuesPanel();
		return "/restrito/hipertensao";
	}
	
	public String editar(){
		setValuesPanel();
		return "/restrito/hipertensao";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Acompanhamento de Hipertensão.", ""));
		HipertensaoRN hipertensaoRN = new HipertensaoRN();
		hipertensaoRN.excluir(this.hipertensao);
		this.lista = null;
		return null;
	}

	public Hipertesao getHipertensao() {
		return hipertensao;
	}

	public void setHipertensao(Hipertesao hipertensao) {
		this.hipertensao = hipertensao;
	}

	public static String getIdMD5() {
		return idMD5;
	}

	public static void setIdMD5(String idMD5) {
		HipertensaoBean.idMD5 = idMD5;
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

