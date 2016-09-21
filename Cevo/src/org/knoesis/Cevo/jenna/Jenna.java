package org.knoesis.Cevo.jenna;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.util.FmtUtils;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.*;
public class Jenna {
	public String getClass(String inp) throws IOException {
		// TODO Auto-generated method stub
		// some definitions
		String result = null;
		Model model = ModelFactory.createDefaultModel();
		ClassLoader classLoader = getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream("org/knoesis/Cevo/jenna/Cevo.owl");//FileManager.get().open("/Cevo/WebContent/Cevo.owl");
        if (in == null) {
            throw new IllegalArgumentException( "File: " + ""+ " not found");
        }
        
        // read the RDF/XML file
        model.read("http://cevo.knoesis.org/CEVO.owl");
        
                    
        // write it to standard out
       // model.write(System.out);  
      // RDFDataMgr.write(System.out, model, RDFFormat.TURTLE_PRETTY);
       
       System.out.println();
       String t = inp;
       String queryString = 
    			"PREFIX sh: <http://www.semanticweb.org/saeedeh/ontologies/2016/3/untitled-ontology-17#>"+ 
    		    "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
    		    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
    		    "SELECT ?class ?label WHERE { ?class rdfs:subClassOf sh:Event. ?class rdfs:label ?label. FILTER regex(?label,\""+t+"\"). }";

    		Query query = QueryFactory.create(queryString);

    		// Execute the query and obtain results
    		QueryExecution qe = QueryExecutionFactory.create(query, model);
    		ResultSet results = qe.execSelect();
    		for ( ; results.hasNext() ; )
    	    {
    	      QuerySolution soln = results.nextSolution() ;
    	     RDFNode label = soln.get("label") ;       // Get a result variable by name.
    	     Resource uri = soln.getResource("class") ; // Get a result variable - must be a resource
    	     // Literal l = soln.getLiteral("class") ;   // Get a result variable - must be a literal
    	      System.out.println(label+" "+uri+" ");
    	      result = label+" "+uri;
    	    }
    		
    		//System.out.println(results.getResultVars().get(1));

    		// Output query results	
    		//ResultSetFormatter.out(System.out, results, query);
//    		QuerySolution rBind = results.next();
//    		System.out.println(rBind.toString());
//    		System.out.println(getVarValueAsString(rBind, results.getResultVars().get(0)));

    		// Important - free up resources used running the query
    		qe.close();
//    		return getVarValueAsString(rBind, results.getResultVars().get(0));
    		return result;

	}

//	private static String getVarValueAsString(QuerySolution rBind, String varName) {
//		// TODO Auto-generated method stub
//		RDFNode obj = rBind.get(varName);
//
//        if ( obj == null )
//            return "";
//
//        return FmtUtils.stringForRDFNode(obj);
//		
//	}

}
