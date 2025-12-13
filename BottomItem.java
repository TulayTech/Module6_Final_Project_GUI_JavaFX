/**
 **Represents a bottom item (jeans, shorts, dress pants, etc.).
    Category is fixed to "Bottom".
 */

public class BottomItem extends ClothingItem {
    public BottomItem(String itemName,
                    String type,
                    String description,
                    String color,
                    String code) {
        super(itemName, "Bottom", type, description, color, code);
    }

    @Override
    public String getDisplayText() {
        return "[Bottom] " + super.getDisplayText();
    }
}