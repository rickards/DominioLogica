package br.ufal.equivalences;

import br.ufal.operators.Implica;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class EliminatingImplication implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		OR or = new OR();
		NOT not = new NOT();
		not.setNegado(((Implica)exp).getImplicador());
		or.add(not);
		or.add(((Implica)exp).getImplicado());
		return new Tuple<String, Expressao>("Elim. Implicação",or);
	}

}
