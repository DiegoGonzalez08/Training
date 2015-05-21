package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<List<LibraryBranch>> {

	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(LibraryBranch branch) throws Exception {
		save("insert into tbl_library_branch (branchName, branchAddress) values (?, ?)",
				new Object[] { branch.getBranchName(), branch.getBranchAddress() });
	}

	public void update(LibraryBranch branch) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId() });
	}

	public void delete(LibraryBranch branch) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<LibraryBranch> readAll() throws Exception {
		return (List<LibraryBranch>) read("select * from tbl_library_branch", null);
	}

	@SuppressWarnings("unchecked")
	public LibraryBranch readOne(int branchId) throws Exception {
		List<LibraryBranch> list = (List<LibraryBranch>) read(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	protected List<?> extractData(ResultSet rs) throws SQLException {
		List<LibraryBranch> list = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			LibraryBranch b = new LibraryBranch();
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			
			list.add(b);
		}
		return list;
	}
	
	
}
