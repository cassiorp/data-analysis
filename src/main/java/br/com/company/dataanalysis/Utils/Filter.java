package br.com.company.dataanalysis.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Filter {

    Writer writer = new Writer();
    private final Logger logger = Logger.getLogger(Filter.class
            .getName());

    public List<File> filter(List<File> files ){
        List<File> acceptedFiles = new ArrayList<>();
        List<File> recusedFiles = new ArrayList<>();
        File pathLog = new File( "/home/cassio/data/log/file.error.dat");
        for (File file : files) {
            if(file.getName().endsWith(".dat")){
                acceptedFiles.add(file);
                System.out.println(file);
            }else{
                recusedFiles.add(file);
                writer.fileLogWriter(file);
                logger.info(file.getAbsolutePath()+"invalid format");
            }
        }
        return acceptedFiles;
    }
}
