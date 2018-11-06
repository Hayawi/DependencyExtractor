package me.yahyaismail.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileAnalyzer {
	
	public static ArrayList<String> getImportStatements(File file) {
		Charset charset = Charset.forName("US-ASCII");
		ArrayList<String> importStatements = new ArrayList<String>();
		try(BufferedReader reader = Files.newBufferedReader(file.toPath(), charset))
		{
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.contains("import "))
					importStatements.add(line);
			}			
		} catch (IOException e){
			System.err.format("IOException: %s%n", e);
		}
		return importStatements;
	}
	
	public static void filterImports(ArrayList<String> importStatements, String packageOrg) {
		for (int i = 0; i < importStatements.size(); i++) {
			if (!importStatements.get(i).contains(packageOrg)) {
				importStatements.remove(i);
				i--;
			}
		}
	}
}
