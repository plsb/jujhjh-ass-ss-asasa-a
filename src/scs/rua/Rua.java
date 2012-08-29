package scs.rua;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.bairro.Bairro;

@Entity
@Table(name = "ruas")
public class Rua implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cod")
	@SequenceGenerator(name = "cod", sequenceName = "ruas_codigo_rua_seq")
	private Integer codigo_rua;
	@org.hibernate.annotations.NaturalId
	private String descricao;
	@ManyToOne
	@JoinColumn(name="COD_BAIRRO")
	private Bairro bairro;
	
	public Integer getCodigo_rua() {
		return codigo_rua;
	}
	public void setCodigo_rua(Integer codigo_rua) {
		this.codigo_rua = codigo_rua;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((codigo_rua == null) ? 0 : codigo_rua.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Rua other = (Rua) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (codigo_rua == null) {
			if (other.codigo_rua != null)
				return false;
		} else if (!codigo_rua.equals(other.codigo_rua))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
	
	
	
	

}
