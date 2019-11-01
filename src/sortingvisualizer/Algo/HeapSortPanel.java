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
public class HeapSortPanel extends SortPanel implements Runnable{
    
    private int indexGreen=-1;
    private int indexRed=-1;
    private int indexBlue=-1;
    private int indexWhiteBlue=-1;
    
    
    public HeapSortPanel(int sleeptime, int[] arrayToSort, int numberOfRows) {
        super(sleeptime, arrayToSort, "Heap Sort", "O(nlog(n))", "O(1)", numberOfRows);
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
        for(int i=0;i<=indexWhiteBlue;i++){
            g.setColor(WHITE_BLUE);
            g.fillRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
        }
        for(int i=0;i<=indexGreen;i++){
            g.setColor(GREEN);
            g.fillRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
            g.setColor(Color.BLACK);
            g.drawRect( borderWidth + columnWidth * i, getHeight() - tab[i] * columnHeight -  borderHeight, columnWidth, tab[i] * columnHeight);
        }
        for(int i=Math.max(indexGreen+1,indexWhiteBlue+1);i<size;i++){
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


    private void triParTas() throws InterruptedException{//Top down split merge
        
        for(int i=(size/2)-1;i>=0;i--){
            tamis(i,size-1);
            indexWhiteBlue = size-(i*2+1);
        }
        for(int i=size-1;i>0;i--){
            int tmp = tab[i];
            tab[i] = tab[0];
            tab[0] = tmp;
            arrayAcceses+=3;
            indexWhiteBlue =i;
            repaint();
            Thread.sleep(sleeptime);
            tamis(0,i);
            
        }
    }
    
    private void tamis(int noeud, int n) throws InterruptedException{
        int k = noeud;
        int j = 2*k;
        while(j<n){
            if(j<n-1 && tab[j]<tab[j+1]){
                j++;
                Thread.sleep(sleeptime);
                repaint();
            }
            arrayComparisons++;
            arrayAcceses+=2;
            if(tab[k]<tab[j]){
                int tmp = tab[j];
                tab[j] = tab[k];
                tab[k] = tmp;
                arrayAcceses+=3;
                k=j;
                j=2*k;
            }
            else{
                j=n+1;
            }
            arrayAcceses+=2;
            arrayComparisons++;
            indexRed = k;
            Thread.sleep(sleeptime);
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
            indexWhiteBlue=size-1;
            triParTas();
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
