package scs.encamMedicos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import scs.profissional.Profissional;
import scs.unidade.Unidade;

@Entity
@Table(name = "encammedicos")
public class EncaminhamentosMedicos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer atend_especializado;
	private Integer internacao_hospitalar;
	private Integer urgencia_emergencia;
	private Integer internacao_domicialiar;
	private Date data_cadastro;
	@ManyToOne
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name = "idprofissional")
	private Profissional profissional;
	
		
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getData_cadastro());
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAtend_especializado() {
		return atend_especializado;
	}
	public void setAtend_especializado(Integer atend_especializado) {
		if(atend_especializado==null){
			atend_especializado=0;
		}
		this.atend_especializado = atend_especializado;
	}
	public Integer getInternacao_hospitalar() {
		return internacao_hospitalar;
	}
	public void setInternacao_hospitalar(Integer internacao_hospitalar) {
		if(atend_especializado==null){
			atend_especializado=0;
		}
		this.internacao_hospitalar = internacao_hospitalar;
	}
	public Integer getUrgencia_emergencia() {
		return urgencia_emergencia;
	}
	public void setUrgencia_emergencia(Integer urgencia_emergencia) {
		this.urgencia_emergencia = urgencia_emergencia;
	}
	public Integer getInternacao_domicialiar() {
		return internacao_domicialiar;
	}
	public void setInternacao_domicialiar(Integer internacao_domicialiar) {
		this.internacao_domicialiar = internacao_domicialiar;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((atend_especializado == null) ? 0 : atend_especializado
						.hashCode());
		result = prime * result
				+ ((data_cadastro == null) ? 0 : data_cadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((internacao_domicialiar == null) ? 0
						: internacao_domicialiar.hashCode());
		result = prime
				* result
				+ ((internacao_hospitalar == null) ? 0 : internacao_hospitalar
						.hashCode());
		result = prime * result
				+ ((profissional == null) ? 0 : profissional.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		result = prime
				* result
				+ ((urgencia_emergencia == null) ? 0 : urgencia_emergencia
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
		EncaminhamentosMedicos other = (EncaminhamentosMedicos) obj;
		if (atend_especializado == null) {
			if (other.atend_especializado != null)
				return false;
		} else if (!atend_especializado.equals(other.atend_especializado))
			return false;
		if (data_cadastro == null) {
			if (other.data_cadastro != null)
				return false;
		} else if (!data_cadastro.equals(other.data_cadastro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (internacao_domicialiar == null) {
			if (other.internacao_domicialiar != null)
				return false;
		} else if (!internacao_domicialiar.equals(other.internacao_domicialiar))
			return false;
		if (internacao_hospitalar == null) {
			if (other.internacao_hospitalar != null)
				return false;
		} else if (!internacao_hospitalar.equals(other.internacao_hospitalar))
			return false;
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		if (urgencia_emergencia == null) {
			if (other.urgencia_emergencia != null)
				return false;
		} else if (!urgencia_emergencia.equals(other.urgencia_emergencia))
			return false;
		return true;
	}
	
	
	

}
