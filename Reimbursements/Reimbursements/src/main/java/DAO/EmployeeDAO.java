package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.reumbursements.model.Employee;

public class EmployeeDAO {
	Connection conn;

	public EmployeeDAO(Connection conn) {
		this.conn = conn;
	}

	public Set<Employee> getEmployee() throws SQLException {
		Set<Employee> eSet = new HashSet<Employee>();
		String ppst = "Select * from users";
		PreparedStatement statement = conn.prepareStatement(ppst);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			eSet.add(new Employee(rs.getString("username"),
					rs.getString("password")));
		}

		return eSet;

	}
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> eSet = new ArrayList<Employee>();
		String ppst = "Select * from users";
		PreparedStatement statement = conn.prepareStatement(ppst);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			eSet.add(new Employee(rs.getInt("userID"),rs.getString("userrole"),rs.getString("lastname"),rs.getString("firstname"),rs.getString("email"),rs.getString("username"),
					rs.getString("password"),rs.getInt("authorid")));
		}

		return eSet;

	}
}
