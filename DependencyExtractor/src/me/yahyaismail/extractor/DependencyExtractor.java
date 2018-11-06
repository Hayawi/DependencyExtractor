package me.yahyaismail.extractor;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

import me.yahyaismail.filesystem.*;

public class DependencyExtractor {

	static Map<String, List<String>> fileDependencies = new HashMap<String, List<String>>();
	static List<String> classNames = new ArrayList<String>();
	static String applicationPath;
	
	public static void main(String[] args) {
		System.out.println(new File("C:\\Users\\Hayawi\\Downloads\\hbase-2.1.0-src\\hbase-2.1.0\\hbase-metrics-api").isDirectory());
		applicationPath = FileSystem.getFolder();
		File applicationFolder = new File(applicationPath);
		ArrayList<File> listOfFiles = FileSystem.recurseFiles(applicationFolder);
		
		for (int i = 0; i < listOfFiles.size(); i++){
			System.out.println(listOfFiles.get(i));
		}
		
		FileSystem.filterSrc(listOfFiles, ".java");
		for (int i = 0; i < listOfFiles.size(); i++){
			System.out.println(listOfFiles.get(i));
		}
	}
	
}
