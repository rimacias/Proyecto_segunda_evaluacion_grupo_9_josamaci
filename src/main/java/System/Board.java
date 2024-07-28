package System;
import TDAs.NodeTree;
import TDAs.Tree;
import java.util.ArrayList;
import java.util.LinkedList;
public class Board {
    Character board[][] = new Character[3][3];

    public Board() {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j] = ' ';
            }
        }
    }
    
    public Board(Character board[][]) {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                this.board[i][j] = board[i][j];
            }
        }
    }
        
    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }
    
    public void insertChar(Coordinate c, Character content){
        board[c.getX()][c.getY()] = content;
    }
    
    public ArrayList<Coordinate> getCoordinatesOf(Character content){
        ArrayList<Coordinate> coords = new ArrayList<>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(content.equals(board[i][j])){
                    coords.add(new Coordinate(i,j));
                }
            }
        }
    return coords;
    }
    
    @Override
    public String toString(){
        String s = "\n------";
        for(int i=0; i<3; i++){
            s+="\n";
            for(int j=0; j<3; j++){
                s+=board[i][j]+" ";
            }
        }
        s+="\n------\n";
        return s; 
    }

    public boolean isNotComplete() {
        return contains(' ');
    }
    
    private boolean contains(Character c){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j].equals(c)){
                    return true;
                }
            }
        }
    return false;
    }
    
    @Override
    public boolean equals(Object o){
        Board b = (Board) o;
        boolean result = false;
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(board[i][j].equals(b.getBoard()[i][j])){
                        result = true;
                    }else{
                        return false;
                    }
                }
            }
        return result;
    }
    
    private int waysToWin(Character c){
        int i = 0;
        ArrayList<Coordinate> coords = this.getCoordinatesOf(c);
        ArrayList<Coordinate> coordsV = this.getCoordinatesOf(' ');
        coords.addAll(coordsV);
        ArrayList<ArrayList<Coordinate>> array = winPossibilities();
        for(ArrayList<Coordinate> t: array){
            if(coords.containsAll(t)){
                i++;
            }
        }
    return i;
    }
  
    public ArrayList<ArrayList<Coordinate>> winPossibilities(){
        ArrayList<ArrayList<Coordinate>> winPos = new ArrayList<>();
            ArrayList<Coordinate> winPos1 = new ArrayList<>();
            winPos1.add(new Coordinate(0,0));
            winPos1.add(new Coordinate(0,1));
            winPos1.add(new Coordinate(0,2));
            
            ArrayList<Coordinate> winPos2 = new ArrayList<>();
            winPos2.add(new Coordinate(1,0));
            winPos2.add(new Coordinate(1,1));
            winPos2.add(new Coordinate(1,2));
            
            ArrayList<Coordinate> winPos3 = new ArrayList<>();
            winPos3.add(new Coordinate(2,0));
            winPos3.add(new Coordinate(2,1));
            winPos3.add(new Coordinate(2,2));
            
            ArrayList<Coordinate> winPos4 = new ArrayList<>();
            winPos4.add(new Coordinate(0,0));
            winPos4.add(new Coordinate(1,1));
            winPos4.add(new Coordinate(2,2));
            
            ArrayList<Coordinate> winPos5 = new ArrayList<>();
            winPos5.add(new Coordinate(0,2));
            winPos5.add(new Coordinate(1,1));
            winPos5.add(new Coordinate(2,0));
            
            ArrayList<Coordinate> winPos6 = new ArrayList<>();
            winPos6.add(new Coordinate(0,0));
            winPos6.add(new Coordinate(1,0));
            winPos6.add(new Coordinate(2,0));
            
            ArrayList<Coordinate> winPos7 = new ArrayList<>();
            winPos7.add(new Coordinate(0,1));
            winPos7.add(new Coordinate(1,1));
            winPos7.add(new Coordinate(2,1));
            
            ArrayList<Coordinate> winPos8 = new ArrayList<>();
            winPos8.add(new Coordinate(0,2));
            winPos8.add(new Coordinate(1,2));
            winPos8.add(new Coordinate(2,2));
            
            winPos.add(winPos1);
            winPos.add(winPos2);
            winPos.add(winPos3);
            winPos.add(winPos4);
            winPos.add(winPos5);
            winPos.add(winPos6);
            winPos.add(winPos7);
            winPos.add(winPos8);
            
        return winPos;
    }
    
    public int boardUtility(Character you, Character rival){
        return waysToWin(you) - waysToWin(rival);
    }
    
    public LinkedList<Tree<Board>> generateAlternatives(Character eval){
        LinkedList<Tree<Board>> b = new LinkedList<>();
            for(Coordinate c:this.getCoordinatesOf(' ')){
                Board bi = new Board(this.getBoard());
                bi.insertChar(c, eval);
                Tree<Board> t = new Tree<>(new NodeTree<>(bi));
                b.add(t);
            }
        return b;
    }
}
