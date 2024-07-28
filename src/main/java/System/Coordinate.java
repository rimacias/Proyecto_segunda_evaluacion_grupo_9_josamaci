package System;

import java.util.ArrayList;

public class Coordinate {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }
    
    @Override
    public String toString(){
        return "x: "+x+" y: "+y;
    }
    
    @Override
    public boolean equals(Object o){
        Coordinate c = (Coordinate)o;
        return (this.x == c.getX() && this.y == c.getY());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        return hash;
    }
    
}
