package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DeMorgan implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		//((NOT)exp)
		if(((NOT)exp).getNegado() instanceof AND){
			AND and = (AND) ((NOT)exp).getNegado();
			OR or = new OR();
			for (Expressao iterable_element : and.getList()) {
				NOT a = new NOT();
				a.setNegado(iterable_element);
				or.add(a);
			}
			return new Tuple<String, Expressao>("de Morgan", or);
		}else if(((NOT)exp).getNegado() instanceof OR){
			OR or = (OR) ((NOT)exp).getNegado();
			AND and = new AND();
			for (Expressao iterable_element : or.getList()) {
				NOT a = new NOT();
				a.setNegado(iterable_element);
				and.add(a);
			}
			return new Tuple<String, Expressao>("de Morgan", and);
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
