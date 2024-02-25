public class FloatStatsCollector extends StatsCollector{

    private float max = Float.NEGATIVE_INFINITY;
    private float min = Float.POSITIVE_INFINITY;
    private float sum = 0;

    public void addValue(float value) {
        count++;
        if (needFullStats) {
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
        return count > 0 ? (sum / count) : 0f;

    }

    public float getSum(){
        return sum;
    }

    public String getStats(){
        if (needFullStats){
            this.stats = "\n- Количество записанных в файл значений Float - " + count + "\n"
                    + "Минимальное значение - " + getMin() + "\n"
                    + "Максимальное значение - " + getMax() + "\n"
                    + "Сумма - " + getSum() + "\n"
                    + "Среднее значение - " + getMean();
        }
        else {
            this.stats = "\n- Количество записанных в файл значений Float - " + count + "\n";
        }
        return this.stats;
    }
}
