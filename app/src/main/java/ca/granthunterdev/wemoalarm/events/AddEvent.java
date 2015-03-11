package ca.granthunterdev.wemoalarm.events;

/**
 * Created by Grant on 2/19/2015.
 */
public class AddEvent {
    long id;
    public  AddEvent(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
