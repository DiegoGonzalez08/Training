package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Book;
import entity.BookCopies;
import entity.LibraryBranch;
import service.AdministratorService;

@WebServlet({ "/updateCopies", "/updateBranch" })
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
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
		
		switch (function) {
		case "/updateBranch" : {
			updateBranch(request, response);
			break;
		} case "/updateCopies" : {
			updateCopies(request, response);
			break;
		}
		
		}
	}

	private void updateCopies(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		AdministratorService admin = new AdministratorService();
		String branchId = request.getParameter("branchId");
		String bkId = request.getParameter("bId");
		String num = request.getParameter("noCopies");
		System.out.println("branchId: " + branchId);
		System.out.println("bookId: " + bkId);
		System.out.println("numCopies: " + num);
		
		request.setAttribute("branchId", branchId);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/updateCopies.jsp");
		
		try {
			BookCopies copies = admin.getBookCopies(Integer.parseInt(bkId), Integer.parseInt(branchId));
			if (copies == null) {
				copies = new BookCopies();
				Book book = admin.getBookById(Integer.parseInt(bkId));
				LibraryBranch branch = admin.getLibraryBranchById(Integer.parseInt(branchId));
				copies.setBook(book);
				copies.setBranch(branch);
				copies.setNoOfCopies(Integer.parseInt(num));
				admin.addBookCopies(copies);
			} else {
				copies.setNoOfCopies(Integer.parseInt(num));
				admin.updateBookCopies(copies);
			}
			
			request.setAttribute("result", "copies edited Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"copies edit Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
		
		
	}

	private void updateBranch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchId = request.getParameter("branchId");
		request.setAttribute("branchId", branchId);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/updateCopies.jsp");
		rd.forward(request, response);
	}

}
