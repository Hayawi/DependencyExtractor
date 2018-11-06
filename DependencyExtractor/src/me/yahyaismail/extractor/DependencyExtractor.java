package me.yahyaismail.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.nio.file.Files;

import me.yahyaismail.filesystem.*;

public class DependencyExtractor {

	static Map<String, ArrayList<String>> fileDependencies = new HashMap<String, ArrayList<String>>();
	static List<String> classNames = new ArrayList<String>();
	static String applicationPath;
	
	public static void main(String[] args) {
		applicationPath = FileSystem.getFolder();
		File applicationFolder = new File(applicationPath);
		ArrayList<File> listOfFiles = FileSystem.recurseFiles(applicationFolder);
		FileSystem.filterSrc(listOfFiles, ".java");
		
		addDependencies(listOfFiles);
		
		try (BufferedWriter writer = Files.newBufferedWriter(new File(applicationPath + "\\DependencyAnalysis.txt").toPath())){
			for (int i = 0; i < classNames.size(); i++){
				ArrayList<String> dependencyList = fileDependencies.get(classNames.get(i));
				for (int j = 0; j < dependencyList.size(); j++){
					writer.write("IMPORT, " + classNames.get(i) + ", " + dependencyList.get(j) + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void addDependencies(ArrayList<File> listOfFiles){
		for (int i = 0; i < listOfFiles.size(); i++){
			ArrayList<String> importStatements = FileAnalyzer.getImportStatements(listOfFiles.get(i));
			FileAnalyzer.filterImports(importStatements, "hbase");
			String className = listOfFiles.get(i).getName();
			classNames.add(className);
			fileDependencies.put(className, new ArrayList<String>());
			
			for (int j = 0; j < importStatements.size(); j++) {
				int subBegin = importStatements.get(j).lastIndexOf(".") + 1;
				int subEnd = importStatements.get(j).length() - 1;
				if (subEnd > 0 && subBegin < subEnd){
					String dependency = importStatements.get(j).substring(subBegin, subEnd) + ".java";
					fileDependencies.get(className).add(dependency);
				}
			}
		}
	}
	
	
}
