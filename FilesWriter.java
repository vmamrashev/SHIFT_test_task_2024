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
    IntStatsCollector intStatsCollector;
    FloatStatsCollector  floatStatsCollector;
    StringStatsCollector stringStatsCollector;

    public FilesWriter(CliArgsParser parser, String[] args, InputFilesReader reader) throws IOException {
        this.parser = parser;
        this.outputPath = parser.getOutputPath();
        this.reader = reader;
        File file = new File(outputPath);
        if (!file.exists()) {
            throw new FileNotFoundException("Папка " + outputPath + "не существует.");
        }
        generateOutputFileNames();

    }

    public void splitAndWriteTypes(InputFilesReader reader) throws IOException {
        boolean isIntFirstOccurrence = true;
        boolean isFloatFirstOccurrence = true;
        boolean isStringFirstOccurrence = true;
        String readString;

        do {
            readString = reader.getNextString();

            String[] subStringsArray = readString.split("\n");
// ******************************** Парсинг строк в типы ********************************
            for (String currentSubString : subStringsArray) {
                try {
                    long i = Long.parseLong(currentSubString);
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
        if (this.intsWriter != null) {
            System.out.println(intStatsCollector.getStats());
            this.intsWriter.close();
        }
        if (this.floatsWriter != null) {
            System.out.println(floatStatsCollector.getStats());
            this.floatsWriter.close();
        }
        if (this.stringsWriter != null) {
            System.out.println(StringStatsCollector.getStats());
            this.stringsWriter.close();
        }
    }

// *************************** Запись в файлы, передача значений в статистику ***************************

// Создание коллектора статистики Integer, запись статистики, запись в файл
    private void writeInteger(long i, boolean isIntFirstOccurrence) throws IOException {
        if (isIntFirstOccurrence) {
            this.intsWriter = new FileWriter(intsOutputFileName, parser.isAddingNeeded());
            this.intStatsCollector = new IntStatsCollector ();
            this.intStatsCollector.setKindOfStats(this.parser.isFullStatsNeeded());
        }
        try {
            if (parser.isAddingNeeded()) this.intsWriter.append(i + "\n");
            else this.intsWriter.write(i + "\n");
            this.intStatsCollector.addValue(i);
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }
// Создание коллектора статистики float, запись статистики, запись в файл
    private void writeFloat(float f, boolean isFloatFirstOccurrence) throws IOException {
        if (isFloatFirstOccurrence) {
            this.floatStatsCollector = new FloatStatsCollector();
            this.floatStatsCollector.setKindOfStats(this.parser.isFullStatsNeeded());
            this.floatsWriter  = new FileWriter(floatsOutputFileName,  parser.isAddingNeeded());
        }
            try {
                if (parser.isAddingNeeded()) this.floatsWriter.append((String.valueOf(f) + "\n"));
                else this.floatsWriter.write((String.valueOf(f) + "\n"));
                this.floatStatsCollector.addValue(f);
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }
// Создание коллектора статистики String, запись статистики, запись в файл
    private void writeString(String currentSubString, boolean isStringFirstOccurrence) throws IOException {
        if (isStringFirstOccurrence) {
            this.stringStatsCollector = new StringStatsCollector();
            this.stringStatsCollector.setKindOfStats(this.parser.isFullStatsNeeded());
            this.stringsWriter  = new FileWriter(stringsOutputFileName, parser.isAddingNeeded());
        }
        try {
            this.stringStatsCollector = new StringStatsCollector();
            if (parser.isAddingNeeded()) this.stringsWriter.append(currentSubString + "\n");
            else this.stringsWriter.write(currentSubString + "\n");
            this.stringStatsCollector.addValue(currentSubString);
        } catch (IOException e) {
            throw new RuntimeException("Wrong string");
        }
    }

    // ************************** Создание имён файлов *******************
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
