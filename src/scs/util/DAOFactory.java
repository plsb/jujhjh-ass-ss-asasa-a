package scs.util;

import scs.acompanhamentoPadrao.AcompanhamentoPadraoDAO;
import scs.acompanhamentoPadrao.AcompanhamentoPadraoDAOHibernate;
import scs.acompcrianca.AcompCriancaDAO;
import scs.acompcrianca.AcompCriancaDAOHibernate;
import scs.agendamento.AgendamentoDAO;
import scs.agendamento.AgendamentoDAOHibernate;
import scs.area.AreaDAO;
import scs.area.AreaDAOHibernate;
import scs.bairro.BairroDAO;
import scs.bairro.BairroDAOHibernate;
import scs.consultasMedicas.ConsultasMedicasDAO;
import scs.consultasMedicas.ConsultasMedicasDAOHibernate;
import scs.diabetes.DiabetesDAO;
import scs.diabetes.DiabetesDAOHibernate;
import scs.encamMedicos.*;
import scs.equipe.EquipeDAO;
import scs.equipe.EquipeDAOHibernate;
import scs.familiar.FamiliarDAO;
import scs.familiar.FamiliarDAOHibernate;
import scs.gestante.GestanteDAO;
import scs.gestante.GestanteDAOHibernate;
import scs.hanseniase.HanseniaseDAO;
import scs.hanseniase.HanseniaseDAOHibernate;
import scs.hipertensao.HipertensaoDAO;
import scs.hipertensao.HipertensaoDAOHibernate;
import scs.microarea.MicroareaDAO;
import scs.microarea.MicroareaDAOHibernate;
import scs.municipio.MunicipioDAO;
import scs.municipio.MunicipioDAOHibernate;
import scs.procedimentos.ProcedimentosDAO;
import scs.procedimentos.ProcedimentosDAOHibernate;
import scs.profissional.ProfissionalDAO;
import scs.profissional.ProfissionalDAOHibernate;
import scs.residencia.ResidenciaDAO;
import scs.residencia.ResidenciaDAOHibernate;
import scs.rua.RuaDAO;
import scs.rua.RuaDAoHibernate;
import scs.segmento.SegmentoDAO;
import scs.segmento.SegmentoDAOHibernate;
import scs.solicExamesComplementares.SolicExamesComplemDAO;
import scs.solicExamesComplementares.SolicExamesComplemDAOHibernate;
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiroDAO;
import scs.tipoAtendimentoMedicoEnfermeiro.TipoAtendimentoMedicoEnfermeiroDAOHibernate;
import scs.tuberculose.TuberculoseDAO;
import scs.tuberculose.TuberculoseDAOHibernate;
import scs.unidade.UnidadeDAO;
import scs.unidade.UnidadeDAOHibernate;
import scs.usuario.UsuarioDAO;
import scs.usuario.UsuarioDAOHibernate;
import scs.vacinas.VacinasDAO;
import scs.vacinas.VacinasDAOHibernate;
import scs.visitas.VisitasDAO;
import scs.visitas.VisitasDAOHibernate;
import scs.visitasDomiciliares.VisitasDomiciliaresDAO;
import scs.visitasDomiciliares.VisitasDomiciliaresDAOHibernate;

public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static UnidadeDAO criarUnidadeDAO(){
		UnidadeDAOHibernate unidadeDAO = new UnidadeDAOHibernate();
		unidadeDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return unidadeDAO;
	}
	
	public static SegmentoDAO criarSegmentoDAO(){
		SegmentoDAOHibernate segmentoDAO = new SegmentoDAOHibernate();
		segmentoDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return segmentoDAO;
	}
	
	public static MunicipioDAO criarMunicipioDAO() {
		MunicipioDAOHibernate municipioDAO = new MunicipioDAOHibernate();
		municipioDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return municipioDAO;
	}
	
	public static BairroDAO criarBairroDAO() {
		BairroDAOHibernate bairroDAO = new BairroDAOHibernate();
		bairroDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return bairroDAO;
	}
	
	public static RuaDAO criarRuaDAO() {
		RuaDAoHibernate ruaDAO = new RuaDAoHibernate();
		ruaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return ruaDAO;
	}

	public static AreaDAO criarAreaDAO() {
		AreaDAOHibernate areaDAO = new AreaDAOHibernate();
		areaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return areaDAO;
	}

	public static EquipeDAO criarEquipeDAO() {		
		EquipeDAOHibernate equipeDAO = new EquipeDAOHibernate();
		equipeDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return equipeDAO;		
	}
	
	public static MicroareaDAO criarMicroareaDAO() {		
		MicroareaDAOHibernate microareaDAO = new MicroareaDAOHibernate();
		microareaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return microareaDAO;		
	}
	
	public static ResidenciaDAO criarResidenciaDAO() {		
		ResidenciaDAOHibernate residenciaDAO = new ResidenciaDAOHibernate();
		residenciaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return residenciaDAO;		
	}
	
	public static FamiliarDAO criarFamiliarDAO() {		
		FamiliarDAOHibernate familiarDAO = new FamiliarDAOHibernate();
		familiarDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return familiarDAO;		
	}
	
	public static HanseniaseDAO criarHanseniaseDAO() {		
		HanseniaseDAOHibernate hanseniaseDAO = new HanseniaseDAOHibernate();
		hanseniaseDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return hanseniaseDAO;		
	}
	
	public static DiabetesDAO criarDiabetesDAO() {		
		DiabetesDAOHibernate diabetesDAO = new DiabetesDAOHibernate();
		diabetesDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return diabetesDAO;		
	}
	
	public static HipertensaoDAO criarHipertensaoDAO() {		
		HipertensaoDAOHibernate hipertensaoDAO = new HipertensaoDAOHibernate();
		hipertensaoDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return hipertensaoDAO;		
	}
	
	public static TuberculoseDAO criarTuberculoseDAO() {		
		TuberculoseDAOHibernate tuberculoseDAO = new TuberculoseDAOHibernate();
		tuberculoseDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return tuberculoseDAO;		
	}
	
	public static GestanteDAO criarGestanteDAO() {		
		GestanteDAOHibernate gestanteDAO = new GestanteDAOHibernate();
		gestanteDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return gestanteDAO;		
	}
	
	public static VacinasDAO criarVacinasDAO() {		
		VacinasDAOHibernate vacinasDAO = new VacinasDAOHibernate();
		vacinasDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return vacinasDAO;		
	}
	
	public static AgendamentoDAO criarAgendamentoDAO() {		
		AgendamentoDAOHibernate agendamentoDAO = new AgendamentoDAOHibernate();
		agendamentoDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return agendamentoDAO;		
	}
	
	public static SolicExamesComplemDAO criarSolicExamComplDAO() {		
		SolicExamesComplemDAOHibernate solicDAO = new SolicExamesComplemDAOHibernate();
		solicDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return solicDAO;		
	}
	
	public static EncaminhamentosMedicosDAO criarEncaminhamentosMedicosDAO() {		
		EncaminhamentosMedicosDAOHibernate encaminhaDAO = new EncaminhamentosMedicosDAOHibernate();
		encaminhaDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return encaminhaDAO;		
	}
	
	public static ProcedimentosDAO criarProcedimentosDAO() {		
		ProcedimentosDAOHibernate proceimentoDAO = new ProcedimentosDAOHibernate();
		proceimentoDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return proceimentoDAO;		
	}
	
	public static TipoAtendimentoMedicoEnfermeiroDAO criarTipoAtendimentoMedicoEnfermeiroDAO() {		
		TipoAtendimentoMedicoEnfermeiroDAOHibernate tipoAtendDAO = new TipoAtendimentoMedicoEnfermeiroDAOHibernate();
		tipoAtendDAO.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipoAtendDAO;		
	}
	
	public static AcompCriancaDAO criarAcompCriancaDAO() {		
		AcompCriancaDAOHibernate acompcrianca = new AcompCriancaDAOHibernate();
		acompcrianca.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return acompcrianca;		
	}
	
	public static ProfissionalDAO criarProfissionalDAO() {		
		ProfissionalDAOHibernate prof = new ProfissionalDAOHibernate();
		prof.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return prof;		
	}
	
	public static AcompanhamentoPadraoDAO criarAcompanhamentoDAO() {		
		AcompanhamentoPadraoDAOHibernate acomp = new AcompanhamentoPadraoDAOHibernate();
		acomp.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return acomp;		
	}

	public static VisitasDAO criarVisitasDAO() {		
		VisitasDAOHibernate visitas = new VisitasDAOHibernate();
		visitas.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return visitas;		
	}
	
	public static VisitasDomiciliaresDAO criarVisitasDomiciliaresDAO() {		
		VisitasDomiciliaresDAOHibernate visitas = new VisitasDomiciliaresDAOHibernate();
		visitas.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return visitas;		
	}
	
	public static ConsultasMedicasDAO criarConsultasMedicasDAO() {		
		ConsultasMedicasDAOHibernate cons = new ConsultasMedicasDAOHibernate();
		cons.setSesson(HibernateUtil.getSessionFactory().getCurrentSession());
		return cons;		
	}

}
