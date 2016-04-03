package com.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejb.FilmEJB;
import com.entity.Film;

@Path("/filmservice")
public class FilmService {	

	@EJB
	FilmEJB fe;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus(){
		return Response.ok("{\"status\":\"Service is running...\"}").build();		
	}
		
	//multiple film with title like
	@GET
	@Path("film")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Film> getFilmByTitle(@QueryParam("title") String title,
									 @QueryParam("start") int start,
									 @QueryParam("count") int count){
		List<Film> films;
		if(title != null){
			films = fe.getAllFilmsByTitle(title, start, count);
		}
		else{
			films = fe.getAllFilms(start, count);
		}
		return films;
	}
	
	//single film
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("film/{filmId}")
	public Response getFilm(@PathParam("filmId") int filmId){
		//em = Resource.getEntityManager();
		//Film f = em.find(Film.class, filmId);
		Film f = fe.getFilm(filmId);
		return Response.ok(f).build();
	}

}
