package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
    public List<List<String>> findDuplicates(String startPath) {
        List<List<String>> listAbslNameFale = new ArrayList<>();
        List<File> listFile = new ArrayList<>();
        try {
        Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                File fal = path.toFile();
                listFile.add(fal);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        for (int i = 0; i < listFile.size()-1; ++i) {
            List<String> listPaths = new ArrayList<>();
            File f = listFile.get(i);
            boolean flag = true;
            for (int j = i+1; j < listFile.size(); ++j) {
                File fj = listFile.get(j);
                if (f.length() == fj.length()) {
                    if (f.lastModified() == fj.lastModified()) {
                        if ((f.getName()).compareTo(fj.getName()) == 0) {
                            String a = Files.readString(f.toPath());
                            String b = Files.readString(fj.toPath());
                            if (a.compareTo(b) == 0) {
                                if (flag) {
                                    listPaths.add(f.getAbsolutePath());
                                    flag = false;
                                }
                                listPaths.add(fj.getAbsolutePath());
                                listFile.remove(j);
                                --j;
                            }
                        }
                    }
                }
            }
            if (!listPaths.isEmpty()) { listAbslNameFale.add(listPaths);}
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listAbslNameFale;
    }
//    public static void main(String[] args) {
//        try {
//            List<List<String>> lis =  findDuplicates("C:/Users/User/IdeaProjects/Test1/src");
//            for (var l : lis)
//            System.out.println(l);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
