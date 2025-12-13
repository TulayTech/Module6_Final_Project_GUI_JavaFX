/**
    **OUTFITGENERATOR:
    uses a Wardrobe and an Event to build a suggested outfit.
*/

/**
 * OutfitGenerator uses a Wardrobe and an Event to build a suggested outfit.
 * This class does not depend on any user interface code.
 */
public class OutfitGenerator {

    private Wardrobe wardrobe;

    // Fields to remember the last selected outfit
    private ClothingItem selectedTop;
    private ClothingItem selectedBottom;
    private ClothingItem selectedOuterwear;
    private ClothingItem selectedShoes;
    private ClothingItem selectedHat;

    // Constructor to initialize the wardrobe so we know where to pick items from.
    public OutfitGenerator(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }

    // Generates an outfit based on the given Event context.
    public void generateOutfit(Event event) {
        if (event == null || !event.isValid()) {
            clearSelection();
            return;
        }

        // Base outfit pieces (top, bottom, shoes)
        selectedTop = wardrobe.getRandomTop();
        selectedBottom = wardrobe.getRandomBottom();
        selectedShoes = wardrobe.getRandomShoes();

        // Outerwear: required for "Business" or "Cold" events
        if ("Business".equalsIgnoreCase(event.getEventType())
                || "Cold".equalsIgnoreCase(event.getRecommendedWeather())) {
            selectedOuterwear = wardrobe.getRandomOuterwear();
        } else {
            selectedOuterwear = null;
        }

        // Hat: optional for "Casual" or "Sunny" events
        if ("Casual".equalsIgnoreCase(event.getEventType())
                || "Sunny".equalsIgnoreCase(event.getRecommendedWeather())) {
            selectedHat = wardrobe.getRandomHat();
        } else {
            selectedHat = null;
        }
    }

    // Clears the current selection
    private void clearSelection() {
        selectedTop = null;
        selectedBottom = null;
        selectedOuterwear = null;
        selectedShoes = null;
        selectedHat = null;
    }

    // Getters for the selected items

    public ClothingItem getSelectedTop() {
        return selectedTop;
    }

    public ClothingItem getSelectedBottom() {
        return selectedBottom;
    }

    public ClothingItem getSelectedOuterwear() {
        return selectedOuterwear;
    }

    public ClothingItem getSelectedShoes() {
        return selectedShoes;
    }

    public ClothingItem getSelectedHat() {
        return selectedHat;
    }

    /**
     * Returns a text summary of the generated outfit.
     * This can be printed to the console or shown in a future JavaFX UI.
     */
    public String getOutfitSummaryText() {
        if (selectedTop == null && selectedBottom == null
                && selectedShoes == null && selectedOuterwear == null
                && selectedHat == null) {
            return "No outfit could be generated. " +
                    "Please add more items or check your event details.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Recommended Outfit:\n");

        if (selectedTop != null) {
            sb.append("Top: ").append(selectedTop.getDisplayText()).append("\n");
        }
        if (selectedBottom != null) {
            sb.append("Bottom: ").append(selectedBottom.getDisplayText()).append("\n");
        }
        if (selectedOuterwear != null) {
            sb.append("Outerwear: ").append(selectedOuterwear.getDisplayText()).append("\n");
        }
        if (selectedShoes != null) {
            sb.append("Shoes: ").append(selectedShoes.getDisplayText()).append("\n");
        }
        if (selectedHat != null) {
            sb.append("Hat: ").append(selectedHat.getDisplayText()).append("\n");
        }

        return sb.toString();
    }
}