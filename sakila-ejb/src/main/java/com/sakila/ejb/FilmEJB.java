package com.sakila.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sakila.model.Film;

@Stateless
@Local(FilmEJBLocal.class)
@LocalBean
public class FilmEJB implements FilmEJBLocal {

	@PersistenceContext(name="filmPU")
	EntityManager em;
	
	public Film getFilm(int id) {
		Film f = em.find(Film.class, id);
		return f;
	}

	public List<Film> getAllFilms(int start, int count) {
		Query query = em.createNamedQuery("Film.findAll", Film.class);		
		query.setFirstResult(start);
		query.setMaxResults(count != 0 ? count : 10); //default 10 rows
		List<Film> films = query.getResultList();
		return films;
	}
	
	public List<Film> getAllFilmsByTitle(String title, int start, int count){
		Query query = em.createNamedQuery("Film.findByTitle", Film.class);
		query.setParameter("title", "%" + title + "%");
		query.setFirstResult(start);
		query.setMaxResults(count != 0 ? count : 10); //default 10 rows
		List<Film> films = query.getResultList();
		return films;
	}

}
