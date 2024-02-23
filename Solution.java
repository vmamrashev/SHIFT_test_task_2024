import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Solution {

    //   List<String> fileNames;

    public static void main(String[] args) {

//Проверка чтения из файлов
        List<String> fileNames = new ArrayList<>();
        InputFilesReader reader;
        try {
            fileNames.add("1.txt");
            fileNames.add("2.txt");
            fileNames.add("3.txt");
            reader = new InputFilesReader(fileNames);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int counter =0;
        String readString = "";
        do {
            readString = reader.getNextString();
            System.out.println(readString);
        } while (!readString.equals(""));

/*

// Проверка работоспособности класса CliArgsParser (не падает ли в рантайме)
        CliArgsParser parser = new CliArgsParser();
        try {
            InputFilesReader filesReader = new InputFilesReader(parser.parse(args));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

 */

/*

// Проверка корректности записи в поля парсера
        CliArgsParser parser = new CliArgsParser();
        List<String> fileNames1 = parser.parse(args);

        System.out.print("Содержимое списка fileNames: ");
        System.out.println(fileNames1.toString()+ "\n");

        System.out.print("Нужна полная статистика: ");
        System.out.println(parser.isFullStatsNeeded()+ "\n");

        System.out.print("Нужен префикс: ");
        System.out.println(parser.isFilenamePrefixNeeded()+ "\n");

        System.out.print("Нужно задавать путь к файлам вывода: ");
        System.out.println(parser.isChoosingPathNeeded()+ "\n");

        System.out.print("Нужно дописывать файлы вывода: ");
        System.out.println(parser.isAddingNeeded()+ "\n");

        System.out.print("outputPath ");
        System.out.println(parser.getOutputPath()+ "\n");

        System.out.print("fileNamePrefix ");
        System.out.println(parser.getFileNamePrefix()+ "\n");
        */

    }

}
