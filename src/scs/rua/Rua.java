package scs.rua;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.bairro.Bairro;
import scs.microarea.Microarea;

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
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	 @JoinTable(
	  name = "microarea_ruas",
	  joinColumns = @JoinColumn(name = "cod_rua"), inverseJoinColumns = @JoinColumn(name = "cod_microarea")
	 )
	private  Collection<Microarea> microAreaLista;
	
	public Collection<Microarea> getMicroAreaLista() {
		return microAreaLista;
	}
	public void setMicroAreaLista(Collection<Microarea> microAreaLista) {
		this.microAreaLista = microAreaLista;
	}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigo_rua == null) ? 0 : codigo_rua.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((microAreaLista == null) ? 0 : microAreaLista.hashCode());
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
		if (microAreaLista == null) {
			if (other.microAreaLista != null)
				return false;
		} else if (!microAreaLista.equals(other.microAreaLista))
			return false;
		return true;
	}
	
	
	
	

}
