package crud_web;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;
    private int selectedProductId;

    public Main() {
        productDAO = new ProductDAO();

        setTitle("Product CRUD");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Fabricante");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Categoria");
        tableModel.addColumn("Descrição");
        tableModel.addColumn("Unidade de Medida");
        tableModel.addColumn("Largura");
        tableModel.addColumn("Altura");
        tableModel.addColumn("Profundidade");
        tableModel.addColumn("Peso");
        tableModel.addColumn("Cor");

        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedProductId = (int) tableModel.getValueAt(selectedRow, 0);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        JButton refreshButton = new JButton("Atualizar Tabela");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTable();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
            Object[] row = {
                    product.getIdProduto(),
                    product.getFabricante(),
                    product.getNome(),
                    product.getMarca(),
                    product.getModelo(),
                    product.getIdCategoria(),
                    product.getDescricao(),
                    product.getUnidadeMedida(),
                    product.getLargura(),
                    product.getAltura(),
                    product.getProfundidade(),
                    product.getPeso(),
                    product.getCor()
            };
            tableModel.addRow(row);
        }
    }

    private void addProduct() {
        JTextField fabricanteField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField unidadeMedidaField = new JTextField();
        JTextField larguraField = new JTextField();
        JTextField alturaField = new JTextField();
        JTextField profundidadeField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField corField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Fabricante:"));
        panel.add(fabricanteField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        panel.add(new JLabel("modelo:"));
        panel.add(modeloField);
        panel.add(new JLabel("categoria:"));
        panel.add(categoriaField);
        panel.add(new JLabel("descricao:"));
        panel.add(descricaoField);
        panel.add(new JLabel("unidadeMedida:"));
        panel.add(unidadeMedidaField);
        panel.add(new JLabel("largura:"));
        panel.add(larguraField);
        panel.add(new JLabel("altura:"));
        panel.add(alturaField);
        panel.add(new JLabel("profundidade:"));
        panel.add(profundidadeField);
        panel.add(new JLabel("peso:"));
        panel.add(pesoField);
        panel.add(new JLabel("cor:"));
        panel.add(corField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Product newProduct = new Product();
            newProduct.setFabricante(fabricanteField.getText());
            newProduct.setNome(nomeField.getText());
            newProduct.setMarca(marcaField.getText());
            newProduct.setModelo(modeloField.getText());
            newProduct.setIdCategoria(Integer.parseInt(categoriaField.getText()));
            newProduct.setDescricao(descricaoField.getText());
            newProduct.setUnidadeMedida(unidadeMedidaField.getText());
            newProduct.setLargura(Double.parseDouble(larguraField.getText()));
            newProduct.setAltura(Double.parseDouble(alturaField.getText()));
            newProduct.setProfundidade(Double.parseDouble(profundidadeField.getText()));
            newProduct.setPeso(Double.parseDouble(pesoField.getText()));
            newProduct.setCor(corField.getText());

            productDAO.addProduct(newProduct);
            refreshTable();
        }
    }

    private void updateProduct() {
        JTextField fabricanteField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        JTextField descricaoField = new JTextField();
        JTextField unidadeMedidaField = new JTextField();
        JTextField larguraField = new JTextField();
        JTextField alturaField = new JTextField();
        JTextField profundidadeField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField corField = new JTextField();

        Product selectedProduct = productDAO.getProductById(selectedProductId);

        fabricanteField.setText(selectedProduct.getFabricante());
        nomeField.setText(selectedProduct.getNome());
        marcaField.setText(selectedProduct.getMarca());
        modeloField.setText(selectedProduct.getModelo());
        descricaoField.setText(selectedProduct.getDescricao());
        unidadeMedidaField.setText(selectedProduct.getUnidadeMedida());
        larguraField.setText(String.valueOf(selectedProduct.getLargura()));
        alturaField.setText(String.valueOf(selectedProduct.getAltura()));
        profundidadeField.setText(String.valueOf(selectedProduct.getProfundidade()));
        pesoField.setText(String.valueOf(selectedProduct.getPeso()));
        corField.setText(selectedProduct.getCor());

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Fabricante:"));
        panel.add(fabricanteField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        panel.add(new JLabel("modelo:"));
        panel.add(modeloField);
        panel.add(new JLabel("descricao:"));
        panel.add(descricaoField);
        panel.add(new JLabel("unidadeMedida:"));
        panel.add(unidadeMedidaField);
        panel.add(new JLabel("largura:"));
        panel.add(larguraField);
        panel.add(new JLabel("altura:"));
        panel.add(alturaField);
        panel.add(new JLabel("profundidade:"));
        panel.add(profundidadeField);
        panel.add(new JLabel("peso:"));
        panel.add(pesoField);
        panel.add(new JLabel("cor:"));
        panel.add(corField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Atualizar Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            selectedProduct.setFabricante(fabricanteField.getText());
            selectedProduct.setNome(nomeField.getText());
            selectedProduct.setMarca(marcaField.getText());
            selectedProduct.setModelo(modeloField.getText());
            selectedProduct.setDescricao(descricaoField.getText());
            selectedProduct.setUnidadeMedida(unidadeMedidaField.getText());
            selectedProduct.setLargura(Double.parseDouble(larguraField.getText()));
            selectedProduct.setAltura(Double.parseDouble(alturaField.getText()));
            selectedProduct.setProfundidade(Double.parseDouble(profundidadeField.getText()));
            selectedProduct.setPeso(Double.parseDouble(pesoField.getText()));
            selectedProduct.setCor(corField.getText());

            productDAO.updateProduct(selectedProduct);
            refreshTable();
        }
    }

    private void deleteProduct() {
        int confirmDialog = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto?", "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmDialog == JOptionPane.YES_OPTION) {
            productDAO.deleteProduct(selectedProductId);
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
