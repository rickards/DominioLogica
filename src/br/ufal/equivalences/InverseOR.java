package br.ufal.equivalences;

import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.operators.Proposition;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class InverseOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao iterable_element : ((OR)exp).getList()) {
			if(iterable_element instanceof NOT && exp.contains(((NOT) iterable_element).getNegado())){
				((OR)exp).remove(iterable_element);
				((OR)exp).remove(((NOT)iterable_element).getNegado());
				((OR)exp).add(new Proposition("1"));
				if(((OR)exp).getList().size()==1) return new Tuple<String, Expressao>("Inverse",new Proposition("1"));
				return new Tuple<String, Expressao>("Inverse",exp);
			}
			if(exp.contains(new NOT(iterable_element))){
				((OR)exp).remove(iterable_element);
				((OR)exp).remove(new NOT(iterable_element));
				((OR)exp).add(new Proposition("1"));
				if(((OR)exp).getList().size()==1) return new Tuple<String, Expressao>("Inverse",new Proposition("1"));
				return new Tuple<String, Expressao>("Inverse",exp);
			}
		}
		//tratORo IDENTITY
		boolean identity=((OR)exp).remove(new Proposition("0"));
		while(((OR)exp).remove(new Proposition("0")));
		if(identity){
			if(((OR)exp).getList().size()==1) exp=((OR)exp).getList().get(0);
			return new Tuple<String, Expressao>("Identity",exp);
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
