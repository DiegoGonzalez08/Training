package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.AuthorDAO;
import dao.BookCopiesDAO;
import dao.BookDAO;
import dao.BookLoansDAO;
import dao.BorrowerDAO;
import dao.GenreDAO;
import dao.LibraryBranchDAO;
import dao.PublisherDAO;
import entity.Author;
import entity.Book;
import entity.BookCopies;
import entity.BookLoans;
import entity.Borrower;
import entity.Genre;
import entity.LibraryBranch;
import entity.Publisher;

public class AdministratorService {

	public void addAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author cannot be null and Name should be 1-45 characters");
			}
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.create(author);
			c.commit();
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void updateAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author cannot be null and Name should be 1-45 characters");
			}
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.update(author);
			c.commit();
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}
	
	public void deleteAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.delete(author);
			c.commit();
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
		
	}

	public Author getAuthorByName(String authorName) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(c);
			return aDAO.readOne(authorName);
		} finally {
			c.close();
		}
	}

	public Author getAuthorByBookId(int id) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO dao = new AuthorDAO(c);
			return dao.readOne(id);
		} finally {
			c.close();
		}
	}

	public List<Author> getAllAuthors() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(c);
			return aDAO.readAll();
		} finally {
			c.close();
		}
	}

	public void addBook(Book book) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookDAO bDAO = new BookDAO(c);
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book title cannot be null and title should be 1-45 characters");
			}
			bDAO.create(book);
			c.commit();
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public List<Book> getAllBooks() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookDAO dao = new BookDAO(c);
			return dao.readAll();
		} finally {
			c.close();
		}
	}

	public Book getBookById(int id) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookDAO dao = new BookDAO(c);
			return dao.readOne(id);
		} finally {
			c.close();
		}
	}

	public void addBookCopies(BookCopies copies) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookCopiesDAO cdao = new BookCopiesDAO(c);
			cdao.update(copies);
			;
			c.commit();
			System.out.println("Copies were added!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public List<BookCopies> getAllBookCopies() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookCopiesDAO dao = new BookCopiesDAO(c);
			return dao.readAll();
		} finally {
			c.close();
		}
	}

	public BookCopies getBookCopies(int bookId, int branchId) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookCopiesDAO dao = new BookCopiesDAO(c);
			if (dao.readOne(bookId, branchId) == null) {
				return null;
			} else {
				return dao.readOne(bookId, branchId);
			}
		} finally {
			c.close();
		}
	}
	
	public void updateBookCopies(BookCopies copies) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			BookCopiesDAO dao = new BookCopiesDAO(c);
			dao.update(copies);
			c.commit();
			System.out.println("Copies were successfully updated!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void addLoan(BookLoans loan) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookLoansDAO dao = new BookLoansDAO(c);
			BookCopiesDAO cdao = new BookCopiesDAO(c);
			dao.create(loan);
			c.commit();
			System.out.println("Loan Successful");
			BookCopies copies = cdao.readOne(loan.getBook().getBookId(), loan
					.getBranch().getBranchId());
			copies.setNoOfCopies(copies.getNoOfCopies() - 1);
			cdao.update(copies);
			c.commit();
			System.out.println("Copies were decreased at branch");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public BookLoans getBookLoan(int bkLoanId) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookLoansDAO dao = new BookLoansDAO(c);
			return dao.readOne(bkLoanId);
		} catch (Exception e) {

		}
		return null;
	}

	public void updateBookLoan(BookLoans loan) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookLoansDAO dao = new BookLoansDAO(c);
			dao.update(loan);
			c.commit();
			System.out.println("Update Successful");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void returnBook(int bkLoanId) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookLoansDAO dao = new BookLoansDAO(c);
			BookCopiesDAO cdao = new BookCopiesDAO(c);
			BookLoans loan = new BookLoans();
			loan = dao.readOne(bkLoanId);
			dao.update(loan);
			c.commit();
			BookCopies copies = cdao.readOne(loan.getBook().getBookId(), loan
					.getBranch().getBranchId());
			copies.setNoOfCopies(copies.getNoOfCopies() + 1);
			cdao.update(copies);
			c.commit();
			System.out.println("Copies was increased at branch");
		} finally {
			c.rollback();
			c.close();
		}
	}

	public void addBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BorrowerDAO dao = new BorrowerDAO(c);
			dao.create(borrower);
			c.commit();
			System.out.println("Added successfully!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void updateBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BorrowerDAO dao = new BorrowerDAO(c);
			dao.update(borrower);
			c.commit();
			System.out.println("Updated Successfully!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BorrowerDAO dao = new BorrowerDAO(c);
			dao.delete(borrower);
			c.commit();
			System.out.println("Delete was successful!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public List<Genre> getAllGenres() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		List<Genre> list = new ArrayList<Genre>();
		try {
			GenreDAO dao = new GenreDAO(c);
			list = dao.readAll();
			return list;
		} finally {
			c.close();
		}
	}

	public void addLibraryBranch(LibraryBranch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			LibraryBranchDAO dao = new LibraryBranchDAO(c);
			dao.create(branch);
			c.commit();
			System.out.println("Added Successfully!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void updateLibraryBranch(LibraryBranch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			LibraryBranchDAO dao = new LibraryBranchDAO(c);
			dao.update(branch);
			c.commit();
			System.out.println("Updated Successfully!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void deleteBranch(LibraryBranch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			LibraryBranchDAO dao = new LibraryBranchDAO(c);
			dao.delete(branch);
			c.commit();
			System.out.println("Deleted Successfully!");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public List<LibraryBranch> getLibraryBranches() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			LibraryBranchDAO dao = new LibraryBranchDAO(c);
			return dao.readAll();
		} finally {
			c.close();
		}
	}

	public LibraryBranch getLibraryBranchById(int branchId) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			LibraryBranchDAO dao = new LibraryBranchDAO(c);
			return dao.readOne(branchId);
		} finally {
			c.close();
		}
	}

	public void addPublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			PublisherDAO dao = new PublisherDAO(c);
			dao.create(publisher);
			c.commit();
			System.out.println("Added Successfully");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void updatePublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			PublisherDAO dao = new PublisherDAO(c);
			dao.update(publisher);
			c.commit();
			System.out.println("Updated Successfully");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}

	public void deletePublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			PublisherDAO dao = new PublisherDAO(c);
			dao.delete(publisher);
			c.commit();
			System.out.println("Deleted Successfully");
		} catch (Exception e) {
			c.rollback();
		} finally {
			c.close();
		}
	}
	
	public List<Publisher> getAllPublishers() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			PublisherDAO dao = new PublisherDAO(c);
			return dao.readAll();
		} finally {
			c.close();
		}
	}

	public Borrower getBorrowerByCardNo(int cardNo) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BorrowerDAO dao = new BorrowerDAO(c);
			return dao.readOne(cardNo);
		} finally {
			c.close();
		}
	}
	
	public List<Borrower> getAllBorrowers() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BorrowerDAO dao = new BorrowerDAO(c);
			return dao.readAll();
		} finally {
			c.close();
		}
	}

	public void addGenre(Genre genre) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			GenreDAO dao = new GenreDAO(c);
			dao.create(genre);
			c.commit();
			System.out.println("Added Successfully!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteGenre(Genre genre) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			GenreDAO dao = new GenreDAO(c);
			dao.delete(genre);
			c.commit();
			System.out.println("Deleted Successfully!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteBook(Book book) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			BookDAO dao = new BookDAO(c);
			dao.delete(book);
			c.commit();
			System.out.println("Deleted Successfully!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public Author getAuthorById(int authorId) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO dao = new AuthorDAO(c);
			return dao.readOne(authorId);
		} finally {
			c.close();
		}
	}

	public Publisher getPublisherById(int pubId) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			PublisherDAO dao = new PublisherDAO(c);
			return dao.readOne(pubId);
		} finally {
			c.close();
		}
	}

	public Genre getGenreById(int genreId) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			GenreDAO dao = new GenreDAO(c);
			return dao.readOne(genreId);
		} finally {
			c.close();
		}
	}

	public void updateGenre(Genre genre) throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			GenreDAO dao = new GenreDAO(c);
			dao.update(genre);
			c.commit();
			System.out.println("Successfully updated!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void updateBook(Book bk) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			BookDAO dao = new BookDAO(c);
			dao.update(bk);
			c.commit();
			System.out.println("Successfully updated!");
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
}