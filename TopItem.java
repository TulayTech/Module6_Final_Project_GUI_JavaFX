/**
**Represents a top item (t-shirts, button-downs, sweaters etc...).
	Category is fixed to "Top"
*/

public class TopItem extends ClothingItem {
	public TopItem (String itemName,
					String type,
					String description,
					String color,
					String code) {
		super(itemName, "Top", type, description, color, code);
	}
	@Override
	public String getDisplayText() {
		return "[Top] " + super.getDisplayText();
	}
}