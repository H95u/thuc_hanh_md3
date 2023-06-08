package com.example.thuc_hanh_md3.DAO;

import com.example.thuc_hanh_md3.DAO.connection.MyConnection;
import com.example.thuc_hanh_md3.model.Department;
import com.example.thuc_hanh_md3.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connection = MyConnection.getConnection();
    private static EmployeeDAO employeeDAO;

    public static EmployeeDAO getInstance() {
        if (employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        return employeeDAO;
    }

    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        String query = "select * from employee;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int departmentId = resultSet.getInt("department_id");
                Department department = DepartmentDAO.getInstance().findById(departmentId);
                employeeList.add(new Employee(id, name, email, address, phone, salary, department));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee findById(int id) {
        Employee employee = null;
        String query = "select * from employee where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int departmentId = resultSet.getInt("department_id");
                Department department = DepartmentDAO.getInstance().findById(departmentId);
                employee = new Employee(id, name, email, address, phone, salary, department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void deleteById(int id) {
        String query = "delete from employee where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewEmployee(Employee employee) {
        String query = "insert into employee(name,email,address,phone,salary,department_id) values(?,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        String query = "update employee set name = ?,email = ?, address = ?," +
                " phone = ?,salary =?,department_id = ? where id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getDepartment().getId());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> searchByName(String nameSearch) {
        List<Employee> employeeList = new ArrayList<>();
        String query = "select * from employee where name like ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + nameSearch + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int departmentId = resultSet.getInt("department_id");
                Department department = DepartmentDAO.getInstance().findById(departmentId);
                employeeList.add(new Employee(id, name, email, address, phone, salary, department));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
