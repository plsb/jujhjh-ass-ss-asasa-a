package scs.acompanhamentoPadrao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="acompanhamento_padrao")
public class AcompanhamentoPadrao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3620802321208682261L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private boolean hospitalizada;
	private String motivo_hospitalizacao;
	private boolean doente;
	private String qual_doenca;
	private String observacao;
	private String idfamiliar;
	private Date dataVisita;
	
	
	public String getDataVisitaFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDataVisita());
	}	
	public Date getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataVisita == null) ? 0 : dataVisita.hashCode());
		result = prime * result + (doente ? 1231 : 1237);
		result = prime * result + (hospitalizada ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idfamiliar == null) ? 0 : idfamiliar.hashCode());
		result = prime
				* result
				+ ((motivo_hospitalizacao == null) ? 0 : motivo_hospitalizacao
						.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((qual_doenca == null) ? 0 : qual_doenca.hashCode());
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
		AcompanhamentoPadrao other = (AcompanhamentoPadrao) obj;
		if (dataVisita == null) {
			if (other.dataVisita != null)
				return false;
		} else if (!dataVisita.equals(other.dataVisita))
			return false;
		if (doente != other.doente)
			return false;
		if (hospitalizada != other.hospitalizada)
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
		if (motivo_hospitalizacao == null) {
			if (other.motivo_hospitalizacao != null)
				return false;
		} else if (!motivo_hospitalizacao.equals(other.motivo_hospitalizacao))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (qual_doenca == null) {
			if (other.qual_doenca != null)
				return false;
		} else if (!qual_doenca.equals(other.qual_doenca))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean getHospitalizada() {
		return hospitalizada;
	}
	public void setHospitalizada(boolean hospitalizada) {
		this.hospitalizada = hospitalizada;
	}
	public String getMotivo_hospitalizacao() {
		return motivo_hospitalizacao;
	}
	public void setMotivo_hospitalizacao(String motivo_hospitalizacao) {
		this.motivo_hospitalizacao = motivo_hospitalizacao;
	}
	public boolean getDoente() {
		return doente;
	}
	public void setDoente(boolean doente) {
		this.doente = doente;
	}
	public String getQual_doenca() {
		return qual_doenca;
	}
	public void setQual_doenca(String qual_doenca) {
		this.qual_doenca = qual_doenca;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getIdfamiliar() {
		return idfamiliar;
	}
	public void setIdfamiliar(String idfamiliar) {
		this.idfamiliar = idfamiliar;
	}
	
}
