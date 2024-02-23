import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class FilesWriter {
    private CliArgsParser parser;
    private Writer writer;
    private String outputPath;
    private String intsOutputfileName;
    private String floatsOutputfileName;
    private String stringsOutputfileName;
    private List<String> inputFileNames;         // Список, хранящий имена выходных файлов
    //private List<ReaderFile> readers = new ArrayList<>();

    public FilesWriter(CliArgsParser parser, String[] args){
        this.parser = parser;
        this.outputPath = parser.getOutputPath();
        File file = new File(outputPath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Папка " + outputPath + "не существует.");
        }
        generateOutputFileNames();

    }
        //writer = new FileWriter(parser.getFileNames().get(0));




    public void write(String stringToWrite){

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
