package scs.microarea;

import scs.area.Area;
import scs.rua.Rua;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.classic.Session;
import org.springframework.jdbc.object.SqlQuery;

import scs.usuario.Usuario;
import scs.util.HibernateUtil;

@Entity
@Table(name = "microarea")
public class Microarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cod")
	@SequenceGenerator(name = "cod", sequenceName = "microarea_codigo_microarea_seq")
	private Integer codigo_microarea;
	private String descricao;
	@ManyToOne
	@JoinColumn(name="idagente")
	private Usuario agente;
	@ManyToMany(cascade = CascadeType.ALL)
    @ManyToOne
	@JoinColumn(name="idarea")
	private Area area;
	@ManyToMany  
    @JoinTable(name = "microarea_ruas",    
      joinColumns = { @JoinColumn(name = "cod_microarea")},  
      inverseJoinColumns={@JoinColumn(name="cod_rua")}  
    )  
    private List<Rua> ruasLista;

	
	//@ManyToMany(cascade = { CascadeType.ALL, CascadeType.MERGE })
	//@JoinTable(
	 // name = "microarea_ruas",
	//  joinColumns = @JoinColumn(name = "cod_microarea"), inverseJoinColumns = @JoinColumn(name = "cod_rua")
	// )
	
	
	public Integer getCodigo_microarea() {
		return codigo_microarea;
	}
	public void setCodigo_microarea(Integer codigo_microarea) {
		this.codigo_microarea = codigo_microarea;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public Usuario getAgente() {
		return agente;
	}
	public void setAgente(Usuario agente) {
		this.agente = agente;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agente == null) ? 0 : agente.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime
				* result
				+ ((codigo_microarea == null) ? 0 : codigo_microarea.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((ruasLista == null) ? 0 : ruasLista.hashCode());
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
		Microarea other = (Microarea) obj;
		if (agente == null) {
			if (other.agente != null)
				return false;
		} else if (!agente.equals(other.agente))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (codigo_microarea == null) {
			if (other.codigo_microarea != null)
				return false;
		} else if (!codigo_microarea.equals(other.codigo_microarea))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (ruasLista == null) {
			if (other.ruasLista != null)
				return false;
		} else if (!ruasLista.equals(other.ruasLista))
			return false;
		return true;
	}
	public List<Rua> getRuasLista() {
		return ruasLista;
	}
	
	public List<Rua> retornaRuas(){
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("SELECT p FROM Rua AS p " +  
	            "INNER JOIN p.microareaLista g WHERE g.codigo_microarea ="+getCodigo_microarea());
		return query.list();
		
	}
	
	public void setRuasLista(List<Rua> ruasLista) {
		this.ruasLista=ruasLista;
	}

}
