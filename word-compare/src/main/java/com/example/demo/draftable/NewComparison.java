package com.example.demo.draftable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import com.draftable.api.client.Comparison;
import com.draftable.api.client.Comparisons;
import com.draftable.api.client.Comparisons.Side;

public class NewComparison {
	
	public static void compare(String source1,String source2) {
		
		String accountId ="riEgjl";
        String authToken = "bb1a9cbbb8ea40727b73f7e69d7ba40c"; 
     

        Comparisons comparisons = new Comparisons(accountId, authToken);

        try {
            Side leftSide = createSide(source1); // can be a full URL starting with http/https, or a path
            Side rightSide = createSide(source2);
            
            Comparison comparison = comparisons.createComparison(leftSide, rightSide);
            String viewerURL = comparisons.signedViewerURL(comparison.getIdentifier(), Duration.ofDays(3650), false);

            System.out.println("Comparison created: " + comparison);
            System.out.println("Viewer URL (expires in 10 years): " + viewerURL);
            comparisons.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
//	private static void usage(String error) {
//        System.err.println("usage: NewComparison <left-url-or-path> <right-url-or-path>\n");
//        System.err.println("Set the following environment:");
//        System.err.println("  ACCOUNT_ID=<account id from https://api.draftable.com/account/credentials under 'Account ID'");
//        System.err.println("  AUTH_TOKEN=<token from the same page, under 'Auth Token'\n");
//        System.err.println("Optionally, set:");
//        System.err.println("  BASE_URL=<url such as https://.../v1 (no trailing lash)");
//        if (error != null || !error.isEmpty()) {
//            System.err.println("\nERROR: " + error);
//        }
//        System.exit(1);
//    }
	
	
	 private static String getExtension(String path) throws MalformedURLException {
	        // handle the case that the URL has query strings, e.g. "http://foo.bar/baz/honk.doc?t=1234"
	        if (path.startsWith("http")) {
	            URL url = new URL(path);
	            path = url.getPath();
	        }
	        return path.contains(".") ? path.substring(path.lastIndexOf('.') + 1).toLowerCase() : "";
	    }

	    private static Side createSide(String urlOrPath) throws IOException {
	        String extension = getExtension(urlOrPath);
	        System.out.println(String.format("Creating side '%s' (file type '%s')", urlOrPath, extension));

	        // If the urlOrPath doesn't start with http, assume it's a file path.
	        if (urlOrPath.startsWith("http")) {
	            return Side.create(urlOrPath, extension);
	        }

	        // Uncomment this version to load content as a byte array
	        return Side.create(Files.readAllBytes(Paths.get(urlOrPath)), extension);

	        // Uncomment this version to load content as a File object
	        //return Side.create(new File(urlOrPath), extension);
	    }
	
	

}
