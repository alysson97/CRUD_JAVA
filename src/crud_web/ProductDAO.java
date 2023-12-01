package crud_web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        DBConnection dbConnection = new DBConnection();
        this.connection = dbConnection.getConnection();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM produtos";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduto(resultSet.getInt("idProduto"));
                product.setFabricante(resultSet.getString("fabricante"));
                product.setNome(resultSet.getString("nome"));
                product.setMarca(resultSet.getString("marca"));
                product.setModelo(resultSet.getString("modelo"));
                // Note: idCategoria is removed from the produtos table
                product.setDescricao(resultSet.getString("descricao"));
                product.setUnidadeMedida(resultSet.getString("unidadeMedida"));
                product.setLargura(resultSet.getDouble("largura"));
                product.setAltura(resultSet.getDouble("altura"));
                product.setProfundidade(resultSet.getDouble("profundidade"));
                product.setPeso(resultSet.getDouble("peso"));
                product.setCor(resultSet.getString("cor"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO produtos (fabricante, nome, marca, modelo, descricao, " +
                "unidadeMedida, largura, altura, profundidade, peso, cor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getFabricante());
            statement.setString(2, product.getNome());
            statement.setString(3, product.getMarca());
            statement.setString(4, product.getModelo());
            // Note: idCategoria is removed from the produtos table
            statement.setString(5, product.getDescricao());
            statement.setString(6, product.getUnidadeMedida());
            statement.setDouble(7, product.getLargura());
            statement.setDouble(8, product.getAltura());
            statement.setDouble(9, product.getProfundidade());
            statement.setDouble(10, product.getPeso());
            statement.setString(11, product.getCor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE produtos SET fabricante=?, nome=?, marca=?, modelo=?, descricao=?, " +
                "unidadeMedida=?, largura=?, altura=?, profundidade=?, peso=?, cor=? WHERE idProduto=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getFabricante());
            statement.setString(2, product.getNome());
            statement.setString(3, product.getMarca());
            statement.setString(4, product.getModelo());
            // Note: idCategoria is removed from the produtos table
            statement.setString(5, product.getDescricao());
            statement.setString(6, product.getUnidadeMedida());
            statement.setDouble(7, product.getLargura());
            statement.setDouble(8, product.getAltura());
            statement.setDouble(9, product.getProfundidade());
            statement.setDouble(10, product.getPeso());
            statement.setString(11, product.getCor());
            statement.setInt(12, product.getIdProduto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Product getProductById(int productId) {
        String query = "SELECT * FROM produtos WHERE idProduto = ?";
        Product product = new Product();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product.setIdProduto(resultSet.getInt("idProduto"));
                    product.setFabricante(resultSet.getString("fabricante"));
                    product.setNome(resultSet.getString("nome"));
                    product.setMarca(resultSet.getString("marca"));
                    product.setModelo(resultSet.getString("modelo"));
                    product.setIdCategoria(resultSet.getInt("idCategoria"));
                    product.setDescricao(resultSet.getString("descricao"));
                    product.setUnidadeMedida(resultSet.getString("unidadeMedida"));
                    product.setLargura(resultSet.getDouble("largura"));
                    product.setAltura(resultSet.getDouble("altura"));
                    product.setProfundidade(resultSet.getDouble("profundidade"));
                    product.setPeso(resultSet.getDouble("peso"));
                    product.setCor(resultSet.getString("cor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }


    public void deleteProduct(int productId) {
        String query = "DELETE FROM produtos WHERE idProduto=?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
