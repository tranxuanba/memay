package controller;

import dao.MotorCycleDAO;
import model.MotorCycle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "MotorCycleServlet", value = "/motors")
public class MotorCycleServlet extends HttpServlet {
    private MotorCycleDAO motorCycleDAO;
    public void init(){
        motorCycleDAO = new MotorCycleDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action) {
            case "create":
                showNewMotorCycle(request, response);
                break;
            case "edit":
                showEditMotorCycle(request, response);
                break;
            case "delete":
                try {
                    deleteMotorCycle(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listMotorCycle(request,response);
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
                try {
                    insertMotorCycle(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    updateMotorCycle(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
    private void listMotorCycle(HttpServletRequest request, HttpServletResponse response){
        List<MotorCycle> list = motorCycleDAO.selectAllMotors();
        request.setAttribute("listMotorCycle", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showNewMotorCycle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }
    private void insertMotorCycle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String manufacturer = request.getParameter("manufacturer");
        String image = request.getParameter("image");
        MotorCycle motorCycle = new MotorCycle(id, name, price, manufacturer, image);
        motorCycleDAO.insertMotor(motorCycle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request,response);
    }
    private void showEditMotorCycle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MotorCycle motorCycle = motorCycleDAO.selectMotor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("listMotorCycle", motorCycle);
        dispatcher.forward(request, response);
    }
    private void updateMotorCycle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String manufacturer = request.getParameter("manufacturer");
        String image = request.getParameter("image");
        MotorCycle motorCycle = new MotorCycle(id, name, price, manufacturer, image);
        motorCycleDAO.updateMotor(motorCycle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void deleteMotorCycle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        motorCycleDAO.daleteMotor(id);
        List<MotorCycle> motorCycles = motorCycleDAO.selectAllMotors();
        request.setAttribute("listMotorCycle", motorCycles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/view.jsp");
        dispatcher.forward(request, response);
    }
}
