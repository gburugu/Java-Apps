package com.ejb;

import java.util.List;

import javax.ejb.Local;

import com.entity.Film;

@Local
public interface FilmEJBLocal {
	public Film getFilm(int id);	
	public List<Film> getAllFilms(int start, int count);
	public List<Film> getAllFilmsByTitle(String title, int start, int count);
}
