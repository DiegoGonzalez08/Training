package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Genre;

public class GenreDAO extends BaseDAO<List<Genre>> {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Genre genre) throws Exception {
		save("insert into tbl_genre (genre_name) values (?)",
				new Object[] { genre.getGenreName() });
	}

	public void update(Genre genre) throws Exception {
		save("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}
	
	public void delete(Genre genre) throws Exception {
		save("delete from tbl_genre where genre_id = ?",
				new Object[] { genre.getGenreId() });
	}
	
	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws Exception {
		return (List<Genre>) read("select * from tbl_genre", null);
	}
	
	@SuppressWarnings("unchecked")
	public Genre readOne(int genreId) throws Exception {
		List<Genre> list = (List<Genre>) read(
				"select * from tbl_genre where genre_id = ?",
				new Object[] { genreId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	protected List<?> extractData(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<Genre>();
		while (rs.next()) {
			Genre g = new Genre();
			g.setGenreId(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			
			list.add(g);
		}
		return list;
	}

}
