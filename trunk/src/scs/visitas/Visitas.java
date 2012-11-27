package scs.visitas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import scs.usuario.Usuario;


@Entity
@Table(name="visitas")
public class Visitas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859981681425193405L;

	@Id
	@GeneratedValue
	private Integer id;
	private Date data;
	@ManyToOne
	@JoinColumn(name = "id_agente")
	private Usuario agente;
	private Date hora;
	private String endereco;
	private Integer numero;
	private String complemento;
	private String casa_fechada;
	private String visita_confirmada;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getAgente() {
		return agente;
	}
	public void setAgente(Usuario agente) {
		this.agente = agente;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCasa_fechada() {
		return casa_fechada;
	}
	public void setCasa_fechada(String casa_fechada) {
		this.casa_fechada = casa_fechada;
	}
	public String getVisita_confirmada() {
		return visita_confirmada;
	}
	public void setVisita_confirmada(String visita_confirmada) {
		this.visita_confirmada = visita_confirmada;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agente == null) ? 0 : agente.hashCode());
		result = prime * result
				+ ((casa_fechada == null) ? 0 : casa_fechada.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime
				* result
				+ ((visita_confirmada == null) ? 0 : visita_confirmada
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
		Visitas other = (Visitas) obj;
		if (agente == null) {
			if (other.agente != null)
				return false;
		} else if (!agente.equals(other.agente))
			return false;
		if (casa_fechada == null) {
			if (other.casa_fechada != null)
				return false;
		} else if (!casa_fechada.equals(other.casa_fechada))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (visita_confirmada == null) {
			if (other.visita_confirmada != null)
				return false;
		} else if (!visita_confirmada.equals(other.visita_confirmada))
			return false;
		return true;
	}
	
	
	
}
