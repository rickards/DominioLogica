package br.ufal.struct;


public class Tuple<A, B> {
	
	private A result;
	private B composite;
	
	public Tuple(A string, B or) {
		this.result=string;
		this.composite=or;
	}
	
	public A getResult() {
		return result;
	}
	
	public void setResult(A rule) {
		this.result = rule;
	}
	
	public B getComposite() {
		return composite;
	}
	
	public void setComposite(B composite) {
		this.composite = composite;
	}
	
	@Override
	public String toString() {
		return composite+" / "+result;
	}
	
}
