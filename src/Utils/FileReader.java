package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private Scanner sc;

    public FileReader(String fileName) throws FileNotFoundException{
        sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
    }
    public String[] readLine(){
        return sc.nextLine().split(",");
    }
}
