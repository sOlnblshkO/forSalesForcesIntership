/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forsalesforces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author koyash
 */
public class startForm extends javax.swing.JFrame {

    
    DefaultTableModel mainTableMdl;
    
    public startForm() {
        initComponents();
        setResizable(false);
        if (ForSalesForces.mainUser == null)
        {
            ForSalesForces.mainUser = new ForSalesForces.User();
        }
        mainTableMdl = (DefaultTableModel) mainTable.getModel();
        mainTable.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            if (me.getClickCount() == 2) {     // to detect doble click events
               JTable target = (JTable)me.getSource();
               int row = target.getSelectedRow(); // select a row
               openMainTable(row);
            }
         }
      });
    }
    
    public void openMainTable(int selectedRow)
    {
        mainForm mForm = new mainForm();
        mForm.changeAccNameLabel((String) mainTable.getValueAt(selectedRow, 0));
        mForm.changeAccIdLabel(ForSalesForces.orders.get(selectedRow).accId);
        
        ForSalesForces.lastUsedOrder = ForSalesForces.orders.get(selectedRow).accId;
        
        setVisible(false);
        mForm.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                refreshMainTable();
            }
        });
        mForm.setStartF(this); 
        mForm.setVisible(true);
        
    }
    
    public void refreshMainTable()
    {
        mainTableMdl.setRowCount(0);
        for (int i = 0; i < ForSalesForces.orders.size(); i++)
        {
            mainTableMdl.addRow(new Object[]{
                ForSalesForces.orders.get(i).newName,                
                ForSalesForces.orders.get(i).totalProductCount, 
                ForSalesForces.orders.get(i).totalPrice
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        nameTextField = new javax.swing.JTextField();
        addOrderBtn = new javax.swing.JButton();
        changeStatusBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Start Page");
        setPreferredSize(new java.awt.Dimension(500, 500));
        setSize(new java.awt.Dimension(500, 500));

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Shop item count", "Total price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(mainTable);
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            mainTable.getColumnModel().getColumn(1).setResizable(false);
        }

        nameTextField.setToolTipText("Name of order");

        addOrderBtn.setText("Add order");
        addOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderBtnActionPerformed(evt);
            }
        });

        changeStatusBtn.setText("Change status");
        changeStatusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStatusBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nameTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addOrderBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(changeStatusBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addOrderBtn))
                .addGap(20, 20, 20)
                .addComponent(changeStatusBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderBtnActionPerformed
        if (!"".equals(nameTextField.getText()))
        {
            if (ForSalesForces.orders == null)
            {
                ForSalesForces.orders = new ArrayList<>();
            }
            ForSalesForces.orders.add(new ForSalesForces.Order(nameTextField.getText(), ForSalesForces.orders.size()));
            refreshMainTable();
            nameTextField.setText("");
        }
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Write name!");
        }
    }//GEN-LAST:event_addOrderBtnActionPerformed

    private void changeStatusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStatusBtnActionPerformed
        if (ForSalesForces.mainUser.isManager)
            JOptionPane.showMessageDialog(rootPane, "Now you can NOT add products");
        else JOptionPane.showMessageDialog(rootPane, "Now you can add products");
        ForSalesForces.mainUser.changeStat();
    }//GEN-LAST:event_changeStatusBtnActionPerformed

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
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(startForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new startForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOrderBtn;
    private javax.swing.JButton changeStatusBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTable;
    private javax.swing.JTextField nameTextField;
    // End of variables declaration//GEN-END:variables
}
