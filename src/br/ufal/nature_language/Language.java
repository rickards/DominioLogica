package br.ufal.nature_language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language {
	
	private Map<Character,String> expressões = new HashMap<Character, String>();
	private ArrayList<String> premissas = new ArrayList<String>();
	private char letra = 'A';
	
	public Map<Character, String> getExpressões() {
		return expressões;
	}

	public void setExpressões(Map<Character, String> expressões) {
		this.expressões = expressões;
	}

	public ArrayList<String> getPremissas() {
		return premissas;
	}

	public void setPremissas(ArrayList<String> premissas) {
		this.premissas = premissas;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	private String tratarLanguage(String text, char... x){
		//implica
		Pattern pattern2 = Pattern.compile("^(.*?) se [e]?[ ]?somente se (.*?)$");
		Matcher matcher2 = pattern2.matcher(text);
		if (matcher2.find()) {
			char premissa1 = letra++;
			char premissa2 = letra++;
			return tratarLanguage(matcher2.group(1),premissa1)+"<->"+tratarLanguage(matcher2.group(2), premissa2);
		}
		
		Pattern pattern = Pattern.compile("(.*?) então (.*?)$");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			char premissa1 = letra++;
			char premissa2 = letra++;
			return tratarLanguage(matcher.group(1), premissa1)+"->"+tratarLanguage(matcher.group(2), premissa2);
		}
		
		Pattern pattern4 = Pattern.compile("^(.*?) ou (.*?)$");
		Matcher matcher4 = pattern4.matcher(text);
		if (matcher4.find()) {
			String frase1[] = matcher4.group(2).split(" ou ");
			char[] letras = new char[frase1.length];
			for (int i = 0; i < frase1.length; i++) {
				letras[i]=letra;
				expressões.put(letra++, frase1[i]);
			}
			expressões.put(letra, matcher4.group(1));
			String exp= ""+letras[0];
			for (int i = 1; i < letras.length; i++) {
				exp=exp+"v"+letras[i];
			}
			return exp+"v"+letra;
		}
		
		Pattern pattern3 = Pattern.compile("^(.*?) e (.*?)$");
		Matcher matcher3 = pattern3.matcher(text);
		if (matcher3.find()) {
			String frase1[] = matcher3.group(1).split(",");
			char[] letras = new char[frase1.length];
			for (int i = 0; i < frase1.length; i++) {
				letras[i]=letra;
				expressões.put(letra++, frase1[i]);
			}
			expressões.put(letra, matcher3.group(2));
			String exp= ""+letras[0];
			for (int i = 1; i < letras.length; i++) {
				exp=exp+"^"+letras[i];
			}
			return exp+"^"+letra;
		}
		if(x!=null&&x.length!=0){
			expressões.put(x[0], text);
			return ""+x[0];
		}
		expressões.put(++letra, text);
		return letra+"";
	}

	public void read(String string) {
		String p[] = string.split("\\. ");
		for (int i = 0; i < p.length; i++) {
			premissas.add(tratarLanguage(p[i]));
		}
		for (Entry<Character, String> entry : getExpressões().entrySet()){
			for (Entry<Character, String> entry2 : getExpressões().entrySet()){
				if(!entry.getKey().equals(entry2.getKey())&&(entry.getValue().equals(entry2.getValue()))){
					System.out.println(entry);
				}
			}
		}
	}

}
