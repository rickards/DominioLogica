package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DeMorganInverseOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao element : ((OR)exp).getList()) {
			if(element instanceof AND){
				OR newElement = new OR();
				for (Expressao string : ((AND)element).getList()) {
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
				if(((AND) element).getList().size()==newElement.getList().size()){//implementar deMANDganInverse
					((OR)exp).remove(element);
					((OR)exp).add(new NOT(newElement));
					return new Tuple<String, Expressao>("DeMorganInverse", exp);
				}
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
