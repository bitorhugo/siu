package main.edu.ufp.inf.en.siu;

public class TimePeriod implements Comparable<TimePeriod>{
    private final long start;
    private final long end;

    public TimePeriod (Long start, long end) {
        if (start > end) throw new IllegalArgumentException("start has to be smaller or equal than end");
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }
    public long getEnd() {
        return end;
    }

    public long getTimePeriod () {
        return this.end - this.start;
    }

    @Override
    public String toString() {
        return this.start + "," + this.end;
    }

    @Override
    public int compareTo(TimePeriod o) {
        if (this.getTimePeriod() < o.getTimePeriod()) return -1;
        if (this.getTimePeriod() > o.getTimePeriod()) return 1;
        else return 0;
    }

}
