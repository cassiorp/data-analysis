package br.com.company.dataanalysis;

import br.com.company.dataanalysis.Services.AnalyzeService;
import br.com.company.dataanalysis.Services.WriterService;
import br.com.company.dataanalysis.Utils.PathCreator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.*;
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
			System.out.println("monitoring directory data/in");
			while (true) {
				watchService.poll(3, TimeUnit.SECONDS);
				for (WatchEvent<?> event : watchKey.pollEvents()) {
					List<File> files = Arrays.asList(pathIn.toFile().listFiles(File::isFile));
					List<Object> objects = analyzeService.analyzer(files);
					writerService.reportWriter(objects);
					watchKey.pollEvents().clear();
					objects.clear();
				}
			}
		}catch (Exception ex){
			logger.info("erro:" + ex.getMessage());
		}
	}
}
