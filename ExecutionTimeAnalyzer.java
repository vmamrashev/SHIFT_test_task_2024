import java.io.IOException;

public class ExecutionTimeAnalyzer {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        CliArgsParser parser = new CliArgsParser();
        String[] arguments = {"-f", "10000000.txt"};
        parser.parse(arguments);
        InputFilesReader filesReader = new InputFilesReader(parser.getFileNames());
        FilesWriter filesWriter = new FilesWriter(parser, args, filesReader);
        filesWriter.splitAndWriteTypes(filesReader);

        System.out.println("Чтение файлов, сортировка по типам и запись в соответствующие типам файлы закончена");
        System.out.println(filesWriter.intStatsCollector.getStats());
        System.out.println(filesWriter.floatStatsCollector.getStats());
        System.out.println(filesWriter.stringStatsCollector.getStats());
        long endTime = System.currentTimeMillis();
        System.out.println("\n Затраченное на сортировку время - " + (endTime-startTime) + " миллисекунд.");

    }
}

