import GUI.HistoryAnalyzer;
import analyzers.ACAAnalyzer;
import analyzers.STAnalyzer;
import components.History;
import creators.HistoryCreator;

import javax.swing.*;
import java.awt.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("History Analyzer");
        frame.setContentPane(new HistoryAnalyzer().getAnalyzerView());
        frame.setSize(900, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //MenuPresenter.menu();
    }
}
