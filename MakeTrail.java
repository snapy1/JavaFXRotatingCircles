package main.javafx;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;

/**
 * This class is able to create lines that we used for MakeTrail objects.
 * It tracks the current and previous position of our Bugs to set a
 * dashed line to follow them as they change position.
 *
 * @author Nicholas G
 */

public class MakeTrail extends Region {
     private Line line = new Line();
     private Color color;

    /**
     * Allowing the user to select a desired
     * color as the parameter for the constructor.
     * @param color desired color
     */
    public MakeTrail(Color color){
        this.color = color;
    }

    /**
     * creates our line object.
     */
    private void createLine(){
        this.line = new Line();
    }

    /**
     * Sets the color with our desired color that was
     * determined by the constructors' parameter.
     */
    private void setColor(){
        this.line.setStroke(this.color);
    }

    /**
     * Gets the position of our bug
     * @param b the desired bug
     * @return the bugs position as a Translate object.
     */
    private Translate getPosition(Bug b){
        return new Translate(b.getPos().getX(), b.getPos().getY());
    }

    /**
     * Allows us to set the initial position
     * of the line based off where our bug is.
     * @param a desired bug.
     */
    private void setStartPos(Bug a){
        this.line.setStartX(getPosition(a).getX());
        this.line.setStartY(getPosition(a).getY());
    }

    /**
     * Allows us to set the end line
     * position based off where our bug is.
     * @param a desired bug.
     */
    private void getEndLinePos(Bug a){
        this.line.setEndX(getPosition(a).getX());
        this.line.setEndY(getPosition(a).getY());
    }

    /**
     * Adds our line to our scene
     * @param p the desired Pane to put the line onto.
     */
    private void addToScene(Pane p){
        p.getChildren().add(this.line);
    }

    /**
     * The start method for our line.
     * @param pane the desired Pane to put the line onto
     * @param bug the desired bug to base its starting location off of.
     */
    public void startLine(Pane pane, Bug bug){
        setStartPos(bug);
        createLine();
        setColor();
        addToScene(pane);
    }

    /**
     * Marks the end point for our line
     * @param pane the desired Pane to put the line onto
     * @param bug the desired bug to determine the lines ending location.
     */
    public void endLine(Pane pane, Bug bug){
        getEndLinePos(bug);
        startLine(pane, bug);
    }
}
