package br.com.company.dataanalysis.Utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathCreator {
    public File createPath(String last) {
        Path path = Paths.get( System.getProperty("user.home").concat(File.separator).concat("data").concat(File.separator).concat(last));
        File diretorio = new File(path.toString());
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        return path.toFile();
    }
}
