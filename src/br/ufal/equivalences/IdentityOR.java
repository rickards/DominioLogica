package br.ufal.equivalences;

import br.ufal.operators.OR;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class IdentityOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		boolean identity=((OR)exp).remove(new Proposition("0"));
		while(((OR)exp).remove(new Proposition("0")));
		if(identity){
			if(((OR)exp).getList().size()==1) exp=((OR)exp).getList().get(0);
			return new Tuple<String, Expressao>("Identity",exp);
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
