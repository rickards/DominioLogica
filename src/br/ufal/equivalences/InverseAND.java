package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class InverseAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao iterable_element : ((AND)exp).getList()) {
			if(iterable_element instanceof NOT && exp.contains(((NOT) iterable_element).getNegado())){
				((AND)exp).remove(iterable_element);
				((AND)exp).remove(((NOT)iterable_element).getNegado());
				((AND)exp).add(new Proposition("0"));
				if(((AND)exp).getList().size()==1) return new Tuple<String, Expressao>("Inverse",new Proposition("0"));
				return new Tuple<String, Expressao>("Inverse",exp);
			}
			if(exp.contains(new NOT(iterable_element))){
				((AND)exp).remove(iterable_element);
				((AND)exp).remove(new NOT(iterable_element));
				((AND)exp).add(new Proposition("0"));
				if(((AND)exp).getList().size()==1) return new Tuple<String, Expressao>("Inverse",new Proposition("0"));
				return new Tuple<String, Expressao>("Inverse",exp);
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
