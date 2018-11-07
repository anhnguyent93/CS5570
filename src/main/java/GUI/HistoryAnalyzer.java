package GUI;

import analyzers.ACAAnalyzer;
import analyzers.STAnalyzer;
import components.History;
import creators.HistoryCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryAnalyzer {
    private JTextField historyField;
    private JPanel analyzerView;
    private JTextArea resultArea;
    private JButton randomButton;
    private JButton analyzeButton;
    private JButton clearButton;

    public HistoryAnalyzer() {
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int testTxnCount = 4;
                    int testDataItemsPerTxn = 4;
                    int maxTxnID = 100;
                    int maxDataItemID = 1000;
                    History history = HistoryCreator.createRandomHistory(
                            testTxnCount, testDataItemsPerTxn, maxTxnID, maxDataItemID);
                    historyField.setText(history.toString());
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(new Frame(), ex.getMessage());
                }
            }
        });
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    History history = HistoryCreator.createCustomHistory(historyField.getText());
                    resultArea.append("History: " + history.toString() + "\n\n");
                    resultArea.append(ACAAnalyzer.ACAChecking(history) + "\n");
                    resultArea.append(STAnalyzer.STChecking(history) + "\n");
                    resultArea.append("-------------------------------------\n");
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(new Frame(), ex.getMessage());
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText(null);
                historyField.setText(null);
            }
        });
        historyField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    History history = HistoryCreator.createCustomHistory(historyField.getText());
                    resultArea.append("History: " + history.toString() + "\n\n");
                    resultArea.append(ACAAnalyzer.ACAChecking(history) + "\n");
                    resultArea.append(STAnalyzer.STChecking(history) + "\n");
                    resultArea.append("-------------------------------------\n");
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(new Frame(), ex.getMessage());
                }
            }
        });
    }

    public JPanel getAnalyzerView() {
        return analyzerView;
    }
}
