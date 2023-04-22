package main.javafx;

import javafx.scene.transform.Translate;

/**
 * Using this class to determine the positions of our
 * Bugs by doing the necessary arithmetic needed to figure that out.
 *
 * @author Nicholas G
 */
public class DistanceCalculator {
    private int steps = 0;

    /**
     * Our constructor takes a parameter for the amount of desired steps to start off with.
     * @param steps amount of steps to begin.
     */
    public DistanceCalculator(int steps){
        this.steps = steps;
    }

    /**
     * Distance formula to determine the distance of two bugs.
     * @param x2 Bug 2 X
     * @param x1 Bug 1 X
     * @param y2 Bug 2 Y
     * @param y1 Bug 1 Y
     * @return the total distance between Bug A and Bug B.
     */
    private double findDistance(double x2, double x1, double y2, double y1){
        return Math.sqrt((Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2)));
    }

    /**
     * Directly finds the distance between two bugs.
     * @param b1 Bug A
     * @param b2 Bug B
     * @return the total distance between Bug A and Bug B
     */
    private double findDistance(Bug b1, Bug b2){
        double x2 = b2.getPos().getX();
        double x1 = b1.getPos().getX();
        double y2 = b2.getPos().getY();
        double y1 = b1.getPos().getY();
        return findDistance(x2, x1, y2, y1);
    }

    /**
     * Solves for X3 and Y3
     * By using..
     * h1 / h2 = (x3-x1) / (x2-x1)
     * stepAmountH1 / distanceH2 = (x3 - bug1Pos) / (bug2Pos - bug1Pos)
     * @param distanceH2 the distance between Bug A and Bug B.
     * @param stepAmountH1 the amount of steps wanting to be taken.
     * @param bug1Pos the current position of Bug A (on either X and Y).
     * @param bug2Pos the current position of Bug B (on either X and Y).
     * @return the solved number for either X3 and Y3.
     */
    private double solveXorY3(double distanceH2, int stepAmountH1, double bug1Pos, double bug2Pos){
        double h1 = stepAmountH1;
        double h2 = distanceH2;
        double leftSideEquation = h1 * (bug2Pos - bug1Pos);     // b2 = X2 or Y2     b1 = X1 or Y1
        double rightSideEquation = (bug1Pos) * distanceH2; // b1 = X1 or Y1      h2 = Distance From Distance Formula
        double addingToBothSides = leftSideEquation + rightSideEquation; // Cancelling out the one side
        return addingToBothSides / h2; // Finally solving for x3 or y3
    }

    /**
     * Sets the amount of desired steps for the bugs to take.
     * @param steps the amount of steps.
     */
    public void setSteps(int steps){
        this.steps = steps;
    }

    /**
     * First gets the distance between Bug A and Bug B.
     * Then finds the specific amount to move the bug(s).
     * @param b1 The desired Bug A
     * @param b2 The desired Bug B
     * @return the new location as a Translate object.
     * We can then get the X and Y from this Translate
     * object to set our new Bug position.
     */
    public Translate doAMove(Bug b1, Bug b2){
        double newX = solveXorY3(findDistance(b1, b2), this.steps, b1.getPos().getX(), b2.getPos().getX()); // Amount of X Value to add to current position
        double newY = solveXorY3(findDistance(b1, b2), this.steps, b1.getPos().getY(), b2.getPos().getY()); // Amount of Y Value to add to current position
        return new Translate(newX, newY);
    }
}
