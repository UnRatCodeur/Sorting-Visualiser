/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingvisualizer.Algo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Juju_2
 */
public abstract class SortPanel extends JPanel implements Runnable{
    
    protected int sleeptime;
    protected int[] tab;
    protected final String ALGONAME;
    protected final String TIME_COMPLEXITY;
    protected final String SPACE_COMPLEXITY;
    protected final int BORDER_SIZE;
    protected int size;
    protected int rows;
    protected int arrayAcceses=0;
    protected int arrayComparisons=0;
    
    protected final Color BLUE = new Color(0, 102, 255);
    protected final Color WHITE_BLUE = new Color(51, 153, 255);
    protected final Color RED = new Color(204, 0, 0);
    protected final Color GREEN = new Color(51, 204, 51);
    protected double timeStart;
    private boolean printTime=false;
    private double realTime;
    private MidiSoundPlayer msp = new MidiSoundPlayer();
    
    
    public SortPanel(int sleeptime, int[] arrayToSort, String algoName, String tComplexity, String sComplexity,int numberOfRows){
        
        this.sleeptime = sleeptime;
        this.size=arrayToSort.length;
        this.tab = java.util.Arrays.copyOf(arrayToSort, this.size);
        this.ALGONAME = algoName;
        this.TIME_COMPLEXITY = tComplexity;
        this.SPACE_COMPLEXITY = sComplexity;
        this.rows=numberOfRows;
        
        this.setBackground(Color.BLACK);
        
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), this.ALGONAME);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        this.setBorder(border);
        this.BORDER_SIZE=5;
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }
    
    protected void paintInfos(Graphics g){
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16-2*this.rows));
        g.setColor(new Color(228,114,25));
        g.drawString("Time Complexity : " + TIME_COMPLEXITY + ", Space Complexity : " + SPACE_COMPLEXITY + ", Sleep time : " + sleeptime + "ms, "+arrayComparisons + " comparisons, " + arrayAcceses + " array accesses" , 20, 30);
        if(printTime){
            g.drawString("Real Time : " + realTime , 20, 30);
        }
    }
    
    protected void playSounds(int i){
        if(rows==1){
            msp.playSound(i);
        }   
    }
    
    @Override
    public void run() {
        System.out.println(ALGONAME+" --> "+(System.currentTimeMillis()-timeStart));
        if(sleeptime==0){
            realTime=(System.currentTimeMillis()-timeStart);
        }
        else{
            printTime = true;
        }
    }
}
