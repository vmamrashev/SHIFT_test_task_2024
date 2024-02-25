public class StringStatsCollector extends StatsCollector{

    private static int maxLength = 0;
    private static int minLength = Integer.MAX_VALUE;
    private static int count;
    private static boolean needFullStats;

    public void addValue(String value) {
        count++;
        if (needFullStats) {
            if (value.length() > maxLength) maxLength = value.length();
            if (value.length() < minLength) minLength = value.length();
        }
    }

    public void setKindOfStats(boolean FullStatsSetter){
        needFullStats = FullStatsSetter;
    }

    public static int getMax(){
        return maxLength;
    }

    public static int getMin(){
        return minLength;
    }

    public static String getStats(){
        String stringStats = "";
        if (needFullStats){
            stringStats = "\n- Количество записанных в файл значений String - " + count + "\n"
                    + "Минимальное длина - " + getMin() + "\n"
                    + "Максимальное длина - " + getMax() + "\n";
        }
        else {
            stringStats = "\n- Количество записанных в файл значений String - " + count;
        }
        return stringStats;
    }

}
