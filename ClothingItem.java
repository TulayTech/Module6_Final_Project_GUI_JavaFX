/**
 * * JAVA FINAL PROJECT: VIRTUAL WARDROBE - Test/TestClasses
    Abstract parent class: Represents a clothing item in the virtual wardrobe.
    Subclasses: (Outerwear, Top Item, BottomItem. etc.) - Will be extended to specialize
    category.
*/

public abstract class ClothingItem {
    /**
     ** FIELDS
     */
    // Encapsulation: Protects data, prevents direct modification, must use getters and setters for controlled access
    //  Private Fields - Stores information about a clothing item.
    private String itemName;
    private String category;
    private String type;
    private String description;
    private String color;
    private String code;

    // Constructors: Main constructors used by subclasses.
    public ClothingItem ( String itemName,
                        String category,
                        String type,
                        String description,
                        String color,
                        String code) {

                            // constructor to initialize a clothing item with all required data. A jacket must have a type, color etc...
                            this.itemName = itemName;
                            this.category = category;
                            this.type = type;
                            this.description = description;
                            this.color = color;
                            this.code = code;
    }

    /**
     ** GETTERS AND SETTERS
     */

     // When using encapsulation, data is private. Access must go through the public method.
     // Helps to prevent unwanted data.

    public String getItemName() {
        return itemName;
    }

    public void setItemName( String itemName) {
        this.itemName = itemName;
    }

    public String setCategory() {
        return category;
    }

    // Only subclasses and classes in the same package can change the category.
    // Setter method to update targeted field (category)
    protected void setCategory ( String category ) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     ** BEHAVIOR METHODS
     */

    // Return a text to represent the item
    public String getDisplayText() {
        return itemName + " (" + color + " " + type + ") - " + code;
    }

    /**
     * @return true if the minimum fields required are non-empty.
    */
    public boolean isValid() {
        return itemName != null && !itemName.isEmpty()
            && category != null && !category.isEmpty()
            && type     != null && !type.isEmpty();
    }

    @Override
    public String toString() {
        return getDisplayText();
    }
}