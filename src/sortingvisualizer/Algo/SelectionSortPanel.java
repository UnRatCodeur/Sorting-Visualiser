package sortingvisualizer.Algo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class SelectionSortPanel extends SortPanel implements Runnable{
    
    private int indexGreen=-1;
    private int indexRed=-1;
    private int indexBlue=-1;
    
    
    public SelectionSortPanel(int sleeptime, int[] arrayToSort, int numberOfRows) {
        super(sleeptime, arrayToSort, "Selection Sort", "O(n²)", "O(1)", numberOfRows);
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
    
    @Override
    public void run() {
        this.timeStart=System.currentTimeMillis();
        try {
	    repaint();
            if(sleeptime>0){
                Thread.sleep(2000);
            }   
            for(int i=0;i<size;i++){
                int minIndex = i;
                super.playSounds(minIndex);
                indexRed = minIndex;
                Thread.sleep(sleeptime);
                for(int j=i+1;j<size;j++){
                    indexBlue = j;
                    repaint();
                    Thread.sleep(sleeptime);
                    if(tab[minIndex]>tab[j]){
                        minIndex=j;
                        super.playSounds(minIndex);
                        indexRed=j;
                        repaint();
                        Thread.sleep(sleeptime);
                    }
                    this.arrayComparisons++;
                    this.arrayAcceses+=2;
                }
                if(minIndex!=i){
                    int tmp = tab[i];
                    tab[i] = tab[minIndex];
                    tab[minIndex] = tmp;
                    repaint();
                    Thread.sleep(sleeptime);
                }
                this.arrayAcceses+=3;
            }
            indexRed = -1;
            indexBlue = -1;
            for(indexGreen=0;indexGreen<size;indexGreen++){
                super.playSounds(indexGreen);
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
