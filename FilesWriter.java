import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class FilesWriter {
    private CliArgsParser parser;
    private InputFilesReader reader;
    private Writer writer;
    private String outputPath;
    private String intsOutputfileName;
    private String floatsOutputfileName;
    private String stringsOutputfileName;
    private List<String> inputFileNames;         // Список, хранящий имена выходных файлов
    //private List<ReaderFile> readers = new ArrayList<>();

    public FilesWriter(CliArgsParser parser, String[] args, InputFilesReader reader){
        this.parser = parser;
        this.outputPath = parser.getOutputPath();
        File file = new File(outputPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Папка " + outputPath + "не существует.");
        }
        generateOutputFileNames();

    }
        //writer = new FileWriter(parser.getFileNames().get(0));




    private void splitAndWriteTypes(String stringToWrite){
        boolean intFirstAccurance = true;
        boolean floatFirstAccurance = true;
        boolean stringFirstAccurance = true;

        String readString = "";
        do {
            readString = reader.getNextString();
            System.out.println(readString);
        } while (!readString.equals(""));

        // ************************ Switch ************************
        switch (arg) {
            case ("-f") -> {
                countStatsFlags++;
                needFullStats = true;
            }
            case ("-s") -> {
                countStatsFlags++;
                needFullStats = false;
            }
            case ("-a") -> {
                needToAddToOutputFiles = true;
            }
            case ("-o") -> {
                needToChooseOutputPath = true;
                localNeedToChooseOutputPath = true;
            }
            case ("-p") -> {
                needFilenamePrefix = true;
                localNeedFilenamePrefix = true;
            }
            default -> throw new IllegalArgumentException("Неизвестный аргумент: " + arg + "\n" +
                    "Программа запускается со следующими аргументами: " + "\n" +
                    "1. -s - краткая, -f - полная статистика." + "\n" +
                    "2. -a (опциональный) дозапись в ранее созданные выходные файлы. " + "\n " +
                    "3. -p (опциональный) добавить префикс к именам выходных файлов. " + "\n " +
                    "4. -o (опциональный) выбор пути к входным файлам. " + "\n " +
                    "5. остальные параметры – имена входных файлов, ");
        }
        // ************************ Switch ************************
    }
    private void generateOutputFileNames(){
        if (parser.isFilenamePrefixNeeded()) {
            intsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
            floatsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
            stringsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
        }
        intsOutputfileName = parser.getOutputPath() + "integers.txt";
        floatsOutputfileName = parser.getOutputPath() + "floats.txt";
        stringsOutputfileName = parser.getOutputPath() + "strings.txt";
    }
    private void typeSort(String stringToSort){

    }
}
