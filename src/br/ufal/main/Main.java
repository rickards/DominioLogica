package br.ufal.main;

import br.ufal.equivalences.AbsorptionAND;
import br.ufal.equivalences.AbsorptionOR;
import br.ufal.equivalences.DeMorgan;
import br.ufal.equivalences.DeMorganInverseAND;
import br.ufal.equivalences.DeMorganInverseOR;
import br.ufal.equivalences.DistributiveAND;
import br.ufal.equivalences.DistributiveOR;
import br.ufal.equivalences.DistrubutiveInverseAND;
import br.ufal.equivalences.DistrubutiveInverseOR;
import br.ufal.equivalences.DominanceAND;
import br.ufal.equivalences.DominanceOR;
import br.ufal.equivalences.DoubleComplement;
import br.ufal.equivalences.EliminatingBiImplication;
import br.ufal.equivalences.EliminatingImplication;
import br.ufal.equivalences.IdentityAND;
import br.ufal.equivalences.IdentityOR;
import br.ufal.equivalences.IdepotenteAND;
import br.ufal.equivalences.IdepotenteOR;
import br.ufal.equivalences.InverseAND;
import br.ufal.equivalences.InverseOR;
import br.ufal.struct.Expressao;
import br.ufal.struct.Motor;
import br.ufal.struct.Resolvedor;
import br.ufal.struct.Tuple;

public class Main {

	public static void main(String[] args) {
		//String exp = "(~(Av1))^P^Q";
		//String exp = "A^B->CvD^B^F";
		//String exp = "A->BvA^C<->A->CvA<->A^B->C";
		//String exp = "(A<->B->~(CvB))^~F^(C->B)";
		//String exp = "A<->B<->C<->(A<->B<->K)<->I<->E";
		//String exp = "AvCv0vTvTvTv1";
		//String exp = "A^C^(F^~A)";
		//String exp = "(A->B)v(B->U)";
		//String exp = "C^(F^~A)^~A^(K^~F)^~C^~A^C^(F^~A)^~A^(K^~F)^~C";;
		//String exp = "A^B<->A<->U->U->UvU^AvB";
		//String exp = "~Av(A^B)";
		//String exp = "~(A^C)^A^C^B";
		//String exp = "(((B^A)^(A^C))v((A^C)^F)v((A^C)^B))^~(A^C)"; best case
		//String exp = "X^(XvA)";
		//String exp = "~A^((A^B)v(A^C))";
		//String exp = "A^B<->C->DvA";
		//String exp = "A<->B<->C";
		//String exp = "X^Y^ZvX^Y";
		//String exp = "A^B^~Av~B^C";
		//String exp = "~A^B^(Av~B)^C";
		//String exp = "A^B^(AvBvCvD)^C";
		//String exp = "A^B<->C->DvA";
		//String exp = "A^D^C^(~AvB)";
		//String exp = "~B^(AvBvC)^(~AvBvC)";
		//String exp = "(A^~B)v(~A^~B)v(A^B^~C)v(~A^B^~C)";
		//String exp = "(A^~B^~C)v(A^~B^C)v(A^B^C)";
		//String exp = "(AvBvC)^~D^(CvBv~A)^(~GvP)";
		//String exp = "A^~Bv~A^~BvA^B^~Cv~A^B^~C";
		//REGRA DE INTRODUÇÃO
		//String exp = "~A^B^CvA^~B^CvA^B^~CvA^B^CvA^B^C";
		//String exp = "~(A^C)^A";
		//String exp = "~Av1";
		String exp = "~(AvB)^~~~C";
		//String exp = "~~C";
		Motor engine = new Motor();
		Expressao result = engine.buildTree(exp);
		System.out.println(result);
		Resolvedor solver = new Resolvedor();
		solver.addLawTopDown(new DoubleComplement());
		solver.addLawTopDown(new DeMorgan());
		solver.addLawTopDown(new DominanceAND());
		solver.addLawTopDown(new DominanceOR());
		solver.addLawTopDown(new InverseAND());
		solver.addLawTopDown(new InverseOR());
		solver.addLawTopDown(new IdentityAND());
		solver.addLawTopDown(new IdentityOR());
		solver.addLawTopDown(new IdepotenteAND());
		solver.addLawTopDown(new IdepotenteOR());
		solver.addLawTopDown(new AbsorptionAND());
		solver.addLawTopDown(new AbsorptionOR());
		solver.addLawTopDown(new DeMorganInverseAND());
		solver.addLawTopDown(new DeMorganInverseOR());
		solver.addLawTopDown(new DistributiveAND());
		solver.addLawTopDown(new DistributiveOR());
		solver.addLawTopDown(new EliminatingImplication());
		solver.addLawTopDown(new EliminatingBiImplication());
		solver.addLawBottonUp(new DistrubutiveInverseAND());
		solver.addLawBottonUp(new DistrubutiveInverseOR());
		Tuple<String, Expressao> solution = solver.simplify(result);
		while(solution.getResult()!=null){
			System.out.println(solution);//(AvB)^~D^(Bv~A)
			solution = solver.simplify(solution.getComposite());
		}
//		System.out.println();
//		Language natural = new Language();
//		natural.read("Se joão tem dez reais, então joana está lisa e marcio fica rico, então pianco é rei. João tem dez reias. pianco é rei ou pianco está doente.");
//		for (Entry<Character, String> entry : natural.getExpressões().entrySet()){
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}
//		System.out.println(natural.getPremissas());
	
	}

}
