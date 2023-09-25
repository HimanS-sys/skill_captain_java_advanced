// program that generates a report of multiple text files using async multithreading

import java.io.*;
import java.util.concurrent.CompletableFuture;

// represents a file processing task
class FileProcessor {

    // processes a file, returns result (CompletableFuture)
    public CompletableFuture<TextToReportResult> processFile(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // counts words and lines in the given text file
                int wordCount = 0;
                int lineCount = 0;

                //System.out.println("file: " + filePath);
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                //System.out.println("BufferReader created");
                String line;
                while((line = br.readLine()) != null) {
                    lineCount += 1;
                    String[] word = line.split("\\s+");
                    wordCount += word.length;

                }
                br.close();
                return new TextToReportResult(filePath, wordCount, lineCount);
            } catch(IOException e){
                throw new RuntimeException("Error while processing file '" + filePath + "'.");
            }
        });
    }
}

// helps with storing and retrieving information having different data types
class TextToReportResult {
    private String filePath;
    private int wordCount;
    private int lineCount;

    public TextToReportResult(String filePath, int wordCount, int lineCount) {
        this.filePath = filePath;
        this.wordCount = wordCount;
        this.lineCount = lineCount;
    }
    public String getFilePath() {
        return this.filePath;
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public int getLineCount() {
        return this.lineCount;
    }

    @Override
    public String toString() {
        return "File: " + this.filePath + "\nWord Count: " + this.wordCount + "\nLine Count: " + this.lineCount + "\n";
    }
}

// manages the file processing
class FileApp {
    //create instance of FileProcessor for each file we want to process and combine them all into one report
    public void processFiles(String[] filePaths) {
        CompletableFuture<Void>[] processingTasks = new CompletableFuture[filePaths.length];
        System.out.println("---------- Report ----------");
        System.out.println("----------------------------");
        for(int i = 0; i < filePaths.length; i++) {
            FileProcessor fileProcessor = new FileProcessor();
            CompletableFuture<TextToReportResult> task = fileProcessor.processFile(filePaths[i]);
            //CompletableFuture<TextToReportResult> result =  task.processFile(filePaths[i]);
            processingTasks[i] = task.thenAccept(result -> {
                System.out.println(result + "\n----------------------------");
            });
        }
        // Waits for all the tasks to be completed
        CompletableFuture<Void> allOf = CompletableFuture.allOf(processingTasks);
        // combines the result of all the tasks
        allOf.join();
    }
}
public class Main {
    public static void main(String[] args) {

        // generating list of filepath
        String directoryPathStr = "D:\\TempFolder\\Essays";
        //Creating a File object for directory
        File directoryPath = new File(directoryPathStr);
        //List of all files
        String[] filePaths = directoryPath.list();
        for(int i=0; i<filePaths.length; i++) {
            filePaths[i] = directoryPathStr + "\\" + filePaths[i];
        }
        // Creating FileApp object
        FileApp fileApp = new FileApp();
        // Passing text files to generate report.
        fileApp.processFiles(filePaths);
    }
}