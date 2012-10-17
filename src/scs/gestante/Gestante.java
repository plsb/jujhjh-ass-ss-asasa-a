package scs.gestante;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gestante")
public class Gestante implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -793953926868792267L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String idMD5familiar;
	private Date dtVisita;
	private Date dtUltRegra;
	private Date dtProvavelParto;
	private String estNutricional;
	private Integer mesGestacao;
	private Date dtConsulPreNatal;
	private boolean fr6mGestacao;
	private boolean fr36ouMais;
	private boolean frSangramento;
	private boolean frDiabetes;
	private boolean frNatrimAborto;
	private boolean frMeno20anos;
	private boolean frEdema;
	private boolean frPressaoAlta;
	private Date dtConsPuerbio;
	private String obs;
	private String resultado_gestacao;
	
	public String getDataVisitaFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDtVisita());
	}
	
	public String getResultado_gestacao() {
		return resultado_gestacao;
	}

	public void setResultado_gestacao(String resultado_gestacao) {
		this.resultado_gestacao = resultado_gestacao;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdMD5familiar() {
		return idMD5familiar;
	}
	public void setIdMD5familiar(String idMD5familiar) {
		this.idMD5familiar = idMD5familiar;
	}
	public Date getDtVisita() {
		return dtVisita;
	}
	public void setDtVisita(Date dtVisita) {
		this.dtVisita = dtVisita;
	}
	public Date getDtUltRegra() {
		return dtUltRegra;
	}
	public void setDtUltRegra(Date dtUltRegra) {
		this.dtUltRegra = dtUltRegra;
	}
	public Date getDtProvavelParto() {
		return dtProvavelParto;
	}
	public void setDtProvavelParto(Date dtProvavelParto) {
		this.dtProvavelParto = dtProvavelParto;
	}
	public String getEstNutricional() {
		return estNutricional;
	}
	public void setEstNutricional(String estNutricional) {
		this.estNutricional = estNutricional;
	}
	public Integer getMesGestacao() {
		return mesGestacao;
	}
	public void setMesGestacao(Integer mesGestacao) {
		this.mesGestacao = mesGestacao;
	}
	public Date getDtConsulPreNatal() {
		return dtConsulPreNatal;
	}
	public void setDtConsulPreNatal(Date dtConsulPreNatal) {
		this.dtConsulPreNatal = dtConsulPreNatal;
	}
	public boolean isFr6mGestacao() {
		return fr6mGestacao;
	}
	public void setFr6mGestacao(boolean fr6mGestacao) {
		this.fr6mGestacao = fr6mGestacao;
	}
	public boolean isFr36ouMais() {
		return fr36ouMais;
	}
	public void setFr36ouMais(boolean fr36ouMais) {
		this.fr36ouMais = fr36ouMais;
	}
	public boolean isFrSangramento() {
		return frSangramento;
	}
	public void setFrSangramento(boolean frSangramento) {
		this.frSangramento = frSangramento;
	}
	public boolean isFrDiabetes() {
		return frDiabetes;
	}
	public void setFrDiabetes(boolean frDiabetes) {
		this.frDiabetes = frDiabetes;
	}
	public boolean isFrNatrimAborto() {
		return frNatrimAborto;
	}
	public void setFrNatrimAborto(boolean frNatrimAborto) {
		this.frNatrimAborto = frNatrimAborto;
	}
	public boolean isFrMeno20anos() {
		return frMeno20anos;
	}
	public void setFrMeno20anos(boolean frMeno20anos) {
		this.frMeno20anos = frMeno20anos;
	}
	public boolean isFrEdema() {
		return frEdema;
	}
	public void setFrEdema(boolean frEdema) {
		this.frEdema = frEdema;
	}
	public boolean isFrPressaoAlta() {
		return frPressaoAlta;
	}
	public void setFrPressaoAlta(boolean frPressaoAlta) {
		this.frPressaoAlta = frPressaoAlta;
	}
	public Date getDtConsPuerbio() {
		return dtConsPuerbio;
	}
	public void setDtConsPuerbio(Date dtConsPuerbio) {
		this.dtConsPuerbio = dtConsPuerbio;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs.toUpperCase();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtConsPuerbio == null) ? 0 : dtConsPuerbio.hashCode());
		result = prime
				* result
				+ ((dtConsulPreNatal == null) ? 0 : dtConsulPreNatal.hashCode());
		result = prime * result
				+ ((dtProvavelParto == null) ? 0 : dtProvavelParto.hashCode());
		result = prime * result
				+ ((dtUltRegra == null) ? 0 : dtUltRegra.hashCode());
		result = prime * result
				+ ((dtVisita == null) ? 0 : dtVisita.hashCode());
		result = prime * result
				+ ((estNutricional == null) ? 0 : estNutricional.hashCode());
		result = prime * result + (fr36ouMais ? 1231 : 1237);
		result = prime * result + (fr6mGestacao ? 1231 : 1237);
		result = prime * result + (frDiabetes ? 1231 : 1237);
		result = prime * result + (frEdema ? 1231 : 1237);
		result = prime * result + (frMeno20anos ? 1231 : 1237);
		result = prime * result + (frNatrimAborto ? 1231 : 1237);
		result = prime * result + (frPressaoAlta ? 1231 : 1237);
		result = prime * result + (frSangramento ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idMD5familiar == null) ? 0 : idMD5familiar.hashCode());
		result = prime * result
				+ ((mesGestacao == null) ? 0 : mesGestacao.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime
				* result
				+ ((resultado_gestacao == null) ? 0 : resultado_gestacao
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
		Gestante other = (Gestante) obj;
		if (dtConsPuerbio == null) {
			if (other.dtConsPuerbio != null)
				return false;
		} else if (!dtConsPuerbio.equals(other.dtConsPuerbio))
			return false;
		if (dtConsulPreNatal == null) {
			if (other.dtConsulPreNatal != null)
				return false;
		} else if (!dtConsulPreNatal.equals(other.dtConsulPreNatal))
			return false;
		if (dtProvavelParto == null) {
			if (other.dtProvavelParto != null)
				return false;
		} else if (!dtProvavelParto.equals(other.dtProvavelParto))
			return false;
		if (dtUltRegra == null) {
			if (other.dtUltRegra != null)
				return false;
		} else if (!dtUltRegra.equals(other.dtUltRegra))
			return false;
		if (dtVisita == null) {
			if (other.dtVisita != null)
				return false;
		} else if (!dtVisita.equals(other.dtVisita))
			return false;
		if (estNutricional == null) {
			if (other.estNutricional != null)
				return false;
		} else if (!estNutricional.equals(other.estNutricional))
			return false;
		if (fr36ouMais != other.fr36ouMais)
			return false;
		if (fr6mGestacao != other.fr6mGestacao)
			return false;
		if (frDiabetes != other.frDiabetes)
			return false;
		if (frEdema != other.frEdema)
			return false;
		if (frMeno20anos != other.frMeno20anos)
			return false;
		if (frNatrimAborto != other.frNatrimAborto)
			return false;
		if (frPressaoAlta != other.frPressaoAlta)
			return false;
		if (frSangramento != other.frSangramento)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idMD5familiar == null) {
			if (other.idMD5familiar != null)
				return false;
		} else if (!idMD5familiar.equals(other.idMD5familiar))
			return false;
		if (mesGestacao == null) {
			if (other.mesGestacao != null)
				return false;
		} else if (!mesGestacao.equals(other.mesGestacao))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (resultado_gestacao == null) {
			if (other.resultado_gestacao != null)
				return false;
		} else if (!resultado_gestacao.equals(other.resultado_gestacao))
			return false;
		return true;
	}
	
	

}
