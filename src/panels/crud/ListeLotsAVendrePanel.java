/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CtegoriePanel.java
 *
 * Created on 17/10/2009, 11:25:33 ص
 */
package panels.crud;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.ResultSet;
import myComponents.MyJFrame;

/**
 *
 * @author alilo
 */
public class ListeLotsAVendrePanel extends ListeLotsEnStockPanel {
    {
        setPreferredSize(new Dimension(700, 500));
    }

    public ListeLotsAVendrePanel(Container owner, boolean checkable) {
        super(owner, checkable);
        showTotalStock(false);
    }

    @Override
    public ResultSet getResultSet() {
        return getTableDAO().getLotsAVendre();
    }

    @Override
    public ListeLotsAVendrePanel initTableView() {
        super.initTableView();
        getTable().setColumnVisible("ID_CATEG", false);
        getTable().setColumnVisible("Réf.Produit", false);
        getTable().setColumnVisible("Dépôt", false);
        getTable().setColumnVisible("PU.Gros", false);
        getTable().setColumnVisible("PU.Demi-Gr", false);
        getTable().setColumnVisible("PU.Super-Gr", false);
        getTable().setColumnVisible("Date.Exp", false);
        getTable().setColumnsPreferredWidths(new int[]{100, 250, 80, 80, 30, 100, 90});
        return this;
    }

    @Override
    public BigDecimal calculateTotal() {
        return BigDecimal.ZERO;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MyJFrame frame = new MyJFrame();
                frame.getContentPane().add(new ListeLotsAVendrePanel(frame, false), BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
