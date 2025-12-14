import java.util.ArrayList;
import java.util.List;

/**
 **Final Project: Virtual Wardrobe
 *
 * Stores clothing items in category lists and can return a random item by category.
 */
public class Wardrobe {

    // Lists for each clothing category
    private List<ClothingItem> outerwearList;
    private List<ClothingItem> topList;
    private List<ClothingItem> bottomList;
    private List<ClothingItem> shoeList;
    private List<ClothingItem> hatList;

    // Builds an empty wardrobe and initializes all category lists.
    // The constructor is public, so it can be accessed from outside the class.
    public Wardrobe() {
        outerwearList = new ArrayList<ClothingItem>();
        topList = new ArrayList<ClothingItem>();
        bottomList = new ArrayList<ClothingItem>();
        shoeList = new ArrayList<ClothingItem>();
        hatList = new ArrayList<ClothingItem>();
    }

    // Adds an outerwear item if it is not null and passes basic validation.
    public void addOuterwear(ClothingItem item) {
        if (item != null && item.isValid()) {
            outerwearList.add(item);
        }
    }

    // Adds a top item if it is not null and passes basic validation.
    public void addTop(ClothingItem item) {
        if (item != null && item.isValid()) {
            topList.add(item);
        }
    }

    // Adds a bottom item if it is not null and passes basic validation.
    public void addBottom(ClothingItem item) {
        if (item != null && item.isValid()) {
            bottomList.add(item);
        }
    }

    // Adds a shoe item if it is not null and passes basic validation.
    public void addShoe(ClothingItem item) {
        if (item != null && item.isValid()) {
            shoeList.add(item);
        }
    }

    // Adds a hat item if it is not null and passes basic validation.
    public void addHat(ClothingItem item) {
        if (item != null && item.isValid()) {
            hatList.add(item);
        }
    }

    // Removes an outerwear item from the wardrobe.
    public void removeOuterwear(ClothingItem item) {
        outerwearList.remove(item);
    }

    // Removes a top item from the wardrobe.
    public void removeTop(ClothingItem item) {
        topList.remove(item);
    }

    // Removes a bottom item from the wardrobe.
    public void removeBottom(ClothingItem item) {
        bottomList.remove(item);
    }

    // Removes a shoe item from the wardrobe.
    public void removeShoe(ClothingItem item) {
        shoeList.remove(item);
    }

    // Removes a hat item from the wardrobe.
    public void removeHat(ClothingItem item) {
        hatList.remove(item);
    }

    // Returns the list of outerwear items.
    public List<ClothingItem> getOuterwearList() {
        return outerwearList;
    }

    // Returns the list of top items.
    public List<ClothingItem> getTopList() {
        return topList;
    }

    // Returns the list of bottom items.
    public List<ClothingItem> getBottomList() {
        return bottomList;
    }

    // Returns the list of shoe items.
    public List<ClothingItem> getShoeList() {
        return shoeList;
    }

    // Returns the list of hat items.
    public List<ClothingItem> getHatList() {
        return hatList;
    }

    // Getter names used by the JavaFX GUI.
    public List<ClothingItem> getOuterwear() {
        return outerwearList;
    }

    public List<ClothingItem> getTops() {
        return topList;
    }

    public List<ClothingItem> getBottoms() {
        return bottomList;
    }

    public List<ClothingItem> getShoes() {
        return shoeList;
    }

    public List<ClothingItem> getHats() {
        return hatList;
    }

    // Wrapper names used by the JavaFX GUI for add/remove.
    public void addShoes(ClothingItem item) {
        addShoe(item);
    }

    public void removeShoes(ClothingItem item) {
        removeShoe(item);
    }

    // Wrapper name used by OutfitGenerator.
    public ClothingItem getRandomShoes() {
        return getRandomShoe();
    }

    // Returns a random item from a list, or null if the list is empty.
    private ClothingItem pickRandomItem(List<ClothingItem> items) {
        if (items == null || items.size() == 0) {
            return null;
        }
        int index = (int)(Math.random() * items.size());
        return items.get(index);
    }

    // Returns a random outerwear item, or null if none exist.
    public ClothingItem getRandomOuterwear() {
        return pickRandomItem(outerwearList);
    }

    // Returns a random top item, or null if none exist.
    public ClothingItem getRandomTop() {
        return pickRandomItem(topList);
    }

    // Returns a random bottom item, or null if none exist.
    public ClothingItem getRandomBottom() {
        return pickRandomItem(bottomList);
    }

    // Returns a random shoe item, or null if none exist.
    public ClothingItem getRandomShoe() {
        return pickRandomItem(shoeList);
    }

    // Returns a random hat item, or null if none exist.
    public ClothingItem getRandomHat() {
        return pickRandomItem(hatList);
    }
}