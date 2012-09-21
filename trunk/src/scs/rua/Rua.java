package scs.rua;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.management.Query;
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

import org.hibernate.classic.Session;

import scs.bairro.Bairro;
import scs.microarea.Microarea;
import scs.util.HibernateUtil;

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
	private String descricao;
	@ManyToMany  
    @JoinTable(name = "microarea_ruas",    
      joinColumns = { @JoinColumn(name = "cod_rua")},  
      inverseJoinColumns={@JoinColumn(name="cod_microarea")}  
    )  
	private List<Microarea> microareaLista;
	  
		
	
	public List<Microarea> getMicroareaLista() {
		return microareaLista;
	}
	public void setMicroareaLista(List<Microarea> microareaLista) {
		this.microareaLista = microareaLista;
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
		this.descricao = descricao.toUpperCase();
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
				+ ((microareaLista == null) ? 0 : microareaLista.hashCode());
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
		if (microareaLista == null) {
			if (other.microareaLista != null)
				return false;
		} else if (!microareaLista.equals(other.microareaLista))
			return false;
		return true;
	}

}
