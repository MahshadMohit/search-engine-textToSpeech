package com.example.searchengineproj;

import java.nio.file.Files;
import java.util.*;
import java.io.*;
public class SearchEngineFunctions {
    private Map<String,List<String>> map;

    public SearchEngineFunctions(){
        map = new HashMap<>();
    }

    public void buildFile(String path){
        File folder = new File(path);
        if (!folder.exists() || folder.isDirectory()){
            return;
        }

        File[] files = folder.listFiles();
        if (files != null){
            for (File file : files){
                if (file.isFile()){
                    String fileName = file.getName();
                    //String filePath = file.getPath();

                    try {
                        List<String> list = Files.readAllLines(file.toPath());
                        for (String line : list){
                            String[] words = line.trim().split("\\s+");
                            List<String> l1 = new ArrayList<>();
                            for (String word : words){
                                String key = word.toLowerCase();
                                l1.add(key);
                            }
                            for (String k : l1){
                                if (map.containsKey(k)){
                                    map.get(k).add(fileName);
                                }else{
                                    List<String> names = new ArrayList<>();
                                    names.add(fileName);
                                    map.put(k,names);
                                }
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
