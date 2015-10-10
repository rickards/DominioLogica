package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DeMorganInverseAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao element : ((AND)exp).getList()) {
			if(element instanceof OR){
				AND newElement = new AND();
				for (Expressao string : ((OR)element).getList()) {
					if(string instanceof NOT){
						if(exp.contains(((NOT)string).getNegado())){
							newElement.add(((NOT)string).getNegado());
						}
					}else{
						if(exp.contains(new NOT(string))){
							newElement.add(new NOT(string));
						}
					}
				}
				if(((OR) element).getList().size()==newElement.getList().size()){//implementar deMorganInverse
					((AND)exp).remove(element);
					((AND)exp).add(new NOT(newElement));
					return new Tuple<String, Expressao>("DeMorganInverse", exp);
				}
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
