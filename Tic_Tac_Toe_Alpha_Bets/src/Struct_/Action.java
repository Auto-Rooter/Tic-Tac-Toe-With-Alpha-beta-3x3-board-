/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct_;
import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Abd
 */
public class Action {
    
    
    private Point p;
    private int cost;
    private long time;

    
    public Action(Point p, int cost) {
        this.p = new Point(p);
        this.cost = cost;
    }
    
    
    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    public long [] elapsedTime(){
        long [] Time_to_finish = new long[6];
        long tmp = time;
        long hours = TimeUnit.NANOSECONDS.toHours(tmp);
        tmp -= TimeUnit.HOURS.toNanos(hours);
        long minutes = TimeUnit.NANOSECONDS.toMinutes(tmp);
        tmp -= TimeUnit.MINUTES.toNanos(minutes);
        long seconds = TimeUnit.NANOSECONDS.toSeconds(tmp);
        tmp -= TimeUnit.SECONDS.toNanos(seconds);
        long millis = TimeUnit.NANOSECONDS.toMillis(tmp);
        tmp -= TimeUnit.MILLISECONDS.toNanos(millis);
        long micro = TimeUnit.NANOSECONDS.toMicros(tmp);
        tmp -= TimeUnit.MICROSECONDS.toNanos(micro);
        long nano = tmp;

        Time_to_finish[0] = hours;
        Time_to_finish[1] = minutes;
        Time_to_finish[2] = seconds;
        Time_to_finish[3] = millis;
        Time_to_finish[4] = micro;
        Time_to_finish[5] = nano;
        
        return Time_to_finish;
    }
    
    
        @Override
    public String toString() {
        return "[" + p.x +","+ p.y+"] => "+cost;
    }
}
