package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        try {
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        String strFile = Files.readString(path);
                        for (String sKey : keys) {
                            if (strFile.contains(sKey)) {
                                Path newDir = Paths.get(outFolder+'/'+sKey);
                                if (Files.notExists(newDir)) {
                                    Files.createDirectory(newDir);
                                }
                                Path newFile = newDir.resolve(path.getFileName());
                                Files.copy(path, newFile, StandardCopyOption.REPLACE_EXISTING);
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        FilesSelect fs = new FilesSelect();
//        List<String> lk = new ArrayList<>();
//        lk.add("check");
//        lk.add("qwert");
//        fs.selectFiles("C:/Users/User/IdeaProjects/Test1", "C:/Users/User/IdeaProjects/Test1/src/lessons", lk);
//    }
}
