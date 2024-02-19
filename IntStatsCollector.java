public class IntStatsCollector extends StatsCollector{

    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;
    private int sum = 0;

    public void addValue(int value) {
        count++;
        if (neadFullStats) {
            sum = sum + value;
            if (value > max) max = value;
            if (value < min) min = value;
        }
    }

    public int getMax(){
        return max;
    }

    public int getMin(){
        return min;
    }

    public int getMean(){
        return count > 0 ? (sum / count) : 0;
    }

    public int getSum(){
        return sum;
    }
}
