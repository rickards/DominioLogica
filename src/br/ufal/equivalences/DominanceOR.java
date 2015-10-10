package br.ufal.equivalences;

import br.ufal.operators.OR;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DominanceOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		if(((OR)exp).contains(new Proposition("1"))){
			return new Tuple<String, Expressao>("DominanceOR",new Proposition("1"));
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
