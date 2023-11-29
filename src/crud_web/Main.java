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

        JButton refreshButton = new JButton("Atualizar Tabela");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        buttonPanel.add(addButton);
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
        // Janela de diálogo para coletar informações do produto
        JTextField fabricanteField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField marcaField = new JTextField();
        // ... (adicionar outros campos conforme necessário)

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Fabricante:"));
        panel.add(fabricanteField);
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Marca:"));
        panel.add(marcaField);
        // ... (adicionar outros campos conforme necessário)

        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Criar um novo objeto Product com os valores inseridos
            Product newProduct = new Product();
            newProduct.setFabricante(fabricanteField.getText());
            newProduct.setNome(nomeField.getText());
            newProduct.setMarca(marcaField.getText());
            // ... (definir outros atributos conforme necessário)

            // Adicionar o produto ao banco de dados
            productDAO.addProduct(newProduct);

            // Atualizar a tabela após adicionar o produto
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

