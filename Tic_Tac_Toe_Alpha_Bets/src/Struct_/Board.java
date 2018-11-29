/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct_;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Abd
 */
public class Board {
    
    private Node[][] grid;
    private int dimension;
        
    private ArrayList<Action> actions;

    public Board(int dimension) {
        this.grid = new Node[dimension][dimension];
        this.dimension = dimension;
        actions = new ArrayList<>();
        initGrid();
    }
    

    public void initGrid() {
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                grid[i][j] = new Node(i, j, Player_.PLAYER_EMPTY);
    }


    public GameState isGameOver() {
        if (hasWon(Player_.PLAYER_O))
            return GameState.O_WON;
        if (hasWon(Player_.PLAYER_X))
            return GameState.X_WON;
        if (availableNodes().isEmpty())
            return GameState.DRAW;
        return GameState.IN_PROGRESS;
    }
    

    boolean hasWon(Player_ player) {
        return checkLines(player) || checkColumns(player) || checkDiags(player);
    }
    

    boolean checkLines(Player_ player) {
        boolean goodLine = false;
        for (int line = 0; line < dimension && !goodLine; line++) {
            goodLine = true;
            for (int i = 0; i < dimension; i++) {
                if (grid[line][i].getPlayer() != player) {
                    goodLine = false;
                    break;
                }
            }
        }
        return goodLine;
    }
    
    

    boolean checkColumns(Player_ player) {
        boolean goodCol = false;
        for (int col = 0; col < dimension && !goodCol; col++) {
            goodCol = true;
            for (int i = 0; i < dimension; i++) {
                if (grid[i][col].getPlayer() != player) {
                    goodCol = false;
                    break;
                }
            }
        }
        return goodCol;
    }


    boolean checkDiags(Player_ player) {
        boolean goodDiag = true;
        for (int i = 0; i < dimension; i++) {
            if (grid[i][i].getPlayer() != player) {
                goodDiag = false;
                break;
            }
        }
        if (goodDiag) return true;

        goodDiag = true;
        for (int i = 0, j = dimension - 1; i < dimension; i++, j--) {
            goodDiag = true;
            if (grid[j][i].getPlayer() != player) {
                goodDiag = false;
                break;
            }
        }
        return goodDiag;
    }
    
    
    ArrayList<Node> availableNodes() {
        ArrayList<Node> squares = new ArrayList<Node>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grid[i][j].getPlayer() == Player_.PLAYER_EMPTY)
                    squares.add(grid[i][j]);
            }
        }
        return squares;
    }
        
        

    int alphabeta(int depth, Player_ player, int A, int B) {
                GameState gameState = isGameOver();
        if (gameState != GameState.IN_PROGRESS)
            return evaluateLeaf(gameState, depth);

        int alpha = A;
        int beta = B;
        // gamestate == IN PROGRESS
        ArrayList<Integer> values = new ArrayList<>();
        for (Node n : availableNodes()) {
            if (player == Player_.PLAYER_O) {
                move(n.p, Player_.PLAYER_O);
                alpha = Math.max(alpha, alphabeta(depth + 1, Player_.PLAYER_X, alpha, beta));
                if (alpha >= beta) {
                    move(n.p, Player_.PLAYER_EMPTY);
                    return beta;
                }
                values.add(alpha);

                if (depth == 0) {
                    actions.add(new Action(new Point(n.p), alpha));
                }
            } else if (player == Player_.PLAYER_X) {
                move(n.p, Player_.PLAYER_X);
                beta = Math.min(beta, alphabeta(depth + 1, Player_.PLAYER_O, alpha, beta));
                if (alpha >= beta) {
                    move(n.p, Player_.PLAYER_EMPTY);
                    return alpha;
                }
                values.add(beta);
            }
            move(n.p, Player_.PLAYER_EMPTY);
        }
        if (player == Player_.PLAYER_O) {
            return max(values);
        }
        return min(values);
    }


    public Action moveOAIAlphaBeta() {
        long startTime = System.nanoTime();
        alphabeta(0, Player_.PLAYER_O, Integer.MIN_VALUE, Integer.MAX_VALUE);
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        Action computerAction = bestAction();
        computerAction.setTime(elapsedTime);
        move(computerAction.getP(), Player_.PLAYER_O);
        return computerAction;
    }

 
    
     public void move(Point p, Player_ player) {
        grid[p.x][p.y].setPlayer(player);
    }

  public int evaluateLeaf(GameState gameState, int depth) {
        if (gameState == GameState.X_WON) {
            return depth - 100;
        } else if (gameState == GameState.O_WON) {
            return 100 - depth;
        }
        return 0;
    }
  
         Integer min(ArrayList<Integer> list) {
        if (list != null && !list.isEmpty()) {
            Integer min = list.get(0);
            for (Integer item : list) {
                min = Math.min(min, item);
            }
            return min;
        }
        return null;
    }

    Integer max(ArrayList<Integer> list) {
        if (list != null && !list.isEmpty()) {
            Integer max = list.get(0);
            for (Integer item : list) {
                max = Math.max(max, item);
            }
            return max;
        }
        return null;
    }

    Action bestAction() {
        if (actions != null && !actions.isEmpty()) {
            Action max = actions.get(0);
            for (Action a : actions) {
                if (a.getCost() > max.getCost())
                    max = a;
            }
            Action best = new Action(new Point(max.getP()), max.getCost());
            actions.clear();
            return best;
        }
        return null;
    }
        
     
    public void display() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grid[i][j].getPlayer() == Player_.PLAYER_X)
                    System.out.print("X\t");
                else if (grid[i][j].getPlayer() == Player_.PLAYER_O)
                    System.out.print("O\t");
                else
                    System.out.print(".\t");
            }
            System.out.println();
        }
    }

    public Node[][] getGrid() {
        return grid;
    }
    
}
