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
import com.reumbursements.model.Reimbursements;

public class ReimbursementsDAO {
Connection conn;
public ReimbursementsDAO() {
}
public ReimbursementsDAO(Connection conn) {
	this.conn = conn;
}

public Set<Reimbursements> getReimbursements() throws SQLException {
	Set<Reimbursements> rSet = new HashSet<Reimbursements>();
	String ppst = "Select * from reimbursements";
	PreparedStatement statement = conn.prepareStatement(ppst);
	ResultSet rs = statement.executeQuery();
	while (rs.next()) {
		rSet.add(new Reimbursements(rs.getInt("reimbursementId"),rs.getString("status"),rs.getDouble("amount"),rs.getString("description"),rs.getString("type"),rs.getInt("resolvetime"),rs.getInt("submittime"),rs.getInt("authorid"),rs.getInt("resolverid")));
	}

	return rSet;

}

}

