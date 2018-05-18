package net.intelie.challenges;

/**
 * This is just an event stub, feel free to expand it if needed.
 */
public class Event {
    private final String type;
    private final long timestamp;
    public Event next;

    public Event(String type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
        next = null;
    }

    public String type() {
        return type;
    }

    public long timestamp() {
        return timestamp;
    }
}
