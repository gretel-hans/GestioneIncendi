package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hans.model.Edificio;
import com.hans.repository.EdificioRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EdificioService {
	
	@Autowired EdificioRepository edR;
	
	public Edificio salvaEdificio(Edificio e) {
		if(edificioEsistente(e)) {
			throw new EntityExistsException("L'edificio già esiste nel DB!!");
		}else 
		return edR.save(e);
	}
	
	public List<Edificio> trovaTuttiEdifici(){
		return edR.findAll();
	}

	public Edificio trovaEdificio(Long id){
		Edificio e=edR.findById(id).get();
		if(e!=null) {
			return e;			
		}else {			
			throw new EntityNotFoundException("ERRORE!! L'edificio cercato non esiste!!"); 
		}
			
	}
	
	public String eliminaEdificio(Long id){
		if(edificioEsistente(edR.findById(id).get())) {
			edR.deleteById(id);	
			return "L'edificio con id: "+id+" è stato eliminato dal DB!!";
		}else 
			throw new EntityNotFoundException("ERRORE!! L'edificio passato con l'id non esiste!!"); 
	}
	
	boolean esiste;
	public boolean edificioEsistente(Edificio ed) {
		esiste=false;
		edR.findAll().forEach(e->{
			if(e.getId()==ed.getId() && e.getLat()==ed.getLat() && e.getLon()==ed.getLon()) {
				esiste=true;
			}
		});
		if(esiste) {
			return true;
		}else 
			return false;
	}
}
