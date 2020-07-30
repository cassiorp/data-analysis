package br.com.company.dataanalysis.Services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReaderServiceTest {

    ReaderService readerService = new ReaderService();

    @Test
    void ifValidLineSalesman(){
        String str = "001ç1234567891234çPedroç50000";
        boolean verify = readerService.linesValidator(str);
        Assertions.assertEquals(true, verify);
    }
    @Test
    void ifNotValidLineSalesman(){
        String str = "001çc1111111111çPedroç50000";
        boolean verify = readerService.linesValidator(str);

        String str2 = "001ç11111111111çPedroçCass";
        boolean verify2 = readerService.linesValidator(str2);

        Assertions.assertEquals(false, verify);
        Assertions.assertEquals(false, verify);
    }

    @Test
    void ifValidLineClient(){
        String str = "002ç2345675434544345çJose da SilvaçRural";
        boolean verify = readerService.linesValidator(str);
        Assertions.assertEquals(true, verify);
    }
    @Test
    void ifNotValidLineClient(){
        String str = "002çletra45675434544345çJose da SilvaçRural";
        boolean verify = readerService.linesValidator(str);
        Assertions.assertEquals(false, verify);
    }

    @Test
    void ifValidLineSale(){
        String str = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
        boolean verify = readerService.linesValidator(str);
        Assertions.assertEquals(true, verify);
    }
    @Test
    void ifNotValidLineSale(){
        String str = "003ç10ç[c-10-100,2-30-2.50,3-40-3.10]çPedro";
        boolean verify = readerService.linesValidator(str);
        Assertions.assertEquals(false, verify);
    }


    @Test
    void ifValidListOfLine(){
        List<String> lines = new ArrayList<>();
        lines.add("001ç1234567891234çPedroç50000");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");
        lines.add("002ç2345675434544345çJose da SilvaçRural");
        lines.add("002ç2345675434544345çJose da SilvaçRural");

        for(String l: lines){
           Boolean verify = readerService.linesValidator(l);
           Assertions.assertEquals(true, verify);
        }
    }

    @Test
    void ifNotValidListOfLine(){
        List<String> lines = new ArrayList<>();
        lines.add("001çg234567891234çPedroç50000");
        lines.add("002ç23g45675434544345çJose da SilvaçRural");
        lines.add("003ç10ç[h-10-100,2-30-2.50,3-40-3.10]çPedro");
        lines.add("002ç23456dsd75434544345çJose da SilvaçRural");
        lines.add("002ç23456754sds34544345çJose da SilvaçRural");

        for(String l: lines){
            Boolean verify = readerService.linesValidator(l);
            Assertions.assertEquals(false, verify);
        }
    }

}
