public class StatsCollector {
    protected int count = 0;
    protected boolean needFullStats = false;

    public StatsCollector(){
        this.needFullStats = false;
        this.count = 0;
    }

    public StatsCollector(boolean needFullStats){
        this.needFullStats = needFullStats;
    }

    public int getCount(){
        return count;
    }
}
