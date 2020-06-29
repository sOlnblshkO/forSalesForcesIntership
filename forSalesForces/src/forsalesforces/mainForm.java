package forsalesforces;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class mainForm extends javax.swing.JFrame {
    
    ArrayList<String> types;
    ArrayList<String> families;
    
    public startForm startF;
    
    public void setStartF(startForm newStartF)
    {
        startF = newStartF;
    }
    
    public mainForm() {
        initComponents();
        setResizable(false);
        
        types = new ArrayList<>();
        families = new ArrayList<>();
        
        if (!ForSalesForces.mainUser.isManager)
            createProdBtn.setVisible(false);
        else
            createProdBtn.setVisible(true);
        
        try
        {
            refreshProducts();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try {
            refreshFilterTypes();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        searchTextField.getDocument().addDocumentListener(new DocumentListener() 
        {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                System.out.println(searchTextField.getText());
                refreshProducts();
            }
        });
        
    }

    public void changeAccNameLabel(String newName)
    {
        accNameLabel.setText(newName);
    }
    public void changeAccIdLabel(int newId)
    {
        accNumbLabel.setText(String.valueOf(newId));
    }
    
    public void refreshProducts(){
        if (ForSalesForces.products != null)
        {
            String a = searchTextField.getText();
            productsPanel.removeAll();
            if (types.size() == 0 && a.equals("")){
                for (int i = 0; i < ForSalesForces.products.size(); i++){
                    productsPanel.add(createProdComp(i), BorderLayout.WEST);
                }
            } 
            else if (searchTextField.getText().equals("") && types.size() != 0)
            {
                for (int i = 0; i < ForSalesForces.products.size(); i++)
                {
                    for (int j = 0; j < types.size(); j++) 
                    {
                        if (ForSalesForces.products.get(i).type.equals(types.get(j))) 
                        {
                            if (families.size() == 0)
                            {
                                productsPanel.add(createProdComp(i), BorderLayout.WEST);
                            }
                            else 
                            {
                                for (int z = 0; z < families.size(); z++)
                                {
                                    if (ForSalesForces.products.get(i).fam.equals(families.get(z)))
                                    {
                                        productsPanel.add(createProdComp(i), BorderLayout.WEST);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else 
            {
                for (int i = 0; i < ForSalesForces.products.size(); i++)
                {
                    String b = ForSalesForces.products.get(i).name;
                    if (b.startsWith(a))
                    {
                        if (types.size() > 0)
                        {
                            for (int j = 0; j < types.size(); j++) 
                            {
                                if (ForSalesForces.products.get(i).type.equals(types.get(j))) 
                                {
                                    if (families.size() == 0)
                                    {
                                        productsPanel.add(createProdComp(i), BorderLayout.WEST);
                                    }
                                    else 
                                    {
                                        for (int z = 0; z < families.size(); z++)
                                        {
                                            if (ForSalesForces.products.get(i).fam.equals(families.get(z)))
                                            {
                                                productsPanel.add(createProdComp(i), BorderLayout.WEST);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            productsPanel.add(createProdComp(i), BorderLayout.WEST);
                        }
                    }
                }
            }
            productsPanel.revalidate();
            productsPanel.repaint();
        }
    }
    
    public Component createProdComp(int index)
    {
        singleProduct sp = new singleProduct();
        
        sp.setNameLabel(ForSalesForces.products.get(index).name);
        sp.setDescLabel(ForSalesForces.products.get(index).desc);
        sp.setNewIdLabel(index);
        sp.setImageLabel(ForSalesForces.products.get(index).imgPath);
        
        JPanel prodPan = sp.getProd();
       
        prodPan.setMaximumSize(new Dimension(productsPanel.getWidth(), 150));
        
        return prodPan;
    }
    
    public void refreshFamilyArray()
    {
        families.clear();
        Component[] components = filterFamilyPanel.getComponents();
        for (Component c : components)
        {
            if (c instanceof JCheckBox)
            {
                if (((JCheckBox) c).isSelected())
                {
                    families.add(((JCheckBox) c).getText());
                }
            }
        }
    }
    
    public void refreshFilterTypes() throws IOException
    {
        String path = "src\\forsalesforces\\types.txt";
        BufferedReader br = null;
        try 
        { 
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        }
        catch (Exception ex1)
        {
            path = "types.txt";
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        }
        try {
            String type;

            ItemListener forChk = new ItemListener(){
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    JCheckBox c = (JCheckBox)e.getItem();
                    if (e.getStateChange() == 1) 
                    {
                        types.add(c.getText());
                    }
                    else 
                    {
                        types.remove(c.getText());
                    }
                    refreshFilterFamily(types);
                    refreshFamilyArray();
                    refreshProducts();
                }
            };

            while (null != (type = br.readLine()))
            {
                JCheckBox chk = new JCheckBox(type);
                chk.addItemListener(forChk);
                filterTypePanel.add(chk);
                System.out.println(chk.getText());
            } 

            br.close();
        }
        catch (Exception ex1)
        {
            
        }
    }
 
    public void refreshFilterFamily(ArrayList<String> newTypes)
    {
        Component[] componList = filterFamilyPanel.getComponents();
        for (Component c: componList)
        {
            if (c instanceof JCheckBox)
                filterFamilyPanel.remove(c);
            if (c instanceof JLabel)
                filterFamilyPanel.remove(c);
            
            filterFamilyPanel.revalidate();
            filterFamilyPanel.repaint();
            refreshFamilyArray();
        }
        
        ItemListener forChk = new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == 1)
                    families.add(((JCheckBox) e.getItem()).getText());
                else families.remove(((JCheckBox) e.getItem()).getText());
                refreshFamilyArray();
                refreshProducts();
            }
        };
        
        for (String str : types)
        {
            String path = "src\\forsalesforces\\families.txt";
            BufferedReader br1 = null;
            try 
            {
                br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            }
            catch(Exception ex1) 
            {
                try 
                {
                    path = "families.txt";
                    br1 = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
                    String family;
                    boolean found = false;

                    JLabel famNameLab = new JLabel(str);
                    filterFamilyPanel.add(famNameLab);

                    while ((family = br1.readLine()) != null)
                    {
                        if (family.startsWith("<"))
                        {
                            if (str.equals(family.substring(1, family.length() - 1)))
                                found = true;
                            else
                                found = false;
                            continue;
                        }
                        if (found == true)
                        {
                            JCheckBox chk1 = new JCheckBox(String.valueOf(family));
                            chk1.addItemListener(forChk);
                            filterFamilyPanel.add(chk1);
                        }
                    }
                    filterFamilyPanel.revalidate();
                    filterFamilyPanel.repaint();
                    br1.close();
                }
                catch (FileNotFoundException ex) {
                    Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                } 
                catch (IOException ex) {
                    Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        accNameLabel = new javax.swing.JLabel();
        accNumbLabel = new javax.swing.JLabel();
        createProdBtn = new javax.swing.JButton();
        cartBtn = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        filterTypePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        filterFamilyPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        productsPanel = new javax.swing.JPanel();

        setTitle("main page");
        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setName("startPageFrame"); // NOI18N
        setSize(new java.awt.Dimension(500, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        accNameLabel.setText("Account Name");
        accNameLabel.setName("accNameLabel"); // NOI18N

        accNumbLabel.setText("Account number");

        createProdBtn.setText("Create product");
        createProdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProdBtnActionPerformed(evt);
            }
        });

        cartBtn.setText("Cart");
        cartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accNameLabel)
                .addGap(26, 26, 26)
                .addComponent(accNumbLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createProdBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartBtn)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accNameLabel)
                    .addComponent(accNumbLabel)
                    .addComponent(createProdBtn)
                    .addComponent(cartBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchTextField.setToolTipText("Search");

        jLabel2.setText("Type");

        jLabel1.setText("Filter");

        jLabel3.setText("Family");

        filterTypePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        filterTypePanel.setMaximumSize(new java.awt.Dimension(175, 173));
        filterTypePanel.setMinimumSize(new java.awt.Dimension(175, 173));
        filterTypePanel.setLayout(new javax.swing.BoxLayout(filterTypePanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(filterTypePanel);

        filterFamilyPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        filterFamilyPanel.setLayout(new javax.swing.BoxLayout(filterFamilyPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(filterFamilyPanel);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        productsPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        productsPanel.setLayout(new javax.swing.BoxLayout(productsPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(productsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 64, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(25, 25, 25))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartBtnActionPerformed
        cartForm cf = new cartForm();
        cf.setStartF(startF);
        cf.setMainF(this);
        cf.setVisible(true);
    }//GEN-LAST:event_cartBtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        startForm sForm = new startForm();
        sForm.setVisible(true);
        sForm.refreshMainTable();
    }//GEN-LAST:event_formWindowClosed

    private void createProdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProdBtnActionPerformed
        createProdForm cpfForm = new createProdForm();
        cpfForm.setVisible(true);
        cpfForm.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                refreshProducts();
            }
        });
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
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accNameLabel;
    private javax.swing.JLabel accNumbLabel;
    private javax.swing.JButton cartBtn;
    private javax.swing.JButton createProdBtn;
    private javax.swing.JPanel filterFamilyPanel;
    private javax.swing.JPanel filterTypePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel productsPanel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
