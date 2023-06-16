package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hans.model.Allarme;
import com.hans.repository.AllarmeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AllarmeService {
	
	@Autowired AllarmeRepository db;;
	
	public Allarme salvaAllarme(Allarme s) {
		//System.out.println("Partito l'allarme nell'edificio con coordinate latitudine: "+s.getSegnalazione().getLat()+" longetudine: "+s.getSegnalazione().getLon()+", livello pericolosità: "+s.getLivelloPeriocolosita());
		return db.save(s);
		
	}
	
	public List<Allarme> trovaTuttiAllarmi(){
		return db.findAll();
	}

	public Allarme trovaAllarme(Long id){
		Allarme s=db.findById(id).get();
		if(s!=null) {
			return s;			
		}else {			
			throw new EntityNotFoundException("ERRORE!! La voce di allarme cercata non esiste!!"); 
		}
			
	}
	
}
