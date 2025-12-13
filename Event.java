/**
 ** EVENT: controls what outfit to generate.
 * Example: "Job Interview", "Outdoor Soccer Game", etc.
*/

public class Event {

    private String eventName;          // e.g., "Job Interview"
    private String eventLocation;      // e.g., "Downtown Office"
    private String eventTime;          // e.g., "6:00 PM"
    private String eventType;          // e.g., "Business", "Casual", "Athletic"
    private String recommendedWeather; // e.g., "Cold", "Warm", "Rainy"

    public Event(String eventName,
                String eventLocation,
                String eventTime,
                String eventType,
                String recommendedWeather) {

        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.recommendedWeather = recommendedWeather;
    }

    // Getters and setters for each field
    public String getEventName() {
        return eventName; // Returns the event name
    }

    public void setEventName(String eventName) {
        this.eventName = eventName; // Sets the event name
    }

    public String getEventLocation() {
        return eventLocation; // Returns the event location
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation; // Sets the event location
    }

    public String getEventTime() {
        return eventTime; // Returns the event time
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime; // Sets the event time
    }

    public String getEventType() {
        return eventType; // Returns the event type
    }

    public void setEventType(String eventType) {
        this.eventType = eventType; // Sets the event type
    }

    public String getRecommendedWeather() {
        return recommendedWeather; // Returns the recommended weather
    }

    public void setRecommendedWeather(String recommendedWeather) {
        this.recommendedWeather = recommendedWeather; // Sets the recommended weather
    }

    // VALIDATION: Checks if the minimum required data is present.
    public boolean isValid() {
        return eventName != null && !eventName.isEmpty() &&
                eventType != null && !eventType.isEmpty();
    }

    // Generates a string representation of the event.
    @Override
    public String toString() {
        return "Event{" +
                "name='" + eventName + '\'' +
                ", location='" + eventLocation + '\'' +
                ", time='" + eventTime + '\'' +
                ", type='" + eventType + '\'' +
                ", weather='" + recommendedWeather + '\'' +
                '}';
    }
}