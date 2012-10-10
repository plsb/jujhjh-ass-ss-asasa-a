package scs.agendamento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import scs.familiar.Familiar;
import scs.segmento.Segmento;
import scs.util.HibernateUtil;

@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8249350903825983844L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private Date dtagendamento;
	private String descricao;
	private String idfamiliar;
	private boolean urgente;
	private String tpconsulta;
	private boolean agendada;
	
	public String getSeeUrgente(){
		if(urgente==true){
			return "É Urgente!";
		} else {
			return "Não é Urgente!";
		}
	}
	public String getFamiliar(){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Familiar where idMD5='"
						+idfamiliar+"'");
		List<Familiar> famil = query.list();
		if (famil.size()==0){
			return "";
		} else {
			return famil.get(0).getNome();
		}
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtagendamento() {
		return dtagendamento;
	}
	public void setDtagendamento(Date dtagendamento) {
		this.dtagendamento = dtagendamento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdfamiliar() {
		return idfamiliar;
	}
	public void setIdfamiliar(String idfamiliar) {
		this.idfamiliar = idfamiliar;
	}
	public boolean isUrgente() {
		return urgente;
	}
	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}
	public String getTpconsulta() {
		return tpconsulta;
	}
	public void setTpconsulta(String tpconsulta) {
		this.tpconsulta = tpconsulta;
	}
	public boolean isAgendada() {
		return agendada;
	}
	public void setAgendada(boolean agendada) {
		this.agendada = agendada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (agendada ? 1231 : 1237);
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((dtagendamento == null) ? 0 : dtagendamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idfamiliar == null) ? 0 : idfamiliar.hashCode());
		result = prime * result
				+ ((tpconsulta == null) ? 0 : tpconsulta.hashCode());
		result = prime * result + (urgente ? 1231 : 1237);
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
		Agendamento other = (Agendamento) obj;
		if (agendada != other.agendada)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtagendamento == null) {
			if (other.dtagendamento != null)
				return false;
		} else if (!dtagendamento.equals(other.dtagendamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idfamiliar == null) {
			if (other.idfamiliar != null)
				return false;
		} else if (!idfamiliar.equals(other.idfamiliar))
			return false;
		if (tpconsulta == null) {
			if (other.tpconsulta != null)
				return false;
		} else if (!tpconsulta.equals(other.tpconsulta))
			return false;
		if (urgente != other.urgente)
			return false;
		return true;
	}
	
	
	

}
