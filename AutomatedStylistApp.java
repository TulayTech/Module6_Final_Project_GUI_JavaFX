/**
 * Automated Stylist App (JavaFX)
 * GUI layer for the wardrobe model (Wardrobe, Event, OutfitGenerator, ClothingItem).
 */

import javafx.application.Application;              // JavaFX app entry point
import javafx.collections.FXCollections;            // Builds observable lists for ListView
import javafx.collections.ObservableList;           // List type used by JavaFX controls

import javafx.geometry.Insets;                      // Padding around layouts
import javafx.geometry.Pos;                         // Alignment inside layouts

import javafx.scene.Scene;                          // Holds the UI
import javafx.scene.control.*;                      // Buttons, labels, fields, alerts, etc.
import javafx.scene.layout.*;                       // VBox, HBox, GridPane
import javafx.stage.Stage;                          // App window

public class AutomatedStylistApp extends Application {

    // Model objects
    private final Wardrobe wardrobe = new Wardrobe();
    private final OutfitGenerator outfitGenerator = new OutfitGenerator(wardrobe);

    // Navigation
    private Stage mainStage;
    private Scene homeScene;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("Automated Stylist App");

        seedSampleWardrobe();

        homeScene = buildHomeScene();
        mainStage.setScene(homeScene);
        mainStage.show();
    }

    // Home screen with navigation buttons
    private Scene buildHomeScene() {
        Label titleLabel = new Label("Automated Stylist App");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Button topsButton = new Button("Tops");
        Button bottomsButton = new Button("Bottoms");
        Button outerwearButton = new Button("Outerwear");
        Button shoesButton = new Button("Shoes");
        Button hatsButton = new Button("Hats");
        Button generateButton = new Button("Generate Outfit");

        topsButton.setOnAction(e -> mainStage.setScene(buildCategoryScene("Top")));
        bottomsButton.setOnAction(e -> mainStage.setScene(buildCategoryScene("Bottom")));
        outerwearButton.setOnAction(e -> mainStage.setScene(buildCategoryScene("Outerwear")));
        shoesButton.setOnAction(e -> mainStage.setScene(buildCategoryScene("Shoes")));
        hatsButton.setOnAction(e -> mainStage.setScene(buildCategoryScene("Hats")));
        generateButton.setOnAction(e -> mainStage.setScene(buildGenerateScene()));

        GridPane menuGrid = new GridPane();
        menuGrid.setAlignment(Pos.CENTER);
        menuGrid.setHgap(12);
        menuGrid.setVgap(12);

        menuGrid.add(topsButton, 0, 0);
        menuGrid.add(bottomsButton, 1, 0);
        menuGrid.add(outerwearButton, 0, 1);
        menuGrid.add(shoesButton, 1, 1);
        menuGrid.add(hatsButton, 0, 2);
        menuGrid.add(generateButton, 1, 2);

        VBox root = new VBox(18, titleLabel, menuGrid);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(24));

        return new Scene(root, 820, 520);
    }

    // Screen for one category: view/add/remove
    private Scene buildCategoryScene(String categoryKey) {
        Label headerLabel = new Label(categoryTitle(categoryKey));
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ListView<ClothingItem> itemListView = new ListView<>();
        updateListView(itemListView, categoryKey);

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Item Name (required)");

        TextField typeField = new TextField();
        typeField.setPromptText("Type (required)");

        TextField colorField = new TextField();
        colorField.setPromptText("Color");

        TextField codeField = new TextField();
        codeField.setPromptText("Code");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete Selected");
        Button backButton = new Button("Back");

        addButton.setOnAction(e -> {
            ClothingItem newItem = buildItem(
                    categoryKey,
                    nameField.getText(),
                    typeField.getText(),
                    descriptionField.getText(),
                    colorField.getText(),
                    codeField.getText()
            );

            if (newItem == null) {
                showError("Invalid input", "Name and Type are required.");
                return;
            }

            addToWardrobe(categoryKey, newItem);

            nameField.clear();
            typeField.clear();
            colorField.clear();
            codeField.clear();
            descriptionField.clear();

            updateListView(itemListView, categoryKey);
        });

        deleteButton.setOnAction(e -> {
            ClothingItem selected = itemListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showError("No selection", "Select an item to delete.");
                return;
            }

            removeFromWardrobe(categoryKey, selected);
            updateListView(itemListView, categoryKey);
        });

        backButton.setOnAction(e -> mainStage.setScene(homeScene));

        // Left side: list
        VBox leftPane = new VBox(10, new Label("Items:"), itemListView);
        leftPane.setPrefWidth(420);

        // Right side: form
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        formGrid.add(new Label("Name:"), 0, 0);
        formGrid.add(nameField, 1, 0);

        formGrid.add(new Label("Type:"), 0, 1);
        formGrid.add(typeField, 1, 1);

        formGrid.add(new Label("Color:"), 0, 2);
        formGrid.add(colorField, 1, 2);

        formGrid.add(new Label("Code:"), 0, 3);
        formGrid.add(codeField, 1, 3);

        formGrid.add(new Label("Description:"), 0, 4);
        formGrid.add(descriptionField, 1, 4);

        HBox formButtons = new HBox(10, addButton, deleteButton, backButton);
        formButtons.setAlignment(Pos.CENTER_LEFT);

        VBox rightPane = new VBox(14, new Label("Add Item:"), formGrid, formButtons);
        rightPane.setPadding(new Insets(0, 0, 0, 10));

        HBox contentRow = new HBox(14, leftPane, rightPane);
        contentRow.setPadding(new Insets(20));

        VBox root = new VBox(12, headerLabel, contentRow);
        root.setPadding(new Insets(16));

        return new Scene(root, 980, 560);
    }

    // Screen for generating an outfit from an Event
    private Scene buildGenerateScene() {
        Label headerLabel = new Label("Generate Outfit");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField eventNameField = new TextField();
        eventNameField.setPromptText("Event Name (required)");

        TextField locationField = new TextField();
        locationField.setPromptText("Location");

        TextField timeField = new TextField();
        timeField.setPromptText("Time");

        ComboBox<String> eventTypeBox = new ComboBox<>();
        eventTypeBox.getItems().addAll("Business", "Casual", "Athletic");
        eventTypeBox.setValue("Casual");

        ComboBox<String> weatherBox = new ComboBox<>();
        weatherBox.getItems().addAll("Cold", "Warm", "Rainy", "Sunny");
        weatherBox.setValue("Warm");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefHeight(220);

        Button generateButton = new Button("Generate");
        Button backButton = new Button("Back");

        generateButton.setOnAction(e -> {
            Event event = new Event(
                    eventNameField.getText(),
                    locationField.getText(),
                    timeField.getText(),
                    eventTypeBox.getValue(),
                    weatherBox.getValue()
            );

            if (!event.isValid()) {
                showError("Invalid event", "Event Name and Event Type are required.");
                return;
            }

            outfitGenerator.generateOutfit(event);
            outputArea.setText(outfitGenerator.getOutfitSummaryText());
        });

        backButton.setOnAction(e -> mainStage.setScene(homeScene));

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);

        formGrid.add(new Label("Event Name:"), 0, 0);
        formGrid.add(eventNameField, 1, 0);

        formGrid.add(new Label("Location:"), 0, 1);
        formGrid.add(locationField, 1, 1);

        formGrid.add(new Label("Time:"), 0, 2);
        formGrid.add(timeField, 1, 2);

        formGrid.add(new Label("Event Type:"), 0, 3);
        formGrid.add(eventTypeBox, 1, 3);

        formGrid.add(new Label("Weather:"), 0, 4);
        formGrid.add(weatherBox, 1, 4);

        HBox buttons = new HBox(10, generateButton, backButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(14, headerLabel, formGrid, buttons, new Label("Output:"), outputArea);
        root.setPadding(new Insets(20));

        return new Scene(root, 860, 560);
    }

    // Loads items from the Wardrobe into the ListView
    private void updateListView(ListView<ClothingItem> listView, String categoryKey) {
        ObservableList<ClothingItem> items = FXCollections.observableArrayList();

        if (categoryKey.equals("Outerwear")) items.addAll(wardrobe.getOuterwear());
        else if (categoryKey.equals("Top")) items.addAll(wardrobe.getTops());
        else if (categoryKey.equals("Bottom")) items.addAll(wardrobe.getBottoms());
        else if (categoryKey.equals("Shoes")) items.addAll(wardrobe.getShoes());
        else if (categoryKey.equals("Hats")) items.addAll(wardrobe.getHats());

        listView.setItems(items);
    }

    // Builds a label for the screen header
    private String categoryTitle(String categoryKey) {
        if (categoryKey.equals("Top")) return "Category: Tops";
        if (categoryKey.equals("Bottom")) return "Category: Bottoms";
        if (categoryKey.equals("Outerwear")) return "Category: Outerwear";
        if (categoryKey.equals("Shoes")) return "Category: Shoes";
        if (categoryKey.equals("Hats")) return "Category: Hats";
        return "Category";
    }

    // Creates the correct subclass for the selected category
    private ClothingItem buildItem(String categoryKey,
                                String name,
                                String type,
                                String description,
                                String color,
                                String code) {

        if (name == null || name.trim().isEmpty()) return null;
        if (type == null || type.trim().isEmpty()) return null;

        if (categoryKey.equals("Outerwear")) return new OuterwearItem(name, type, description, color, code);
        if (categoryKey.equals("Top")) return new TopItem(name, type, description, color, code);
        if (categoryKey.equals("Bottom")) return new BottomItem(name, type, description, color, code);
        if (categoryKey.equals("Shoes")) return new ShoeItem(name, type, description, color, code);
        if (categoryKey.equals("Hats")) return new HatItem(name, type, description, color, code);

        return null;
    }

    // Adds an item to the correct wardrobe list
    private void addToWardrobe(String categoryKey, ClothingItem item) {
        if (categoryKey.equals("Outerwear")) wardrobe.addOuterwear(item);
        else if (categoryKey.equals("Top")) wardrobe.addTop(item);
        else if (categoryKey.equals("Bottom")) wardrobe.addBottom(item);
        else if (categoryKey.equals("Shoes")) wardrobe.addShoe(item);
        else if (categoryKey.equals("Hats")) wardrobe.addHat(item);
    }

    // Removes an item from the correct wardrobe list
    private void removeFromWardrobe(String categoryKey, ClothingItem item) {
        if (categoryKey.equals("Outerwear")) wardrobe.removeOuterwear(item);
        else if (categoryKey.equals("Top")) wardrobe.removeTop(item);
        else if (categoryKey.equals("Bottom")) wardrobe.removeBottom(item);
        else if (categoryKey.equals("Shoes")) wardrobe.removeShoe(item);
        else if (categoryKey.equals("Hats")) wardrobe.removeHat(item);
    }

    // Simple error dialog
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Starter data for testing the UI quickly
    private void seedSampleWardrobe() {
        wardrobe.addTop(new TopItem("White Dress Shirt", "Dress Shirt", "Slim fit, long sleeve", "White", "TOP-WHT-DRS"));
        wardrobe.addTop(new TopItem("Black T-Shirt", "T-Shirt", "Casual crew neck", "Black", "TOP-BLK-TSH"));

        wardrobe.addBottom(new BottomItem("Navy Dress Pants", "Dress Pants", "Stretch fit", "Navy", "BOT-NVY-DRS"));
        wardrobe.addBottom(new BottomItem("Black Jeans", "Jeans", "Casual denim", "Black", "BOT-BLK-JNS"));

        wardrobe.addOuterwear(new OuterwearItem("Gray Blazer", "Blazer", "Business outerwear", "Gray", "OUT-GRY-BLZ"));

        wardrobe.addShoe(new ShoeItem("Brown Dress Shoes", "Dress Shoes", "Formal leather shoes", "Brown", "SHO-BRN-DRS"));
        wardrobe.addShoe(new ShoeItem("White Sneakers", "Sneakers", "Everyday sneakers", "White", "SHO-WHT-SNK"));

        wardrobe.addHat(new HatItem("Black Baseball Cap", "Baseball Cap", "Sunny day cap", "Black", "HAT-BLK-CAP"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}