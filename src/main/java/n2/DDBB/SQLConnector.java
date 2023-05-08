package n2.DDBB;

import n2.tiquets.Tiquet;
import n2.tiquets.Tiquets;
import n2.enums.Material;
import n2.items.Arbre;
import n2.items.Decoracio;
import n2.items.Flor;
import n2.items.Item;

import java.sql.*;
import java.util.LinkedList;

public class SQLConnector {

    //region Attributes

    private static SQLConnector instance;
    Connection connection;
    private String url = "jdbc:mysql://localhost:3306/floristeria";
    private String user = "root";
    private String pwd = "password";

    //endregion

    public static SQLConnector Instance() {
        return instance;
    }

    public SQLConnector() {
        instance = this;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //region Floristeria + incomes

    public int getFloristeriaIncomes() {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM floristeria WHERE id = 0;");
            if (resultSet.next()) {
                return resultSet.getInt("guanys");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateFloristeriaIncomes(float guanys) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO floristeria (id, guanys) VALUES (?, ?) ON DUPLICATE KEY UPDATE guanys = ?");
            statement.setInt(1, 1);
            statement.setFloat(2, guanys);
            statement.setFloat(3, guanys);
            int rows = statement.executeUpdate();
            System.out.println("Incomes updated. " + rows + " row(s) inserted or updated.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //endregion

    //region Tickets

    public void addTicket(int tiquetId){
        String sql = "INSERT INTO tiquet (id, floristeria_id) VALUES (?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, tiquetId);
            statement.setInt(2, 1);
            int rows = statement.executeUpdate();
            System.out.println("Tiquet added. " + rows + " row(s) inserted.");
        } catch (SQLException e) {
        }
    }

    public Tiquets getTickets() {

        Tiquets tiquets = new Tiquets();

        try {
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tiquet;");
            while (resultSet.next()) {
                Tiquet t = new Tiquet();
                ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM flor WHERE tiquet_id =  " + resultSet.getInt("id"));
                while (resultSet2.next()) {
                    t.addProduct(new Flor(resultSet2.getString("nom"), resultSet2.getInt("preu"), resultSet2.getString("color")));
                }
                resultSet2 = statement2.executeQuery("SELECT * FROM arbre WHERE tiquet_id =  " + resultSet.getInt("id"));
                while (resultSet2.next()) {
                    t.addProduct(new Arbre(resultSet2.getString("nom"), resultSet2.getInt("preu"), resultSet2.getInt("alcada")));
                }
                resultSet2 = statement2.executeQuery("SELECT * FROM decoracio WHERE tiquet_id =  " + resultSet.getInt("id"));
                while (resultSet2.next()) {
                    t.addProduct(new Decoracio(resultSet2.getString("nom"), resultSet2.getInt("preu"), Material.valueOf(resultSet2.getString("material"))));
                }
                tiquets.addTiquet(t);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return tiquets;
    }

    //endregion

    public LinkedList<Item> getItems() {

        LinkedList<Item> items = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM flor WHERE floristeria_id = 1;");
            while (resultSet.next()) {
                items.add(new Flor(resultSet.getString("nom"), resultSet.getInt("preu"), resultSet.getString("color")));
            }

            resultSet = statement.executeQuery("SELECT * FROM arbre WHERE floristeria_id = 1;");
            while (resultSet.next()) {
                items.add(new Arbre(resultSet.getString("nom"), resultSet.getInt("preu"), resultSet.getInt("alcada")));
            }

            resultSet = statement.executeQuery("SELECT * FROM decoracio WHERE floristeria_id = 1;");
            while (resultSet.next()) {
                items.add(new Decoracio(resultSet.getString("nom"), resultSet.getInt("preu"), Material.valueOf(resultSet.getString("material"))));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    //region Add, remove and buy items

    public void addFlor(Flor f) {

        String sql = "INSERT INTO flor (nom, preu, color, floristeria_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, f.getNom());
            statement.setFloat(2, f.getPreu());
            statement.setString(3, f.getColor());
            statement.setInt(4, 1);
            int rows = statement.executeUpdate();
            System.out.println("Flor added. " + rows + " row(s) inserted.");
        } catch (SQLException e) {
        }
    }

    public void removeFlor(Flor f) {

        String sql = "DELETE FROM flor WHERE nom = ? AND preu = ? AND color = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, f.getNom());
            statement.setFloat(2, f.getPreu());
            statement.setString(3, f.getColor());
            int rows = statement.executeUpdate();
            System.out.println("Flor removed. " + rows + " row(s) removed.");
        } catch (SQLException e) {
        }
    }

    public void buyFlor(Flor f, int tiquetId) {

        String sql = "UPDATE flor SET floristeria_id = NULL, tiquet_id = ? WHERE nom = ? AND preu = ? AND color = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, tiquetId);
            statement.setString(2, f.getNom());
            statement.setFloat(3, f.getPreu());
            statement.setString(4, f.getColor());
            int rows = statement.executeUpdate();
            System.out.println("Flor bought. " + rows + " row(s) updated.");
        } catch (SQLException e) {
        }
    }

    public void addArbre(Arbre a) {

        String sql = "INSERT INTO arbre (nom, preu, alcada, floristeria_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, a.getNom());
            statement.setFloat(2, a.getPreu());
            statement.setFloat(3, a.getAlcada());
            statement.setInt(4, 1);
            int rows = statement.executeUpdate();
            System.out.println("Arbre added. " + rows + " row(s) inserted.");
        } catch (SQLException e) {
        }
    }

    public void removeArbre(Arbre a) {
        String sql = "DELETE FROM arbre WHERE nom = ? AND preu = ? AND alcada = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, a.getNom());
            statement.setFloat(2, a.getPreu());
            statement.setFloat(3, a.getAlcada());
            int rows = statement.executeUpdate();
            System.out.println("Arbre removed. " + rows + " row(s) removed.");
        } catch (SQLException e) {
        }
    }

    public void buyArbre(Arbre a, int tiquetId) {
        String sql = "UPDATE arbre SET floristeria_id = NULL, tiquet_id = ? WHERE nom = ? AND preu = ? AND alcada = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, tiquetId);
            statement.setString(2, a.getNom());
            statement.setFloat(3, a.getPreu());
            statement.setFloat(4, a.getAlcada());
            int rows = statement.executeUpdate();
            System.out.println("Arbre bought. " + rows + " row(s) updated.");
        } catch (SQLException e) {
        }
    }

    public void addDecoracio(Decoracio d) {

        String sql = "INSERT INTO decoracio (nom, preu, material, floristeria_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, d.getNom());
            statement.setFloat(2, d.getPreu());
            statement.setString(3, d.getMaterial());
            statement.setInt(4, 1);
            int rows = statement.executeUpdate();
            System.out.println("Decoracio added. " + rows + " row(s) inserted.");
        } catch (SQLException e) {
        }
    }

    public void removeDecoracio(Decoracio d) {
        String sql = "DELETE FROM decoracio WHERE nom = ? AND preu = ? AND material = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, d.getNom());
            statement.setFloat(2, d.getPreu());
            statement.setString(3, d.getMaterial());
            int rows = statement.executeUpdate();
            System.out.println("Decoracio removed. " + rows + " row(s) removed.");
        } catch (SQLException e) {
        }
    }

    public void buyDecoracio(Decoracio d, int tiquetId) {
        String sql = "UPDATE decoracio SET floristeria_id = NULL, tiquet_id = ? WHERE nom = ? AND preu = ? AND material = ? LIMIT 1";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, tiquetId);
            statement.setString(2, d.getNom());
            statement.setFloat(3, d.getPreu());
            statement.setString(4, d.getMaterial());
            int rows = statement.executeUpdate();
            System.out.println("Decoracio bought. " + rows + " row(s) updated.");
        } catch (SQLException e) {
        }
    }

    //endregion

    public void Close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

/*

GET:
statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("select * from floristeria");
int id = resultSet.getInt("id");

SET:
String sql = "INSERT INTO floristeria (name, price) VALUES (?, ?)";
    statement = connection.prepareStatement(sql);
    statement.setString(1, name);
    statement.setInt(2, price);
    int rows = statement.executeUpdate();
    System.out.println(rows + " row(s) inserted.");

*/
