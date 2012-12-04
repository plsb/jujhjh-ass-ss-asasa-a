package scs.visitasDomiciliares;

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
import scs.usuario.Usuario;

@Entity
@Table(name = "visitas_domiciliares")
public class VisitasDomiciliares implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6016293493919855825L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer quantidade;
	private String tipovisita;
	@ManyToOne
	@JoinColumn(name = "idunidade")
	private Unidade unidade;
	private Date dtcadastro;
	
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtcadastro());
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipovisita() {
		return tipovisita;
	}
	public void setTipovisita(String tipovisita) {
		this.tipovisita = tipovisita;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtcadastro == null) ? 0 : dtcadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result
				+ ((tipovisita == null) ? 0 : tipovisita.hashCode());
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
		VisitasDomiciliares other = (VisitasDomiciliares) obj;
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
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (tipovisita == null) {
			if (other.tipovisita != null)
				return false;
		} else if (!tipovisita.equals(other.tipovisita))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	
	
	

}
