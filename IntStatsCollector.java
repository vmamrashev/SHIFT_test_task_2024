public class IntStatsCollector extends StatsCollector{
    private int count;
    private int max = Integer.MinValue;  // Не надо ли сделать поле статическим?
    private int min = Integer.MaxValue; // Не надо ли сделать поле статическим?
    private int sum = 0;
    private bulean neadFullStats = false; // Поле уже унаследовано ?

/* Метод уже унаследован 
    public IntStatsCollector(bulean neadFullStats){
        this.neadFullStats = neadFullStats;
    }
*/
    public void addValue(int value) {
        count++;
        if (neadFullStats) {
            sum = sum + value;
            if (value > max) max = value;
            if (value < min) min = value;
        }
    }

/* Метод уже унаследован 
    public int getCount(){
        return count;
    }
*/
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
