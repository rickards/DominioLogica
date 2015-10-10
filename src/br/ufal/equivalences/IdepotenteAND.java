package br.ufal.equivalences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.ufal.operators.AND;
import br.ufal.struct.Leis;
import br.ufal.struct.Expressao;
import br.ufal.struct.Tuple;

public class IdepotenteAND implements Leis{

	@Override
	public Tuple<String, Expressao> apply(Expressao exp) {
		Set<String> set = new HashSet<String>(((AND)exp).getToStringList());
		if(set.size()<((AND)exp).getList().size()){
			AND and = new AND();
			ArrayList<String> distinct = new ArrayList<>();
			for (String name : set) {
				if(!distinct.contains(name)){
					distinct.add(name);
					and.add(((AND)exp).getForToString(name));		
				}
			}
			return new Tuple<String, Expressao>("Idepotente",and);
		}
		return new Tuple<String, Expressao>(null, exp);
	}

}
