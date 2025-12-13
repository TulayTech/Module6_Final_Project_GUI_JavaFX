# Copilot Instructions for Automated Stylist JavaFX Project

## Project Overview
This is a JavaFX-based GUI application for an automated stylist system. The project follows a layered architecture with GUI components separated from business logic models.

## Architecture Pattern
- **GUI Layer**: [AutomatedStylistApp.java](AutomatedStylistApp.java) - Main JavaFX application class
- **Model Layer**: Expected classes - `Wardrobe`, `OutfitGenerator`, `Event`, and `ClothingItem` subclasses
- **Single-file structure**: Currently a simple project structure suitable for academic coursework

## JavaFX Development Guidelines

### Application Structure
- Extend `javafx.application.Application` in the main class
- Use `Scene` and `Stage` for window management
- Implement MVC pattern separating UI from business logic

### UI Components
- Use JavaFX FXML for complex layouts or Scene Builder for visual design
- Implement event handlers for user interactions
- Follow JavaFX naming conventions (e.g., `onButtonClick` for event handlers)

### Model Classes to Implement
Based on the header comment, create these core classes:
- `Wardrobe` - Container for clothing items collection
- `OutfitGenerator` - Logic for creating outfit combinations
- `Event` - Represents occasions/events for outfit selection
- `ClothingItem` - Base class with subclasses for different clothing types

### Development Workflow
- Compile: `javac --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml *.java`
- Run: `java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml AutomatedStylistApp`
- No build system configured - pure Java compilation

### Code Organization
- Keep GUI components in the main app class initially
- Separate model classes into individual files as the project grows
- Use packages if the project expands beyond basic structure

### Academic Project Context
- This appears to be a Module 6 final project for a Java class
- Focus on demonstrating JavaFX GUI concepts and OOP principles
- Prioritize clear, educational code structure over complex patterns

## Key Implementation Areas
1. **Scene Management**: Create primary stage with appropriate scene graphs
2. **Event Handling**: Implement user interaction for outfit generation
3. **Data Management**: Design classes to represent clothing items and wardrobes
4. **UI Layout**: Use appropriate JavaFX layout managers (VBox, HBox, GridPane)
5. **Styling**: Apply CSS or inline styles for visual appeal