import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DataSplitter {
    static public void mkAcopyFromSourceDir(String datesetPath, StringBuilder train, StringBuilder test, int trainSize) {
        if(trainSize <1 || trainSize >99)throw new RuntimeException("Are You Kidding ME!!!!!");
        File sourceFile = new File(datesetPath);
        File trainFile = new File(train.toString());
        File testFile = new File(test.toString());
        trainFile.mkdir();
        testFile.mkdir();
        File[] files = sourceFile.listFiles();
        String[] fileNames = sourceFile.list();
        int n = files.length;
        if(n < 1)return;
        if(!files[0].isDirectory()){
            int startTrain = 0;
            int endTrain = (n * trainSize)/100;
            int startTest = endTrain;
            int endTest = n;
            try {
                train_splitter(files, fileNames,train,startTrain, endTrain);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                test_splitter(files, fileNames, test, startTest, endTest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (files[i].isDirectory()) {
                StringBuilder trainCopy = new StringBuilder(train);
                StringBuilder testCopy = new StringBuilder(test);
                trainCopy.append("\\" + fileNames[i]);
                testCopy.append("\\" + fileNames[i]);
                mkAcopyFromSourceDir(files[i].getAbsolutePath(), trainCopy, testCopy, trainSize);
            }
        }
    }

    private static void train_splitter(File[] files, String[] fileNames, StringBuilder train, int start, int end) throws IOException {
        for(int i= start; i< end; i++){
            File copied = new File(train.toString() + "\\" + fileNames[i]);
            Files.copy(files[i].toPath(), copied.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
    private static void test_splitter(File [] files , String[] filesNames, StringBuilder test, int start, int end) throws IOException {
        for(int i= start; i< end; i++){
            File copied = new File(test.toString() + "\\" + filesNames[i]);
            Files.copy(files[i].toPath(), copied.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
