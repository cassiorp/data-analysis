package br.com.company.dataanalysis;

import br.com.company.dataanalysis.Entities.Client;
import br.com.company.dataanalysis.Services.AnalyzeService;
import br.com.company.dataanalysis.Services.WriterService;
import br.com.company.dataanalysis.Utils.PathCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@SpringBootApplication
public class DataAnalysisApplication {

	public static void main(String[] args){
		final Logger logger = Logger.getLogger(DataAnalysisApplication.class.getName());
		PathCreator pathCreator = new PathCreator();
		Path pathIn = pathCreator.createPath("in").toPath();
		AnalyzeService analyzeService = new AnalyzeService();
		WriterService writerService = new WriterService();
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			WatchKey watchKey = pathIn.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
			while (true) {
				watchService.poll(5, TimeUnit.SECONDS);
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					List<File> files = Arrays.asList(pathIn.toFile().listFiles(File::isFile));
					writerService.reportWriter(analyzeService.analyzer(files));
				}
			}
		}catch (Exception ex){
			logger.info("erro:" + ex.getMessage());
		}

	}
}
