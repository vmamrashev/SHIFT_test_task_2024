public class StringStatsCollector extends StatsCollector{

    private int maxLength = 0;
    private int minLength = Integer.MAX_VALUE;

    public void addValue(String value) {
        count++;
        if (neadFullStats) {
            if (value.length() > maxLength) maxLength = value.length();
            if (value.length() < minLength) minLength = value.length();
        }
    }

    public int getMax(){
        return maxLength;
    }

    public int getMin(){
        return minLength;
    }

}
