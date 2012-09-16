package scs.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import scs.usuario.Usuario;
import scs.usuario.UsuarioRN;
import scs.util.HibernateUtil;

@ManagedBean(name = "exportacaoBean")
@RequestScoped
public class Exportacao {

	private Session session;

	public void setSesson(Session session) {
		this.session = session;
	}

	public void expoUsuariosMobile() {

		Element usuarios = new Element("usuarios");
		UsuarioRN usuarioRN = new UsuarioRN();
		List<Usuario> listusuarios = usuarioRN.listar();

		if (listusuarios.size() > 0) {
			for (Usuario usuario : listusuarios) {
				Element dados = new Element("dados");
				Element codigo = new Element("codigo");
				Element nome = new Element("nome");
				Element login = new Element("login");
				Element senha = new Element("senha");
				Element ativo = new Element("ativo");

				codigo.setText(usuario.getCodigo().toString());
				nome.setText(usuario.getNome());
				login.setText(usuario.getLogin());
				senha.setText(usuario.getSenha());
				if (usuario.isAtivo()) {
					ativo.setText("S");
				} else
					ativo.setText("N");

				if (verificaUsuarioMobile(usuario.getCodigo())) {
					dados.addContent(codigo);
					dados.addContent(nome);
					dados.addContent(login);
					dados.addContent(senha);
					dados.addContent(ativo);

					usuarios.addContent(dados);
				}
			}

		}

		Document doc = new Document();
		doc.setRootElement(usuarios);

		try {

			XMLOutputter xout = new XMLOutputter();
			OutputStream out = new FileOutputStream(
					new File("C:\\usuarios.xml"));

			xout.output(doc, out);

			System.out.println("XML criado com sucesso!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean verificaUsuarioMobile(Integer codigo) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select u.permissao from usuario_permissao u where u.funcionario= "
						+ String.valueOf(codigo)
						+ " and u.permissao='ROLE_USU_MOBILE' ");
		List permissoes = query.list();
		// query.setParameter("idfunc", codigo).uniqueResult();
		if (permissoes.isEmpty()) {
			return false;
		} else
			return true;

	}

}
