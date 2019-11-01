package sortingvisualizer.Algo;

import java.awt.Color;
import java.awt.Graphics;

public class CombSortPanel extends SortPanel implements Runnable{
    
    private int indexGreen=-1;
    private int indexRed=-1;
    private int indexBlue=-1;
    
    
    public CombSortPanel(int sleeptime, int[] arrayToSort, int numberOfRows) {
        super(sleeptime, arrayToSort, "Comb Sort", "O(nlog(n)/n²/n²)", "O(1)", numberOfRows);
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
            int intervalle = size;
            boolean isSorted = false;
            double shrink = 1.3;
            
            while(!isSorted){
                intervalle /= shrink;
                if(intervalle<1){
                    intervalle=1;
                }
                isSorted = true;
                for(int i=0;i<size-intervalle;i++){
                    indexBlue = i+intervalle;
                    indexRed=i;
                    if(tab[i]>tab[i+intervalle]){
                        int tmp = tab[i];
                        tab[i] = tab[i+intervalle];
                        tab[i+intervalle] = tmp;
                        isSorted = false;
                        indexRed = i+intervalle;
                        indexBlue=i;
                        arrayAcceses+=3;
                    }
                    repaint();
                    Thread.sleep(sleeptime);
                    arrayAcceses+=2;
                    arrayComparisons++;
                }
            }
                    
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
