package com.example.thuc_hanh_md3.DAO;

import com.example.thuc_hanh_md3.DAO.connection.MyConnection;
import com.example.thuc_hanh_md3.model.Department;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private Connection connection = MyConnection.getConnection();
    private static DepartmentDAO departmentDAO;

    public static DepartmentDAO getInstance() {
        if (departmentDAO == null) {
            departmentDAO = new DepartmentDAO();
        }
        return departmentDAO;
    }

    public List<Department> findAll() {
        List<Department> departmentList = new ArrayList<>();
        String query = "select * from department;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                departmentList.add(new Department(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public Department findById(int id) {
        Department department = null;
        String query = "SELECT * FROM department WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                department = new Department(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return department;
    }
}
