/**
 **Final Project: Virtual Wardrobe
 *
 * Represents a shoe item. Category is always "Shoes".
 */
public class ShoeItem extends ClothingItem {

    public ShoeItem(String itemName,
                    String type,
                    String description,
                    String color,
                    String code) {

        super(itemName, "Shoes", type, description, color, code);
    }

    @Override
    public String getDisplayText() {
        return "[Shoes] " + super.getDisplayText();
    }
}