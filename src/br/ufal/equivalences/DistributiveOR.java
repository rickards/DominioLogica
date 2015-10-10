package br.ufal.equivalences;

import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class DistributiveOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		for (Expressao iteratAND_OR : ((OR)exp).getList()) {
			if(iteratAND_OR instanceof AND){
				for (Expressao in_AND : ((AND)iteratAND_OR).getList()) {
					if(in_AND instanceof NOT){
						if(exp.contains(((NOT) in_AND).getNegado())){
							((OR)exp).remove(((NOT) in_AND).getNegado());
							((OR)exp).remove(iteratAND_OR);
							AND AND = new AND();
							for (Expressao in : ((AND)iteratAND_OR).getList()) {
								OR OR = new OR();
								OR.add(in);
								OR.add(((NOT) in_AND).getNegado());
								AND.add(OR);
							}
							((OR)exp).add(AND);
							return new Tuple<String, Expressao>("Distributive", exp);
						}
					}else{
						if(exp.contains(new NOT(in_AND))){
							((OR)exp).remove(new NOT(in_AND));
							((OR)exp).remove(iteratAND_OR);
							AND AND = new AND();
							for (Expressao in : ((AND)iteratAND_OR).getList()) {
								OR OR = new OR();
								OR.add(in);
								OR.add(new NOT(in_AND));
								AND.add(OR);
							}
							((OR)exp).add(AND);
							return new Tuple<String, Expressao>("Distributive", exp);
						}
					}
				}
			}
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
