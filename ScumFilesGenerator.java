import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ScumFilesGenerator {
    public static void main(String[] args) throws IOException {
        String fileName =  "./testFile.txt";
        int numberOfStringsInFile = 2147483647;
        FileWriter testFilesWriter  = new FileWriter(fileName);
        for (int i = 0; i < numberOfStringsInFile; i++)
        String stringToWrite = getARandomWritable();
    }

    public String getARandomWritable(){
        String generatedRamdomString ="";
        int coinSide = tossAThreeSidedCoin();
        Random random = new Random(System.nanoTime());
        return generatedRamdomString;
    }


    private int tossAThreeSidedCoin(){
        Random random = new Random(System.nanoTime());
        return 0;
    }

    private int generateARandomInt(){
        return 0;
    }

    private float generateARandomFloat(){
        return 0.0f;
    }

    private String generateARandomString(){
        int leftLimit = 97; // буква 'a'
        int rightLimit = 122; // буква 'z'
        int targetStringLength = 128;
        Random random = new Random(System.nanoTime());
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
