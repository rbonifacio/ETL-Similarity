package br.unb.cic.etl.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static List<File> findFiles(String directory) throws Exception {
        File dir = new File(directory);

        if(!(dir.exists() && dir.isDirectory())) {
            throw new IllegalArgumentException("not a valid path: " + directory);
        }
        List<File> files = new ArrayList<>();
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(!attrs.isDirectory() && file.getFileName().toFile().getName().endsWith("xml")){
                    files.add(file.toFile());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return files;
    }

}
