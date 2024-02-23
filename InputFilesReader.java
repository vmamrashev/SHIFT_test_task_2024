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
        if (fileNames.isEmpty()) throw new IllegalArgumentException("Не указаны имена входных файлов");
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
        
        String stringFromFiles = "";

            for (Scanner fileReader : fileReaders) {
                if (fileReader.hasNext()) {
                    stringFromFiles = stringFromFiles + fileReader.nextLine() + "\n";
                }
            }

        return stringFromFiles;
    }






}
