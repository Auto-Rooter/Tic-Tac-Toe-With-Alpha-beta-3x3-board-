/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Struct_.Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Abd
 */
public class Dashboard extends JFrame implements ActionListener {

    JPanel board, configuration, generate,  results;

    JButton JBReset;
    static JLabel resultsLab;
    
    public Dashboard(){
        setLayout(new GridLayout(1, 2));
        setTitle("TicTacToe Game With ALpha_Beta");

        board = new JPanel();
        Board b = new Board(3);
        
        BoardGUI bb = new BoardGUI(b);
        board.add(bb);
        add(board);
        
         configuration = new JPanel();
        configuration.setLayout(new BoxLayout(configuration, BoxLayout.Y_AXIS));

        generate = new JPanel();
        generate.setLayout(new FlowLayout());
        //generate.setBorder();

        JBReset = new JButton("Reset board");
        JBReset.addActionListener(this);
        ButtonGroup dim = new ButtonGroup();

        generate.add(JBReset);

        configuration.add(generate);


        

        results = new JPanel();
        results.setBorder(BorderFactory.createTitledBorder("Results"));
        resultsLab = new JLabel("");
        results.add(resultsLab);

        configuration.add(results);

        add(configuration);

        setMinimumSize(new Dimension(600, 300));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         Board b = new Board(3);
        
        BoardGUI bb = new BoardGUI(b);
        resultsLab.setText("");
        board.removeAll();
        board.revalidate();
        board.repaint();
        board.add(bb);
        board.repaint();
    }
    
}
