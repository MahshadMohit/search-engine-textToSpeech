package com.example.searchengineproj;

import java.nio.file.Files;
import java.util.*;
import java.io.*;
public class SearchEngineFunctions {
    private Map<String,List<String>> map;

    public SearchEngineFunctions(){
        map = new HashMap<>();
    }

    public void buildFile(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory.");
            return;
        }

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();

                    try {
                        List<String> lines = Files.readAllLines(file.toPath());
                        for (String line : lines) {
                            List<String> list = everyKey(line);
                            for (String keyword : list) {
                                if (map.containsKey(keyword)) {
                                    map.get(keyword).add(fileName);
                                } else {
                                    List<String> fileNames = new ArrayList<>();
                                    fileNames.add(fileName);
                                    map.put(keyword, fileNames);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private List<String> everyKey(String str){
        String[] words = str.trim().split("\\s+");
        List<String> allWords = new ArrayList<>();
        for (String s : words){
            allWords.add(s.toLowerCase());
        }
        return allWords;
    }
}
