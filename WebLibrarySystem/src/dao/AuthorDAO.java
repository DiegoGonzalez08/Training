package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Author;

public class AuthorDAO extends BaseDAO<List<Author>> {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void create(Author author) throws Exception {
		save("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });
	}

	public void update(Author author) throws Exception {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void delete(Author author) throws Exception {
		save("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });
	}

	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws Exception {
		return (List<Author>) read("select * from tbl_author", null);
	}

	
	@SuppressWarnings("unchecked")
	public Author readOne(int authorId) throws Exception {
		List<Author> list = (List<Author>) read(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	@SuppressWarnings("unchecked")
	public Author readOne(int bookId) throws Exception {
		List<Author> list = (List<Author>) read("SELECT * FROM library.tbl_book_authors join tbl_book on tbl_book_authors.bookId = tbl_book.bookId" + 
							" join tbl_author on tbl_author.authorId=tbl_book_authors.authorId where tbl_book_authors.bookId = ?",
							new Object[]{bookId});
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	*/

	@SuppressWarnings("unchecked")
	public Author readOne(String authorName) throws Exception {
		List<Author> list = (List<Author>) read(
				"select * from tbl_author where authorName = ?",
				new Object[] { authorName });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

			list.add(a);
		}
		return list;
	}

}
