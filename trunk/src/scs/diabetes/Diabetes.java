package scs.diabetes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diabetes")
public class Diabetes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8944441474702116326L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String idMD5familiar;
	private String fzDieta;
	private String fzExFisicos;
	private String usInsulina;
	private String tmHipoglicOral;
	private Date dtUltVisita;
	private Date dtVisita;
	private String Obs;
	
	public String getDataVisitaFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtVisita());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Obs == null) ? 0 : Obs.hashCode());
		result = prime * result
				+ ((dtUltVisita == null) ? 0 : dtUltVisita.hashCode());
		result = prime * result
				+ ((dtVisita == null) ? 0 : dtVisita.hashCode());
		result = prime * result + ((fzDieta == null) ? 0 : fzDieta.hashCode());
		result = prime * result
				+ ((fzExFisicos == null) ? 0 : fzExFisicos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idMD5familiar == null) ? 0 : idMD5familiar.hashCode());
		result = prime * result
				+ ((tmHipoglicOral == null) ? 0 : tmHipoglicOral.hashCode());
		result = prime * result
				+ ((usInsulina == null) ? 0 : usInsulina.hashCode());
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
		Diabetes other = (Diabetes) obj;
		if (Obs == null) {
			if (other.Obs != null)
				return false;
		} else if (!Obs.equals(other.Obs))
			return false;
		if (dtUltVisita == null) {
			if (other.dtUltVisita != null)
				return false;
		} else if (!dtUltVisita.equals(other.dtUltVisita))
			return false;
		if (dtVisita == null) {
			if (other.dtVisita != null)
				return false;
		} else if (!dtVisita.equals(other.dtVisita))
			return false;
		if (fzDieta == null) {
			if (other.fzDieta != null)
				return false;
		} else if (!fzDieta.equals(other.fzDieta))
			return false;
		if (fzExFisicos == null) {
			if (other.fzExFisicos != null)
				return false;
		} else if (!fzExFisicos.equals(other.fzExFisicos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idMD5familiar == null) {
			if (other.idMD5familiar != null)
				return false;
		} else if (!idMD5familiar.equals(other.idMD5familiar))
			return false;
		if (tmHipoglicOral == null) {
			if (other.tmHipoglicOral != null)
				return false;
		} else if (!tmHipoglicOral.equals(other.tmHipoglicOral))
			return false;
		if (usInsulina == null) {
			if (other.usInsulina != null)
				return false;
		} else if (!usInsulina.equals(other.usInsulina))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdMD5familiar() {
		return idMD5familiar;
	}
	public void setIdMD5familiar(String idMD5familiar) {
		this.idMD5familiar = idMD5familiar;
	}
	public String getFzDieta() {
		return fzDieta;
	}
	public void setFzDieta(String fzDieta) {
		this.fzDieta = fzDieta;
	}
	public String getFzExFisicos() {
		return fzExFisicos;
	}
	public void setFzExFisicos(String fzExFisicos) {
		this.fzExFisicos = fzExFisicos;
	}
	public String getUsInsulina() {
		return usInsulina;
	}
	public void setUsInsulina(String usInsulina) {
		this.usInsulina = usInsulina;
	}
	public String getTmHipoglicOral() {
		return tmHipoglicOral;
	}
	public void setTmHipoglicOral(String tmHipoglicOral) {
		this.tmHipoglicOral = tmHipoglicOral;
	}
	public Date getDtUltVisita() {
		return dtUltVisita;
	}
	public void setDtUltVisita(Date dtUltVisita) {
		this.dtUltVisita = dtUltVisita;
	}
	public Date getDtVisita() {
		return dtVisita;
	}
	public void setDtVisita(Date dtVisita) {
		this.dtVisita = dtVisita;
	}
	public String getObs() {
		return Obs;
	}
	public void setObs(String obs) {
		Obs = obs.toUpperCase();
	}
	
	

}
