package br.ufal.equivalences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.ufal.operators.AND;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Motor;
import br.ufal.struct.Tuple;

public class DistrubutiveInverseAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		Set<String> list = new HashSet<String>();
		for (Expressao iterator_and : ((AND)exp).getList()) {
			if(iterator_and instanceof OR){
				for (Expressao element : ((OR) iterator_and).getList()) {
					list.add(element.toString());
				}
			}
			//if(iterator_and instanceof NOT) list.add(iterator_and.toString());
		}
		//System.out.println(list);
		String saida="";
		int maior = 0;
		for (String string : list) {
			int cont=0;
			for (Expressao iterator_and : ((AND)exp).getList()) {
				if(iterator_and.contains(new Motor().buildTree(string))){
					cont++;
				}
				if(cont>maior){
					maior=cont;
					saida=string;
				}
			}
		}
		//System.out.println(saida+":"+maior);
		if(maior>1){
			OR or = new OR();
			or.add(new Motor().buildTree(saida));
			AND and = new AND();
			ArrayList<Expressao> eliminados = new ArrayList<Expressao>();
			for (Expressao iterator_and : ((AND)exp).getList()) {
				if(iterator_and.contains(new Motor().buildTree(saida))){
					((OR)iterator_and).remove(new Motor().buildTree(saida));
					eliminados.add(iterator_and);
					if(((OR)iterator_and).getList().size()==1) and.add(((OR)iterator_and).getList().get(0));
					else and.add(iterator_and);
				}
			}
			or.add(and);
			for (Expressao eliminar : eliminados) {
				((AND)exp).remove(eliminar);
			}
			((AND)exp).add(or);
			if(((AND)exp).getList().size()==1) exp = ((AND)exp).getList().get(0);
			return new Tuple<String, Expressao>("Distributive Inverse", exp); 
		}		

		return new Tuple<String, Expressao>(null, exp);
	}

}
