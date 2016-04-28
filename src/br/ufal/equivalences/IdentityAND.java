package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class IdentityAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		boolean identity=((AND)exp).remove(new Proposition("1"));
		while(((AND)exp).remove(new Proposition("1")));
		if(identity){
			if(((AND)exp).getList().size()==1) exp=((AND)exp).getList().get(0);
			return new Tuple<String, Expressao>("Identity",exp);
		}
		return new Tuple<String, Expressao>(null, exp);
	}
	
}
