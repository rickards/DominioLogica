package br.ufal.operators;

import br.ufal.struct.Expressao;

public class Implica extends Expressao{
	
	private Expressao implicador;
	private Expressao implicado;
	
	public Expressao getImplicador() {
		return implicador;
	}

	public void setImplicador(Expressao implicador) {
		this.implicador = implicador;
	}

	public Expressao getImplicado() {
		return implicado;
	}

	public void setImplicado(Expressao implicado) {
		this.implicado = implicado;
	}

	public void addImplicador(Expressao implicador){
		this.implicador=implicador;
	}
	
	public void addImplicado(Expressao implicado){
		this.implicado=implicado;
	}
	
	@Override
	public String toString() {
		return "("+implicador+"->"+implicado+")";
	}

	@Override
	public boolean contains(Expressao comparador) {
		if(comparador.equals(implicado)||comparador.equals(implicador)) return true;
		return false;
	}
}
