public class StringStatsCollector extends StatsCollector{
    private int count;
    private int maxLength = 0;  // Не надо ли сделать поле статическим?
    private int minLength = Integer.MAX_VALUE; // Не надо ли сделать поле статическим?
    private bulean neadFullStats = false; // Поле уже унаследовано ?

/* Метод уже унаследован 
    public IntStatsCollector(bulean neadFullStats){
        this.neadFullStats = neadFullStats;
    }
*/

    public void addValue(String value) {
        count++;
        if (neadFullStats) {
            if (value.length > maxLength) maxLength = value.length;
            if (value.length < minLength) minLength = value.length;
        }
    }

/* Метод уже унаследован 
    public int getCount(){
        return count;
    }
*/

    public int getMax(){
        return maxLength;
    }

    public int getMin(){
        return minLength;
    }

}
