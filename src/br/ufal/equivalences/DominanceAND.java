package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DominanceAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		if(((AND)exp).contains(new Proposition("0"))){
			return new Tuple<String, Expressao>("Dominance", new Proposition("0"));
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
