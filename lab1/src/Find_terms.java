import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;


public class Find_terms {
	
	static String query;
	Stemmer s= new Stemmer();
	char[] w = new char[501];
	int count=1;
	int count1 = 0;
	int count3 = 0;	
	public Find_terms(String containt)
	{
		this.query=containt;
		
		
	}
	// method for geting terms removing Stopwords
	public HashMap<String, Integer> allterms() throws IOException
	{
		
		HashMap<String, Integer> stopword =new HashMap<String, Integer>();
		HashMap<String, Integer> words =new HashMap<String, Integer>();
		String[] word=query.split(" ");
		//path to stopwords
		File dir1 = new File("/home/kushal/Desktop/ir-labs/lab1/src/dirs/Stopword.txt");

	
		    
			   String str=FileUtils.readFileToString(dir1);
			   String[] stpword2=str.split("\n");

			  // int i=0;
 			  // System.out.println(stpword2);
			   for(String stp:stpword2)
			   { 
			         stp = stp.trim();
				     stopword.put(stp,1);
			   }

	
			   	// Stemming of the words.
			    for(String wrd1:word)
			   {
			   		
			      for (int xx = 0; xx < wrd1.length(); xx++) 
			      w[xx] = wrd1.charAt(xx);
			      
			      for (int c = 0; c < wrd1.length(); c++) 
			      s.add(w[c]);
			      s.stem();       
			   	  
			   	  String u = s.toString();
			   	  word[count1] = u;
			   	 // System.out.println(word[count1]);ariable stopword of type HashMap<String,Integer>


			   	  count1 = count1+1;

			   }
			    
			    
			    for(String wrd:word)
			   {
				  if(!stopword.containsKey(wrd))
				  {
					  if(words.containsKey(wrd))
					  {
						  int fr=words.get(wrd)+1;
						  words.put(wrd, fr);
					  }else
					  {
						  words.put(wrd,count);
				  
					  }
				  }
			   }
			     
			   
			 
			 
		return words;
	}

		
}
