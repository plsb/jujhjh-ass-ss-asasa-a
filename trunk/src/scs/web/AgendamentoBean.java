package scs.web;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import scs.agendamento.Agendamento;
import scs.agendamento.AgendamentoDAO;
import scs.agendamento.AgendamentoRN;
import scs.familiar.Familiar;
import scs.usuario.Usuario;
import scs.util.HibernateUtil;


@ManagedBean(name="agendamentoBean")
@RequestScoped

public class AgendamentoBean {
	
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent(); 
	private AgendamentoDAO agendamentoDAO;
	
	private Agendamento agendamento = new Agendamento();
	private List<Agendamento> lista;
	
	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	

	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();		
		AgendamentoRN agendamentoRN = new AgendamentoRN();
		Integer codigo = agendamento.getId();
		if(codigo==null || codigo == 0){
			if (verificaUnique()){
				context.addMessage(null, new FacesMessage("Sucesso ao Inserir Agendamento ", ""));
				
			} else {
				return "";
			}
		} else {
			context.addMessage(null, new FacesMessage("Sucesso ao Editar Agendamento ", ""));
			
		}
		
		agendamentoRN.salvar(this.agendamento);
		
		return "/restrito/lista_agendamento";//this.destinoSalvar;
	}
	
	public boolean verificaUnique(){
		boolean a=true;
		
		return a;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agendamento == null) ? 0 : agendamento.hashCode());
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
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
		AgendamentoBean other = (AgendamentoBean) obj;
		if (agendamento == null) {
			if (other.agendamento != null)
				return false;
		} else if (!agendamento.equals(other.agendamento))
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	public List<Agendamento> getLista() {
		if(this.lista==null){
			AgendamentoRN agendamentoRN = new AgendamentoRN();
			this.lista = agendamentoRN.listar();
		}
		return lista;
	}

	public void setLista(List<Agendamento> lista) {
		this.lista = lista;
	}
	
	public String novo(){
		this.agendamento = new Agendamento();
		return "/restrito/agendamento";
	}
	
	public String editar(){
		return "/restrito/agendamento";
	}
	
	public String excluir(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso ao Excluir Agendamento ", ""));
		AgendamentoRN agendamentoRN = new AgendamentoRN();
		agendamentoRN.excluir(this.agendamento);
		this.lista = null;
		return null;
	}

	public ScheduleModel getEventModel() {
		AgendamentoRN agenRN = new AgendamentoRN();
		List<Agendamento> listA = agenRN.listar();
		if (listA.size() > 0) {
			eventModel = new DefaultScheduleModel(); 
			for (Agendamento agendamento : listA) {
				//verifica se o agendamento já foi marcado, verificando pela data
				if(agendamento.getDtagendamento()!=null){
					//adiciona um dia a data, o componente do prime faces esta com problemas, tirando um dia da data
					Calendar calendar = Calendar.getInstance();  
					calendar.setTime(agendamento.getDtagendamento());
					calendar.add(Calendar.DAY_OF_MONTH, 1);
										
					//adiciona um evento ao caledário
					eventModel.addEvent(new DefaultScheduleEvent(agendamento.getDescricao()+" | "+
							prcFamiliar(agendamento.getIdfamiliar())+" | "+agendamento.getTpconsulta()+" | "+
							agendamento.getSeeUrgente(), 
							calendar.getTime(),calendar.getTime()));
				}
				
			}
		}
		
		return eventModel;
	}
	
	public String prcFamiliar(String idfamiliar){
		//procura o nome do familiar na tabela familiara, para retornar seu nome para mostrar no calendário
		Session session;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session
				.createQuery("From Familiar where idMD5='"
						+idfamiliar+"'");
		List<Familiar> famil = query.list();
		
		return famil.get(0).getNome();
	}
	
	public void addEvent(ActionEvent actionEvent) {  
		//método que adiciona o evento no calendário
        if(event.getId() == null)  
            eventModel.addEvent(event);  
        else  
            eventModel.updateEvent(event);  
          
        event = new DefaultScheduleEvent();  
    }  

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {  
        event = selectEvent.getScheduleEvent();  
    }  
      
    public void onDateSelect(DateSelectEvent selectEvent) {  
        event = new DefaultScheduleEvent(Math.random() + "", selectEvent.getDate(), selectEvent.getDate());  
    }  
      
    public void onEventMove(ScheduleEntryMoveEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    }  
      
    public void onEventResize(ScheduleEntryResizeEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());  
          
        addMessage(message);  
    } 
    
    private void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
	
	
	
		

}

