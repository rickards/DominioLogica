package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DistributiveAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao iterator_and : ((AND)exp).getList()) {
			if(iterator_and instanceof OR){
				for (Expressao in_or : ((OR)iterator_and).getList()) {
					if(in_or instanceof NOT){
						if(exp.contains(((NOT) in_or).getNegado())){
							((AND)exp).remove(((NOT) in_or).getNegado());
							((AND)exp).remove(iterator_and);
							OR or = new OR();
							for (Expressao in : ((OR)iterator_and).getList()) {
								AND and = new AND();
								and.add(in);
								and.add(((NOT) in_or).getNegado());
								or.add(and);
							}
							((AND)exp).add(or);
							return new Tuple<String, Expressao>("Distributive", exp);
						}
					}else{
						if(exp.contains(new NOT(in_or))){
							((AND)exp).remove(new NOT(in_or));
							((AND)exp).remove(iterator_and);
							OR or = new OR();
							for (Expressao in : ((OR)iterator_and).getList()) {
								AND and = new AND();
								and.add(in);
								and.add(new NOT(in_or));
								or.add(and);
							}
							((AND)exp).add(or);
							return new Tuple<String, Expressao>("Distributive", exp);
						}
					}
				}
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
