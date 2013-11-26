package es.upm.oeg.semanticmeasures.impl.crosslingual;


import org.apache.lucene.queryParser.QueryParser;

import es.upm.oeg.semanticmeasures.CLRelatedness;
import eu.monnetproject.clesa.core.lang.Language;
import eu.monnetproject.clesa.core.utils.Pair;
import eu.monnetproject.clesa.ds.clesa.CLESA;

/**
 * Defines the basic methods to operate with the CL-ESA metric. 
 * CL-ESA implementation (wsd.jar) by Kartik Asooja for the Monnet project 
 * 
 * @author Jorge Gracia
 * @author Kartik Asooja
 *
 */
abstract public class CLESA_Sim implements CLRelatedness {

	//attributes
	private String languageSource, languageTarget;
	private	String configFilePath = "load/eu.monnetproject.clesa.CLESA.properties";

	protected static CLESA clesaService; 
	private static boolean started = false;

	//constructors
	public CLESA_Sim(){
		super();
		setLanguageSource(null); 
		setLanguageTarget(null);
		if(started == false) {
			try{
				clesaService  = new CLESA(configFilePath);
				started = true;
			} catch(Exception e){
		
				System.err.println("\n\nCheck if the error comes as if no wiki index in the path. \nDownload wiki indices for clesa (en, es, de, nl, fr, pt) from this link: https://dl.dropboxusercontent.com/u/40572132/compSixth.zip . \n " +
						"Unzip it and provide the indexpath in the config file: eu.monnetproject.clesa.CLESA.properties or whatever being used. \n" +
						"Increase the heap space as the size of index is around 1 GB and it goes fully to the RAM. \n\n ");
			}
		}
	}

	public CLESA_Sim(String lang1, String lang2) {
		super();
		setLanguageSource(lang1);
		setLanguageTarget(lang2);

		if(started == false) {
			try{
				clesaService = new CLESA(configFilePath);
				started = true;
			} catch(Exception e){
				System.err.println("\n\nCheck if the error comes as if no wiki index in the path. \nDownload wiki indices for clesa (en, es, de, nl, fr, pt) from this link: https://dl.dropboxusercontent.com/u/40572132/compSixth.zip . \n " +
						"Unzip it and provide the indexpath in the config file: eu.monnetproject.clesa.CLESA.properties or whatever being used. \n" +
						"Increase the heap space as the size of index is around 1 GB and it goes fully to the RAM. \n\n ");
			}
		}
	}

	//methods

	/**
	 * Gives the score between two Pairs <String, Language> the CL-ESA measure. 
	 * 
	 * @param s source pair
	 * @param t target pair
	 * @return relatedness value
	 */
	public static double score(Pair<String, Language> pair1, Pair<String, Language> pair2){
		String query1 = pair1.getFirst();
		String query2 = pair2.getFirst();		
		query1 = QueryParser.escape(query1);		
		if(query1.equalsIgnoreCase("")) 
			return 0.0;
		query2 = QueryParser.escape(query2);		
		if(query2.equalsIgnoreCase("")) 
			return 0.0;					
		return clesaService.score(pair1, pair2);		
	}	

	public void setLanguageSource(String language) {

		this.languageSource = language;

	}

	public String getLanguageSource() {

		return this.languageSource;

	}

	public void setLanguageTarget(String language) {

		this.languageTarget = language;
	}

	public String getLanguageTarget() {

		return this.languageTarget;

	}


}
