import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    public static void main(String[] args) {
        List<Integer> wordCounts = new ArrayList<>();
        System.out.println("Program to count total words in all the text files in directory.");
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try{
            File directoryPath = new File("D:\\TempFolder\\EssayTemp");
            File[] fileList = directoryPath.listFiles();
            if(fileList != null) {
                for(File file: directoryPath.listFiles()) {
                    if(file.isFile()) {
                        System.out.println("inside if loop");
                        Runnable task = new WordCountTask(file, wordCounts);
                        executorService.submit(task);
                    }
                }
            }

            executorService.shutdown();

            //Adding all the word counts: total word count
            int totalWordCount = wordCounts.stream().mapToInt(Integer::intValue).sum();
            System.out.println("total word count: " + totalWordCount);

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    private static class WordCountTask implements Runnable {
        private final File file;
        private final List<Integer> wordCounts;

        public WordCountTask(File file, List<Integer> wordCounts) {
            this.wordCounts = wordCounts;
            this.file = file;
        }

        @Override
        public void run() {
            // do something
            int count = fileWordCounter();
            synchronized (wordCounts) {
                this.wordCounts.add(count);
            }
        }

        private int fileWordCounter() {
            int wordCount = 0;
            try{
                BufferedReader br = new BufferedReader( new FileReader(this.file));
                String line;
                while((line = br.readLine()) != null) {
                    String[] wordArr = line.split("\\s+");
                    wordCount += wordArr.length;
                }
            } catch(FileNotFoundException e) {
                System.out.println("File '" + this.file + "' does not exist.");
            } catch(IOException e) {
                System.out.println("failed to read the file + '" + this.file + "'.");
            }
            return wordCount;
        }
    }
}