package main.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

/**
 * The Main class that controls all elements
 * of our bugs, and the backend behind them.
 *
 * @author Nicholas G
 */

public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        double sceneX = 400;
        double sceneY = 520;
        double containingBugsSize = 400;
        DistanceCalculator blankCalc = new DistanceCalculator(5); // Setting the step amount to 5 by default.

        BorderPane root = new BorderPane(); // Main Pane
        Pane containingBugs = new Pane(); // Will be the pane containing our bugs.
        FlowPane buttonsPane = new FlowPane(); // Right side, will contain radio buttons and button
        FlowPane rgbValuePane = new FlowPane(); // Left side, will contain our 3 text fields

        buttonsPane.setVgap(5);
        buttonsPane.setOrientation(Orientation.VERTICAL);
        buttonsPane.setPadding(new Insets(5, 5, 5, 5));

        rgbValuePane.setVgap(5);
        rgbValuePane.setOrientation(Orientation.VERTICAL);
        rgbValuePane.setPadding(new Insets(5, 5, 5, 5));

        Button moveBugs = new Button("Move Bugs");
        RadioButton fiveSteps = new RadioButton("5 Steps");
        RadioButton tenSteps = new RadioButton("10 Steps");
        RadioButton fifteenSteps = new RadioButton("15 Steps");

        TextField rValue = new TextField("Red"); // Set back to RED
        TextField gValue = new TextField("Green"); // Set back to GREEN
        TextField bValue = new TextField("Blue"); // Set back to BLUE

        // Creating toggle group and adding radio button nodes to toggle
        // group so only one radio button may be selected at a time.
        ToggleGroup toggleRadioButtons = new ToggleGroup();
        fiveSteps.setToggleGroup(toggleRadioButtons);
        tenSteps.setToggleGroup(toggleRadioButtons);
        fifteenSteps.setToggleGroup(toggleRadioButtons);

        // Adding nodes to the VBox which will contain our 3 radio buttons and one regular button.
        buttonsPane.getChildren().addAll(moveBugs, fiveSteps, tenSteps, fifteenSteps);

        // Using this loop to create all the bug objects
        // and position them at a particular corner.
        Bug[] bugArr = new Bug[4];
        for (int i = 0; i < bugArr.length; i++) {
            bugArr[i] = new Bug(5, Color.BLACK);
            bugArr[i].startCircle(containingBugs, containingBugsSize, i);
        }

        // Adding nodes to our other VBox which will contain our 3 text fields.
        rgbValuePane.getChildren().addAll(rValue, gValue, bValue);
        root.setLeft(rgbValuePane);
        root.setRight(buttonsPane);
        root.setBottom(containingBugs);

        containingBugs.setStyle("-fx-background-color: gray");
        containingBugs.setPrefHeight(containingBugsSize);
        containingBugs.setPrefWidth(containingBugsSize);

        // Setting the action groups for the radio buttons and normal button
        moveBugs.setOnAction(e -> {

            // Firstly retrieving data from the RGB value text box
                double r = Double.parseDouble(rValue.getText());
                double g = Double.parseDouble(gValue.getText());
                double b = Double.parseDouble(bValue.getText());

            // Making all four of our trails and adding the colors if desired by the user
            MakeTrail[] trailArr = new MakeTrail[4];

            // Setting the color (if so desired) to our bugs,
            // and it will add / end a line per iteration of the action group.
            // Giving the look of a neatly drawn dotted line.
            for (int i = 0; i < trailArr.length; i++) {
                bugArr[i].setColor(new Color(r, g, b, 1));
                trailArr[i] = new MakeTrail(Color.YELLOW);
                trailArr[i].startLine(containingBugs, bugArr[i]);
                trailArr[i].endLine(containingBugs, bugArr[i]);
            }

            Translate tb1 = blankCalc.doAMove(bugArr[0], bugArr[2]);
            Translate tb2 = blankCalc.doAMove(bugArr[2], bugArr[3]);
            Translate tb3 = blankCalc.doAMove(bugArr[3], bugArr[1]);
            Translate tb4 = blankCalc.doAMove(bugArr[1], bugArr[0]);

            if (fiveSteps.isSelected()){
                blankCalc.setSteps(5); // Setting the step amount to 5.
                bugArr[0].setPos(tb1.getX(), tb1.getY()); // Bug One Moving To Bug Three
                bugArr[2].setPos(tb2.getX(), tb2.getY()); // Bug Three Moving To Bug Four
                bugArr[3].setPos(tb3.getX(), tb3.getY()); // Bug Four Moving To Bug Two
                bugArr[1].setPos(tb4.getX(), tb4.getY()); // Bug Two Moving To Bug One
            }
            if (tenSteps.isSelected()){
                blankCalc.setSteps(10); // Setting the step amount to 10.
                bugArr[0].setPos(tb1.getX(), tb1.getY()); // Bug One Moving To Bug Three
                bugArr[2].setPos(tb2.getX(), tb2.getY()); // Bug Three Moving To Bug Four
                bugArr[3].setPos(tb3.getX(), tb3.getY()); // Bug Four Moving To Bug Two
                bugArr[1].setPos(tb4.getX(), tb4.getY()); // Bug Two Moving To Bug One
            }
            if (fifteenSteps.isSelected()){
                blankCalc.setSteps(15); // Setting the step amount to 15.
                bugArr[0].setPos(tb1.getX(), tb1.getY()); // Bug One Moving To Bug Three
                bugArr[2].setPos(tb2.getX(), tb2.getY()); // Bug Three Moving To Bug Four
                bugArr[3].setPos(tb3.getX(), tb3.getY()); // Bug Four Moving To Bug Two
                bugArr[1].setPos(tb4.getX(), tb4.getY()); // Bug Two Moving To Bug One
            }
            if (!fiveSteps.isSelected() && !tenSteps.isSelected() && !fifteenSteps.isSelected()) {
                throw new RuntimeException("Must select an amount of steps");
            }
        });

        Scene scene = new Scene(root, sceneX, sceneY);
        stage.setTitle("Nicks Walking Bugs");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {launch();
    }
}