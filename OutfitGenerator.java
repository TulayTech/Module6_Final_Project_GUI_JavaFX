/**
 **Final Project: Virtual Wardrobe
 *
 * Builds an outfit suggestion by selecting random items from a Wardrobe
 * using details from an Event.
 */
public class OutfitGenerator {

    private Wardrobe wardrobe;

    // The currently selected outfit pieces
    private ClothingItem selectedTop;
    private ClothingItem selectedBottom;
    private ClothingItem selectedOuterwear;
    private ClothingItem selectedShoe;
    private ClothingItem selectedHat;

    // Creates an OutfitGenerator that pulls items from the given wardrobe.
    public OutfitGenerator(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
        clearSelection();
    }

    // Generates an outfit based on the event type and recommended weather.
    public void generateOutfit(Event event) {
        if (event == null || !event.isValid()) {
            clearSelection();
            return;
        }

        // Always pick the base items first
        selectedTop = wardrobe.getRandomTop();
        selectedBottom = wardrobe.getRandomBottom();
        selectedShoe = wardrobe.getRandomShoe();

        // Add outerwear for business events or cold weather
        if (isBusinessEvent(event) || isColdWeather(event)) {
            selectedOuterwear = wardrobe.getRandomOuterwear();
        } else {
            selectedOuterwear = null;
        }

        // Add a hat for casual events or sunny weather
        if (isCasualEvent(event) || isSunnyWeather(event)) {
            selectedHat = wardrobe.getRandomHat();
        } else {
            selectedHat = null;
        }
    }

    // Clears the current outfit selection.
    // This is called when an invalid event is provided.
    private void clearSelection() {
        selectedTop = null;
        selectedBottom = null;
        selectedOuterwear = null;
        selectedShoe = null;
        selectedHat = null;
    }

    // Returns true if the event type is Business.
    private boolean isBusinessEvent(Event event) {
        return "Business".equalsIgnoreCase(event.getEventType());
    }

    // Returns true if the event type is Casual.
    private boolean isCasualEvent(Event event) {
        return "Casual".equalsIgnoreCase(event.getEventType());
    }

    // Returns true if the recommended weather is Cold.
    private boolean isColdWeather(Event event) {
        return "Cold".equalsIgnoreCase(event.getRecommendedWeather());
    }

    // Returns true if the recommended weather is Sunny.
    private boolean isSunnyWeather(Event event) {
        return "Sunny".equalsIgnoreCase(event.getRecommendedWeather());
    }

    // Returns the selected top item.
    public ClothingItem getSelectedTop() {
        return selectedTop;
    }

    // Returns the selected bottom item.
    public ClothingItem getSelectedBottom() {
        return selectedBottom;
    }

    // Returns the selected outerwear item (may be null).
    public ClothingItem getSelectedOuterwear() {
        return selectedOuterwear;
    }

    // Returns the selected shoe item.
    public ClothingItem getSelectedShoe() {
        return selectedShoe;
    }

    // Returns the selected hat item (may be null).
    public ClothingItem getSelectedHat() {
        return selectedHat;
    }

    // Returns a printable summary of the current outfit selection.
    public String getOutfitSummaryText() {

        if (selectedTop == null && selectedBottom == null
                && selectedShoe == null && selectedOuterwear == null
                && selectedHat == null) {
            return "No outfit could be generated. Please add more items or check your event details.";
        }

        String summary = "Recommended Outfit:\n";

        if (selectedTop != null) {
            summary += "Top: " + selectedTop.getDisplayText() + "\n";
        }
        if (selectedBottom != null) {
            summary += "Bottom: " + selectedBottom.getDisplayText() + "\n";
        }
        if (selectedOuterwear != null) {
            summary += "Outerwear: " + selectedOuterwear.getDisplayText() + "\n";
        }
        if (selectedShoe != null) {
            summary += "Shoes: " + selectedShoe.getDisplayText() + "\n";
        }
        if (selectedHat != null) {
            summary += "Hat: " + selectedHat.getDisplayText() + "\n";
        }

        return summary;
    }
}