public abstract class StatsCollector {
    protected int count = 0;
    protected boolean needFullStats = false;
    protected String stats;

    public StatsCollector(){
        this.needFullStats = false;
        this.count = 0;
        this.stats = "";
    }

    public StatsCollector(boolean needFullStats){
        this.needFullStats = needFullStats;
        this.count = 0;
        this.stats = "";
    }

    public void setKindOfStats(boolean needFullStats){
        this.needFullStats = needFullStats;
    }

    public int getCount(){
        return count;
    }
}
