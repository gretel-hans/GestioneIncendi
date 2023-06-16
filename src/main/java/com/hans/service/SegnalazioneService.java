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
	
	 GestioneProcessoSonda gestione= new GestioneProcessoSonda();
	
	ProcessoSonda p1= new ProcessoSondaConcreto();
	
	
	
	
	public Segnalazione salvaSegnalazioni(Segnalazione s) {
		Segnalazione segnalazione=db.save(s);
		gestione.aggiungiProcesso(p1);		
		gestione.allertaProcesso(segnalazione);
		
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
