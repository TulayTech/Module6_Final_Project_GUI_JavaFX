/**
 **Final Project: Virtual Wardrobe
 *
 * Abstract superclass that represents a generic clothing item.
 * This class defines common data fields and behaviors shared
 * by all clothing items in the wardrobe.
 *
 * Concepts used:
 * - Abstraction (abstract class): defines common attributes and methods
 * - Encapsulation (private fields with getters/setters): data is hidden and controlled
 * - Inheritance (extended by specific clothing types): specific types inherit and extend common behaviors
 * - Polymorphism (toString method): different subclasses can provide their own implementations
 */
public abstract class ClothingItem {

    /*
        *DATA FIELDS
    */

    // Private fields enforce encapsulation
    private String itemName;
    private String category;
    private String type;
    private String description;
    private String color;
    private String code;

    /*
        *CONSTRUCTOR
    */

    // Constructs a ClothingItem with all required attributes.
    // This constructor is called by subclasses using super().
    public ClothingItem(String itemName,
                        String category,
                        String type,
                        String description,
                        String color,
                        String code) {

        this.itemName = itemName;
        this.category = category;
        this.type = type;
        this.description = description;
        this.color = color;
        this.code = code;
    }

    /*
       *ACCESSOR & MUTATOR METHODS
    */

    // Returns the item name
    public String getItemName() {
        return itemName;
    }

    // Updates the item name
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Returns the category
    public String getCategory() {
        return category;
    }

    // Updates the category.
    // Protected so only subclasses can modify it.
    protected void setCategory(String category) {
        this.category = category;
    }

    // Returns the clothing type
    public String getType() {
        return type;
    }

    // Updates the clothing type
    public void setType(String type) {
        this.type = type;
    }

    // Updates the description
    public void setDescription(String description) {
        this.description = description;
    }

    // Returns the color
    public String getColor() {
        return color;
    }

    // Updates the color
    public void setColor(String color) {
        this.color = color;
    }

    // Returns the item code
    public String getCode() {
        return code;
    }

    // Updates the item code
    public void setCode(String code) {
        this.code = code;
    }

    /*
        *BEHAVIOR METHODS
    */

    // Returns a formatted text description of the clothing item.
    public String getDisplayText() {
        return itemName + " (" + color + " " + type + ") - " + code;
    }

    // Checks whether the required fields contain valid values.
    public boolean isValid() {
        return itemName != null && !itemName.isEmpty()
            && category != null && !category.isEmpty()
            && type != null && !type.isEmpty();
    }

    // Returns a string representation of the clothing item.
    @Override
    public String toString() {
        return getDisplayText();
    }
}