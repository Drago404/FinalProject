package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PatkiDAO {
	
	private final List<Patka> allPatki = new ArrayList<Patka>(Arrays.asList(new Patka("Papi", 3), 
			new Patka("Stoqn", 4000, "Anastas i Ananas"), 
			new Patka("Techo", 400, "Stamat i Domat"), 
			new Patka("Toshko", 300, "Kosio i Mosio"), 
			new Patka("Pamela", 400, "Azis i Kitaeca i Qponeca")));
	
	public List<Patka> getAll() {
		return this.allPatki;
	}
	
	public void addPatka(Patka newPatka) {
		this.allPatki.add(newPatka);
	}
	
	public Patka getPatkaByIndex(int index) {
		return this.allPatki.get(index);
	}
}
