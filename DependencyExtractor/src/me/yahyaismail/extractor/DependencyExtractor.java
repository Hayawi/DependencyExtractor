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

public class DependencyExtractor {

	static Map<String, List<String>> fileDependencies = new HashMap<String, List<String>>();
	static List<String> classNames = new ArrayList<String>();
	static String applicationPath;
	
	public static void main(String[] args) {
		applicationPath = getFolder();
		File applicationFolder = new File(applicationPath);
		ArrayList<File> listOfFiles = recurseFiles(applicationFolder);
		for (int i = 0; i < listOfFiles.size(); i++){
			System.out.println(listOfFiles.get(i));
		}
	}
	
	private static ArrayList<File> recurseFiles(File directory){
		ArrayList<File> localObjects = new ArrayList<File>(Arrays.asList(directory.listFiles()));
		ArrayList<File> mergedFiles = new ArrayList<File>();
		for (int i = 0; i < localObjects.size(); i++)
		{
			if (localObjects.get(i).isDirectory()){
				File directoryToRecurse = localObjects.get(i);
				localObjects.remove(i);
				mergedFiles.addAll(recurseFiles(directoryToRecurse));
			}
		}
		mergedFiles.addAll(localObjects);
		return mergedFiles;
	}

	private static String getFolder(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this folder: " + chooser.getSelectedFile().getName());
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}
	
}
