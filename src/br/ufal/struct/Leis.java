package br.ufal.struct;


public interface Leis {

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
}
