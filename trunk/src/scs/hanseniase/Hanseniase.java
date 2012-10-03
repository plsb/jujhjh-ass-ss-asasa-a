package scs.hanseniase;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hanseniase")
public class Hanseniase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3690839527114801397L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String idMD5familiar;
	private Date dtVisita;
	private String tmMedicacaoDiaria;
	private Date dtUtDoseSupervisionada;
	private String fzAutosCuidados;
	private Integer comExaminados;
	private Integer cmRecebBCG;
	private String observacoes;
	private Date dtUltCOnsulta;
	
	public String getDataVisitaFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtVisita());
	}	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cmRecebBCG == null) ? 0 : cmRecebBCG.hashCode());
		result = prime * result
				+ ((comExaminados == null) ? 0 : comExaminados.hashCode());
		result = prime * result
				+ ((dtUltCOnsulta == null) ? 0 : dtUltCOnsulta.hashCode());
		result = prime
				* result
				+ ((dtUtDoseSupervisionada == null) ? 0
						: dtUtDoseSupervisionada.hashCode());
		result = prime * result
				+ ((dtVisita == null) ? 0 : dtVisita.hashCode());
		result = prime * result
				+ ((fzAutosCuidados == null) ? 0 : fzAutosCuidados.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idMD5familiar == null) ? 0 : idMD5familiar.hashCode());
		result = prime * result
				+ ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime
				* result
				+ ((tmMedicacaoDiaria == null) ? 0 : tmMedicacaoDiaria
						.hashCode());
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
		Hanseniase other = (Hanseniase) obj;
		if (cmRecebBCG == null) {
			if (other.cmRecebBCG != null)
				return false;
		} else if (!cmRecebBCG.equals(other.cmRecebBCG))
			return false;
		if (comExaminados == null) {
			if (other.comExaminados != null)
				return false;
		} else if (!comExaminados.equals(other.comExaminados))
			return false;
		if (dtUltCOnsulta == null) {
			if (other.dtUltCOnsulta != null)
				return false;
		} else if (!dtUltCOnsulta.equals(other.dtUltCOnsulta))
			return false;
		if (dtUtDoseSupervisionada == null) {
			if (other.dtUtDoseSupervisionada != null)
				return false;
		} else if (!dtUtDoseSupervisionada.equals(other.dtUtDoseSupervisionada))
			return false;
		if (dtVisita == null) {
			if (other.dtVisita != null)
				return false;
		} else if (!dtVisita.equals(other.dtVisita))
			return false;
		if (fzAutosCuidados == null) {
			if (other.fzAutosCuidados != null)
				return false;
		} else if (!fzAutosCuidados.equals(other.fzAutosCuidados))
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
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (tmMedicacaoDiaria == null) {
			if (other.tmMedicacaoDiaria != null)
				return false;
		} else if (!tmMedicacaoDiaria.equals(other.tmMedicacaoDiaria))
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
	public Date getDtVisita() {
		return dtVisita;
	}
	public void setDtVisita(Date dtVisita) {
		this.dtVisita = dtVisita;
	}
	public String getTmMedicacaoDiaria() {
		return tmMedicacaoDiaria;
	}
	public void setTmMedicacaoDiaria(String tmMedicacaoDiaria) {
		this.tmMedicacaoDiaria = tmMedicacaoDiaria.toUpperCase();
	}
	public Date getDtUtDoseSupervisionada() {
		return dtUtDoseSupervisionada;
	}
	public void setDtUtDoseSupervisionada(Date dtUtDoseSupervisionada) {
		this.dtUtDoseSupervisionada = dtUtDoseSupervisionada;
	}
	public String getFzAutosCuidados() {
		return fzAutosCuidados;
	}
	public void setFzAutosCuidados(String fzAutosCuidados) {
		this.fzAutosCuidados = fzAutosCuidados.toUpperCase();
	}
	public Integer getComExaminados() {
		return comExaminados;
	}
	public void setComExaminados(Integer comExaminados) {
		this.comExaminados = comExaminados;
	}
	public Integer getCmRecebBCG() {
		return cmRecebBCG;
	}
	public void setCmRecebBCG(Integer cmRecebBCG) {
		this.cmRecebBCG = cmRecebBCG;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes.toUpperCase();
	}
	public Date getDtUltCOnsulta() {
		return dtUltCOnsulta;
	}
	public void setDtUltCOnsulta(Date dtUltCOnsulta) {
		this.dtUltCOnsulta = dtUltCOnsulta;
	}
	
	
	
	
	
	
	
	
	

}
