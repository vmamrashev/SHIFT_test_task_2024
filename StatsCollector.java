public class StatsCollector {
    private int count;
    private bulean neadFullStats = false;

    public StatsCollector(bulean neadFullStats){
        this.neadFullStats = neadFullStats;
    }

    public int getCount(){
        return count;
    }
}
