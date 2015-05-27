package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.AdministratorService;
import entity.Author;
import entity.Book;
import entity.Borrower;
import entity.Genre;
import entity.LibraryBranch;
import entity.Publisher;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet({ "/addAuthor", "/deleteAuthor", "/addPublisher", "/deletePublisher", 
	"/addBranch", "/deleteBranch", "/addBorrower", "/deleteBorrower", "/addGenre", 
	"/deleteGenre", "/addBook", "/deleteBook", "/editAuthor", "/editPublisher",
	"/editGenre", "/editBorrower", "/editBranch", "/editBook", "/getAllAuthors"})
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String function = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());

		//System.out.println(function);

		switch (function) {
		case "/addAuthor": {
			addAuthor(request, response);
			break;
		}
		case "/deleteAuthor": {
			deleteAuthor(request, response);
			break;
		}
		case "/addPublisher": {
			addPublisher(request, response);
			break;
		}
		case "/deletePublisher": {
			deletePublisher(request, response);
			break;
		}
		case "/addBranch" : {
			addBranch(request, response);
			break;
		}
		case "/deleteBranch" : {
			deleteBranch(request, response);
			break;
		}
		case "/addBorrower" : {
			addBorrower(request, response);
			break;
		}
		case "/deleteBorrower" : {
			deleteBorrower(request, response);
			break;
		}
		case "/addGenre" : {
			addGenre(request, response);
			break;
		}
		case "/deleteGenre" : {
			deleteGenre(request, response);
			break;
		}
		case "/addBook" : {
			addBook(request, response);
			break;
		}
		case "/deleteBook" : {
			deleteBook(request, response);
			break;
		}
		case "/editAuthor" : {
			editAuthor(request, response);
			break;
		}
		case "/editPublisher" : {
			editPublisher(request, response);
			break;
		}
		case "/editGenre" : {
			editGenre(request, response);
			break;
		}
		case "/editBorrower" : {
			editBorrower(request, response);
			break;
		}
		case "/editBranch" : {
			editBranch(request, response);
			break;
		}
		case "/editBook" : {
			editBook(request, response);
			break;
		}
		case "/getAllAuthors" : {
			getAllAuthors(request, response);
			break;
		}
		case "/getAllPublishers" : {
			getAllPublishers(request, response);
			break;
		}
		default:
			break;
		}

	}	
	
	private void getAllPublishers(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Publisher> publishers = new AdministratorService().getAllPublishers();
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getWriter(), publishers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void getAllAuthors(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Author> authors = new AdministratorService().getAllAuthors();
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getWriter(), authors);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bId");
		System.out.println(bookId);
		String[] authorIds = request.getParameterValues("authorId");
		String[] genreIds = request.getParameterValues("genreId");
		String publisherId = request.getParameter("publisherId");
		String title = request.getParameter("title");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBooks.jsp");
		
		try {
			Book bk = new AdministratorService().getBookById(Integer.parseInt(bookId));
			bk.setTitle(title);
			if(authorIds != null && authorIds.length > 0) {
				bk.setAuthors(new ArrayList<Author>());
				for(String s : authorIds) {
					Author author = new Author();
					author.setAuthorId(Integer.parseInt(s));
					bk.getAuthors().add(author);
				}
			}
			if (genreIds != null && genreIds.length > 0) {
				bk.setGenres(new ArrayList<Genre>());
				for (String s : genreIds) {
					Genre genre = new Genre();
					genre.setGenreId(Integer.parseInt(s));
					bk.getGenres().add(genre);
				}
			}
			Publisher publisher = new Publisher();
			publisher.setPublisherId(Integer.parseInt(publisherId));
			bk.setPublisher(publisher);

			new AdministratorService().updateBook(bk);
			

			request.setAttribute("result", "Book edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void editBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchId = request.getParameter("bId");
		String branchName = request.getParameter("bName");
		String branchAddress = request.getParameter("bAddress");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBranches.jsp");
		
		try {
			LibraryBranch branch = new AdministratorService().getLibraryBranchById(Integer.parseInt(branchId));
			branch.setBranchName(branchName);
			branch.setBranchAddress(branchAddress);
			new AdministratorService().updateLibraryBranch(branch);
			
			request.setAttribute("result", "Branch edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void editBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String borrowerId = request.getParameter("bId");
		String borrowerName = request.getParameter("bName");
		String borrowerAddress = request.getParameter("bAddress");
		String borrowerPhone = request.getParameter("bPhone");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBorrowers.jsp");
		try {
			Borrower borrower = new AdministratorService().getBorrowerByCardNo(Integer.parseInt(borrowerId));
			borrower.setName(borrowerName);
			borrower.setAddress(borrowerAddress);
			borrower.setPhone(borrowerPhone);
			new AdministratorService().updateBorrower(borrower);

			request.setAttribute("result", "Borrower edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void editGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genreId = request.getParameter("gId");
		String genreName = request.getParameter("genreName");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listGenres.jsp");
		
		try {
			Genre genre = new AdministratorService().getGenreById(Integer.parseInt(genreId));
			genre.setGenreName(genreName);
			new AdministratorService().updateGenre(genre);
			
			request.setAttribute("result", "Genre edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherId = request.getParameter("pubId");
		String publisherName = request.getParameter("pubName");
		String publisherAddress = request.getParameter("pubAddress");
		String publisherPhone = request.getParameter("pubPhone");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listPublishers.jsp");
		try {
			Publisher publisher = new AdministratorService().getPublisherById(Integer.parseInt(publisherId));
			publisher.setPublisherName(publisherName);
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);
			new AdministratorService().updatePublisher(publisher);
			
			request.setAttribute("result", "publisher edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void editAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("jackId");
		String authorName = request.getParameter("authorName");
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		try {
			Author author = new AdministratorService().getAuthorById(Integer.parseInt(authorId));
			author.setAuthorName(authorName);

			new AdministratorService().updateAuthor(author);

			request.setAttribute("result", "Author edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
	}

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));
		System.out.println(book.getBookId());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBooks.jsp");
		try {
			new AdministratorService().deleteBook(book);

			request.setAttribute("result", "Book Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void deleteGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genreId = request.getParameter("genreId");
		Genre genre = new Genre();
		genre.setGenreId(Integer.parseInt(genreId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listGenres.jsp");
		try {
			new AdministratorService().deleteGenre(genre);

			request.setAttribute("result", "Borrower Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void addGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genreName = request.getParameter("genreName");
		Genre genre = new Genre();
		genre.setGenreName(genreName);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/genre.jsp");
		try {
			new AdministratorService().addGenre(genre);

			request.setAttribute("result", "Genre Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardNo = request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardNo));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBorrowers.jsp");
		try {
			new AdministratorService().deleteBorrower(borrower);

			request.setAttribute("result", "Borrower Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);		
	}

	private void addBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower();
		borrower.setName(borrowerName);
		borrower.setAddress(borrowerAddress);
		borrower.setPhone(borrowerPhone);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/borrower.jsp");
		try {
			new AdministratorService().addBorrower(borrower);

			request.setAttribute("result", "Borrower Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void addPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/publishers.jsp");
		try {
			new AdministratorService().addPublisher(publisher);

			request.setAttribute("result", "Publisher Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	
	private void deletePublisher(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(publisherId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listPublishers.jsp");
		try {
			new AdministratorService().deletePublisher(publisher);

			request.setAttribute("result", "Publisher Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		try {
			new AdministratorService().deleteAuthor(author);

			request.setAttribute("result", "Author Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		try {
			new AdministratorService().addAuthor(author);

			request.setAttribute("result", "Author Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	
	private void addBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/branches.jsp");
		try {
			new AdministratorService().addLibraryBranch(branch);

			request.setAttribute("result", "Branch Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	
	private void deleteBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchId = request.getParameter("branchId");
		LibraryBranch branch = new LibraryBranch();
		branch.setBranchId(Integer.parseInt(branchId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBranches.jsp");
		try {
			new AdministratorService().deleteBranch(branch);;

			request.setAttribute("result", "Library Branch Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] authorIds = request.getParameterValues("authorId");
		String[] genreIds = request.getParameterValues("genreId");
		String publisherId = request.getParameter("publisherId");
		String title = request.getParameter("title");
		
		Book bk = new Book();
		bk.setTitle(title);
		if(authorIds != null && authorIds.length > 0) {
			bk.setAuthors(new ArrayList<Author>());
			for(String s : authorIds) {
				Author author = new Author();
				author.setAuthorId(Integer.parseInt(s));
				bk.getAuthors().add(author);
			}
		}
		if (genreIds != null && genreIds.length > 0) {
			bk.setGenres(new ArrayList<Genre>());
			for (String s : genreIds) {
				Genre genre = new Genre();
				genre.setGenreId(Integer.parseInt(s));
				bk.getGenres().add(genre);
			}
		}
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(publisherId));
		bk.setPublisher(publisher);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/books.jsp");
		try {
			new AdministratorService().addBook(bk);

			request.setAttribute("result", "Book Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book Add Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}

}