package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hans.enums.LivelloPericolosita;
import com.hans.model.Allarme;
import com.hans.model.Edificio;
import com.hans.model.GestioneProcessoSonda;
import com.hans.model.ProcessoSonda;
import com.hans.model.ProcessoSondaConcreto;
import com.hans.model.Segnalazione;
import com.hans.model.Sonda;
import com.hans.repository.AllarmeRepository;
import com.hans.repository.EdificioRepository;
import com.hans.repository.SegnalazioneRepository;
import com.hans.repository.SondaRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SegnalazioneService {
	
	@Autowired SegnalazioneRepository db;
	@Autowired AllarmeService dbAllarme;
	
	GestioneProcessoSonda gPS=new GestioneProcessoSonda();
	ProcessoSonda p1= new ProcessoSondaConcreto();
	
	
	public Segnalazione salvaSegnalazioni(Segnalazione s) {
		 gPS.aggiungiProcesso(p1);
	   
		Segnalazione segnalazione=db.save(s);
		if(s.getLivelloFumo()>=5) {
			Allarme a=new Allarme();
			a.setSegnalazione(s);
			if(s.getLivelloFumo()>=5 &&s.getLivelloFumo()<=7) {
				a.setLivelloPeriocolosita(LivelloPericolosita.Medio);
				dbAllarme.salvaAllarme(a);
				 gPS.allertaProcesso(s);
			}else if(s.getLivelloFumo()>7) {
				a.setLivelloPeriocolosita(LivelloPericolosita.Alto);
				dbAllarme.salvaAllarme(a);
				gPS.allertaProcesso(s);
			}
		}
		return segnalazione;
	}
	
	public List<Segnalazione> trovaTutteSegnalazioni(){
		return db.findAll();
	}

	public Segnalazione trovaSegnalazione(Long id){
		Segnalazione s=db.findById(id).get();
		if(s!=null) {
			return s;			
		}else {			
			throw new EntityNotFoundException("ERRORE!! La segnalazione cercata non esiste!!"); 
		}
			
	}
	

}
