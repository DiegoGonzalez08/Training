package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import service.ConnectionUtil;
import entity.BookLoans;

public class BookLoansDAO extends BaseDAO<List<BookLoans>>{

	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookLoans loans) throws Exception {
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?, ?, ?, now(), date_add(now(), INTERVAL 7 DAY), null)",
				new Object[] { loans.getBook().getBookId(), loans.getBranch().getBranchId(), loans.getBorrower().getCardNo()});
	}

	public void update(BookLoans loans) throws Exception {
		save("update tbl_book_loans set bookId = ?, branchId = ?, cardNo = ?, dateOut = ?, dueDate = ?, dateIn = now() where bkLoanId = ?",
				new Object[] { loans.getBook().getBookId() , loans.getBranch().getBranchId(), loans.getBorrower().getCardNo(), loans.getDateOut(), loans.getDueDate(), loans.getBkLoanId()});
	}

	public void delete(BookLoans loans) throws Exception {
		save("delete from tbl_book_loans where bkLoanId = ?",
				new Object[] { loans.getBkLoanId()});
	}

	@SuppressWarnings("unchecked")
	public List<BookLoans> readAll() throws Exception {
		return (List<BookLoans>) read("select * from tbl_book_copies", null);
	}

	@SuppressWarnings("unchecked")
	public BookLoans readOne(int bkLoanId) throws Exception {
		List<BookLoans> list = (List<BookLoans>) read(
				"select * from tbl_book_loans where bkLoanId = ?",
				new Object[] {bkLoanId});

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	protected List<?> extractData(ResultSet rs) throws Exception {
		List<BookLoans> list = new ArrayList<BookLoans>();
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDao = new BookDAO(c);
		LibraryBranchDAO ldao = new LibraryBranchDAO(c);
		BorrowerDAO dao = new BorrowerDAO(c);
		while (rs.next()) {
			BookLoans b = new BookLoans();
			b.setBkLoanId(rs.getInt("bkLoanId"));
			b.setBook(bDao.readOne(rs.getInt("bookId")));
			b.setBranch(ldao.readOne(rs.getInt("branchId")));
			b.setBorrower(dao.readOne(rs.getInt("cardNo")));
			b.setDateOut(rs.getString("dateOut"));
			b.setDueDate(rs.getString("dueDate"));
			b.setDateIn(rs.getString("dateIn"));
			
			list.add(b);
		}
		return list;
	}
}