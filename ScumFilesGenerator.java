import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ScumFilesGenerator {
    private static final Random random = new Random(System.nanoTime());

    public static void main(String[] args) throws IOException {
        String fileName = "./testFile.txt";
        int numberOfLinesInFile = 10000000;
        String stringToWrite = "";
        FileWriter testFilesWriter = new FileWriter(fileName);

        for (int i = 0; i < numberOfLinesInFile; i++){
            testFilesWriter.write(getARandomWritable()+"\n");
        }
        testFilesWriter.close();
    }

    public static String getARandomWritable(){
        String generatedRamdomString ="";
        int coinSide = tossAThreeSidedCoin();
        switch (coinSide){
            case 0 -> generatedRamdomString = String.valueOf(generateARandomInt());
            case 1 -> generatedRamdomString = String.valueOf(generateARandomFloat());
            case 2 -> generatedRamdomString = generateARandomString();
        }
        Random random = new Random(System.nanoTime());
        return generatedRamdomString;
    }


    private static int tossAThreeSidedCoin(){
        return random.nextInt(3);
    }

    private static int generateARandomInt(){
        return random.nextInt(2147483647) - 2147483647;
    }

    private static  float generateARandomFloat(){
        return random.nextFloat((float) 3.4E+38F) ;
    }

    private static String generateARandomString(){
        //Random random = new Random(System.nanoTime());
        int leftLimit = 97; // буква 'a'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = random.nextInt(127)+1;

        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}