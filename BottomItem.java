/**
 **Final Project: Virtual Wardrobe
 *
 * Represents a bottom clothing item (examples: jeans, shorts, dress pants).
 * The category for this item is always "Bottom".
 */
public class BottomItem extends ClothingItem {

    // Constructs a BottomItem and sets the category to "Bottom".
    public BottomItem(String itemName,
                    String type,
                    String description,
                    String color,
                    String code) {

        super(itemName, "Bottom", type, description, color, code);
    }

    // Returns a display string that labels this item as a Bottom.
    @Override
    public String getDisplayText() {
        return "[Bottom] " + super.getDisplayText();
    }
}
