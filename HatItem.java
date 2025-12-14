/**
 * Final Project: Virtual Wardrobe
 *
 * Represents a hat item. Category is always "Hat".
 */
public class HatItem extends ClothingItem {

    public HatItem(String itemName,
                String type,
                String description,
                String color,
                String code) {

        super(itemName, "Hat", type, description, color, code);
    }

    @Override
    public String getDisplayText() {
        return "[Hat] " + super.getDisplayText();
    }
}