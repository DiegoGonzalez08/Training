package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Author;
import entity.Book;
import entity.Genre;

public class BookDAO extends BaseDAO<List<Book>> {

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void create(Book book) throws Exception {
		int bookId;

		if (book.getPublisher() != null) {
			bookId = saveWithId(
					"insert into tbl_book (title, pubId) values (?,?)",
					new Object[] { book.getTitle(),
							book.getPublisher().getPublisherId() });
		} else {
			bookId = saveWithId(
					"insert into tbl_book (title, pubId) values (?,?)",
					new Object[] { book.getTitle(), null });
		}
		for (Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for (Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genre_id) values (?,?)",
					new Object[] { bookId, g.getGenreId() });

		}
	}

	public void update(Book book) throws Exception {
		// delete(book);
		// create(book);
		int bookId;
		if (book.getPublisher() != null) {
			bookId = saveWithId(
					"update tbl_book set title = ?, pubId = ? where bookId = ?",
					new Object[] {book.getTitle(),
							book.getPublisher().getPublisherId(), book.getBookId()});
		} else {
			bookId = saveWithId(
					"insert into tbl_book (title, pubId) values (?,?)",
					new Object[] { book.getTitle(), null });
		}
		/**
		for (Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for (Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genre_id) values (?,?)",
					new Object[] { bookId, g.getGenreId() });

		}
		*/
	}

	public void delete(Book book) throws Exception {
		save("delete from tbl_book where bookId = ?",
				new Object[] { book.getBookId() });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws Exception {
		return (List<Book>) read("select * from tbl_book", null);
	}

	@SuppressWarnings("unchecked")
	public Book readOne(int bookId) throws Exception {
		List<Book> list = (List<Book>) read(
				"select * from tbl_book where bookId = ?",
				new Object[] { bookId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			// b.setPublisher(publisher);

			list.add(b);
		}
		return list;
	}
}
