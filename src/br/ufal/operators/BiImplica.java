package br.ufal.operators;

import br.ufal.struct.Expressao;

public class BiImplica extends Expressao{

	private Expressao biImplicador;
	private Expressao biImplicado;
	
	public Expressao getBiImplicador() {
		return biImplicador;
	}

	public void setBiImplicador(Expressao biImplicador) {
		this.biImplicador = biImplicador;
	}

	public Expressao getBiImplicado() {
		return biImplicado;
	}

	public void setBiImplicado(Expressao biImplicado) {
		this.biImplicado = biImplicado;
	}

	public void addImplicador(Expressao biImplicador){
		this.biImplicador=biImplicador;
	}
	
	public void addImplicado(Expressao biImplicado){
		this.biImplicado=biImplicado;
	}
	
	@Override
	public String toString() {
		return "("+biImplicador+"<->"+biImplicado+")";
	}

	@Override
	public boolean contains(Expressao comparador) {
		if(comparador.equals(biImplicado)||comparador.equals(biImplicador)) return true;
		return false;
	}

}
