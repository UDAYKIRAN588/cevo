package org.knoesis.Cevo.Result;

public class Result {
	
	private String PartOfSpeechAnnotation;
	private int OffsetBegin; 
	private int OffsetEnd; 
	private String lemma;
	private String verbClass;
	private String vebUri;
	private String word;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPartOfSpeechAnnotation() {
		return PartOfSpeechAnnotation;
	}
	public void setPartOfSpeechAnnotation(String partOfSpeechAnnotation) {
		PartOfSpeechAnnotation = partOfSpeechAnnotation;
	}
	public int getOffsetBegin() {
		return OffsetBegin;
	}
	public void setOffsetBegin(int offsetBegin) {
		OffsetBegin = offsetBegin;
	}
	public int getOffsetEnd() {
		return OffsetEnd;
	}
	public void setOffsetEnd(int offsetEnd) {
		OffsetEnd = offsetEnd;
	}
	public String getLemma() {
		return lemma;
	}
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	public String getVerbClass() {
		return verbClass;
	}
	public void setVerbClass(String verbClass) {
		this.verbClass = verbClass;
	}
	public String getVebUri() {
		return vebUri;
	}
	public void setVebUri(String vebUri) {
		this.vebUri = vebUri;
	}
	

}
