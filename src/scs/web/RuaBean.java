package scs.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import scs.bairro.Bairro;
import scs.bairro.BairroRN;
import scs.rua.Rua;
import scs.rua.RuaRN;
import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;

@ManagedBean(name="ruaBean")
@RequestScoped
public class RuaBean {
	
	private Rua rua = new Rua();
	private List<Rua> lista;
	private List<SelectItem> ruaSelect;
	
	
	public Rua getUnidade() {
		return rua;
	}
	public void setUnidade(Rua rua) {
		this.rua = rua;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result
				+ ((ruaSelect == null) ? 0 : ruaSelect.hashCode());
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
		RuaBean other = (RuaBean) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (ruaSelect == null) {
			if (other.ruaSelect != null)
				return false;
		} else if (!ruaSelect.equals(other.ruaSelect))
			return false;
		return true;
	}
	
	public String novo(){
		this.rua = new Rua();
		return "/restrito/rua";
	}
	
	public String editar(){
		return "/restrito/rua";
	}
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();

		
		RuaRN ruaRN = new RuaRN();
		ruaRN.salvar(this.rua);
		
		return "/restrito/lista_rua";//this.destinoSalvar;
	}
	
	public void setLista(List<Rua> lista) {
		this.lista = lista;
	}

	public String excluir(){
		RuaRN ruaRN = new RuaRN();
		ruaRN.excluir(this.rua);
		this.lista = null;
		return null;
	}
	
	public List<Rua> getLista(){
		if(this.lista==null){
			RuaRN ruaRN = new RuaRN();
			this.lista = ruaRN.listar();
		}
		return this.lista;
	}
	
	public Rua getRua() {
		return rua;
	}
	public void setRua(Rua rua) {
		this.rua = rua;
	}
	
	public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();  
          
        RuaRN ruaRN = new RuaRN();
		List<Rua> ruas = ruaRN.listar();
		
		if (ruas != null) {
			for (Rua rua : ruas) {
				 results.add(rua.getDescricao());
			}
		} 
          
        return results;  
    } 
	
	public List<SelectItem> getRuaSelect() {
		if (this.ruaSelect == null) {
			this.ruaSelect = new ArrayList<SelectItem>();
			//ContextoBean contextoBean = scs.util.ContextoUtil.getContextoBean();

			RuaRN ruaRN = new RuaRN();
			List<Rua> categorias = ruaRN.listar();
			this.montaDadosSelectRua(this.ruaSelect, categorias, "");
		}
		
		return ruaSelect;
	}

	private void montaDadosSelectRua(List<SelectItem> select, List<Rua> ruas, String prefixo) {

		SelectItem item = null;
		if (ruas != null) {
			for (Rua rua : ruas) {
				item = new SelectItem(rua, prefixo + rua.getDescricao());
				item.setEscape(false);
				select.add(item);
				//this.montaDadosSelect(select, usuario.getNome(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public void setRuaSelect(List<SelectItem> ruaSelect) {
		this.ruaSelect = ruaSelect;
	}	
	
}
