package br.ufal.equivalences;

import br.ufal.operators.NOT;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DoubleComplement implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		if(((NOT)exp).getNegado().toString().equals("0")){
			return new Tuple<String, Expressao>("~0", new Proposition("1"));
		}
		else if(((NOT)exp).getNegado().toString().equals("1")){
			return new Tuple<String, Expressao>("~1", new Proposition("0"));
		}
		else if(((NOT)exp).getNegado() instanceof NOT){
			return new Tuple<String, Expressao>("DoubleComplement", ((NOT)((NOT)exp).getNegado()).getNegado());
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
