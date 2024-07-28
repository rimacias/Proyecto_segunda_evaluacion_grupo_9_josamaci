package System;

import TDAs.NodeTree;
import TDAs.Tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Game {
    private Tree<Board> tree;
    private Board board;
    private int gameMode;
    private Player p1;
    private Player p2;
    private String result;
    
    public Game(Tree<Board> board, Player p1, Player p2, int gameMode) {
        this.tree = board;
        this.board = tree.getRoot().getContent();
        this.gameMode = gameMode;
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public Game(Board board, Player p1, Player p2, int gameMode) {
        this.tree = new Tree(new NodeTree(board));
        this.board = tree.getRoot().getContent();
        this.gameMode = gameMode;
        this.p1 = p1;
        this.p2 = p2;
    }

    public Tree<Board> getTree() {
        return tree;
    }

    public void setTree(Tree<Board> tree) {
        this.tree = tree;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
    
    public void generateAlternatives(){
        Tree<Board> tmp = tree.search(board, (Board b1, Board b2)->{return b1.equals(b2) ? 0:1;});
        if(tmp.getChildren()==null){
            LinkedList<Tree<Board>> b = tmp.getRoot().getContent().generateAlternatives(whoTurn().getCharacter());
            tmp.getRoot().setChildren(b);
        }
    }
    
    public void insertChar(Coordinate c){
        generateAlternatives();
        Board b = new Board(board.getBoard());
        b.insertChar(c,whoTurn().getCharacter());
        Tree<Board> tb = tree.search(b, (Board b1, Board b2)->{return b1.equals(b2) ? 0:1;});
        if(tb!= null && tb.getRoot().getContent().equals(b)){
            board = tb.getRoot().getContent();
        }
        switchTurn();
        winOrLose();
    }

    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
    
    public void winOrLose(){
        result = "STILL PLAYING";
        if(winner(p1)){
            result = p1.getName()+" WIN";
        }else if(winner(p2)){
            if(p2.getIsPC() && p2.getName().equals("PC")){
                result = p1.getName()+" LOSE";
            }else{
                result = p2.getName()+" WIN";
            }
        }else if (!board.isNotComplete()){
            result = "DRAW";
        }
    }
    
    private boolean winner(Player eval){
        boolean win = false;
        ArrayList<Coordinate> coordsEval = board.getCoordinatesOf(eval.getCharacter()); 
        ArrayList<ArrayList<Coordinate>> arrayWins = board.winPossibilities();
        for (ArrayList<Coordinate> c: arrayWins){
            if(coordsEval.containsAll(c)){
                win = true;
            }
        }
        return win;
    }
    
    public Map<Tree<Board>, Integer> generateMinimax(){
        int i = -100;
        Tree<Board> tmp = tree.search(board, (Board b1, Board b2)->{return b1.equals(b2) ? 0:1;});
        Map<Tree<Board>, Integer> mapYou = new HashMap<>();
        if(tmp.getChildren()==null){
            generateAlternatives();
        }
        if(tmp.getChildren().size()>1){
            for(Tree<Board> boa: tmp.getChildren()){
                int j = 0;
                LinkedList<Tree<Board>> alternativesRival = boa.getRoot().getContent().generateAlternatives(nextTurn().getCharacter());
                j = alternativesRival.get(0).getRoot().getContent().boardUtility(whoTurn().getCharacter(), nextTurn().getCharacter());
                for(Tree<Board> board: alternativesRival){
                    mapYou.put(boa, j);
                    if(board.getRoot().getContent().boardUtility(whoTurn().getCharacter(), nextTurn().getCharacter())<j){
                        j = board.getRoot().getContent().boardUtility(whoTurn().getCharacter(), nextTurn().getCharacter());
                        mapYou.replace(boa,j);
                    }
                }
            }
        }else if(tmp.getChildren().size()==1){
            mapYou.put(tmp.getChildren().get(0),0);
        }
        return mapYou;
    }
    
    public Tree<Board> minimax(){
        Map<Tree<Board>, Integer> mapYou = this.generateMinimax();
        LinkedList<Tree<Board>> choosenList = new LinkedList<>();
        Tree<Board> b = null;
        if(mapYou.size()>0){
            System.out.println("----MINIMAX----");
            System.out.println(mapYou);
            System.out.println("---------------");
            int content = -10;
            for(int i: mapYou.values()){
                if(i>content){
                    content = i;
                }
            }
            for(Tree<Board> boa: mapYou.keySet()){
                if(mapYou.get(boa).equals(content)){
                    choosenList.add(boa);
                }
            }
            Random r = new Random();
            b = choosenList.get(r.nextInt(choosenList.size()));
            System.out.println("----CHOOSEN----");
            System.out.println(b+", "+content);
            System.out.println("---------------");
        }
        return b;
    }
    
    //This is only for PC
    public Coordinate minimaxCoord(){
        Coordinate coords = new Coordinate();
        if(whoTurn().getIsPC()){
            ArrayList<Coordinate> newCoords = this.minimax().getRoot().getContent().getCoordinatesOf(whoTurn().getCharacter());
            ArrayList<Coordinate> oldCoords = tree.search(board, (Board b1, Board b2)->{return b1.equals(b2) ? 0:1;}).getRoot().getContent().getCoordinatesOf(whoTurn().getCharacter());
            if(newCoords!=null && oldCoords!=null){
                for(Coordinate c: newCoords){
                    if(!oldCoords.contains(c)){
                        coords = c;
                    }
                }
            }
        }
        return coords;
    }
    
    public void switchTurn(){
        if(p1.getIsTurn()){
            p1.setIsTurn(false);
            p2.setIsTurn(true);
        }else{
            p1.setIsTurn(true);
            p2.setIsTurn(false);
        }
    }
    
    public Player whoTurn(){
        return (p1.getIsTurn()) ? p1: p2;
    }
    
    public Player nextTurn(){
        return (p1.getIsTurn()) ? p2: p1;
    }
}
