package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Director;

public class DbDirector {

	public static Director getDirector(long directorId)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Director course = em.find(Director.class, directorId);
		return course;		
	}
	
	public static ArrayList<Movie> getAllMoviesByDirector(long directorId){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select m from Movie m where m.director.directorId=:directorId";
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try{
			TypedQuery<Movie> query = em.createQuery(qString,Movie.class)
					.setParameter("directorId", directorId);
			
			ArrayList<Movie> results = (ArrayList<Movie>) query.getResultList();
			for (Movie item : results) {
				movies.add(item);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
				em.close();
			}
		return movies;
	}
	
	
	public static ArrayList<Director> getAllDirectors (){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select d from Director d";
		
		ArrayList<Director> directors = new ArrayList<Director>();
		try{
			TypedQuery<Director> query = em.createQuery(qString,Director.class);
			
			for (Director director : query.getResultList()) {
				System.out.println(director.getName());
				directors.add(director);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
			em.close();
		}
		return directors;
	}

	public static void insert(Director director) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(director);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Director director) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(director);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

		
}