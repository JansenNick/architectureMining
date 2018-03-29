package org.architecturemining.fam.graphics;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

@SuppressWarnings("serial")
public class Arrowhead extends Path2D.Double {

        private double angle = 0;
        private int x = 0, y = 0;

        public Arrowhead(int width, int height) {

            moveTo(0, height/2);
            lineTo(-width/2, -height/2);
            lineTo(width/2, -height/2);
            lineTo(0, height/2);

            closePath();
        }

        public double getAngle() {
            return angle;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void moveLocationBy(int xDelta, int yDelta) {
            this.x += xDelta;
            this.y += yDelta;
        }

        public void rotateByDegrees(double delta) {
            angle += delta;
        }

        public void setLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Shape getTransformedInstance() {
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angle), x , y);
            at.translate(x, y);
            return createTransformedShape(at);
        }

    }