public class StatsCollector {
    protected int count = 0;
    protected boolean neadFullStats = false;

    public StatsCollector(){
        this.neadFullStats = false;
        this.count = 0;
    }

    public StatsCollector(boolean neadFullStats){
        this.neadFullStats = neadFullStats;
    }

    public int getCount(){
        return count;
    }
}
