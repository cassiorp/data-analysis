package br.com.company.dataanalysis.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Writer {

    Date data = new Date(System.currentTimeMillis());

    File pathLog = new File("/home/cassio/data/log/logs.dat");

    public void writerLine ( String str, File path ){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lineLogWriter(Integer wichLine, String tipo, File file){

        String str =   data +" Line: " + wichLine + " - File: " + file.getAbsoluteFile() +
                " - " + tipo +" with wrong information";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLog, true))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileLogWriter(File file){

        String str =   data +" - File: " + file.getAbsoluteFile() + " invalid format file!";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathLog, true))) {
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
