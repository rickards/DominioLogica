package br.ufal.equivalences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.ufal.operators.AND;
import br.ufal.operators.OR;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class IdepotenteOR implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		Set<String> set = new HashSet<String>(((OR)exp).getToStringList());
		if(set.size()<((OR)exp).getList().size()){
			OR OR = new OR();
			ArrayList<String> distinct = new ArrayList<>();
			for (String name : set) {
				if(!distinct.contains(name)){
					distinct.add(name);
					OR.add(((OR)exp).getForToString(name));		
				}
			}
			if(((OR)OR).getList().size()==1) return new Tuple<String, Expressao>("Idepotente",((OR)OR).getList().get(0));
			return new Tuple<String, Expressao>("Idepotente",OR);
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
