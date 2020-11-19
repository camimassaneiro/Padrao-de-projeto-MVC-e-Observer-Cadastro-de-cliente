package view;

import control.Controle;
import control.Observado;
import utils.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import observer.Observador;

public class SistemaClientes extends javax.swing.JFrame implements Observador {

    private Observado observado;

    @Override
    public void notificarAddCliente(int id, String nome, String sexo) {
        String cliente = nome + " " + sexo;
        if (indexOfCliente == -1) {
            tabelaClientes.add(cliente);
        } else {
            tabelaClientes.set(indexOfCliente, cliente);
        }
        model.update();
    }

    @Override
    public void notificarRemoverCliente(int id) {
        tabelaClientes.remove(indexOfCliente);
        indexOfCliente = -1;
        model.update();
    }

    @Override
    public void notificarEditarCliente(int id, String nome, String sexo) {

        this.tabelaClientes.add(id, jtfNome.getText() + " " + (jrbMasc.isSelected() ? "M" : "F"));
        this.tabelaClientes.remove(id);
    }

    private class ClienteListModel extends AbstractListModel<String> {

        public void update() {
            fireContentsChanged(this, 0, tabelaClientes.size());
        }

        public String getElementAt(int index) {
            return tabelaClientes.get(index);
        }

        public int getSize() {
            return tabelaClientes.size();
        }

    }

    private JList<String> jList1;
    private JRadioButton jrbMasc;
    private JRadioButton jrbFem;
    private JTextField jtfNome;

    private List<String> tabelaClientes = new ArrayList<>();

    private JButton jbConfirmar;
    private JButton jbCancelar;
    private JButton jbExcluir;
    private JButton jbNovo;
    protected int indexOfCliente = -1;
    private JButton jbAlterar;
    private ClienteListModel model;

    public SistemaClientes() {

        this.observado = new Controle();
        observado.addObservador(this);

        setTitle("Cadastro de Clientes");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

        setResizable(false);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

        ButtonGroup buttonGroup1 = new ButtonGroup();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        jtfNome = new JTextField();
        jrbMasc = new JRadioButton("Masc");
        jrbFem = new JRadioButton("Femin");
        jbConfirmar = new JButton("Confirmar");
        jbCancelar = new JButton("Cancelar");
        jbNovo = new JButton("Novo");
        jbAlterar = new JButton("Alterar");
        jbExcluir = new JButton("Excluir");
        jList1 = new JList<String>();

        jPanel1.add(new JLabel("Nome :"));
        jPanel1.add(jtfNome);
        jtfNome.setEnabled(false);

        jPanel1.add(new JLabel("Sexo :"));
        buttonGroup1.add(jrbMasc);
        jrbMasc.setSelected(true);
        jrbMasc.setEnabled(false);

        buttonGroup1.add(jrbFem);
        jPanel2.add(jrbMasc);
        jPanel2.add(jrbFem);
        jPanel1.add(jPanel2);
        jrbFem.setEnabled(false);

        jPanel1.setLayout(new SpringLayout());
        // apenas para ajeitar a posicao dos componentes na tela
        SpringUtilities.makeCompactGrid(jPanel1, 2, 2, 3, 3, 3, 3);

        jbConfirmar.setEnabled(false);
        jbConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                observado.adicionarCliente(jtfNome.getText(), (jrbMasc.isSelected() ? "M" : "F"));
                trocarEnabledsSaida();
            }
        });

        jbCancelar.setEnabled(false);
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trocarEnabledsSaida();
            }
        });

        jbNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                trocarEnabledsEntrada();

                jtfNome.setText("");
                jrbMasc.setSelected(true);

                indexOfCliente = -1;
            }
        });

        jbAlterar.setEnabled(false);
        jbAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                observado.editarCliente(indexOfCliente, jtfNome.getText(), (jrbMasc.isSelected() ? "M" : "F"));
                trocarEnabledsEntrada();
            }
        });

        jbExcluir.setEnabled(false);
        jbExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                observado.removerCliente(indexOfCliente);
                trocarEnabledsSaida();
            }
        });

        JPanel jPanel3 = new JPanel();
        jPanel3.add(jbNovo);
        jPanel3.add(jbAlterar);
        jPanel3.add(jbExcluir);
        jPanel3.add(jbConfirmar);
        jPanel3.add(jbCancelar);

        this.model = new ClienteListModel();
        jList1.setModel(this.model);
        jList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    indexOfCliente = jList1.getSelectedIndex();
                    if (indexOfCliente > -1) {
                        jbAlterar.setEnabled(true);
                        jbExcluir.setEnabled(true);
                    }
                }
            }
        });

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new BorderLayout());
        jPanel4.add(jPanel1, BorderLayout.CENTER);
        jPanel4.add(jPanel3, BorderLayout.SOUTH);

        add(jPanel4, BorderLayout.NORTH);
        add(new JScrollPane(jList1), BorderLayout.CENTER);

        pack();
    }

    private void trocarEnabledsEntrada() {
        jbNovo.setEnabled(false);
        jbAlterar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbConfirmar.setEnabled(true);
        jbCancelar.setEnabled(true);

        jrbMasc.setEnabled(true);
        jrbFem.setEnabled(true);
        jtfNome.setEnabled(true);
        jtfNome.requestFocus();

        jList1.setEnabled(false);

    }

    private void trocarEnabledsSaida() {
        jbNovo.setEnabled(true);
        jbAlterar.setEnabled(false);
        jbExcluir.setEnabled(false);
        jbConfirmar.setEnabled(false);
        jbCancelar.setEnabled(false);

        jrbMasc.setEnabled(false);
        jrbFem.setEnabled(false);
        jtfNome.setEnabled(false);

        jtfNome.setText("");
        jrbMasc.setSelected(true);

        jList1.setEnabled(true);
        jList1.requestFocus();
    }

    public static void main(String args[]) {
        new SistemaClientes().setVisible(true);
    }

}
