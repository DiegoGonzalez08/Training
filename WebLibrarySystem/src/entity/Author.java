package entity;

import java.lang.reflect.Method;

public class Author {

	private int authorId;
	private String authorName;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Author a = new Author();
			a.setAuthorName("test");

			System.out.println(a.getAuthorName());

			String className = "com.gcit.training.lms.entity.Author";
			String attrib = "authorName";

			// Author a = new Author();
			Class c = Class.forName(className);
			Object obj = c.newInstance();

			//a.setAuthorName("test");
			Method setter = c.getMethod(
					"set" + attrib.substring(0, 1).toUpperCase()
							+ attrib.substring(1, attrib.length()),
					String.class);
			setter.invoke(obj, "test");

			//System.out.println(a.getAuthorName());
			Method getter = c.getMethod(
					"get" + attrib.substring(0, 1).toUpperCase()
							+ attrib.substring(1, attrib.length()),
					String.class);
			System.out.println(getter.invoke(obj));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
