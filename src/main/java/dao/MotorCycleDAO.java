package dao;

import model.MotorCycle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorCycleDAO implements IMotorCycleDAO{
    private String jdbcURL="jdbc:mysql://localhost:3306/motorcycle";
    private String jdbcMotorName="root";
    private String jdbcMotorPassword="Thoitran2107";
    private static final String INSERT_MOTOR_SQL="insert into motors(id, name, price, manufacturer, image) values (?, ?, ?, ?, ?);";
    private static final String SELECT_MOTOR_BY_ID="select id, name, price, manufacturerm, image from motors where id = ?";
    private static final String SELECT_ALL_MOTOR="select * from motors";
    private static final String DELETE_MOTOR_SQL="delete from motors where id= ?";
    private static final String UPDATE_MOTOR_SQL="update motors set name = ?, price = ?, manufacturerm = ?, image = ? where id = ?";

    public MotorCycleDAO() {
    }
    protected Connection getConnection(){
        Connection connection =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcMotorName, jdbcMotorPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return connection;
    }

//    public static void main(String[] args) {
//        MotorCycleDAO motorCycleDAO = new MotorCycleDAO();
//        motorCycleDAO.getConnection();
//    }

    @Override
    public void insertMotor(MotorCycle motorCycle) throws SQLException {
        System.out.println(INSERT_MOTOR_SQL);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOTOR_SQL)){
            preparedStatement.setInt(1, motorCycle.getId());
            preparedStatement.setString(2, motorCycle.getName());
            preparedStatement.setDouble(3, motorCycle.getPrice());
            preparedStatement.setString(4, motorCycle.getManufacturer());
            preparedStatement.setString(5, motorCycle.getImage());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("loi roi");
        }
    }

    @Override
    public MotorCycle selectMotor(int id) {
        MotorCycle motorCycle = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOTOR_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String manufacturer = resultSet.getString("manufacturer");
                String image = resultSet.getString("image");
                motorCycle = new MotorCycle(id, name, price, manufacturer, image);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return motorCycle;
    }

    @Override
    public List<MotorCycle> selectAllMotors() {
        List<MotorCycle> motorCycles = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOTOR);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String manufacturer = resultSet.getString("manufacturer");
                String image = resultSet.getString("image");
                motorCycles.add(new MotorCycle(id, name, price, manufacturer, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return motorCycles;
    }

    @Override
    public boolean daleteMotor(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOTOR_SQL);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate()>0;
        return rowDeleted;
    }

    @Override
    public boolean updateMotor(MotorCycle motorCycle) throws SQLException {
        boolean rowUpdated;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOTOR_SQL);
        preparedStatement.setInt(1, motorCycle.getId());
        preparedStatement.setString(2, motorCycle.getName());
        preparedStatement.setDouble(3, motorCycle.getPrice());
        preparedStatement.setString(4, motorCycle.getManufacturer());
        preparedStatement.setString(5, motorCycle.getImage());
        rowUpdated = preparedStatement.executeUpdate()>0;
        return  rowUpdated;
    }
}
