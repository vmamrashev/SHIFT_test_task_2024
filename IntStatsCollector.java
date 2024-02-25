public class IntStatsCollector extends StatsCollector{

    private long max = Long.MIN_VALUE;
    private long min = Long.MAX_VALUE;
    private long sum = 0;

    public void addValue(long value) {
        count++;
        if (needFullStats) {
            sum = sum + value;
            if (value > max) max = value;
            if (value < min) min = value;
        }
    }

    public long getMax(){
        return max;
    }

    public long getMin(){
        return min;
    }

    public long getMean(){
        return count > 0 ? (sum / count) : 0;
    }

    public long getSum(){
        return sum;
    }

    public String getStats(){
        if (needFullStats){
            this.stats = "\n- Количество записанных в файл значений Int - " + count + "\n"
                            + "Минимальное значение - " + getMin() + "\n"
                            + "Максимальное значение - " + getMax() + "\n"
                            + "Сумма - " + getSum() + "\n"
                            + "Среднее значение - " + getMean();
        }
        else {
            this.stats = "\n- Количество записанных в файл значений Int - " + count + "\n";
        }
        return this.stats;
    }

}
