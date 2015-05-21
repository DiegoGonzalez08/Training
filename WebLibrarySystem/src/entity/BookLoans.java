package entity;

public class BookLoans {
	
	private int bkLoanId;
	private Book book;
	private LibraryBranch branch;
	private Borrower borrower;
	private String dateOut;
	private String dueDate;
	private String dateIn;
	
	public String getDateOut() {
		return dateOut;
	}
	
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	
	public String getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getDateIn() {
		return dateIn;
	}
	
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	
	public int getBkLoanId() {
		return bkLoanId;
	}
	
	public void setBkLoanId(int bkLoanId) {
		this.bkLoanId = bkLoanId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
}
