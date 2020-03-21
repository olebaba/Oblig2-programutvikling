package App;

import javafx.concurrent.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileLoader extends Task<String> {
    final private String filepath;

    public FileLoader(String filepath) {
        this.filepath = filepath;
    }

    @Override
    protected String call() throws Exception {
        String out = "";
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
        }

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                out = scanner.nextLine();
            }
            scanner.close();
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("Fant ingen fil med det navnet.");
        }


        return out;
    }
}
