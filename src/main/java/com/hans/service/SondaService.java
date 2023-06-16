package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hans.model.Edificio;
import com.hans.model.Sonda;
import com.hans.repository.EdificioRepository;
import com.hans.repository.SondaRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SondaService {
	
	@Autowired SondaRepository db;;
	
	public Sonda salvaSonda(Sonda s) {
		return db.save(s);
	}
	
	public List<Sonda> trovaTutteSonde(){
		return db.findAll();
	}

	public Sonda trovaSonda(Long id){
		Sonda s=db.findById(id).get();
		if(s!=null) {
			return s;			
		}else {			
			throw new EntityNotFoundException("ERRORE!! La sonda cercata non esiste!!"); 
		}
			
	}
	
	public String eliminaSonda(Long id){
		if(sondaEsistente(db.findById(id).get())) {
			db.deleteById(id);	
			return "La sonda con id: "+id+" è stata eliminata dal DB!!";
		}else 
			throw new EntityNotFoundException("ERRORE!! La sonda passata con l'id non esiste!!"); 
	}
	
	boolean esiste;
	public boolean sondaEsistente(Sonda sonda) {
		esiste=false;
		db.findAll().forEach(s->{
			if(s.getId()==sonda.getId() && s.getPosizioneSonda()==sonda.getPosizioneSonda() && s.getEdificio().equals(sonda.getEdificio())) {
				esiste=true;
			}
		});
		if(esiste) {
			return true;
		}else 
			return false;
	}
}
