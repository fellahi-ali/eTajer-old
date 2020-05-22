/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MyJFrame.java
 *
 * Created on 17/10/2009, 10:55:11 ص
 */
package myComponents;

import java.awt.Container;
import panels.maj.MajUnitePanel;

/**
 *
 * @author alilo
 */
public class MyJFrame extends javax.swing.JFrame {

    public MyJFrame(String title) {
        setTitle(title);
        initComponents();
    }

    public MyJFrame() {
        initComponents();
    }

    public void hold(Container c) {
        setContentPane(c);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * To Do on Frame Closing
     */
    public void doOnClose() {
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
        setTitle("Test Frame");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_close
        doOnClose();
    }//GEN-LAST:event_close

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        MyJFrame f = new MyJFrame();
        f.hold(new MajUnitePanel(null));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
