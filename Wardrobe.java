import java.util.ArrayList; // Importing ArrayList from java.util package
import java.util.List; // Importing List from java.util package


// WARDROBE CLASS: represents the user's stored collection of clothing items.
public class Wardrobe {

    // creates a list to store clothing items by category
    private List<ClothingItem> outerwear;
    private List<ClothingItem> tops;
    private List<ClothingItem> bottoms;
    private List<ClothingItem> shoes;
    private List<ClothingItem> hats;

    // CONSTRUCTOR: initializes the lists for each category
    public Wardrobe() {
        outerwear = new ArrayList<>();
        tops = new ArrayList<>();
        bottoms = new ArrayList<>();
        shoes = new ArrayList<>();
        hats = new ArrayList<>();
    }

    // ADD ITEM METHOD: adds a clothing item to the appropriate list based on its category
    public void addOuterwear(ClothingItem item) {
        if (item != null && item.isValid()) {
            outerwear.add(item);
        }
    }

    public void addTop(ClothingItem item) {
        if (item != null && item.isValid()) {
            tops.add(item);
        }
    }

    public void addBottom(ClothingItem item) {
        if (item != null && item.isValid()) {
            bottoms.add(item);
        }
    }

    public void addShoes(ClothingItem item) {
        if (item != null && item.isValid()) {
            shoes.add(item);
        }
    }

    public void addHat(ClothingItem item) {
        if (item != null && item.isValid()) {
            hats.add(item);
        }
    }

    // REMOVE ITEM METHOD: removes a clothing item from the appropriate list based on its category
    public void removeOuterwear(ClothingItem item) {
        outerwear.remove(item);
    }

    public void removeTop(ClothingItem item) {
        tops.remove(item);
    }

    public void removeBottom(ClothingItem item) {
        bottoms.remove(item);
    }

    public void removeShoes(ClothingItem item) {
        shoes.remove(item);
    }

    public void removeHat(ClothingItem item) {
        hats.remove(item);
    }

    // GETTERS FOR LISTS: returns the list of clothing items for a specific category.
    public List<ClothingItem> getOuterwear() {
        return outerwear;
    }

    public List<ClothingItem> getTops() {
        return tops;
    }

    public List<ClothingItem> getBottoms() {
        return bottoms;
    }

    public List<ClothingItem> getShoes() {
        return shoes;
    }

    public List<ClothingItem> getHats() {
        return hats;
    }

    //HELPER METHODS: returns a random item from a list or null if the list is empty.
    private ClothingItem getRandomItem(List<ClothingItem> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }
        int index = (int) (Math.random() * items.size());
        return items.get(index);
    }

    public ClothingItem getRandomOuterwear() {
        return getRandomItem(outerwear);
    }

    public ClothingItem getRandomTop() {
        return getRandomItem(tops);
    }

    public ClothingItem getRandomBottom() {
        return getRandomItem(bottoms);
    }

    public ClothingItem getRandomShoes() {
        return getRandomItem(shoes);
    }

    public ClothingItem getRandomHat() {
        return getRandomItem(hats);
    }
}