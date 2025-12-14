/**
 **Final Project: Virtual Wardrobe
 *
 * Represents a top clothing item (examples: t-shirt, sweater, button-down).
 * This class fixes the category to "Top".
 */
public class TopItem extends ClothingItem {

    // Constructs a TopItem and sets the category to "Top".
    public TopItem(String itemName,
                String type,
                String description,
                String color,
                String code) {

        super(itemName, "Top", type, description, color, code);
    }

    // Returns a display string that labels this item as a Top.
    @Override
    public String getDisplayText() {
        return "[Top] " + super.getDisplayText();
    }
}