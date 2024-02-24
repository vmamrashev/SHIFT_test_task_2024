import java.io.*;

public class FilesWriter {
    private CliArgsParser parser;
    private InputFilesReader reader;
    private Writer writer;
    private String outputPath;
    private String intsOutputFileName;
    private String floatsOutputFileName;
    private String stringsOutputFileName;
    FileWriter intsWriter;
    FileWriter floatsWriter;
    FileWriter stringsWriter;
    
    public FilesWriter(CliArgsParser parser, String[] args, InputFilesReader reader) throws IOException {
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
        FileWriter intsWriter = new FileWriter(intsOutputFileName);
        FileWriter floatsWriter = new FileWriter(floatsOutputFileName);
        FileWriter stringsWriter = new FileWriter(stringsOutputFileName);

    }

    public void splitAndWriteTypes(InputFilesReader reader) throws IOException {
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
    private void writeInteger(int i, boolean isIntFirstOccurrence) throws IOException {
        try {
        this.intsWriter.write(i + "/n");
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void writeFloat(float f, boolean isFloatFirstOccurrence) throws IOException {
        try {
        this.floatsWriter.write((String.valueOf(f) + "/n"));
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void writeString(String s, boolean isStringFirstOccurrence) throws IOException {

        try {
            this.intsWriter.write(s + "/n");
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void generateOutputFileNames(){
        if (parser.isFilenamePrefixNeeded()) {
            this.intsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix();
            this.floatsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix();
            this.stringsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix();
        }
        this.intsOutputFileName = parser.getOutputPath() + "integers.txt";
        this.floatsOutputFileName = parser.getOutputPath() + "floats.txt";
        this.stringsOutputFileName = parser.getOutputPath() + "strings.txt";
    }

}
