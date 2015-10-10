package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.Implica;
import br.ufal.operators.BiImplica;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class EliminatingBiImplication implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		AND and = new AND();
		Implica a = new Implica();
		a.addImplicador(((BiImplica)exp).getBiImplicador());
		a.addImplicado(((BiImplica)exp).getBiImplicado());
		Implica b = new Implica();
		b.addImplicador(((BiImplica)exp).getBiImplicado());
		b.addImplicado(((BiImplica)exp).getBiImplicador());
		and.add(a);
		and.add(b);
		return new Tuple<String, Expressao>("Elim. BiImplicação", and);
	}

}
