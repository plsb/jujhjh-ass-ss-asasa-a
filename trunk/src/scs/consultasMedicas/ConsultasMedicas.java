package scs.consultasMedicas;

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

import scs.unidade.Unidade;

@Entity
@Table(name = "cons_medicas")
public class ConsultasMedicas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6427360252079771049L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer quantidadeForaAreaAbragencia;
	private String tipo;
	private Integer quantidadeAreaAbrangencia;
	private Date dtcadastro;
	@ManyToOne
	@JoinColumn(name = "idunidade")
	private Unidade unidade;
	
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtcadastro());
	}
	public String getTipoFormatada(){
		if((quantidadeAreaAbrangencia==null)||(tipo==null)){
			return "Residentes fora da área de abragência";			
		} else {
			return "Residentes na área de abragência";
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidadeForaAreaAbragencia() {
		return quantidadeForaAreaAbragencia;
	}
	public void setQuantidadeForaAreaAbragencia(Integer quantidadeForaAreaAbragencia) {
		this.quantidadeForaAreaAbragencia = quantidadeForaAreaAbragencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getQuantidadeAreaAbrangencia() {
		return quantidadeAreaAbrangencia;
	}
	public void setQuantidadeAreaAbrangencia(Integer quantidadeAreaAbrangencia) {
		this.quantidadeAreaAbrangencia = quantidadeAreaAbrangencia;
	}
	public Date getDtcadastro() {
		return dtcadastro;
	}
	public void setDtcadastro(Date dtcadastro) {
		this.dtcadastro = dtcadastro;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtcadastro == null) ? 0 : dtcadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((quantidadeAreaAbrangencia == null) ? 0
						: quantidadeAreaAbrangencia.hashCode());
		result = prime
				* result
				+ ((quantidadeForaAreaAbragencia == null) ? 0
						: quantidadeForaAreaAbragencia.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ConsultasMedicas other = (ConsultasMedicas) obj;
		if (dtcadastro == null) {
			if (other.dtcadastro != null)
				return false;
		} else if (!dtcadastro.equals(other.dtcadastro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantidadeAreaAbrangencia == null) {
			if (other.quantidadeAreaAbrangencia != null)
				return false;
		} else if (!quantidadeAreaAbrangencia
				.equals(other.quantidadeAreaAbrangencia))
			return false;
		if (quantidadeForaAreaAbragencia == null) {
			if (other.quantidadeForaAreaAbragencia != null)
				return false;
		} else if (!quantidadeForaAreaAbragencia
				.equals(other.quantidadeForaAreaAbragencia))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	

}
