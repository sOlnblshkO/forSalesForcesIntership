/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsalesforces;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author koyash
 */
public class createProdForm extends javax.swing.JFrame {

    /**
     * Creates new form createProdForm
     */
    public createProdForm() {
        initComponents();
        setResizable(false);
        try {
            refreshTypesComboBox();
        } catch (IOException ex) {
            Logger.getLogger(createProdForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        typeComboBox.addItemListener(new ItemListener() {
            // Listening if a new items of the combo box has been selected.
            public void itemStateChanged(ItemEvent event) {
                try {
                    familyComboBox.removeAllItems();
                    refreshFamiliesComboBox(typeComboBox.getSelectedItem().toString());
                } catch (IOException ex) {
                    Logger.getLogger(createProdForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        familyComboBox.addItem("None");
        imagePathTextField.setEditable(false);
    }

    
    public void refreshFamiliesComboBox(String type) throws IOException
    {
        String path = "families.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String family;
        boolean found = false;
        familyComboBox.addItem("None");
        while (null != (family = br.readLine()))
        {
            if (family.startsWith("<") && type.equals(family.substring(1, family.length() - 1)))
            {
                found = true;
                continue;
            }
            else if (family.startsWith("<") && !type.equals(family.substring(1, family.length() - 1)))
            {
                found = false;
                continue;
            }
            if (found == true)
            {
                familyComboBox.addItem(String.valueOf(family));
            }
        }
        br.close();
    }
    
    public void refreshTypesComboBox() throws IOException
    {
        String path = "types.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String type;
        typeComboBox.addItem("None");
        while (null != (type = br.readLine()))
        {
            typeComboBox.addItem(String.valueOf(type));
        }
        br.close();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        prodNameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descProdTextField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        familyComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        imagePathTextField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        priceSpinner = new javax.swing.JSpinner();
        createProdBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setSize(new java.awt.Dimension(500, 500));

        jLabel1.setText("Name of product");

        jLabel2.setText("Description");

        descProdTextField.setColumns(20);
        descProdTextField.setLineWrap(true);
        descProdTextField.setRows(5);
        descProdTextField.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descProdTextField);

        jLabel3.setText("Type");

        jLabel4.setText("Family");

        jLabel5.setText("Image");

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Price");

        priceSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        priceSpinner.setToolTipText("");

        createProdBtn.setText("Create product");
        createProdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProdBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(familyComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(prodNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagePathTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseBtn))
                            .addComponent(priceSpinner))
                        .addContainerGap(148, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createProdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prodNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(familyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(browseBtn)
                    .addComponent(imagePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(priceSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createProdBtn)
                    .addComponent(cancelBtn))
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("png", "png");
        jFileChooser1.setFileFilter(filter);
        jFileChooser1.showOpenDialog(null);
        try{
            String choosedPath = jFileChooser1.getSelectedFile().getAbsolutePath();
            imagePathTextField.setText(choosedPath);
        } catch (Exception e)
        {
            System.out.println(String.valueOf(e));
        }
    }//GEN-LAST:event_browseBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void createProdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProdBtnActionPerformed
        if (prodNameTextField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Please write name of product");
        } 
        else if (descProdTextField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, "Please write description of product");
        }
        else if (typeComboBox.getSelectedItem().toString().equals("None"))
        {
            JOptionPane.showMessageDialog(rootPane, "Please choose type");
        }
        else if (familyComboBox.getSelectedItem().toString().equals("None"))
        {
            JOptionPane.showMessageDialog(rootPane, "Please choose family");
        }
        else if (priceSpinner.getValue().equals(0))
        {
            JOptionPane.showMessageDialog(rootPane, "Please set cost");
        }
        else
        {
            if (ForSalesForces.products == null)
            {
                ForSalesForces.products = new ArrayList<>();
            }
            ForSalesForces.products.add(new ForSalesForces.Product(prodNameTextField.getText(),
                                                    descProdTextField.getText(),
                                                    typeComboBox.getSelectedItem().toString(),
                                                    familyComboBox.getSelectedItem().toString(),
                                                    imagePathTextField.getText(),
                                                    (int)priceSpinner.getValue()
                                        ));                                   
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }//GEN-LAST:event_createProdBtnActionPerformed

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
            java.util.logging.Logger.getLogger(createProdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createProdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createProdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createProdForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createProdForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton createProdBtn;
    private javax.swing.JTextArea descProdTextField;
    private javax.swing.JComboBox<String> familyComboBox;
    private javax.swing.JTextField imagePathTextField;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner priceSpinner;
    private javax.swing.JTextField prodNameTextField;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration//GEN-END:variables
}
