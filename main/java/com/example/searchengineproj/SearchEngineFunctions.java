package com.example.searchengineproj;

//import org.apache.commons.codec.language.Soundex;

import java.nio.file.Files;
import java.util.*;
import java.io.*;

public class SearchEngineFunctions {
    public static Map<String, List<String>> map;
    //private final Soundex soundex;

    public SearchEngineFunctions() {
        map = new HashMap<>();
        //soundex = new Soundex();

    }

    private List<String> everyKey(String str) {
        String[] words = str.trim().split("\\s+");
        List<String> allWords = new ArrayList<>();
        for (String s : words) {
            allWords.add(s.toLowerCase());
        }
        return allWords;
    }

    public void buildFile(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist");
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

    public List<String> findDoc(String word) {
        return map.get(word);
    }

    /*public List<String> findSimilarDocs(String str) {
        List<String> docs = new ArrayList<>();
        String query = soundex.encode(str.toLowerCase());
        for (String word : map.keySet()) {
            String s = soundex.encode(word.toLowerCase());

            if (query.equals(s)) {
                docs.addAll(map.get(word));
            }
        }
        return docs;
    }*/

    public List<String> plusSearch(String first, String second) {
        List<String> list1 = findDoc(first);
        List<String> list2 = findDoc(second);
        List<String> listCommon = new ArrayList<>(list2);
        listCommon.retainAll(list1);
        return listCommon;
    }

    public List<String> notCommon(String first, String second) {
        List<String> list1 = findDoc(first);
        List<String> list2 = findDoc(second);
        list1.removeAll(list2);
        return list1;
    }

    public List<String> allCommon(String first, String second) {
        List<String> list = new ArrayList<>();
        list.addAll(plusSearch(first, second));
        list.addAll(notCommon(first, second));
        return list;


    }


}
