package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import service.ConnectionUtil;
import entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<List<BookCopies>> {

	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(BookCopies copies) throws Exception {
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)",
				new Object[] { copies.getBook().getBookId(), copies.getBranch().getBranchId(), copies.getNoOfCopies()});
	}

	public void update(BookCopies copies) throws Exception {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] { copies.getNoOfCopies(), copies.getBook().getBookId(), copies.getBranch().getBranchId()});
	}

	public void delete(BookCopies copies) throws Exception {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { copies.getBook().getBookId(), copies.getBranch().getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<BookCopies> readAll() throws Exception {
		return (List<BookCopies>) read("select * from tbl_book_copies", null);
	}

	@SuppressWarnings("unchecked")
	public BookCopies readOne(int bookId, int branchId) throws Exception {
		List<BookCopies> list = (List<BookCopies>) read(
				"select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookId, branchId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
	
	@Override
	protected List<?> extractData(ResultSet rs) throws Exception {
		List<BookCopies> list = new ArrayList<BookCopies>();
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDao = new BookDAO(c);
		LibraryBranchDAO lDao = new LibraryBranchDAO(c);
		while (rs.next()) {
			BookCopies copies = new BookCopies();
			copies.setBook(bDao.readOne(rs.getInt("bookId")));
			copies.setBranch(lDao.readOne(rs.getInt("branchId")));
			copies.setNoOfCopies(rs.getInt("noOfCopies"));
			
			list.add(copies);
		}
		return list;
	}
		
	
}
