package scs.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;
import scs.vacinas.Vacinas;
import scs.vacinas.VacinasRN;

@ManagedBean(name="vacinasBean")
@RequestScoped

public class VacinasBean {

	private Vacinas vacinas = new Vacinas();
	private List<Vacinas> lista;
	public static String idMD5;
	public String nome;
	public String rua;
	public String numero;
	public String ocupacao;
	public String dtNascimentoFormatada;
	public Date dtNasc;
	private String vacina,dose;
	private boolean gestante;
	
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
		dtNascimentoFormatada = famil.get(0).getDataFormtada();
		dtNasc = famil.get(0).getDataNascimento();
		gestante = famil.get(0).getGestante();
	}

	public Vacinas getVacinas() {
		return vacinas;
	}

	public void setVacinas(Vacinas vacina) {
		this.vacinas = vacina;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		VacinasRN vacinaRN = new VacinasRN();
		Integer codigo = vacinas.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Vacina", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Vacina", ""));
			
		}
		
		this.vacinas.setIdfamiliar(idMD5);
		vacinaRN.salvar(this.vacinas);
		
		return "/restrito/acompanhamentoVacinas";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
		
		
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((vacinas == null) ? 0 : vacinas.hashCode());
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
		VacinasBean other = (VacinasBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (vacinas == null) {
			if (other.vacinas != null)
				return false;
		} else if (!vacinas.equals(other.vacinas))
			return false;
		return true;
	}

	public List<Vacinas> getLista() {
		if(this.lista==null){
			VacinasRN vacinaRN = new VacinasRN();
			this.lista = vacinaRN.listar();
		}
		return lista;
	}

	public void setLista(List<Vacinas> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		setValuesPanel();
		this.vacinas = new Vacinas();
		return "/restrito/vacinas";
	}
	
	public String editar(){
		setValuesPanel();
		return "/restrito/vacinas";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Vacina", ""));
		VacinasRN vacinaRN = new VacinasRN();
		vacinaRN.excluir(this.vacinas);
		this.lista = null;
		return null;
	}
	
	public Integer getIdade(){
		 setValuesPanel();
		 Calendar dataNascimento = Calendar.getInstance();  
		    dataNascimento.setTime(dtNasc);  
		    Calendar dataAtual = Calendar.getInstance();  
		  
		    Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);  
		    Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);  
		    Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));  
		  
		    if(diferencaMes < 0  || (diferencaMes == 0 && diferencaDia < 0)) {  
		        idade--;  
		    }    
		return idade;  
		
	}
	
	private boolean adolescente;
	public boolean getAdolescente(){
		if(getIdade()>10){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getGestante(){
		if(gestante==true){
			return true;
		} else {
			return false;
		}
	}
	
	private boolean adultoIdoso;
	public boolean getAdultoIdoso(){
		if((getIdade()>=20)&&(gestante==false)){
			return true;
		} else {
			return false;
		}
	}
	
	//private String trazIconeVacina; 
	public String getTrazIconeVacina(String tipoVacina, String dose, String tipo){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Vacinas where idfamiliar='"
						+idMD5+"' and doseAplicada='"+dose+"' and tipoVacina='"+tipoVacina+"' and tipo='"+tipo+"' and aplicada=true");
		if(query.list().size()>0){
			return "vacinado2.png";
		} else {
			return "naovacinado2.png";
		}
		
	}
	
	
	public boolean ChamaPanelEdicaoOuInsercao(String tipoVacina, String dose, String tipo){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Vacinas where idfamiliar='"
						+idMD5+"' and doseAplicada='"+dose+"' and tipoVacina='"+tipoVacina+"' and tipo='"+tipo+"'");
		if(query.list().size()>0){
			return true;
		} else {
			return false;
		}
		
	}
	
	public String InserirEditarVacina(String tipoVacina, String outraDose, String tipo){
		vacina = tipoVacina;
		if(outraDose.equals("U")){
			this.dose="Única";
		} else if(outraDose.equals("Z")){
			this.dose="a cada 10 Anos";
		} else {
			this.dose = outraDose;
		}
		
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Vacinas where idfamiliar='"
						+idMD5+"' and doseAplicada='"+outraDose+"' and tipoVacina='"+tipoVacina+"' and tipo='"+tipo+"'");
		if(query.list().size()>0){
			List<Vacinas> list = query.list();
			vacinas = list.get(0);
			return "/restrito/vacinas";
		} else {
			vacinas = new Vacinas();
			vacinas.setTipoVacina(tipoVacina);
			vacinas.setTipo(tipo);
			vacinas.setDoseAplicada(outraDose);
			return "/restrito/vacinas";
		}
	}

	public String getVacina() {
		return vacina;
	}

	public void setVacina(String vacina) {
		this.vacina = vacina;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}
	
	
	

}

