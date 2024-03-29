package scs.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import scs.util.DAOFactory;

public class UsuarioRN {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioRN(){
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario carregar(Integer codigo){
		return this.usuarioDAO.carregar(codigo);
	}
	
	public Usuario buscarPorLogin(String login){
		return this.usuarioDAO.buscarPorLogin(login);
	}
	
	public void salvar(Usuario usuario){
		Integer codigo = usuario.getCodigo();
		if(codigo==null || codigo == 0){
			List<String> lista = new ArrayList<>();
			lista.add("ROLE_USU_WEB");
			usuario.setPermissao(lista); //getPermissao().add("ROLE_USU_WEB");
			this.usuarioDAO.salvar(usuario);
		} else {
			this.usuarioDAO.atualizar(usuario);
		}
	}
	
	public void excluir(Usuario usuario){
		this.usuarioDAO.excluir(usuario);
	}
	
	public List<Usuario> listar(){
		return this.usuarioDAO.listar();
	}

}
