package br.ufal.equivalences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.ufal.operators.AND;
import br.ufal.operators.OR;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Motor;
import br.ufal.struct.Tuple;

public class DistrubutiveInverseOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		Set<String> list = new HashSet<String>();
		for (Expressao iterator_and : ((OR)exp).getList()) {
			if(iterator_and instanceof AND){
				for (Expressao element : ((AND) iterator_and).getList()) {
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
			for (Expressao iterator_and : ((OR)exp).getList()) {
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
			AND and = new AND();
			and.add(new Motor().buildTree(saida));
			OR or = new OR();
			ArrayList<Expressao> eliminados = new ArrayList<Expressao>();
			for (Expressao iterator_and : ((OR)exp).getList()) {
				if(iterator_and.contains(new Motor().buildTree(saida))){
					if(iterator_and instanceof AND){
						((AND)iterator_and).remove(new Motor().buildTree(saida));
						eliminados.add(iterator_and);
						if(((AND)iterator_and).getList().size()==1) or.add(((AND)iterator_and).getList().get(0));
						else or.add(iterator_and);
					}else if(iterator_and instanceof Proposition){
						eliminados.add(iterator_and);
						or.add(new Proposition("1"));
					}
				}
			}
			and.add(or);
			for (Expressao eliminar : eliminados) {
				((OR)exp).remove(eliminar);
			}
			((OR)exp).add(and);
			if(((OR)exp).getList().size()==1) exp = ((OR)exp).getList().get(0);
			return new Tuple<String, Expressao>("Distributive Inverse", exp); 
		}	

		return new Tuple<String, Expressao>(null, exp);
	}

}
