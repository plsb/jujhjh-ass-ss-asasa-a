package scs.solicExamesComplementares;

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
@Table(name = "solic_exames_compl")
public class SolicExamesComplem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private Integer patologia_clinica;
	private Integer radiodiagnostico;
	private Integer clitooatologico_cevico_vaginal;
	private Integer ultrasonografia_obstetica;
	private Integer outros;
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
	public Integer getPatologia_clinica() {
		return patologia_clinica;
	}
	public void setPatologia_clinica(Integer patologia_clinica) {
		this.patologia_clinica = patologia_clinica;
	}
	public Integer getRadiodiagnostico() {
		return radiodiagnostico;
	}
	public void setRadiodiagnostico(Integer radiodiagnostico) {
		this.radiodiagnostico = radiodiagnostico;
	}
	public Integer getClitooatologico_cevico_vaginal() {
		return clitooatologico_cevico_vaginal;
	}
	public void setClitooatologico_cevico_vaginal(
			Integer clitooatologico_cevico_vaginal) {
		this.clitooatologico_cevico_vaginal = clitooatologico_cevico_vaginal;
	}
	public Integer getUltrasonografia_obstetica() {
		return ultrasonografia_obstetica;
	}
	public void setUltrasonografia_obstetica(Integer ultrasonografia_obstetica) {
		this.ultrasonografia_obstetica = ultrasonografia_obstetica;
	}
	public Integer getOutros() {
		return outros;
	}
	public void setOutros(Integer outros) {
		this.outros = outros;
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
				+ ((clitooatologico_cevico_vaginal == null) ? 0
						: clitooatologico_cevico_vaginal.hashCode());
		result = prime * result
				+ ((data_cadastro == null) ? 0 : data_cadastro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((outros == null) ? 0 : outros.hashCode());
		result = prime
				* result
				+ ((patologia_clinica == null) ? 0 : patologia_clinica
						.hashCode());
		result = prime * result
				+ ((profissional == null) ? 0 : profissional.hashCode());
		result = prime
				* result
				+ ((radiodiagnostico == null) ? 0 : radiodiagnostico.hashCode());
		result = prime
				* result
				+ ((ultrasonografia_obstetica == null) ? 0
						: ultrasonografia_obstetica.hashCode());
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
		SolicExamesComplem other = (SolicExamesComplem) obj;
		if (clitooatologico_cevico_vaginal == null) {
			if (other.clitooatologico_cevico_vaginal != null)
				return false;
		} else if (!clitooatologico_cevico_vaginal
				.equals(other.clitooatologico_cevico_vaginal))
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
		if (outros == null) {
			if (other.outros != null)
				return false;
		} else if (!outros.equals(other.outros))
			return false;
		if (patologia_clinica == null) {
			if (other.patologia_clinica != null)
				return false;
		} else if (!patologia_clinica.equals(other.patologia_clinica))
			return false;
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		if (radiodiagnostico == null) {
			if (other.radiodiagnostico != null)
				return false;
		} else if (!radiodiagnostico.equals(other.radiodiagnostico))
			return false;
		if (ultrasonografia_obstetica == null) {
			if (other.ultrasonografia_obstetica != null)
				return false;
		} else if (!ultrasonografia_obstetica
				.equals(other.ultrasonografia_obstetica))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	
}
