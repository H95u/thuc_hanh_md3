package com.example.thuc_hanh_md3.controller;

import com.example.thuc_hanh_md3.DAO.DepartmentDAO;
import com.example.thuc_hanh_md3.DAO.EmployeeDAO;
import com.example.thuc_hanh_md3.model.Department;
import com.example.thuc_hanh_md3.model.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/home")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                findAll(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            case "search":
                searchByName(request, response);
                break;
            default:

                break;
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = EmployeeDAO.getInstance().findAll();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = EmployeeDAO.getInstance().findById(id);
        List<Department> departmentList = DepartmentDAO.getInstance().findAll();
        request.setAttribute("employee", employee);
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        Department department = DepartmentDAO.getInstance().findById(departmentId);
        Employee employee = new Employee(id, name, email, address, phone, salary, department);
        EmployeeDAO.getInstance().updateEmployee(employee);
        response.sendRedirect("/home");
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = DepartmentDAO.getInstance().findAll();
        request.setAttribute("departmentList", departmentList);
        request.getRequestDispatcher("/create.jsp").forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        Department department = DepartmentDAO.getInstance().findById(departmentId);
        Employee employee = new Employee(name, email, address, phone, salary, department);
        EmployeeDAO.getInstance().createNewEmployee(employee);
        response.sendRedirect("/home");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDAO.getInstance().deleteById(id);
        response.sendRedirect("/home");
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("nameSearch");
        List<Employee> employeeList = EmployeeDAO.getInstance().searchByName(nameSearch);
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}