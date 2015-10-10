package br.ufal.struct;

public abstract class Expressao implements Comparable<Expressao>{
	
	public Expressao() {}
	
	@Override
	public int compareTo(Expressao o) {
		return this.toString().compareTo(o.toString());
	}
	
	public abstract boolean contains(Expressao comparador);
	
	@Override
	public boolean equals(Object comparador){
		if(!(comparador instanceof Expressao)) return false;
		return this.toString().equals(comparador.toString());
	}
}
