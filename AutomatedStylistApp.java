/**
 * AUTOMATED STYLIST APP: GUI LAYER
 * GUI layer. Uses non-GUI model classes (Wardrobe, OutfitGenerator, Event, and ClothingItem subclasses)
 */

import javafx.application.Application;              // Base import for all JavaFX apps
import javafx.collections.FXCollections;            // For ObservableList and other GUI collections
import javafx.collections.ObservableList;

import javafx.geometry.Insets;                     // Spacing
import javafx.geometry.Pos;                        // Alignment

import javafx.scene.Scene;                         // Scene
import javafx.scene.control.*;                     // UI Controls
import javafx.scene.layout.*;                      // Layouts
import javafx.stage.Stage;                         // Stage

public class AutomatedStylistApp extends Application {

    // ================== NON-GUI LOGIC OBJECTS ==================
    private final Wardrobe wardrobe = new Wardrobe();                    // Starts empty, ready to store items
    private final OutfitGenerator outfitGenerator = new OutfitGenerator(wardrobe); // Needs wardrobe to generate outfits

    // ================== STAGE AND SCENE ==================
    private Stage primaryStage;
    private Scene homeScene;

    // ================== START ==================
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Automated Stylist App");

        // Seed starter items so the app can generate outfits right away
        seedSampleWardrobe();

        // Create and set the home scene
        homeScene = buildHomeScene();
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    // ================== BUILD HOME SCENE ==================
    private Scene buildHomeScene() {
        Label title = new Label("Welcome to the Automated Stylist App!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button bottomsButton = new Button("Bottoms");
        Button topsButton = new Button("Tops");
        Button hatsButton = new Button("Hats");
        Button shoesButton = new Button("Shoes");
        Button outerwearButton = new Button("Outerwear");
        Button generateOutfitButton = new Button("Generate Outfit");

        // EVENT HANDLERS FOR BUTTONS
        // IMPORTANT: category keys must match helper switches: "Bottom", "Top", "Hats", "Shoes", "Outerwear"
        bottomsButton.setOnAction(e -> primaryStage.setScene(buildCategoryScene("Bottom")));
        topsButton.setOnAction(e -> primaryStage.setScene(buildCategoryScene("Top")));
        hatsButton.setOnAction(e -> primaryStage.setScene(buildCategoryScene("Hats")));
        shoesButton.setOnAction(e -> primaryStage.setScene(buildCategoryScene("Shoes")));
        outerwearButton.setOnAction(e -> primaryStage.setScene(buildCategoryScene("Outerwear")));
        generateOutfitButton.setOnAction(e -> primaryStage.setScene(buildGenerateScene()));

        // LAYOUT
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(12);
        grid.setVgap(12);

        grid.add(bottomsButton, 0, 0);
        grid.add(topsButton, 1, 0);
        grid.add(hatsButton, 0, 1);
        grid.add(shoesButton, 1, 1);
        grid.add(outerwearButton, 0, 2);
        grid.add(generateOutfitButton, 1, 2);

        VBox root = new VBox(18, title, grid);
        root.setPadding(new Insets(24));
        root.setAlignment(Pos.CENTER);

        return new Scene(root, 820, 520);
    }

    // ================== BUILD CATEGORY SCENE ==================
    private Scene buildCategoryScene(String categoryKey) {
        Label header = new Label("Category: " + categoryLabel(categoryKey));
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // ListView showing current items in that category
        ListView<ClothingItem> listView = new ListView<>();
        refreshList(listView, categoryKey);

        // Fields for adding a new item
        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");

        TextField typeField = new TextField();
        typeField.setPromptText("Type (e.g., Blazer, Jeans)");

        TextField colorField = new TextField();
        colorField.setPromptText("Color (e.g., Blue)");

        TextField codeField = new TextField();
        codeField.setPromptText("Code (e.g., TOP-BLU-POL)");

        TextField descField = new TextField();
        descField.setPromptText("Description");

        Button addButton = new Button("Add Item");
        Button deleteButton = new Button("Delete Selected");
        Button backButton = new Button("Back");

        // ADD BUTTON EVENT HANDLER
        addButton.setOnAction(e -> {
            // IMPORTANT: method signature expects (name, type, desc, color, code)
            ClothingItem created = createItemFromInputs(
                    categoryKey,
                    nameField.getText(),
                    typeField.getText(),
                    descField.getText(),
                    colorField.getText(),
                    codeField.getText()
            );

            if (created == null) {
                showError("Invalid input", "Please fill in at least the Name and Type.");
                return;
            }

            addItemToWardrobe(categoryKey, created);

            // Clear inputs
            nameField.clear();
            typeField.clear();
            colorField.clear();
            codeField.clear();
            descField.clear();

            // Refresh list
            refreshList(listView, categoryKey);
        });

        // DELETE BUTTON EVENT HANDLER
        deleteButton.setOnAction(e -> {
            ClothingItem selected = listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("No item selected", "Please select an item to delete.");
                return;
            }

            removeItemFromWardrobe(categoryKey, selected);
            refreshList(listView, categoryKey);
        });

        // BACK BUTTON EVENT HANDLER
        backButton.setOnAction(e -> primaryStage.setScene(homeScene));

        // LAYOUT
        VBox left = new VBox(10, new Label("Items:"), listView);
        left.setPrefWidth(420);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Type:"), 0, 1);
        form.add(typeField, 1, 1);

        form.add(new Label("Color:"), 0, 2);
        form.add(colorField, 1, 2);

        form.add(new Label("Code:"), 0, 3);
        form.add(codeField, 1, 3);

        form.add(new Label("Description:"), 0, 4);
        form.add(descField, 1, 4);

        HBox formButtons = new HBox(10, addButton, deleteButton, backButton);
        formButtons.setAlignment(Pos.CENTER_LEFT);

        VBox right = new VBox(14, new Label("Add Item:"), form, formButtons);
        right.setPadding(new Insets(0, 0, 0, 10));

        HBox contentBox = new HBox(14, left, right);
        contentBox.setPadding(new Insets(20));

        VBox root = new VBox(12, header, contentBox);
        root.setPadding(new Insets(16));

        return new Scene(root, 980, 560);
    }

    // ================== BUILD GENERATE SCENE ==================
    private Scene buildGenerateScene() {
        Label header = new Label("Generate Outfit");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField eventName = new TextField();
        eventName.setPromptText("Event Name (required)");

        TextField location = new TextField();
        location.setPromptText("Location");

        TextField time = new TextField();
        time.setPromptText("Time");

        ComboBox<String> eventType = new ComboBox<>();
        eventType.getItems().addAll("Business", "Casual", "Athletic");
        eventType.setValue("Casual");

        ComboBox<String> weather = new ComboBox<>();
        weather.getItems().addAll("Cold", "Warm", "Rainy", "Sunny");
        weather.setValue("Warm");

        TextArea output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setPrefHeight(220);

        Button generateBtn = new Button("Generate");
        Button backBtn = new Button("Back");

        // GENERATE OUTFIT EVENT HANDLER
        generateBtn.setOnAction(e -> {
            Event ev = new Event(
                    eventName.getText(),
                    location.getText(),
                    time.getText(),
                    eventType.getValue(),
                    weather.getValue()
            );

            if (!ev.isValid()) {
                showError("Invalid Event", "Event Name and Event Type are required.");
                return;
            }

            outfitGenerator.generateOutfit(ev);
            output.setText(outfitGenerator.getOutfitSummaryText());
        });

        backBtn.setOnAction(e -> primaryStage.setScene(homeScene));

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Event Name:"), 0, 0);
        form.add(eventName, 1, 0);

        form.add(new Label("Location:"), 0, 1);
        form.add(location, 1, 1);

        form.add(new Label("Time:"), 0, 2);
        form.add(time, 1, 2);

        form.add(new Label("Event Type:"), 0, 3);
        form.add(eventType, 1, 3);

        form.add(new Label("Weather:"), 0, 4);
        form.add(weather, 1, 4);

        HBox buttons = new HBox(10, generateBtn, backBtn);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(14, header, form, buttons, new Label("Output:"), output);
        root.setPadding(new Insets(20));

        return new Scene(root, 860, 560);
    }

    // ================== HELPER METHODS ==================
    private void refreshList(ListView<ClothingItem> listView, String categoryKey) {
        ObservableList<ClothingItem> items = FXCollections.observableArrayList();

        switch (categoryKey) {
            case "Outerwear" -> items.addAll(wardrobe.getOuterwear());
            case "Top" -> items.addAll(wardrobe.getTops());
            case "Bottom" -> items.addAll(wardrobe.getBottoms());
            case "Shoes" -> items.addAll(wardrobe.getShoes());
            case "Hats" -> items.addAll(wardrobe.getHats());
        }

        listView.setItems(items);
    }

    private String categoryLabel(String key) {
        return switch (key) {
            case "Outerwear" -> "Outerwear";
            case "Top" -> "Tops";
            case "Bottom" -> "Bottoms";
            case "Shoes" -> "Shoes";
            case "Hats" -> "Hats";
            default -> key;
        };
    }

    /**
     * Creates a ClothingItem based on the category and inputs.
     * Returns null if minimum required fields are missing.
     */
    private ClothingItem createItemFromInputs(String categoryKey,
                                              String name,
                                              String type,
                                              String desc,
                                              String color,
                                              String code) {

        // Basic validation: Name and Type should exist
        if (name == null || name.isBlank() || type == null || type.isBlank()) {
            return null;
        }

        return switch (categoryKey) {
            case "Outerwear" -> new OuterwearItem(name, type, desc, color, code);
            case "Top" -> new TopItem(name, type, desc, color, code);
            case "Bottom" -> new BottomItem(name, type, desc, color, code);

            // Temporary storage if you don't have ShoeItem / HatItem classes yet
            case "Shoes" -> new TopItem(name, "Shoes - " + type, desc, color, code);
            case "Hats" -> new TopItem(name, "Hat - " + type, desc, color, code);

            default -> null;
        };
    }

    private void addItemToWardrobe(String categoryKey, ClothingItem item) {
        switch (categoryKey) {
            case "Outerwear" -> wardrobe.addOuterwear(item);
            case "Top" -> wardrobe.addTop(item);
            case "Bottom" -> wardrobe.addBottom(item);
            case "Shoes" -> wardrobe.addShoes(item);
            case "Hats" -> wardrobe.addHat(item);
        }
    }

    private void removeItemFromWardrobe(String categoryKey, ClothingItem item) {
        switch (categoryKey) {
            case "Outerwear" -> wardrobe.removeOuterwear(item);
            case "Top" -> wardrobe.removeTop(item);
            case "Bottom" -> wardrobe.removeBottom(item);
            case "Shoes" -> wardrobe.removeShoes(item);
            case "Hats" -> wardrobe.removeHat(item);
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void seedSampleWardrobe() {
        wardrobe.addTop(new TopItem("White Dress Shirt", "Dress Shirt", "Slim fit, long sleeve", "White", "TOP-WHT-DRS"));
        wardrobe.addTop(new TopItem("Black T-Shirt", "T-Shirt", "Casual crew neck", "Black", "TOP-BLK-TSH"));
        wardrobe.addBottom(new BottomItem("Navy Dress Pants", "Dress Pants", "Stretch fit", "Navy", "BOT-NVY-DRS"));
        wardrobe.addBottom(new BottomItem("Black Jeans", "Jeans", "Casual denim", "Black", "BOT-BLK-JNS"));
        wardrobe.addOuterwear(new OuterwearItem("Gray Blazer", "Blazer", "Business outerwear", "Gray", "OUT-GRY-BLZ"));

        wardrobe.addShoes(new TopItem("Brown Dress Shoes", "Shoes", "Formal leather shoes", "Brown", "SHO-BRN-DRS"));
        wardrobe.addShoes(new TopItem("White Sneakers", "Shoes", "Everyday sneakers", "White", "SHO-WHT-SNK"));
        wardrobe.addHat(new TopItem("Black Baseball Cap", "Hat", "Sunny day cap", "Black", "HAT-BLK-CAP"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}