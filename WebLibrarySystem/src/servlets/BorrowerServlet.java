package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Borrower;
import service.AdministratorService;

@WebServlet({ "/checkCard" })
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
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
		case ("/checkCard"): {
			checkCard(request, response);
			break;
		}
		default:
			break;
		}
	}

	private void checkCard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdministratorService admin = new AdministratorService();
		String cardNo = request.getParameter("cardNo");
		RequestDispatcher rd = null;
		try {
			Borrower borrower = admin.getBorrowerByCardNo(Integer.parseInt(cardNo));
			if (borrower == null) {
				rd = getServletContext().getRequestDispatcher(
						"/login.jsp");
				request.setAttribute("result", "card number not valid!");
			} else {
				rd = getServletContext().getRequestDispatcher(
						"/chooseBranch.jsp");
				request.setAttribute("cardNo", cardNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Card Number not valid: " + e.getMessage());
		}
		rd.forward(request, response);
	}

}
