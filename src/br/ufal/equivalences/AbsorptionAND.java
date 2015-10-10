package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class AbsorptionAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao element : ((AND)exp).getList()) {
			for (Expressao element2 : ((AND)exp).getList()) {
				if(element2 instanceof OR){
					if(element2.contains(element)&&!element.equals(element2)){
						((AND)exp).remove(element2);
						return new Tuple<String, Expressao>("Absorption",exp);
					}
				}
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
