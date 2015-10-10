package br.ufal.operators;

import java.util.ArrayList;
import java.util.Collections;

import br.ufal.struct.Expressao;

public class AND extends Expressao{

	private ArrayList<Expressao> list = new ArrayList<Expressao>();
	
	public void add(Expressao brench){
		if(brench instanceof AND){
			for (Expressao brech_children : ((AND)brench).list) {
				list.add(brech_children);
			}
		}else{
			list.add(brench);
		}
		Collections.sort(list);
	}
	
	public ArrayList<Expressao> getList() {
		return list;
	}

	@Override
	public String toString() {
		if(list.size()==0) return "AND";
		String toString = list.get(0)+"";
		for (int i = 1; i < list.size(); i++) {
			toString+="^"+list.get(i);
		}
		return "("+toString+")";
	}
	
	public boolean remove(Expressao exp){
		for (Expressao composite : list) {
			if(composite.equals(exp)){
				return list.remove((Object)composite);
			}
		}
		if(exp instanceof AND && contains(exp)){
			for (Expressao composite : ((AND)exp).list) {
				remove(composite);
			}
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getToStringList() {
		ArrayList<String> names = new ArrayList<>();
		for (Expressao comp : list) {
			names.add(comp.toString());
		}
		return names;
	}
	
	public Expressao getForToString(String name) {
		for (Expressao composite : list) {
			if(composite.toString().equals(name)) return composite;
		}
		return null;
	}

	@Override
	public boolean contains(Expressao comparador) {
		for (Expressao composite : list) {
			if(composite.equals(comparador)) return true;
		}
		if(comparador instanceof AND){
			for (Expressao composite : ((AND)comparador).list) {
				if(!this.contains(composite)) return false;
			}
			return true;
		}
		return false;
	}

}
