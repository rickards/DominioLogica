package br.ufal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufal.struct.Expressao;

public class OR extends Expressao{

	private List<Expressao> list = new ArrayList<>();
	
	public void add(Expressao brench){
		if(brench instanceof OR){
			for (Expressao brech_children : ((OR)brench).list) {
				list.add(brech_children);
			}
		}else{
			list.add(brench);
		}
		Collections.sort(list);
	}
	
	public List<Expressao> getList() {
		return list;
	}

	public void setList(ArrayList<Expressao> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		if(list.size()==0) return "OR";
		String toString = list.get(0)+"";
		for (int i = 1; i < list.size(); i++) {
			toString+="v"+list.get(i);
		}
		return "("+toString+")";
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
	
	public boolean remove(Expressao exp){
		for (Expressao composite : list) {
			if(composite.equals(exp)){
				return list.remove((Object)composite);
			}
		}
		if(exp instanceof OR && contains(exp)){
			for (Expressao composite : ((OR)exp).list) {
				remove(composite);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Expressao comparador) {
		for (Expressao composite : list) {
			if(composite.equals(comparador)) return true;
		}
		if(comparador instanceof OR){
			for (Expressao composite : ((OR)comparador).list) {
				if(!this.contains(composite)) return false;
			}
			return true;
		}
		return false;
	}

}
