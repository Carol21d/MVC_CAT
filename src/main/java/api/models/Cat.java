package api.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import api.payloads.CatPayload;
import api.repositories.mysql.MysqlConnexion;

public class Cat {

    private Long id;
    private String name;

    MysqlConnexion repository = new MysqlConnexion();;
    String table = "cats";

    public Cat() {
    }

    public Cat(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat [id=" + id + ", name=" + name + "]";
    }

    public List<Object> index() {

        try {
            Statement statement = repository.conn.createStatement();
            String sql = String.format("SELECT * FROM %s", table);
            ResultSet rs = statement.executeQuery(sql);

            List<Object> cats = new ArrayList<>();

            while (rs.next()) {
                CatPayload cat = new CatPayload();
                cat.setId(rs.getLong("id_cat"));
                cat.setName(rs.getString("name"));
                cats.add(cat);
            }

            return cats;

        } catch (Exception ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }

    }

    public CatPayload save(CatPayload cat) throws SQLException {

        String sql_insert = "INSERT INTO cats (name,owner,breed) VALUES (?,?,?)";
        PreparedStatement preparedStatement = repository.conn.prepareStatement(sql_insert);
        preparedStatement.setString(1, cat.getName());
        preparedStatement.setString(2, null);
        preparedStatement.setString(3, null);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        Statement statement = repository.conn.createStatement();
        String sql = String.format("SELECT * FROM %s ORDER BY id_cat DESC LIMIT 1", table);
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            cat.setId(rs.getLong("id_cat"));
            cat.setName(rs.getString("name"));
        }
        
        return cat;
    }
}
