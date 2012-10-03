package scs.tuberculose;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tuberculose")
public class Tuberculose implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2708235908228900292L;
	

	@Id
	@GeneratedValue
	private Integer id;
	private String idMD5familiar;
	private Date dtvisita;
	private String tmmeddiaria;
	private String recindesej;
	private String exescar;
	private Integer comexami;
	private Integer mn5anoscombcg;
	private String obs;
	
	public String getDataVisitaFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtvisita());
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
	public Date getDtvisita() {
		return dtvisita;
	}
	public void setDtvisita(Date dtvisita) {
		this.dtvisita = dtvisita;
	}
	public String getTmmeddiaria() {
		return tmmeddiaria;
	}
	public void setTmmeddiaria(String tmmeddiaria) {
		this.tmmeddiaria = tmmeddiaria;
	}
	public String getRecindesej() {
		return recindesej;
	}
	public void setRecindesej(String recindesej) {
		this.recindesej = recindesej;
	}
	public String getExescar() {
		return exescar;
	}
	public void setExescar(String exescar) {
		this.exescar = exescar;
	}
	public Integer getComexami() {
		return comexami;
	}
	public void setComexami(Integer comexami) {
		this.comexami = comexami;
	}
	public Integer getMn5anoscombcg() {
		return mn5anoscombcg;
	}
	public void setMn5anoscombcg(Integer mn5anoscombcg) {
		this.mn5anoscombcg = mn5anoscombcg;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comexami == null) ? 0 : comexami.hashCode());
		result = prime * result
				+ ((dtvisita == null) ? 0 : dtvisita.hashCode());
		result = prime * result + ((exescar == null) ? 0 : exescar.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idMD5familiar == null) ? 0 : idMD5familiar.hashCode());
		result = prime * result
				+ ((mn5anoscombcg == null) ? 0 : mn5anoscombcg.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result
				+ ((recindesej == null) ? 0 : recindesej.hashCode());
		result = prime * result
				+ ((tmmeddiaria == null) ? 0 : tmmeddiaria.hashCode());
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
		Tuberculose other = (Tuberculose) obj;
		if (comexami == null) {
			if (other.comexami != null)
				return false;
		} else if (!comexami.equals(other.comexami))
			return false;
		if (dtvisita == null) {
			if (other.dtvisita != null)
				return false;
		} else if (!dtvisita.equals(other.dtvisita))
			return false;
		if (exescar == null) {
			if (other.exescar != null)
				return false;
		} else if (!exescar.equals(other.exescar))
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
		if (mn5anoscombcg == null) {
			if (other.mn5anoscombcg != null)
				return false;
		} else if (!mn5anoscombcg.equals(other.mn5anoscombcg))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (recindesej == null) {
			if (other.recindesej != null)
				return false;
		} else if (!recindesej.equals(other.recindesej))
			return false;
		if (tmmeddiaria == null) {
			if (other.tmmeddiaria != null)
				return false;
		} else if (!tmmeddiaria.equals(other.tmmeddiaria))
			return false;
		return true;
	}
	
	
	
}
