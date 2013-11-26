package es.upm.oeg.semanticmeasures.impl.crosslingual;

import eu.monnetproject.clesa.core.lang.Language;
import eu.monnetproject.clesa.core.utils.Pair;


/**
 * Computes relatedness between two texts according to CL-ESA measure
 * 
 * @author Jorge Gracia
 *
 */
public class CLESABetweenText extends CLESA_Sim{

	public CLESABetweenText(String lang1, String lang2) {
		super(lang1, lang2);
	}

	public CLESABetweenText() {
		super();
	}

	//methods
	public double getValue(Object object1, Object element1, Object object2,	Object element2) {
		//Compute clesa between elements. Do nothing with objects 1 and 2
		Pair<String, Language> pair1 = new Pair<String, Language>((String)element1, Language.getByIso639_1(getLanguageSource()));
		Pair<String, Language> pair2 = new Pair<String, Language>((String)element2, Language.getByIso639_1(getLanguageTarget()));
		return CLESA_Sim.score(pair1, pair2);
	}

	public double getValue(Object object1, Object element1, String lang1, Object object2, Object element2, String lang2) {
		setLanguageSource(lang1);
		setLanguageTarget(lang2);
		return getValue(object1, element1, object2, element2);
	}

	//uncomment for testing
	public static void main(String[] args) {
		String text1 = "administrator of the";
		String text2 = "administrador";		
		CLESABetweenText measure = new  CLESABetweenText();
		System.out.println(measure.getValue(null, text1, "en", null, text2, "es"));
	}



}
