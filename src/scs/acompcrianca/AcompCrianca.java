package scs.acompcrianca;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acompcrianca")
public class AcompCrianca implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2084564253406880146L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private double altura;
	private double peso;
	private double perimetrocefalico;
	private double apgar;
	private String tipoparto;
	private String obs;
	private String idfamiliar;
	private Date dtvisita;
	private String situacao;
	
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
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
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getPerimetrocefalico() {
		return perimetrocefalico;
	}
	public void setPerimetrocefalico(double perimetrocefalico) {
		this.perimetrocefalico = perimetrocefalico;
	}
	public double getApgar() {
		return apgar;
	}
	public void setApgar(double apgar) {
		this.apgar = apgar;
	}
	public String getTipoparto() {
		return tipoparto;
	}
	public void setTipoparto(String tipoparto) {
		this.tipoparto = tipoparto;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getIdfamiliar() {
		return idfamiliar;
	}
	public void setIdfamiliar(String idfamiliar) {
		this.idfamiliar = idfamiliar;
	}
	public Date getDtvisita() {
		return dtvisita;
	}
	public void setDtvisita(Date dtvisita) {
		this.dtvisita = dtvisita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(altura);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(apgar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((dtvisita == null) ? 0 : dtvisita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idfamiliar == null) ? 0 : idfamiliar.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		temp = Double.doubleToLongBits(perimetrocefalico);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((tipoparto == null) ? 0 : tipoparto.hashCode());
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
		AcompCrianca other = (AcompCrianca) obj;
		if (Double.doubleToLongBits(altura) != Double
				.doubleToLongBits(other.altura))
			return false;
		if (Double.doubleToLongBits(apgar) != Double
				.doubleToLongBits(other.apgar))
			return false;
		if (dtvisita == null) {
			if (other.dtvisita != null)
				return false;
		} else if (!dtvisita.equals(other.dtvisita))
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
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (Double.doubleToLongBits(perimetrocefalico) != Double
				.doubleToLongBits(other.perimetrocefalico))
			return false;
		if (Double.doubleToLongBits(peso) != Double
				.doubleToLongBits(other.peso))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (tipoparto == null) {
			if (other.tipoparto != null)
				return false;
		} else if (!tipoparto.equals(other.tipoparto))
			return false;
		return true;
	}
	
}
