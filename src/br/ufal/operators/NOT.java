package br.ufal.operators;

import br.ufal.struct.Expressao;

public class NOT extends Expressao{
	
	private Expressao negado;
	
	public NOT(){}
	
	public NOT(Expressao negado) {
		this.negado = negado;
	}
	
	public Expressao getNegado() {
		return negado;
	}

	public void setNegado(Expressao negado) {
		this.negado = negado;
	}
	
	@Override
	public String toString() {
		{//CONDICIONAL IMPLEMENTADO PARA INABIT
			if(negado instanceof NOT){
				return "~("+negado+")";
			}
		}
		return "~"+negado;
	}
	
	@Override
	public boolean contains(Expressao comparador) {
		return comparador.equals(negado);
	}

}
