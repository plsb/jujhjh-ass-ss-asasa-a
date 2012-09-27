package scs.equipe;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.bairro.Bairro;

@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer codigo_equipe;
	private Integer codigo;
	private Integer codigo_municipio;
	private String acs;
	public Integer getCodigo_equipe() {
		return codigo_equipe;
	}
	public void setCodigo_equipe(Integer codigo_equipe) {
		this.codigo_equipe = codigo_equipe;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo_municipio() {
		return codigo_municipio;
	}
	public void setCodigo_municipio(Integer codigo_municipio) {
		this.codigo_municipio = codigo_municipio;
	}
	public String getAcs() {
		return acs;
	}
	public void setAcs(String acs) {
		this.acs = acs;
	}
	
}
