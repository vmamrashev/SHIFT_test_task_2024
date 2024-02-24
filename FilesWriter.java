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
        //this.parser = new CliArgsParser(); // удалить после тестирования
        //parser.parse(args);                // удалить после тестирования
        this.parser = parser;                // вернуть после тестирования
        this.outputPath = parser.getOutputPath();
        this.reader = reader;
        File file = new File(outputPath);
        if (!file.exists()) {
            throw new FileNotFoundException("Папка " + outputPath + "не существует.");
        }
        generateOutputFileNames();
        this.intsWriter = new FileWriter(intsOutputFileName);
        this.floatsWriter  = new FileWriter(floatsOutputFileName);
        this.stringsWriter  = new FileWriter(stringsOutputFileName);

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
                    if (currentSubString.matches("[+,-]?[0-9]+")) {
                        // int i = Integer.parseInt(currentSubString);
                        // writeInteger(i, isIntFirstOccurrence);
                        writeInteger(currentSubString, isIntFirstOccurrence);
                        isIntFirstOccurrence = false;
                        continue;
                    } else throw new NumberFormatException();
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
        this.intsWriter.close();
        this.floatsWriter.close();
        this.stringsWriter.close();
    }
    // ***************************** Запись файлы ***********************
    private void writeInteger(String i, boolean isIntFirstOccurrence) throws IOException {
        try {

            this.intsWriter.write(i + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void writeFloat(float f, boolean isFloatFirstOccurrence) throws IOException {
        try {
            this.floatsWriter.write((String.valueOf(f) + "\n"));
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void writeString(String currentSubString, boolean isStringFirstOccurrence) throws IOException {

        try {
            this.stringsWriter.write(currentSubString + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    private void generateOutputFileNames(){
        if (parser.isFilenamePrefixNeeded()) {
            this.intsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix() + "integers.txt";
            this.floatsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix() + "floats.txt";
            this.stringsOutputFileName = parser.getOutputPath() + parser.getFileNamePrefix() + "strings.txt";
        } else {
            this.intsOutputFileName = parser.getOutputPath() + "integers.txt";
            this.floatsOutputFileName = parser.getOutputPath() + "floats.txt";
            this.stringsOutputFileName = parser.getOutputPath() + "strings.txt";
        }
    }

}
