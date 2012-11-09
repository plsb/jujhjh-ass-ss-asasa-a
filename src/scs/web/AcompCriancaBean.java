package scs.web;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import scs.acompcrianca.AcompCrianca;
import scs.acompcrianca.AcompCriancaRN;
import scs.familiar.Familiar;
import scs.tuberculose.Tuberculose;
import scs.tuberculose.TuberculoseRN;
import scs.util.HibernateUtil;

@ManagedBean(name="acompCriancaBean")
@RequestScoped

public class AcompCriancaBean {

	private AcompCrianca acompCrianca = new AcompCrianca();
	private List<AcompCrianca> lista;
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
		AcompCriancaRN acompCriancaRN = new AcompCriancaRN();
		Integer codigo = acompCrianca.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Acompanhamento de Criança", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Acompanhamento de Criança", ""));
			
		}
		acompCrianca.setIdfamiliar(idMD5);
		acompCrianca.setDtvisita(new Date());
		acompCriancaRN.salvar(this.acompCrianca);
		
		
		return "/restrito/acompanhamentoFamiliar";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
			
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acompCrianca == null) ? 0 : acompCrianca.hashCode());
		result = prime * result
				+ ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
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
		AcompCriancaBean other = (AcompCriancaBean) obj;
		if (acompCrianca == null) {
			if (other.acompCrianca != null)
				return false;
		} else if (!acompCrianca.equals(other.acompCrianca))
			return false;
		if (dtNascimento == null) {
			if (other.dtNascimento != null)
				return false;
		} else if (!dtNascimento.equals(other.dtNascimento))
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

	public List<AcompCrianca> getLista() {
		if(this.lista==null){
			Session session;
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Query qry = session.createQuery("From AcompCrianca where idfamiliar='"+
					idMD5+"' order by dtvisita desc");	
			this.lista = qry.list();
		}
		return lista;
	}

	public void setLista(List<AcompCrianca> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.acompCrianca = new AcompCrianca();
		setValuesPanel();
		return "/restrito/acompCrianca";
	}
	
	public String editar(){
		setValuesPanel();
		return "/restrito/acompCrianca";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Acompanhamento de Crianca", ""));
		AcompCriancaRN acompCriancaRN = new AcompCriancaRN();
		acompCriancaRN.excluir(this.acompCrianca);
		this.lista = null;
		return null;
	}

	

	public AcompCrianca getAcompCrianca() {
		return acompCrianca;
	}

	public void setAcompCrianca(AcompCrianca acompCrianca) {
		this.acompCrianca = acompCrianca;
	}

	public static String getIdMD5() {
		return idMD5;
	}

	public static void setIdMD5(String idMD5) {
		AcompCriancaBean.idMD5 = idMD5;
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

