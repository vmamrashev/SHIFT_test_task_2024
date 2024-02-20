import java.util.ArrayList;
import java.util.List;


public class Solution {

    //   List<String> fileNames;

    public static void main(String[] args) {


        cliArgsParser parser = new cliArgsParser();
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

    }

}
