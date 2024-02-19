public class FloatStatsCollector extends StatsCollector{

    private float max = Float.NEGATIVE_INFINITY;
    private float min = Float.POSITIVE_INFINITY;
    private float sum = 0;

    public void addValue(float value) {
        count++;
        if (neadFullStats) {
            sum = sum + value;
            if (value > max) max = value;
            if (value < min) min = value;
        }
    }

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
