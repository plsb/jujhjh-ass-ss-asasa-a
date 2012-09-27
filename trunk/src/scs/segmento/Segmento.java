package scs.segmento;

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
@Table(name="segmento")
public class Segmento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer codigo_segmento;
	private Integer codigo;
	private String se_e_urbano_rural;
	@ManyToOne
	@JoinColumn(name="idbairro")
	private Bairro bairro;
	
	
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public Integer getCodigo_segmento() {
		return codigo_segmento;
	}
	public void setCodigo_segmento(Integer codigo_segmento) {
		this.codigo_segmento = codigo_segmento;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getSe_e_urbano_rural() {
		return se_e_urbano_rural;
	}
	public String getZona() {
		String zona="";
		if (se_e_urbano_rural.equalsIgnoreCase("u")){
			zona = "Zona Urbana";
		} else {
			zona = "Zona Rural";
		}
		return zona;
	}	
	public String pegaSegmento(){
		return "Código: "+getCodigo().toString()+" | Zona: "+getZona();
	}
	public void setSe_e_urbano_rural(String se_e_urbano_rural) {
		this.se_e_urbano_rural = se_e_urbano_rural;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigo_segmento == null) ? 0 : codigo_segmento.hashCode());
		result = prime
				* result
				+ ((se_e_urbano_rural == null) ? 0 : se_e_urbano_rural
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
		Segmento other = (Segmento) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigo_segmento == null) {
			if (other.codigo_segmento != null)
				return false;
		} else if (!codigo_segmento.equals(other.codigo_segmento))
			return false;
		if (se_e_urbano_rural == null) {
			if (other.se_e_urbano_rural != null)
				return false;
		} else if (!se_e_urbano_rural.equals(other.se_e_urbano_rural))
			return false;
		return true;
	}
	
	

}
