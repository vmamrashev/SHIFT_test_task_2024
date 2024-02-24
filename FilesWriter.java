import java.io.File;
import java.io.FileNotFoundException;
import java.io.Writer;

public class FilesWriter {
    private CliArgsParser parser;
    private InputFilesReader reader;
    private Writer writer;
    private String outputPath;
    private String intsOutputfileName;
    private String floatsOutputfileName;
    private String stringsOutputfileName;


    public FilesWriter(CliArgsParser parser, String[] args, InputFilesReader reader) throws FileNotFoundException {
        this.parser = new CliArgsParser(); // удалить после тестирования
        parser.parse(args);                // удалить после тестирования
        //this.parser = parser;                // вернуть после тестирования
        this.outputPath = parser.getOutputPath();
        this.reader = reader;
        File file = new File(outputPath);
        if (!file.exists()) {
            throw new FileNotFoundException("Папка " + outputPath + "не существует.");
        }
        generateOutputFileNames();
    }

    public void splitAndWriteTypes(InputFilesReader reader){
        boolean isIntFirstOccurrence = true;
        boolean isFloatFirstOccurrence = true;
        boolean isStringFirstOccurrence = true;
        String readString;

        do {
            readString = reader.getNextString();

            String[] subStringsArray = readString.split("\n");
            // **************** Парсинг строк в типы ****************
            for (String currentSubString : subStringsArray) {
                try {
                    int i = Integer.parseInt(currentSubString);
                    writeInteger(i, isIntFirstOccurrence);
                    isIntFirstOccurrence = false;
                    continue;
                }
                catch (NumberFormatException e){
                    try {
                        float f = Float.parseFloat(currentSubString);
                        writeFloat(f, isFloatFirstOccurrence);
                        isFloatFirstOccurrence = false;
                        continue;
                    }
                        catch (NumberFormatException e1){
                            if (!currentSubString.equals(" ")&& !currentSubString.equals("\n")&& !currentSubString.isEmpty()) {
                                writeString(currentSubString, isStringFirstOccurrence);
                                isStringFirstOccurrence = false;
                            }
                        }
                }
            }

        } while (!readString.isEmpty());

    }
    // ***************************** Запись файлы ***********************
    private void writeInteger(int i, boolean isIntFirstOccurrence){

    }

    private void writeFloat(float f, boolean isFloatFirstOccurrence){

    }

    private void writeString(String s, boolean isStringFirstOccurrence){

    }

    private void generateOutputFileNames(){
        if (parser.isFilenamePrefixNeeded()) {
            this.intsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
            this.floatsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
            this.stringsOutputfileName = parser.getOutputPath() + parser.getFileNamePrefix();
        }
        this.intsOutputfileName = parser.getOutputPath() + "integers.txt";
        this.floatsOutputfileName = parser.getOutputPath() + "floats.txt";
        this.stringsOutputfileName = parser.getOutputPath() + "strings.txt";
    }

}
