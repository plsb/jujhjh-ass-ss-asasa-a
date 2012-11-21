package scs.familiar;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import scs.rua.Rua;
import scs.segmento.Segmento;
import scs.unidade.Unidade;

@Entity
@Table(name = "familiar")
public class Familiar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7904382777613204984L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="rua")
	private Rua ruaFamilia;
	private String idMD5;
	private String nome;
	private Integer numero;
	private Date dataNascimento;
	private char sexo;
	private char freqEsc;
	private char alfabetizado;
	private String ocupacao;
	private boolean hanseniase;
	private boolean hipertensao;
	private boolean gestante;
	private boolean tuberculose;
	private boolean alcolismo;
	private boolean chagas;
	private boolean deficiencia;
	private boolean malaria;
	private boolean diabestes;
	private boolean epilepsia;
	private String nomepai;
	private String nomemae;
	private String complemento;
	
	
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getNomepai() {
		return nomepai;
	}
	public void setNomepai(String nomepai) {
		this.nomepai = nomepai.toUpperCase();
	}
	public String getNomemae() {
		return nomemae;
	}
	public void setNomemae(String nomemae) {
		this.nomemae = nomemae.toUpperCase();
	}
	public String getDataFormtada(){
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
		return formatador.format(getDataNascimento());
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdMD5() {
		return idMD5;
	}
	public void setIdMD5(String idMD5) {
		this.idMD5 = idMD5;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	public Rua getRuaFamilia() {
		return ruaFamilia;
	}
	public void setRuaFamilia(Rua ruaFamilia) {
		this.ruaFamilia = ruaFamilia;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public char getFreqEsc() {
		return freqEsc;
	}
	public void setFreqEsc(char freqEsc) {
		this.freqEsc = freqEsc;
	}
	public char getAlfabetizado() {
		return alfabetizado;
	}
	public void setAlfabetizado(char alfabetizado) {
		this.alfabetizado = alfabetizado;
	}
	public String getOcupacao() {
		return ocupacao;
	}
	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao.toUpperCase();
	}
	public boolean getHanseniase() {
		return hanseniase;
	}
	public void setHanseniase(boolean hanseniase) {
		this.hanseniase = hanseniase;
	}
	public boolean getHipertensao() {
		return hipertensao;
	}
	public void setHipertensao(boolean hipertensao) {
		this.hipertensao = hipertensao;
	}
	public boolean getGestante() {
		return gestante;
	}
	public void setGestante(boolean gestante) {
		this.gestante = gestante;
	}
	public boolean getTuberculose() {
		return tuberculose;
	}
	public void setTuberculose(boolean tuberculose) {
		this.tuberculose = tuberculose;
	}
	public boolean getAlcolismo() {
		return alcolismo;
	}
	public void setAlcolismo(boolean alcolismo) {
		this.alcolismo = alcolismo;
	}
	public boolean getChagas() {
		return chagas;
	}
	public void setChagas(boolean chagas) {
		this.chagas = chagas;
	}
	public boolean getDeficiencia() {
		return deficiencia;
	}
	public void setDeficiencia(boolean deficiencia) {
		this.deficiencia = deficiencia;
	}
	public boolean getMalaria() {
		return malaria;
	}
	public void setMalaria(boolean malaria) {
		this.malaria = malaria;
	}
	public boolean getDiabestes() {
		return diabestes;
	}
	public void setDiabestes(boolean diabestes) {
		this.diabestes = diabestes;
	}
	public boolean getEpilepsia() {
		return epilepsia;
	}
	public void setEpilepsia(boolean epilepsia) {
		this.epilepsia = epilepsia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alcolismo ? 1231 : 1237);
		result = prime * result + alfabetizado;
		result = prime * result + (chagas ? 1231 : 1237);
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + (deficiencia ? 1231 : 1237);
		result = prime * result + (diabestes ? 1231 : 1237);
		result = prime * result + (epilepsia ? 1231 : 1237);
		result = prime * result + freqEsc;
		result = prime * result + (gestante ? 1231 : 1237);
		result = prime * result + (hanseniase ? 1231 : 1237);
		result = prime * result + (hipertensao ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idMD5 == null) ? 0 : idMD5.hashCode());
		result = prime * result + (malaria ? 1231 : 1237);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomemae == null) ? 0 : nomemae.hashCode());
		result = prime * result + ((nomepai == null) ? 0 : nomepai.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((ocupacao == null) ? 0 : ocupacao.hashCode());
		result = prime * result
				+ ((ruaFamilia == null) ? 0 : ruaFamilia.hashCode());
		result = prime * result + sexo;
		result = prime * result + (tuberculose ? 1231 : 1237);
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
		Familiar other = (Familiar) obj;
		if (alcolismo != other.alcolismo)
			return false;
		if (alfabetizado != other.alfabetizado)
			return false;
		if (chagas != other.chagas)
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (deficiencia != other.deficiencia)
			return false;
		if (diabestes != other.diabestes)
			return false;
		if (epilepsia != other.epilepsia)
			return false;
		if (freqEsc != other.freqEsc)
			return false;
		if (gestante != other.gestante)
			return false;
		if (hanseniase != other.hanseniase)
			return false;
		if (hipertensao != other.hipertensao)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idMD5 == null) {
			if (other.idMD5 != null)
				return false;
		} else if (!idMD5.equals(other.idMD5))
			return false;
		if (malaria != other.malaria)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomemae == null) {
			if (other.nomemae != null)
				return false;
		} else if (!nomemae.equals(other.nomemae))
			return false;
		if (nomepai == null) {
			if (other.nomepai != null)
				return false;
		} else if (!nomepai.equals(other.nomepai))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (ocupacao == null) {
			if (other.ocupacao != null)
				return false;
		} else if (!ocupacao.equals(other.ocupacao))
			return false;
		if (ruaFamilia == null) {
			if (other.ruaFamilia != null)
				return false;
		} else if (!ruaFamilia.equals(other.ruaFamilia))
			return false;
		if (sexo != other.sexo)
			return false;
		if (tuberculose != other.tuberculose)
			return false;
		return true;
	}

}
