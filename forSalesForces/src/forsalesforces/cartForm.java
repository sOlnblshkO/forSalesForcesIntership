package forsalesforces;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class cartForm extends javax.swing.JFrame {

    public JFrame mainF;
    public startForm startF;
    
    public void setStartF(startForm newStartF)
    {
        startF = newStartF;
    }
    
    public void setMainF(JFrame newMainF)
    {
        mainF = newMainF;
    }
    
    public cartForm() {
        initComponents();
        setResizable(false);
        int idUser1 = -1;
        for (int i = 0; i < ForSalesForces.orders.size(); i++)
        {
            if (ForSalesForces.orders.get(i).accId == ForSalesForces.lastUsedOrder)
            {
                idUser1 = i;
                break;
            }
        }
        if (ForSalesForces.orders.get(idUser1).totalProductCount != 0)
        {
            
            emptyLabel.setVisible(false);
            for (int i = 0; i < ForSalesForces.orderItems.size(); i++)
            {
                if (ForSalesForces.orderItems.get(i).orderId == ForSalesForces.lastUsedOrder)
                {
                    int idProd = ForSalesForces.orderItems.get(i).productId;
                    cartProduct cp = new cartProduct();
                    cp.setNameLabel(ForSalesForces.products.get(idProd).name);
                    cp.setDescTextArea(ForSalesForces.products.get(idProd).desc);
                    cp.setCountLabel(ForSalesForces.orderItems.get(i).quantity);
                    cp.setPriceLabel(ForSalesForces.products.get(idProd).price);
                    cp.setIdLabel(idProd);
                    cp.setImageLabel(ForSalesForces.products.get(idProd).imgPath);
                    final int finalIdUser = idUser1;
                    cp.deleteBtn.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) { 
                            int reply = JOptionPane.showConfirmDialog(rootPane, "Are you sure? The item will be deleted from the cart.", null, JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION)
                            {
                                for (int i = 0; i < ForSalesForces.orderItems.size();i++)
                                {
                                    if (ForSalesForces.orderItems.get(i).orderId == ForSalesForces.lastUsedOrder && ForSalesForces.orderItems.get(i).productId == Integer.valueOf(idProd))
                                    {
                                        int a = ForSalesForces.orderItems.get(i).quantity;
                                        int b = ForSalesForces.orderItems.get(i).price;
                                        ForSalesForces.orders.get(finalIdUser).totalProductCount--;
                                        ForSalesForces.orders.get(finalIdUser).totalPrice -= a * b;
                                        countProdLabel.setText("Total count = " + ForSalesForces.orders.get(finalIdUser).totalProductCount);
                                        costProdLabel.setText("Total price = " + ForSalesForces.orders.get(finalIdUser).totalPrice);
                                        ForSalesForces.orderItems.remove(i);
                                        cp.mainPanel.setVisible(false);
                                        break;
                                    }
                                }
                            }
                        } 
                    });
                    cartPanel.add(cp.getCartProd());
                }
            }
        }
        countProdLabel.setText("Total count = " + ForSalesForces.orders.get(idUser1).totalProductCount);
        costProdLabel.setText("Total price = " + ForSalesForces.orders.get(idUser1).totalPrice);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        countProdLabel = new javax.swing.JLabel();
        costProdLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartPanel = new javax.swing.JPanel();
        emptyLabel = new javax.swing.JLabel();
        checkOutBtn = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(500, 600));
        setPreferredSize(new java.awt.Dimension(500, 600));

        countProdLabel.setText("count label");

        costProdLabel.setText("total cost label");

        cartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cartPanel.setLayout(new javax.swing.BoxLayout(cartPanel, javax.swing.BoxLayout.Y_AXIS));

        emptyLabel.setText("This cart is empty!");
        cartPanel.add(emptyLabel);

        jScrollPane1.setViewportView(cartPanel);

        checkOutBtn.setText("Check out!");
        checkOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(countProdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(costProdLabel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countProdLabel)
                    .addComponent(costProdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutBtnActionPerformed
        try{
            int j = 0;
            while (j < ForSalesForces.orderItems.size())
            {
                if (ForSalesForces.orderItems.get(j).orderId == ForSalesForces.lastUsedOrder)
                {
                    ForSalesForces.orderItems.remove(j);
                }
                else
                    j++;
            }
            for (int i = 0; i < ForSalesForces.orders.size(); i++)
            {
                if (ForSalesForces.orders.get(i).accId == ForSalesForces.lastUsedOrder)
                {
                    ForSalesForces.orders.remove(i);
                    break;
                }
            }
            this.dispose();
            startF.refreshMainTable();
            startF.setVisible(true);
            mainF.dispose();
        }
        catch(Exception ex)
        {
        }
    }//GEN-LAST:event_checkOutBtnActionPerformed

    
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
            java.util.logging.Logger.getLogger(cartForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cartForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cartForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cartForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cartForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartPanel;
    private javax.swing.JButton checkOutBtn;
    private javax.swing.JLabel costProdLabel;
    private javax.swing.JLabel countProdLabel;
    private javax.swing.JLabel emptyLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
