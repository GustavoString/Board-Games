public class Coordinate {
    
    int x;
    int y;

    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setCoordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public boolean equals(Coordinate c){
        if(this.x == c.x && this.y == c.y){
            return true;
        } else{
            return false;
        }
    }
}
