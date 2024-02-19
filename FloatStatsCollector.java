public class FloatStatsCollector extends StatsCollector{
    private int count;
    private float max = Float.NEGATIVE_INFINITY;  // Не надо ли сделать поле статическим?
    private float min = Float.POSITIVE_INFINITY; // Не надо ли сделать поле статическим?
    private float sum = 0;
    private bulean neadFullStats = false; // Поле уже унаследовано ?

/* Метод уже унаследован 
    public IntStatsCollector(bulean neadFullStats){
        this.neadFullStats = neadFullStats;
    }
*/

    public void addValue(float value) {
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

    public float getMax(){
        return max;
    }

    public float getMin(){
        return min;
    }

    public float getMean(){
        return count > 0 ? (sum / count) : 0f; // При значении sum = +/- infinity будет ошибка?
                                               // Может если не встретились float во вводе, вернуть Nan? 
    }

    public float getSum(){
        return sum;
    }
}
