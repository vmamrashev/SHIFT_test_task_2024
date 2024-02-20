import java.util.ArrayList;
import java.util.List;

//Парсер не универсален. Не устойчив к случаям произвольной очередности ввода аргументов командной строки

public class cliArgsParser {
    boolean needFullStats;          // Флаг, устанавливаемый в true, если нужна полная статистика
    boolean needFilenamePrefix;     // Флаг, устанавливаемый в true, если необходимо добавлять префикс к именам выходных файлов
    boolean needToChooseOutputPath; // Флаг, устанавливаемый в true, если необходимо сохранять выходные файлы не в текущую папку
    boolean needToAddToOutputFiles; // Флаг, устанавливаемый в true, если необходимо дописывать, а не перезаписывать выходные файлы
    String outputPath;              // Строка, хранящая путь к папке, куда сохранять выходные файлы
    String fileNamePrefix;          // Строка, хранящая префикс имён файлов
    List<String> fileNames;         // Список, хранящий имена выходных файлов

    public  cliArgsParser(){
        boolean needFullStats = false;
        boolean needFilenamePrefix = false;
        boolean needToChooseOutputPath = false;
        boolean needToAddToOutputFiles = false;
        String outputPath = "./";
        String fileNamePrefix = "";
        List<String> fileNames = new ArrayList<>();
    }

    public List<String> parse(String[] args){
        int countStatsFlags = 0;                     // Счетчик флагов статистики -p и -s для проверки ввода взаимоисключающих флагов
        boolean localNeedFilenamePrefix = false;     // Введено локально для контроля однократности ввода префикса выходных файлов
        boolean localNeedToChooseOutputPath = false; // Введено локально для контроля однократности ввода пути к выходным файлам
        for (String arg : args) {
                if (arg.charAt(0) == '-') {
                    switch (arg) {
                        case ("-f") -> {
                            countStatsFlags++;                  // Увеличиваем счетчик флагов статистики для дальнейшей проверки ввода взаимоисключающих флагов
                            needFullStats = true;               // Устанавливаем поле needFullStats в true: нужна полная статистика
                        }
                        case ("-s") -> {
                            countStatsFlags++;                  // Увеличиваем счетчик флагов статистики для дальнейшей проверки ввода взаимоисключающих флагов
                            needFullStats = false;              // Устанавливаем поле needFullStats в false: нужна краткая статистика
                        }
                        case ("-a") -> {
                            needToAddToOutputFiles = true;      // Устанавливаем поле needToAddToOutputFiles в true: нужно дописывать выходные файлы
                        }
                        case ("-o") -> {
                            needToChooseOutputPath = true;      // Устанавливаем флаг в true: нужно вводить путь к выходным файлам
                            localNeedToChooseOutputPath = true; // Введено для контроля однократности ввода пути к выходным файлам
                        }
                        case ("-p") -> {
                            needFilenamePrefix = true;
                            localNeedFilenamePrefix = true;     // Введено для контроля однократности ввода префикса выходных файлов
                        }
                        default ->
                                throw new IllegalArgumentException("Неизвестный аргумент: " + arg + "\n" +
                                        "Программа запускается со следующими аргументами: " + "\n" +
                                        "1. -s - краткая, -f - полная статистика." + "\n" +
                                        "2. -a (опциональный) дозапись в ранее созданные выходные файлы. " + "\n " +
                                        "3. -p (опциональный) добавить префикс к именам выходных файлов. " + "\n " +
                                        "4. -o (опциональный) выбор пути к входным файлам. " + "\n " +
                                        "5. остальные параметры – имена входных файлов, ");
                    }
                } else {

                    if(localNeedFilenamePrefix) {
                        fileNamePrefix = fileNamePrefix + arg;  // В поле объекта fileNamePrefix сохраняется значение arg как префикс к именам выходных файлов
                        localNeedFilenamePrefix = false;        // Флаг устанавливается в false, чтобы при последующих итерациях аргументы не возпринимались как префикс
                    }

                    if(localNeedToChooseOutputPath) {
                        outputPath = outputPath + arg;          // В поле объекта outputPath сохраняется значение arg как путь к выходным файлам
                        localNeedToChooseOutputPath = false;    // Флаг устанавливается в false, чтобы при последующих итерациях аргументы не возпринимались как путь к выходным файлам
                    }

                    fileNames.add(arg);

                }
            }

            if(fileNames.isEmpty()) {
                throw new IllegalArgumentException("Не указаны имена входных файлов");
            }

            if(countStatsFlags > 1) {
                throw new IllegalArgumentException("Выбраны взаимоисключающие варианты статистики.\n" +
                        "Введите 1 аргумент: -s - краткая, -f - полная статистика.\n" +
                        "По умолчанию статистика будет краткой");
            }

            if(needFilenamePrefix && fileNamePrefix.isEmpty()) {
                throw new IllegalArgumentException("Не введен префикс для имен выходных файлов");
            }

            if(needToChooseOutputPath && outputPath.equals("./")) {
                throw new IllegalArgumentException("Не введен путь для выходных файлов");
            }

        return fileNames;
    }

    public List<String> getFileNames() {
        return fileNames;
    }
//
    public boolean isFullStatsNeeded() {
        return needFullStats;
    }
    //
    public boolean isFilenamePrefixNeeded() {
        return needFilenamePrefix;
    }
    //
    public boolean isChoosingPathNeeded() {
        return needToChooseOutputPath;
    }

    public boolean isAddingNeeded() {
        return needToAddToOutputFiles;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }
}
