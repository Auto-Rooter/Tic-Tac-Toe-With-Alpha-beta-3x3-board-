/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Abd
 */
public class UI_Square extends JButton{
    Point p;
    
 public UI_Square(Point p) {
        this.p = p;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Sans Serif", Font.BOLD, 40));
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}
