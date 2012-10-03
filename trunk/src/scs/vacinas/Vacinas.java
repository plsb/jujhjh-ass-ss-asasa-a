package scs.vacinas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacinas")
public class Vacinas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8030382739899567037L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String idfamiliar;
	private String loteVacina;
	private String tipoVacina;
	private String doseAplicada;
	private Date dataAplicacao;
	private String tipo;
	private boolean aplicada;
	
	public String getDoseAplicada() {
		return doseAplicada;
	}
	public void setDoseAplicada(String doseAplicada) {
		this.doseAplicada = doseAplicada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aplicada ? 1231 : 1237);
		result = prime * result
				+ ((dataAplicacao == null) ? 0 : dataAplicacao.hashCode());
		result = prime * result
				+ ((doseAplicada == null) ? 0 : doseAplicada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idfamiliar == null) ? 0 : idfamiliar.hashCode());
		result = prime * result
				+ ((loteVacina == null) ? 0 : loteVacina.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result
				+ ((tipoVacina == null) ? 0 : tipoVacina.hashCode());
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
		Vacinas other = (Vacinas) obj;
		if (aplicada != other.aplicada)
			return false;
		if (dataAplicacao == null) {
			if (other.dataAplicacao != null)
				return false;
		} else if (!dataAplicacao.equals(other.dataAplicacao))
			return false;
		if (doseAplicada == null) {
			if (other.doseAplicada != null)
				return false;
		} else if (!doseAplicada.equals(other.doseAplicada))
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
		if (loteVacina == null) {
			if (other.loteVacina != null)
				return false;
		} else if (!loteVacina.equals(other.loteVacina))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (tipoVacina == null) {
			if (other.tipoVacina != null)
				return false;
		} else if (!tipoVacina.equals(other.tipoVacina))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdfamiliar() {
		return idfamiliar;
	}
	public void setIdfamiliar(String idfamiliar) {
		this.idfamiliar = idfamiliar;
	}
	public String getLoteVacina() {
		return loteVacina;
	}
	public void setLoteVacina(String loteVacina) {
		this.loteVacina = loteVacina;
	}
	public String getTipoVacina() {
		return tipoVacina;
	}
	public void setTipoVacina(String tipoVacina) {
		this.tipoVacina = tipoVacina;
	}
	public Date getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isAplicada() {
		return aplicada;
	}
	public void setAplicada(boolean aplicada) {
		this.aplicada = aplicada;
	}
	
	

}
