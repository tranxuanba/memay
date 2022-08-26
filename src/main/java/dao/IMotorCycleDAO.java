package dao;

import model.MotorCycle;

import java.sql.SQLException;
import java.util.List;

public interface IMotorCycleDAO {
    void insertMotor(MotorCycle motorCycle) throws SQLException;
    MotorCycle selectMotor(int id);
    List<MotorCycle> selectAllMotors();
    boolean daleteMotor(int id) throws SQLException;
    boolean updateMotor(MotorCycle motorCycle) throws SQLException;
}
