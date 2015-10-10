package br.ufal.operators;

import br.ufal.struct.Expressao;

public class Proposition extends Expressao{
	
	String premissa;
	
	public Proposition(String preposicao) {
		premissa=preposicao;
	}
	
	public String getPremissa() {
		return premissa;
	}

	public void setPremissa(String premissa) {
		this.premissa = premissa;
	}

	@Override
	public String toString() {
		return premissa;
	}
	
	@Override
	public boolean contains(Expressao comparador) {
		return this.equals(comparador);
	}

}
