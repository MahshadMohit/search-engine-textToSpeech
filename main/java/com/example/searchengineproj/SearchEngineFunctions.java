package com.example.searchengineproj;


import org.apache.commons.codec.language.Soundex;

import java.nio.file.Files;
import java.util.*;
import java.io.*;

public class SearchEngineFunctions {
    public static Map<String, List<String>> map;
    private final Soundex soundex;
    public static List<String> firstLines = new ArrayList<>();


    public SearchEngineFunctions() {
        map = new HashMap<>();
        soundex = new Soundex();
        buildFile();

    }

    private List<String> everyKey(String str) {
        String[] words = str.trim().split("\\s+");
        List<String> allWords = new ArrayList<>();
        for (String s : words) {
            allWords.add(s.toLowerCase());
        }
        return allWords;
    }

    public void buildFile() {
        File folder = new File("C:\\Users\\asus\\IdeaProjects\\searchEngineProj\\src\\EnglishData");
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
                            firstLines.add(lines.get(0));
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

    public static StringBuilder textOfEeachFile(String nameFile) throws IOException {
        StringBuilder str = new StringBuilder();
        File folder = new File("C:\\Users\\asus\\IdeaProjects\\searchEngineProj\\src\\EnglishData");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String name = file.getName();
                    if (nameFile.equals(name)) {
                        List<String> lines = Files.readAllLines(file.toPath());
                        for (String s : lines) {
                            str.append(s).append("\n");
                        }
                    }
                }
            }
        }
        return str;
    }

    public List<String> findDoc(String word) {
        return map.get(word);
    }

    public List<String> findSimilarDocs(String str) {
        List<String> docs = new ArrayList<>();
        String query = soundex.encode(str.toLowerCase());
        for (String word : map.keySet()) {
            String s = soundex.encode(word.toLowerCase());

            if (query.equals(s)) {
                docs.addAll(map.get(word));
            }
        }
        return docs;
    }

    public List<String> plusSearch(String first, String second) {
        List<String> list1 = findSimilarDocs(first);
        List<String> list2 = findSimilarDocs(second);
        for (String s : list2) {
            if (!list1.contains(s)) {
                list1.add(s);
            }
        }

        return list1;
    }

    public List<String> notCommon(String first, String second) {
        List<String> list1 = findDoc(first);
        List<String> list2 = findDoc(second);
        for (String s : list2) {
            list1.remove(s);
        }
        return list1;
    }




}
