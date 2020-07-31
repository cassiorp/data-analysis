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

	public static void main(String[] args) {
		final Logger logger = Logger.getLogger(DataAnalysisApplication.class.getName());
		AnalyzeService analyzeService = new AnalyzeService();
		PathCreator pathCreator = new PathCreator();
		File pathIn = pathCreator.createPath("in");
		List<File> files = Arrays.asList(pathIn.listFiles(File::isFile));
		List<Object> objects = new ArrayList<>();
		WriterService writerService = new WriterService();
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			WatchKey watchKey = pathIn.toPath().register(watchService,StandardWatchEventKinds.ENTRY_CREATE);
			while (true) {
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					objects = analyzeService.analyzer(files);
					writerService.reportWriter(objects);
				}
			}
		}catch (Exception ex){
			logger.info("Error" + ex.getMessage());
		}
	}
}
