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
public class QuickSortPanel  extends SortPanel implements Runnable{
    
    private int indexGreen=-1;
    private int indexRed=-1;
    private int indexBlue=-1;
    private int indexWhiteBlue=-1;
    
    
    public QuickSortPanel(int sleeptime, int[] arrayToSort, int numberOfRows) {
        super(sleeptime, arrayToSort, "Quick Sort", "O(nlog(n)/nlog(n)/n²)", "O(1)", numberOfRows);
    }
    
    private void reset(){
        indexGreen=-1;
        indexRed=-1;
        indexBlue=-1;
        indexWhiteBlue=-1;
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
        if(indexWhiteBlue!=-1){
            g.setColor(WHITE_BLUE);
            g.fillRect( borderWidth + columnWidth * indexWhiteBlue, getHeight() - tab[indexWhiteBlue] * columnHeight -  borderHeight, columnWidth, tab[indexWhiteBlue] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * indexWhiteBlue, getHeight() - tab[indexWhiteBlue] * columnHeight -  borderHeight, columnWidth, tab[indexWhiteBlue] * columnHeight);
        }
        if(indexRed!=-1){
            g.setColor(RED);
            g.fillRect( borderWidth + columnWidth * indexRed, getHeight() - tab[indexRed] * columnHeight -  borderHeight, columnWidth, tab[indexRed] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * indexRed, getHeight() - tab[indexRed] * columnHeight -  borderHeight, columnWidth, tab[indexRed] * columnHeight);
        }
        this.paintInfos(g);
    }
    
    private void quicksort(int start, int end) throws InterruptedException{
        if(end-start>0){
            int p = partition(start,end);
            quicksort(start,p);
            quicksort(p+1,end);
            Thread.sleep(sleeptime);
        }
    }
    
    private int partition(int start,int end) throws InterruptedException{
        indexWhiteBlue=start;
        indexBlue=end;
        int pivot = tab[start+(end-start)/2];
        arrayAcceses+=1;
        indexRed=start+(end-start)/2;
        repaint();
        Thread.sleep(sleeptime);
        while(true){
            while(tab[start]<pivot){
                start++;
                indexWhiteBlue=start;
                repaint();
                Thread.sleep(sleeptime);
                arrayAcceses+=1;
                arrayComparisons+=1;
            }
            while(tab[end]>pivot){
                end--;
                indexBlue=end;
                arrayAcceses+=1;
                arrayComparisons+=1;
                repaint();
                Thread.sleep(sleeptime);
            }
            if(start>=end){
                return end;
            }
            int tmp = tab[start];
            tab[start] = tab[end];
            tab[end] = tmp;
            arrayAcceses+=3;
            start++;
            end--;
            indexWhiteBlue=start;
            indexBlue=end;
            repaint();
            Thread.sleep(sleeptime);
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
            quicksort(0,size-1);
            indexRed = -1;
            indexBlue = -1;
            indexWhiteBlue=-1;
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
