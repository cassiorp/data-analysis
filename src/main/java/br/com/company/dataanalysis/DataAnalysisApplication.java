package br.com.company.dataanalysis;

import br.com.company.dataanalysis.Services.AnalyzeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DataAnalysisApplication {

	public static void main(String[] args) throws IOException {
		File pathIn = new File("/home/cassio/data/in");
		List<File> files = Arrays.asList(pathIn.listFiles(File::isFile));
		AnalyzeService analyzeService = new AnalyzeService();
		analyzeService.analyzer(files);
	}

}
