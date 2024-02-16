import javax.crypto.spec.PSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSplitter.mkAcopyFromSourceDir("E:\\dataset", new StringBuilder("E:\\train"), new StringBuilder("E:\\test"), 70);
    }

}