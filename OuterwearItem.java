/**
**Represents an outerwear clothing item (jackets, coats, hoodies, etc..)
Category is fixed to outerwear
*/

public class OuterwearItem extends ClothingItem {
	public OuterwearItem(String itemName,
						String type,
						String description,
						String color,
						String code) {
		// Passes category "Outerwear" to the parent constructor
		super(itemName, "Outerwear", type, description, color, code);
		}
		@Override
		public String getDisplayText() {
			return "[Outerwear] " + super.getDisplayText();
		}
}