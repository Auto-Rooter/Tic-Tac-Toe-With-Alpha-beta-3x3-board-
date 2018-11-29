/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import Struct_.Player_;
import Struct_.Node;
import Struct_.GameState;
import Struct_.Board;
import Struct_.Action;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author Abd
 */
public class BoardGUI extends JPanel implements MouseListener{
    
     Board board;
    long [] Time_to_split = new long[6];
    long [] temp = new long[6];
     private GridBagConstraints gbc;
     private GridBagLayout gbl;
    
     UI_Square[][] grid;
    
    public BoardGUI(Board board) {
    
       this.board = board;
       Node[][] logGrid = board.getGrid();
       grid = new UI_Square[logGrid.length][logGrid.length];
    
       gbl = new GridBagLayout();
       setLayout(gbl);
       gbc = new GridBagConstraints();
    
       for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new UI_Square(new Point(i, j));
                gbc.gridx = j;
                gbc.gridy = i;
                Border border = null;

                if (i < grid.length - 1) {
                    if (j < grid.length - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (j < grid.length - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                grid[i][j].setBorder(border);
                grid[i][j].addMouseListener(this);
                add(grid[i][j], gbc);
            }
        }
    }
    
    
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        GameState gameState = board.isGameOver();
        UI_Square n = (UI_Square) e.getSource();

        if (gameState == GameState.IN_PROGRESS) {
            if (n.isEnabled()) {
                Dashboard.resultsLab.setText("");
                n.setText("X");
                n.setEnabled(false);
                board.move(n.p, Player_.PLAYER_X);
                gameState = board.isGameOver();
                if (gameState == GameState.IN_PROGRESS) {
                    Action m = board.moveOAIAlphaBeta();
                    
                    if (m != null && m.getP() != null) {
                        
                               temp = m.elapsedTime(); 
                                        Time_to_split[0] += temp[0];
                                        Time_to_split[1] += temp[1];
                                        Time_to_split[2] += temp[2];
                                        Time_to_split[3] += temp[3];
                                        Time_to_split[4] += temp[4];
                                        Time_to_split[5] += temp[5];
                             
                               
                               grid[m.getP().x][m.getP().y].setText("O");
                               grid[m.getP().x][m.getP().y].setEnabled(false);
                                   }
                }

                        gameState = board.isGameOver();
                        if (gameState == GameState.O_WON) {
                            System.out.println(">>>>> : "+time_show());
                             
                             Dashboard.resultsLab.setText("State : Computer WON ");
                             Dashboard.resultsLab.setText(Dashboard.resultsLab.getText()+" \n" + time_show() );
                          
                        } else if (gameState == GameState.X_WON) {
                            System.out.println(">>>>> : "+time_show());
                     
                             Dashboard.resultsLab.setText("State : YOU WIN");
                             Dashboard.resultsLab.setText(Dashboard.resultsLab.getText()+" \n" + time_show() );
                        } else if (gameState == GameState.DRAW) {
                            System.out.println(">>>>> : "+time_show());
                             Dashboard.resultsLab.setText("State : DRAW"); 
                             Dashboard.resultsLab.setText(Dashboard.resultsLab.getText()+" \n" + time_show() );
                             
                        }

                        }
                        }
    }

    @Override
public void mousePressed(MouseEvent e) {

        }

@Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
    String time_show(){
                String elapsed = "";
        if(Time_to_split[0] > 0)
            elapsed += Time_to_split[0]+ " h,";
        if(Time_to_split[1] > 0)
            elapsed += Time_to_split[1] + " min,";
        if(Time_to_split[2] > 0)
            elapsed += Time_to_split[2] + " s,";
        if(Time_to_split[3] > 0)
            elapsed += Time_to_split[3] + " ms,";
        if(Time_to_split[4] > 0)
            elapsed += Time_to_split[4]  + " Âµs,";
        if(Time_to_split[5]  > 0)
            elapsed += Time_to_split[5]  + " ns";
        
        
        
                return elapsed;
    }
}
