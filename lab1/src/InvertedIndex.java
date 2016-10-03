import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



import org.apache.commons.io.FileUtils;


public class InvertedIndex{


	public static void main(String[] args) throws IOException {

		int total = 0;
		HashMap<String, Integer> word = new HashMap<String, Integer>();
	    HashMap<String, HashMap<Integer, Integer>> InvertedIndex =new HashMap<String, HashMap<Integer,Integer>>();
		HashMap<Integer, String> docname = new HashMap<Integer, String>();
		
		int document_id=1,count=0;
		File dir = new File("/home/kushal/wikiextractor/OUTPUT/AA");
		File[] list= dir.listFiles();
		//read all files
		for (File file : list) {
			//System.out.println(count++);
			String filename =file.getName();
			System.out.println("Indexing the file with filename " + filename);
			total = total +1;
			docname.put(document_id,filename);
		    String str= FileUtils.readFileToString(file);
		    Find_terms tp=new Find_terms(str);
		    word=tp.allterms();  // call for function
	     	//create inverted InvertedIndex.
		    for(String s:word.keySet())
		    {
				
		    	HashMap<Integer, Integer> wordfrequency=new HashMap<Integer,Integer>();
		    	if(InvertedIndex.containsKey(s))
		    	{
		    		int m=word.get(s);

		    		InvertedIndex.get(s).put(document_id,m);
		    	
		    	}else
		    	{
		    		wordfrequency.put(document_id,word.get(s));
		    		InvertedIndex.put(s, wordfrequency);
		    	}
		    	
		    }
		    document_id++;
		}
				System.out.println("All files are Indexed. Total Number of files is" +" "+ total);
		//Getting the output stream of the file for writing
		File f = new File("Output.txt");
		FileOutputStream fos = new FileOutputStream(f);
		PrintWriter pw = new PrintWriter(fos);
		
	for(String str:InvertedIndex.keySet())		// print invertedInvertedIndex
		{
		pw.write(str+"   {");
	
			for(int i:InvertedIndex.get(str).keySet()){
					pw.write("{"+docname.get(i)+"-");
				    pw.write("  fr-"+InvertedIndex.get(str).get(i)+"}");
			}
			pw.write(  "} " +"\n");
		}
		pw.flush();
		fos.close();
		pw.close();	
		
	}
	
	

}
