import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFilesReader {
    List<String> fileNames;
    List<Scanner> fileReaders;

    public InputFilesReader(List<String> fileNames) throws FileNotFoundException {
        this.fileNames = fileNames;
        this.fileReaders = new ArrayList<Scanner>();
        for (String fileName : fileNames) {
            try {
                fileReaders.add(new Scanner(new File(fileName)));
            }
            catch (FileNotFoundException e) {
                throw new FileNotFoundException("Файла с именем: " + fileName + " не существует."); // Довольно дебильный способ "обработки" исключения
            }

        }
    }

    public String getNextString(){
        boolean allFilesAreDone = false;
        String stringFromFile = "";
        while (!fileReaders.isEmpty()) {
            for (Scanner fileReader : fileReaders) {
                //System.out.println(fileName);
                stringFromFile = fileReader.nextLine();
            }
        }
        return stringFromFile;
    }






}
