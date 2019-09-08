/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.proway.estacionamento.visao;

import br.com.proway.estacionamento.dao.ClienteDAO;
import br.com.proway.estacionamento.dao.VendaDAO;
import br.com.proway.estacionamento.modelo.Cliente;
import br.com.proway.estacionamento.modelo.Venda;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 65316
 */
public class CadastroVendaJFrame extends javax.swing.JFrame {

    private final VendaDAO dao;
    private final ClienteDAO clienteDAO ;
    public static final int STATUS_CADASTRO = -1;
    private int id = STATUS_CADASTRO;
    
    /**
     * Creates new form CadastroVendaJFrame
     */
    public CadastroVendaJFrame() {
        initComponents();
        
        dao = new VendaDAO();
        clienteDAO = new ClienteDAO();
        adicionarClientes();        
    }

    // overload = sobrecarga de metodo
    CadastroVendaJFrame(Venda venda) {
        this();
        id = venda.getId();
        jTextFieldValor.setText(String.valueOf(venda.getValor()));
        
        for (int i = 0; i < jComboBoxCliente.getItemCount(); i++) {
            Cliente cliente = (Cliente) jComboBoxCliente.getItemAt(i);
            if (venda.getIdCliente() == cliente.getId()) {
                jComboBoxCliente.setSelectedIndex(i);
                break;
            }
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

        jLabel1 = new javax.swing.JLabel();
        jComboBoxCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jButtonSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel1.setText("Cliente");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setText("Valor");

        jButtonSalvar.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/proway/estacionamento/dao/imagens/diskette (1).png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        // TODO add your handling code here:
        
        Venda venda = new Venda();
        
        if (jComboBoxCliente.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selcione um cliente");
            jComboBoxCliente.showPopup();;
            return;
        }
        
        try {
            venda.setValor(Double.parseDouble(jTextFieldValor.getText().replace(",", ".")));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor da venda deve conter números reais");
            jTextFieldValor.requestFocus();
            return;
        }
        
        Cliente cliente = (Cliente) jComboBoxCliente.getSelectedItem();
        venda.setIdCliente(cliente.getId());
        
        if (this.id == STATUS_CADASTRO) {
            int id = dao.inserir(venda);
            JOptionPane.showMessageDialog(this, "Venda realizada com sucesso!");
            
            String[] opcoes = new String[] {"Novo", "Continuar Editando", "Fechar"};
            int opcao = JOptionPane.showOptionDialog(this, "Escolha", "Sistema de vendas", 0, JOptionPane.WARNING_MESSAGE, null, opcoes, "Novo");
            if (opcao == 0) {
                jTextFieldValor.setText("");
                jComboBoxCliente.setSelectedIndex(-1);
                jComboBoxCliente.showPopup();
            } else if (opcao == 1) {
                this.id = id; 
            } else {
                dispose();
            }
            
        } else {
            venda.setId(this.id);
            boolean alterou = dao.alterar(venda);
            if (alterou) {
                JOptionPane.showMessageDialog(this, "Venda alterada com sucesso!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possivel elterar a venda!", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        }
 
    }//GEN-LAST:event_jButtonSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroVendaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroVendaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroVendaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroVendaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroVendaJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<br.com.proway.estacionamento.modelo.Cliente> jComboBoxCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables

    private void adicionarClientes() {
        ArrayList<Cliente> clientes = clienteDAO.obterTodos("");
        DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) jComboBoxCliente.getModel();
        for (Cliente cliente : clientes) {
            defaultComboBoxModel.addElement(cliente);
        }
        
        jComboBoxCliente.setSelectedIndex(-1);
    }
}
