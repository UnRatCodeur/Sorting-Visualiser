/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingvisualizer.Algo;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Juju_2
 */
public class MergeSortPanel extends SortPanel implements Runnable{
    
    private int indexGreen=-1;
    private int indexRed=-1;
    private int indexBlue=-1;
    
    
    public MergeSortPanel(int sleeptime, int[] arrayToSort, int numberOfRows) {
        super(sleeptime, arrayToSort, "Merge Sort", "O(nlog(n))", "O(n)", numberOfRows);
    }
    
    private void reset(){
        indexGreen=-1;
        indexRed=-1;
        indexBlue=-1;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int bordeRespect = Math.max((getWidth() - ((getWidth() ) / size) * size)/2,2*BORDER_SIZE);// bordure minimale que l'on peut avoir mais supérieur à un minimum
        int columnWidth = (getWidth() - 2 * bordeRespect) / size;// on calcule alors la largeur d'une barre
        int columnHeight = (getHeight() - 4 * BORDER_SIZE) / size; // On centre le graphe sur l'axe des y mais un peu plus en bas
        int borderHeight = Math.max((getHeight() - columnHeight * size - 4*BORDER_SIZE)/2,2*BORDER_SIZE); //on calcule alors la taille d'une barre
        int borderWidth = (getWidth() - columnWidth * size)/2; // on centre le graphe sur l'axe des x avec une marge minimale
        for(int i=0;i<=indexGreen;i++){
            g.setColor(GREEN);
            g.fillRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
        }
        for(int i=indexGreen+1;i<size;i++){
            g.setColor(Color.WHITE);
            g.fillRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
        }
        if(indexBlue!=-1){
            g.setColor(BLUE);
            g.fillRect( borderWidth + columnWidth * indexBlue, getHeight() - tab[indexBlue] * columnHeight -  borderHeight, columnWidth, tab[indexBlue] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * indexBlue, getHeight() - tab[indexBlue] * columnHeight -  borderHeight, columnWidth, tab[indexBlue] * columnHeight);
        }
        if(indexRed!=-1){
            g.setColor(RED);
            g.fillRect( borderWidth + columnWidth * indexRed, getHeight() - tab[indexRed] * columnHeight -  borderHeight, columnWidth, tab[indexRed] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * indexRed, getHeight() - tab[indexRed] * columnHeight -  borderHeight, columnWidth, tab[indexRed] * columnHeight);
        }
        this.paintInfos(g);
    }
    
    private void topdownMergeSort(int start,int end) throws InterruptedException{//Top down split merge
        
        if(end-start>0){
            int middle = (end+start)/2;
            topdownMergeSort(start,middle);
            topdownMergeSort(middle+1,end);
            topdownmerge(start,middle,end);
        }
    }
    
    private void topdownmerge(int start, int middle, int end) throws InterruptedException {
        
        int i =start;
        int j=middle+1;
        int[] tmp = new int[(end-start)+1];

        indexRed = i;
        indexBlue = j;
        for(int k=0;k<=end-start;k++){
            if(i<=middle && (j>end || tab[i]<tab[j])){
                tmp[k] = tab[i];
                i++;
                indexRed = i;
            }
            else{
                tmp[k] = tab[j];
                j++;
                indexBlue = j-1;
            }
            arrayAcceses+=3;
            arrayComparisons+=1;
            repaint();
            Thread.sleep(sleeptime);
        }
        indexRed = -1;
        for (int k = 0; k <=end-start; k++) {
            tab[start + k] =  tmp[k];
            arrayAcceses+=1;
            indexBlue = start + k-1;
            repaint();
        }

    }
    
    @Override
    public void run() {
        this.timeStart=System.currentTimeMillis();
        try {
            repaint();
            if(sleeptime>0){
                Thread.sleep(2000);
            }   
            topdownMergeSort(0,size-1);
            indexRed = -1;
            indexBlue = -1;
            for(indexGreen=0;indexGreen<size;indexGreen++){
                repaint();
                Thread.sleep(sleeptime);
            }
            indexGreen-=1;
    } catch (InterruptedException e) {
    }
    repaint();
    super.run();
    }

    
    
}
