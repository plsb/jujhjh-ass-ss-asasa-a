package scs.webservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import scs.unidade.Unidade;
import scs.unidade.UnidadeRN;
import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;

@WebService
public class SCSWS {
	
	@WebMethod
	public Boolean estaConectado(){
		return true;
	}
	
    @WebMethod
	public ArrayList<String> listaUsuarios(){
		ArrayList<String> arraylistUsuario = new ArrayList();
		UsuarioRN usuarioRN = new UsuarioRN();
		List<Usuario> listusuarios = usuarioRN.listar();
		
		if (listusuarios != null) {
			for (Usuario usuario : listusuarios) {
				arraylistUsuario.add(String.valueOf(usuario.getCodigo())+"|"+usuario.getNome()+"|"+
							usuario.getLogin()+"|"+usuario.getSenha());
			}
			return arraylistUsuario;
		}
		return null;
		
	}

}
