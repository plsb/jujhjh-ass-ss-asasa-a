package scs.usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="funcionario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7390392097656238443L;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO, generator="cod")  
	@SequenceGenerator(name="cod", sequenceName="funcionario_codigo_seq")
	private Integer codigo;
	@org.hibernate.annotations.NaturalId
	private String nome;
	@org.hibernate.annotations.NaturalId
	private String cpf;
	private String rg;
	private Date data_nasc;
	private String cel;
	private String telefone;
	private String matricula;
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String nome_mae;
	private String nome_pai;
	private String email;	
	private String titulo_eleitor;	
	private String pis;
	private String ctps;
	private String especialidade;
	private String portaria;
	private String conselho_regional;		
	private boolean concursado;
	private Date data_nomeacao;
	private Date data_admissao;		
	private String bd_horas;
	@org.hibernate.annotations.NaturalId
	private String login;
	private String senha;
	private boolean ativo;
	private String tipofuncionario;
	
	@ElementCollection(targetClass=String.class)
	@JoinTable(
			name="usuario_permissao",
			uniqueConstraints={@UniqueConstraint(columnNames={"funcionario","permissao"})},
			joinColumns=@JoinColumn(name="funcionario"))
	@Column(name="permissao",length=50)
	private Set<String> permissao = new HashSet<String>();

	public String getMaskCpf(){  
		   return cpf.substring(0, 3)+"."+cpf.substring(3, 6)+"."+cpf.substring(6, 9)+"-"+cpf.substring(9);  
	}  
	
	public Integer getCodigo() {
		return codigo;
	}
	

	public String getTipofuncionario() {
		return tipofuncionario;
	}
	
	public String getTipofuncionario2() {
		String tipo="";
		if (tipofuncionario.equalsIgnoreCase("O")) {
			tipo = "Outros";
		} else if (tipofuncionario.equalsIgnoreCase("A")) {
			tipo = "Agente";
		} else if (tipofuncionario.equalsIgnoreCase("C")) {
			tipo = "Coordenador";
		}
		return tipo;
	}

	public void setTipofuncionario(String tipofuncionario) {
		this.tipofuncionario = tipofuncionario;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Set<String> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<String> permissao) {
		this.permissao = permissao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTitulo_eleitor() {
		return titulo_eleitor;
	}

	public void setTitulo_eleitor(String tituloEleitor) {
		this.titulo_eleitor = tituloEleitor;
	}

	public String getNome_mae() {
		return nome_mae;
	}

	public void setNome_mae(String nomeMae) {
		this.nome_mae = nomeMae;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getData_nomeacao() {
		return data_nomeacao;
	}

	public void setData_nomeacao(Date data_nomeacao) {
		this.data_nomeacao = data_nomeacao;
	}

	public Date getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Date data_admissao) {
		this.data_admissao = data_admissao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isConcursado() {
		return concursado;
	}

	public void setConcursado(boolean concursado) {
		this.concursado = concursado;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getNome_pai() {
		return nome_pai;
	}

	public void setNome_pai(String nome_pai) {
		this.nome_pai = nome_pai;
	}

	public String getBd_horas() {
		return bd_horas;
	}

	public void setBd_horas(String bd_horas) {
		this.bd_horas = bd_horas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPortaria() {
		return portaria;
	}

	public void setPortaria(String portaria) {
		this.portaria = portaria;
	}

	public String getConselho_regional() {
		return conselho_regional;
	}

	public void setConselho_regional(String conselho_regional) {
		this.conselho_regional = conselho_regional;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((bd_horas == null) ? 0 : bd_horas.hashCode());
		result = prime * result + ((cel == null) ? 0 : cel.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + (concursado ? 1231 : 1237);
		result = prime
				* result
				+ ((conselho_regional == null) ? 0 : conselho_regional
						.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((ctps == null) ? 0 : ctps.hashCode());
		result = prime * result
				+ ((data_admissao == null) ? 0 : data_admissao.hashCode());
		result = prime * result
				+ ((data_nasc == null) ? 0 : data_nasc.hashCode());
		result = prime * result
				+ ((data_nomeacao == null) ? 0 : data_nomeacao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((nome_mae == null) ? 0 : nome_mae.hashCode());
		result = prime * result
				+ ((nome_pai == null) ? 0 : nome_pai.hashCode());
		result = prime * result
				+ ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((pis == null) ? 0 : pis.hashCode());
		result = prime * result
				+ ((portaria == null) ? 0 : portaria.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result
				+ ((tipofuncionario == null) ? 0 : tipofuncionario.hashCode());
		result = prime * result
				+ ((titulo_eleitor == null) ? 0 : titulo_eleitor.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Usuario other = (Usuario) obj;
		if (ativo != other.ativo)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (bd_horas == null) {
			if (other.bd_horas != null)
				return false;
		} else if (!bd_horas.equals(other.bd_horas))
			return false;
		if (cel == null) {
			if (other.cel != null)
				return false;
		} else if (!cel.equals(other.cel))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (concursado != other.concursado)
			return false;
		if (conselho_regional == null) {
			if (other.conselho_regional != null)
				return false;
		} else if (!conselho_regional.equals(other.conselho_regional))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (ctps == null) {
			if (other.ctps != null)
				return false;
		} else if (!ctps.equals(other.ctps))
			return false;
		if (data_admissao == null) {
			if (other.data_admissao != null)
				return false;
		} else if (!data_admissao.equals(other.data_admissao))
			return false;
		if (data_nasc == null) {
			if (other.data_nasc != null)
				return false;
		} else if (!data_nasc.equals(other.data_nasc))
			return false;
		if (data_nomeacao == null) {
			if (other.data_nomeacao != null)
				return false;
		} else if (!data_nomeacao.equals(other.data_nomeacao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (especialidade == null) {
			if (other.especialidade != null)
				return false;
		} else if (!especialidade.equals(other.especialidade))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nome_mae == null) {
			if (other.nome_mae != null)
				return false;
		} else if (!nome_mae.equals(other.nome_mae))
			return false;
		if (nome_pai == null) {
			if (other.nome_pai != null)
				return false;
		} else if (!nome_pai.equals(other.nome_pai))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (pis == null) {
			if (other.pis != null)
				return false;
		} else if (!pis.equals(other.pis))
			return false;
		if (portaria == null) {
			if (other.portaria != null)
				return false;
		} else if (!portaria.equals(other.portaria))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipofuncionario == null) {
			if (other.tipofuncionario != null)
				return false;
		} else if (!tipofuncionario.equals(other.tipofuncionario))
			return false;
		if (titulo_eleitor == null) {
			if (other.titulo_eleitor != null)
				return false;
		} else if (!titulo_eleitor.equals(other.titulo_eleitor))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}	

	
}
