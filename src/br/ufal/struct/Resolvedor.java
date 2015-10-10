package br.ufal.struct;

import java.util.ArrayList;

import br.ufal.exceptions.LawlessResolverException;
import br.ufal.operators.AND;
import br.ufal.operators.NOT;
import br.ufal.operators.OR;
import br.ufal.operators.Proposition;

public class Resolvedor {
	
	private ArrayList<Leis> laws = new ArrayList<Leis>();
	private ArrayList<Leis> laws_retorno = new ArrayList<Leis>();
	
	public Tuple<String, Expressao> simplify(Expressao exp){
		if(laws.size()==0) throw new LawlessResolverException("No there laws in the Resolver");
		int i = 0;
		Tuple<String, Expressao> result = new Tuple<String, Expressao>(null, exp);
		while(i<laws.size()){
			result = laws.get(i).visit(result.getComposite());
			if(result.getResult()!=null) return result;
			i++;
		}
		//visitar os nós
		if(exp instanceof AND){
			if(((AND)exp).getList().size()==1){
				return simplify(((AND)exp).getList().get(0));
			}
			for (Expressao iterator_and : ((AND)exp).getList()) {
				Tuple<String, Expressao> result2 = simplify(iterator_and);
				if(result2.getResult()!=null){
					((AND)exp).remove(iterator_and);
					((AND)exp).add(result2.getComposite());
					return new Tuple<String, Expressao>(result2.getResult(), exp);
				}
			}
		}else if(exp instanceof OR){
			if(((OR)exp).getList().size()==1){
				return simplify(((OR)exp).getList().get(0));
			}
			for (Expressao iterator_OR : ((OR)exp).getList()) {
				Tuple<String, Expressao> result2 = simplify(iterator_OR);
				if(result2.getResult()!=null){
					((OR)exp).remove(iterator_OR);
					((OR)exp).add(result2.getComposite());
					return new Tuple<String, Expressao>(result2.getResult(), exp);
				}
			}
		}else if(exp instanceof NOT) return this.simplify(((NOT)exp).getNegado());
		else if(exp instanceof Proposition) return new Tuple<String, Expressao>(null, exp);
		if(laws_retorno.size()==0) throw new LawlessResolverException("No there laws in the Resolver");
		int j = 0;
		while(j<laws_retorno.size()){
			result = laws_retorno.get(j).visit(result.getComposite());
			if(result.getResult()!=null) return result;
			j++;
		}
		return result;
	}
	
	public void addLawTopDown(Leis law){
		if(law!=null) laws.add(law);
	}
	
	public void addLawBottonUp(Leis law){
		if(law!=null) laws_retorno.add(law);
	}
}
