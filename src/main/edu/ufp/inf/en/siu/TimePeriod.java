package main.edu.ufp.inf.en.siu;

import java.time.LocalDateTime;

public class TimePeriod implements Comparable<TimePeriod>{
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimePeriod(LocalDateTime start, LocalDateTime end) {
		this.start = start;
		this.end = end;
	}
    
	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}
    

	@Override
    public String toString() {
        return this.start + "," + this.end;
    }

	@Override
	public int compareTo(TimePeriod o) {
		if (this.end.isBefore(o.end)) return -1;
        if (this.end.isAfter(o.end)) return 1;
        return 0;
	}

}
