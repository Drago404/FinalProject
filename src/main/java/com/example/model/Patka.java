package com.example.model;

public class Patka {
	private String name;
	private int gradusi;
	private String patenca;
	
	public String getName() {
		return name;
	}
	public Patka(String name, int gradusi, String patenca) {
		super();
		this.name = name;
		this.gradusi = gradusi;
		this.patenca = patenca;
	}
	
	public String getPatenca() {
		return patenca;
	}
	public void setPatenca(String patenca) {
		this.patenca = patenca;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGradusi() {
		return gradusi;
	}
	public void setGradusi(int gradusi) {
		this.gradusi = gradusi;
	}
	
	
	public Patka() {
		super();
	}
	
	
	public Patka(String name, int gradusi) {
		super();
		this.name = name;
		this.gradusi = gradusi;
	}
}
