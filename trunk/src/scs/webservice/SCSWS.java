package scs.webservice;

import java.util.List;

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
	public List<Usuario> ListarUsuariosFuncionarios(){
		UsuarioRN usu = new UsuarioRN();
		return usu.listar();
	}
	
	@WebMethod
	public List<Unidade> ListarUnidades(){
		UnidadeRN uni = new UnidadeRN();
		return uni.listar();
	}


}
