package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Borrower;

public class BorrowerDAO extends BaseDAO<List<Borrower>> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void create(Borrower borrower) throws Exception {
		save("insert into tbl_borrower (name, address, phone) values (?, ?, ?)",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone()});
	}

	public void update(Borrower borrower) throws Exception {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo()});
	}

	public void delete(Borrower borrower) throws Exception {
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardNo()});
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws Exception {
		return (List<Borrower>) read("select * from tbl_borrower", null);
	}

	@SuppressWarnings("unchecked")
	public Borrower readOne(int cardNo) throws Exception {
		List<Borrower> list = (List<Borrower>) read(
				"select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo});

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<?> extractData(ResultSet rs) throws SQLException {
		List<Borrower> list = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));
			
			list.add(b);
		}
		return list;
	}

}
