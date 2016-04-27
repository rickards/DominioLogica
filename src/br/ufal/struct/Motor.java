package br.ufal.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import br.ufal.operators.AND;
import br.ufal.operators.BiImplica;
import br.ufal.operators.Implica;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.operators.Proposition;

public class Motor {
	
	private ArrayList<Expressao> list = new ArrayList<>();
	
	public Expressao buildTree(String exp){
		//replace (-> to #) and (<-> to @)
		exp = exp.replace("<->", "@");
		exp = exp.replace("->", "#");
		
		String letra;
		for (int i = 0; i < exp.length(); i++) {
			
			letra = exp.charAt(i)+"";

			if(Pattern.compile("[A-Z0-1]").matcher(letra).find()){
				list.add(new Proposition(letra));
			}
			else if(letra.equals("^")){
				list.add(new AND());
			}
			else if(letra.equals("v")){
				list.add(new OR());
			}
			else if(letra.equals("#")){
				list.add(new Implica());
			}
			else if(letra.equals("@")){
				list.add(new BiImplica());
			}
			else if(letra.equals("(")){
				String piece = "";
				int qtde_parenteses=1;
				i++;
				letra = exp.charAt(i)+"";
				while(!(letra.equals(")")&&qtde_parenteses==1)){
					piece+=letra;
					if(letra.equals("(")) qtde_parenteses++;
					else if (letra.equals(")")) qtde_parenteses--;
					i++;
					letra = exp.charAt(i)+"";
				}
				list.add(new Motor().buildTree(piece));
			}
			else if(letra.equals("~")){
				list.add(new NOT());
			}
		}
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NOT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		ArrayList<Integer> num = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof NOT && ((NOT)list.get(i)).getNegado()==null){
				if(!num.contains(i)) num.add(i);
			}
		}
		
		ArrayList<Expressao> newlist = new ArrayList<>();
		for (int i = list.size()-1; i >= 0; i--) {
			if(!num.contains(i)){
				newlist.add(list.get(i));
			}else{
				((NOT) list.get(i)).setNegado(list.get(i+1));
				newlist.remove(list.get(i+1));
				newlist.add(list.get(i));
			}
		}
		Collections.reverse(newlist);
		list=newlist;

		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~AND~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		num = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof AND && ((AND)list.get(i)).getList().size()==0){
				if(!num.contains(i-1)) num.add(i-1);
				if(!num.contains(i+1)) num.add(i+1);
				
			}
		}
		
		newlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(!num.contains(i)){
				newlist.add(list.get(i));
			}else{
				int j=i+1; //variável entre as proposições deve ser um AND
				AND a = new AND();
				while(num.contains(i)&&list.get(j) instanceof AND){
					a.add(list.get(i));
					num.remove((Object)i);
					i+=2;
					j=i-1;
				}
				i-=2;
				newlist.add(a);
			}
		}
		list=newlist;
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		num = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof OR && ((OR)list.get(i)).getList().size()==0){
				if(!num.contains(i-1)) num.add(i-1);
				if(!num.contains(i+1)) num.add(i+1);
			}
		}
		
		newlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(!num.contains(i)){
				newlist.add(list.get(i));
			}else{
				OR a = new OR();
				while(num.contains(i)){
					a.add(list.get(i));
					num.remove((Object)i);
					i+=2;
				}
				i-=2;
				newlist.add(a);
			}
		}
		list=newlist;
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Implica~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		num = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Implica && list.get(i).toString().contains("null")){
				num.add(i-1);
				num.add(i+1);
			}
		}
		do{
			boolean bool = false;
			newlist = new ArrayList<>();
			for (int i = list.size()-1; i >= 0; i--) {
				if(!num.contains(i)||bool){
					newlist.add(list.get(i));
				}else{
					Implica a = new Implica();
					if(num.contains(i)){
						a.addImplicador(list.get(i-2));
						a.addImplicado(list.get(i));
						num.remove((Object)(i-2));
						num.remove((Object)i);
						bool=true;
						i-=2;
					}
					newlist.add(a);
				}
			}
			list=new ArrayList<>();
			for (int i = newlist.size()-1; i >= 0 ; i--) {
				list.add(newlist.get(i));
			}
			if(!bool) break;
		}while(true);
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~biImplica~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		num = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof BiImplica && list.get(i).toString().contains("null")){
				num.add(i-1);
				num.add(i+1);
			}
		}
		do{
			boolean bool = false;
			newlist = new ArrayList<>();
			for (int i = list.size()-1; i >= 0; i--) {
				if(!num.contains(i)||bool){
					newlist.add(list.get(i));
				}else{
					BiImplica a = new BiImplica();
					if(num.contains(i)){
						a.addImplicador(list.get(i-2));
						a.addImplicado(list.get(i));
						num.remove((Object)(i-2));
						num.remove((Object)i);
						bool=true;
						i-=2;
					}
					newlist.add(a);
				}
			}
			list=new ArrayList<>();
			for (int i = newlist.size()-1; i >= 0 ; i--) {
				list.add(newlist.get(i));
			}
			if(!bool) break;
		}while(true);
		return list.get(0);
	}
	
	public void printList() {
		for (Expressao composite : list) {
			System.out.println(composite);			
		}
		System.out.println("-----------------------------------------");
	}

}
