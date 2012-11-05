package scs.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;

@ManagedBean(name="contextBean")
@SessionScoped
public class ContextoBean {
	private Usuario usuariologado = null;
	
	public Usuario getUsuarioLogado(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if(this.usuariologado==null||!login.equals(this.usuariologado.getLogin())){
			if(login!=null){
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuariologado = usuarioRN.buscarPorLogin(login);
			}
		}
	        
		return usuariologado;
	}
	
	public void setUsuarioLogado(Usuario usuario){
		this.usuariologado=usuario;
	}

}
