package sortingvisualizer.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sortingvisualizer.Algo.*;


public class SortFrame extends javax.swing.JFrame {

    private final int BORDER_SIZE = 20;
    private final int MARGIN = 10;
    private final int ROWS=1;
    private HashMap<String,Double> times;
    
    public SortFrame() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim);
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sorting Visualisation");

        JPanel algoPanel = new JPanel();
        algoPanel.setBackground(Color.BLACK);
        algoPanel.setPreferredSize(dim);
        algoPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE,BORDER_SIZE,BORDER_SIZE,BORDER_SIZE));
        GridLayout gl = new GridLayout(ROWS,ROWS,MARGIN,0);
        algoPanel.setLayout(gl);
        int[] tab = this.shuffleArray(this.createArray());
        
        
//        ExecutorService ex = Executors.newFixedThreadPool(9);
//        //for(int i = 0; i < ROWS; i++){
//        //    for(int j = 0;j<ROWS;j++){
//                SelectionSortPanel ssp2 = new SelectionSortPanel(0, tab,3);
//                
//                InsersionSortPanel isp2 = new InsersionSortPanel(0, tab,3);
//                
//                BubbleSortOptiPanel bsp2 = new BubbleSortOptiPanel(0, tab,3);
//                
//                CocktailSortPanel csp2 = new CocktailSortPanel(0, tab,3);
//                
//                CombSortPanel cbsp2 = new CombSortPanel(0, tab,3);
//                
//                GnomeSortPanel gsp2 = new GnomeSortPanel(0, tab,3);
//                
//                MergeSortPanel msp2 = new MergeSortPanel(0, tab,3);
//                
//                HeapSortPanel hsp2 = new HeapSortPanel(0, tab,3);
//
//                QuickSortPanel qsp2 = new QuickSortPanel(0, tab,3);
//                
//                ex.execute(ssp2);
//                ex.execute(isp2);
//                ex.execute(bsp2);
//                ex.execute(csp2);
//                ex.execute(cbsp2);
//                ex.execute(gsp2);
//                ex.execute(msp2);
//                ex.execute(hsp2);
//                ex.execute(qsp2);
                
        ExecutorService executor = Executors.newFixedThreadPool(1);
        
        SelectionSortPanel ssp = new SelectionSortPanel(15, tab,1);
        algoPanel.add(ssp);

//        InsersionSortPanel isp = new InsersionSortPanel(15, tab,3);
//        algoPanel.add(isp);
//
//        BubbleSortOptiPanel bsp = new BubbleSortOptiPanel(15, tab,3);
//        algoPanel.add(bsp);
//        
//        CocktailSortPanel csp = new CocktailSortPanel(15, tab,3);
//        algoPanel.add(csp);
//
//        CombSortPanel cbsp = new CombSortPanel(15, tab,3);
//        algoPanel.add(cbsp);
//        
//        GnomeSortPanel gsp = new GnomeSortPanel(15, tab,3);
//        algoPanel.add(gsp);
//
//        MergeSortPanel msp = new MergeSortPanel(15, tab,3);
//        algoPanel.add(msp);
//
//        HeapSortPanel hsp = new HeapSortPanel(15, tab,3);
//        algoPanel.add(hsp);        
//
//        QuickSortPanel qsp = new QuickSortPanel(15, tab,3);
//        algoPanel.add(qsp);
                
        executor.execute(ssp);
//        executor.execute(isp);
//        executor.execute(bsp);
//        executor.execute(csp); 
//        executor.execute(cbsp);
//        executor.execute(gsp);
//        executor.execute(msp);
//        executor.execute(hsp);
//        executor.execute(qsp);
        
        this.add(algoPanel);
        
    }
    
    public void giveTime(String s, double t){
        this.times.put(s, t);
    }
    
    private int[] createArray(){
        int[] tab = new int[100];
        for(int i=0;i<100;i++){
            tab[i]=i+1;
        }
        return tab;
    }
    
    private int[] shuffleArray(int[] tab){//mélange de Fisher-Yates
        for(int i=tab.length-1;i>0;i--){
            int j = (int) (Math.random()*i);
            int tmp = tab[j];
            tab[j] = tab[i];
            tab[i] = tmp;
        }
        return tab;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SortFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SortFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SortFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SortFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SortFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
