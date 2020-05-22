/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package panels.maj;

import entities.Unite;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import myComponents.MyJFrame;
import dao.UniteDAO;
import java.awt.Component;
import java.awt.Window;
import panels.CRUDPanel;

/**
 *
 * @author alilo
 */
public class MajUnitePanel extends panels.maj.MajPanel<Unite, UniteDAO> {

    // Emballage fields
    private String des;
    private double qte;

    /**
     * Creates new form MajFamillePanel
     *
     * @param listPanel
     */
    public MajUnitePanel(CRUDPanel listPanel) {
        super(listPanel);
        initComponents();
        initMajPanel(fieldsPanel);
    }

    // Getters
    @Override
    public UniteDAO getTableDAO() {
        return UniteDAO.getInstance();
    }

    @Override
    public Component getDefaultFocusedComp() {
        return qteCondF;
    }

    @java.lang.Override
    public void initFields(Unite oldEntity) {
        qteCondF.setText(String.valueOf(oldEntity.getQte()));
        desF.setText(oldEntity.getDes());
    }

    @Override
    public void clearFields() {
        // clear vars
        des = "";
        qte = 1;
        // clear UI
        desF.setText("");
        qteCondF.setText("");
        qteCondF.requestFocus();
    }

    @Override
    public boolean verifyFields() {
        String qteTxt = qteCondF.getText().trim();
        String desTxt = desF.getText().trim();

        if (qteTxt.length() == 0) {
            JOptionPane.showMessageDialog(this, "Entrer la quantité conditionnée par cette unité/colis SVP!!!", "Attention...", JOptionPane.WARNING_MESSAGE);
            qteCondF.requestFocus();
            return false;
        }

        this.qte = qteCondF.getValue();
        if (this.qte <= 0) {
            JOptionPane.showMessageDialog(this, "La Qte conditionnée doit être supérieur à 0 !!!", "Attention...", JOptionPane.WARNING_MESSAGE);
            qteCondF.requestFocus();
            return false;
        }

        if (desTxt.length() == 0) {
             des = String.valueOf(qte);
        }else{
            des = desTxt;
        }
        return true;
    }

    @Override
    public boolean save() {
        if (verifyFields()) {
            Unite unite = new Unite(0, des, qte);
            setEditedEntity(unite);
            return super.save();
        } else {
            setSaved(false);
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        fieldsPanel = new javax.swing.JPanel();
        qteP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        qteCondF = new myComponents.DecimalJField();
        desP = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        desF = new myComponents.MyJField();

        fieldsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        qteP.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                qtePComponentHidden(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                qtePComponentShown(evt);
            }
        });
        qteP.setLayout(new javax.swing.BoxLayout(qteP, javax.swing.BoxLayout.LINE_AXIS));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Quantité Condionné : ");
        qteP.add(jLabel6);

        qteCondF.setToolTipText("Le nombre d'unités dans un colis.");
        qteP.add(qteCondF);

        desP.setMinimumSize(new java.awt.Dimension(75, 32));
        desP.setPreferredSize(new java.awt.Dimension(83, 32));
        desP.setLayout(new javax.swing.BoxLayout(desP, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Désignation d'Unité: ");
        jLabel9.setPreferredSize(new java.awt.Dimension(135, 17));
        desP.add(jLabel9);

        desF.setToolTipText("La désignation de l'unité, qui apparait sur les bons et les factures");
        desF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        desF.setPreferredSize(new java.awt.Dimension(16, 17));
        desF.setPrefsLangKey("MajProd_Ref_Lang");
        desP.add(desF);

        javax.swing.GroupLayout fieldsPanelLayout = new javax.swing.GroupLayout(fieldsPanel);
        fieldsPanel.setLayout(fieldsPanelLayout);
        fieldsPanelLayout.setHorizontalGroup(
            fieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(qteP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        fieldsPanelLayout.setVerticalGroup(
            fieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldsPanelLayout.createSequentialGroup()
                .addComponent(qteP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void qtePComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_qtePComponentShown
        if (getTopLevelAncestor() != null) {
            ((Window) getTopLevelAncestor()).pack();
        }
    }//GEN-LAST:event_qtePComponentShown

    private void qtePComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_qtePComponentHidden
        if (getTopLevelAncestor() != null) {
            ((Window) getTopLevelAncestor()).pack();
        }
    }//GEN-LAST:event_qtePComponentHidden

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private myComponents.MyJField desF;
    private javax.swing.JPanel desP;
    private javax.swing.JPanel fieldsPanel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private myComponents.DecimalJField qteCondF;
    private javax.swing.JPanel qteP;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MyJFrame frame = new MyJFrame();
                frame.getContentPane().add(new MajUnitePanel(null), BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
