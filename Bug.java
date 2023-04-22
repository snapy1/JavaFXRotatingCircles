package main.javafx;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

/**
 * This bug class is able to create bugs by using circles.
 * Extends Region so all Nodes within JavaFX are available.
 *
 * @author Nicholas G
 */
public class Bug extends Region {

    private Circle c;
    private int size = 0;
    private Color defaultColor;

    /**
     * Creates our bug constructor.
     * @param size desired size of the bug.
     * @param defaultColor desired default color.
     */
    public Bug (int size, Color defaultColor){
        this.size = size;
        this.defaultColor = defaultColor;
    }

    /**
     * Creates a JavaFX circle object
     * to be used for our bug object.
     */
    public void createBug(){
       this.c = new Circle(size, defaultColor);
    }

    /**
     * Adds the circle (bug) to our desired Pane.
     * @param p the Pane we want to use.
     */
    public void addToScene(Pane p){
        p.getChildren().add(this.c);
    }

    /**
     * Used to set a desired position of our bugs.
     * @param posX desired x pos.
     * @param posY desired y pos.
     */
    public void setPos(double posX, double posY){
        this.c.setLayoutX(posX);
        this.c.setLayoutY(posY);
    }

    /**
     * Sets the color of our bug.
     * @param color the desired color object.
     */
    public void setColor(Color color){
        c.setFill(color);
    }

    /**
     * Gets the position of our current bug object.
     * @return the position as a Translate object, which is
     * easier to get the X and Y later when necessary by doing so.
     */
    public Translate getPos(){
        return new Translate(this.c.getLayoutX(), this.c.getLayoutY());
    }

    /**
     * Sets the default position of our bugs to the corners.
     * Also adds custom padding as desired.
     * @param paneSize The size of the Pane used that contains our bugs.
     * @param corner which corner to position our bug in.
     */
    public void setDefaultPos(double paneSize, int corner){
        double amountOfPadding = 20;
        // Using these if statements we can coordinate which
        // specific corner we would like to position our bugs.
            if (corner == 0){
                this.c.setLayoutX(0 + amountOfPadding);
                this.c.setLayoutY(0 + amountOfPadding);
            }
            if (corner == 1){
                this.c.setLayoutX(paneSize - amountOfPadding);
                this.c.setLayoutY(0 + amountOfPadding);
            }
            if (corner == 2){
                this.c.setLayoutX(0 + amountOfPadding);
                this.c.setLayoutY(paneSize - amountOfPadding);
            }
            if (corner == 3){
                this.c.setLayoutX(paneSize - amountOfPadding);
                this.c.setLayoutY(paneSize - amountOfPadding);
            }
            if (corner < 0 || corner > 3){
                throw new RuntimeException("Corner select must be 1 through 4");
            }
    }

    /**
     * The initial method that starts creating out circle objects and calling them to the Pane.
     * @param p the pane we are using to put our objects on
     * @param paneSize the size of the pane
     * @param cornerNumber which corner we would like to position a bug.
     */
    public void startCircle(Pane p, double paneSize, int cornerNumber){
            createBug();
            setDefaultPos(paneSize, cornerNumber);
            addToScene(p);
            System.out.println(cornerNumber);
    }
}
