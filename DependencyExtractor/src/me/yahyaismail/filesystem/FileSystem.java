package me.yahyaismail.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;

public class FileSystem {

	public static void filterSrc(ArrayList<File> files, String srcExtension){
		for (int i = 0; i < files.size(); i++){
			if (!files.get(i).getName().endsWith(srcExtension)){
				files.remove(i);
				i--;
			}
		}
	}
	
	public static ArrayList<File> recurseFiles(File directory){
		ArrayList<File> localObjects = new ArrayList<File>(Arrays.asList(directory.listFiles()));
		ArrayList<File> mergedFiles = new ArrayList<File>();
		for (int i = 0; i < localObjects.size(); i++)
		{
			if (localObjects.get(i).isDirectory()){
				mergedFiles.addAll(recurseFiles(localObjects.get(i)));
				localObjects.remove(i);
				i--;
			}
		}
		
		mergedFiles.addAll(localObjects);		
		return mergedFiles;
	}

	public static String getFolder(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this folder: " + chooser.getSelectedFile().getName());
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}
	
}
