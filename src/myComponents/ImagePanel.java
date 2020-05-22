package myComponents;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/*
 * NewJPanel.java
 *
 * Created on 01/10/2009, 03:50:09 م
 */
/**
 *
 * @author alilo
 */
public class ImagePanel extends javax.swing.JPanel {

    private String imgLocationPath = "/res/png/logo.png";

    /**
     * Creates new form NewJPanel
     */
    public ImagePanel() {
        initComponents();
    }
    
    public ImagePanel(String img) {
        imgLocationPath = img;
        initComponents();
    }

    public void setImgLocationPath(String imgLocationPath) {
        String oldImg = imgLocationPath;
        this.imgLocationPath = imgLocationPath;
        if(!oldImg.equals(imgLocationPath)){
            repaint();        
        }
    }

    public String getImgLocationPath() {
        return imgLocationPath;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon ico = new javax.swing.ImageIcon(getClass().getResource(imgLocationPath));
        Image img = ico.getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(new ImagePanel(), BorderLayout.CENTER);
        f.setSize(500, 400);
        f.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 255, 153));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
