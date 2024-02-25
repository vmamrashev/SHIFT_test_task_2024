import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {

        CliArgsParser parser = new CliArgsParser();
        parser.parse(args);
        InputFilesReader filesReader = new InputFilesReader(parser.getFileNames());
        FilesWriter filesWriter = new FilesWriter(parser, args, filesReader);
        filesWriter.splitAndWriteTypes(filesReader);

        System.out.println("Чтение файлов, сортировка по типам и запись в соответствующие типам файлы закончена");
        System.out.println(filesWriter.intStatsCollector.getStats());
        System.out.println(filesWriter.floatStatsCollector.getStats());
        System.out.println(filesWriter.stringStatsCollector.getStats());
    }
}
