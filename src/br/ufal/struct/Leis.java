package br.ufal.struct;

public interface Leis {

	//boolean evaluator(Expressao ant_exp, Expressao exp);
	Tuple<String, Expressao> apply(Expressao exp);
	
	public default Tuple<String, Expressao> visit(Expressao exp){
		Tuple<String, Expressao> result = null;
		try{
			result = this.apply(exp);
		}catch(ClassCastException e){
			result = new Tuple<String, Expressao>(null, exp);
		}
		return result;
	}
	
	public default boolean evaluate(Expressao ant_exp, Expressao exp) {
		Resolvedor solver = new Resolvedor();
		solver.addLawTopDown(this);
		solver.addLawBottonUp(this);
		System.out.println(ant_exp+"|-"+exp);
		return solver.simplify(ant_exp).getComposite().equals(exp);
	}
}
