package scs.area;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.bairro.Bairro;
import scs.segmento.Segmento;
import scs.unidade.Unidade;

@Entity
@Table(name = "area")
public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Area() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	private Integer codigo_area;
	private Integer codigo;		
	@ManyToOne
	@JoinColumn(name="idunidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name="codigo_segmento")
	private Segmento segmento;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo_area() {
		return codigo_area;
	}
	public void setCodigo_area(Integer codigo_area) {
		this.codigo_area = codigo_area;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Segmento getSegmento() {
		return segmento;
	}
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigo_area == null) ? 0 : codigo_area.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		Area other = (Area) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigo_area == null) {
			if (other.codigo_area != null)
				return false;
		} else if (!codigo_area.equals(other.codigo_area))
			return false;
		if (segmento == null) {
			if (other.segmento != null)
				return false;
		} else if (!segmento.equals(other.segmento))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	
}
