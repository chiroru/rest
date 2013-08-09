package jp.co.ctc_g.sample.rest;

import java.util.List;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Stateless
@DataSourceDefinition(
          url="jdbc:oracle:thin:@127.0.0.1:1521:XE",
          name="java:app/jdbc/oracle_test",
          className="oracle.jdbc.driver.OracleDriver",
          user="TEST",
          password="TEST")
public class MovieBean {

    @PersistenceContext
    EntityManager em;

    public List<Movie> listMovies() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery listCriteria = builder.createQuery(Movie.class);
        Root<Movie> listRoot = listCriteria.from(Movie.class);
        listCriteria.select(listRoot);
        TypedQuery<Movie> query = em.createQuery(listCriteria);
        return query.getResultList();
    }

    public void updateMovie() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate updateCriteria = builder.createCriteriaUpdate(Movie.class);
        Root<Movie> updateRoot = updateCriteria.from(Movie.class);
        updateCriteria.where(builder.equal(updateRoot.get(Movie_.name), "Inception"));
        updateCriteria.set(updateRoot.get(Movie_.name), "INCEPTION");
        Query q = em.createQuery(updateCriteria);
        q.executeUpdate();
        em.flush();
    }
    
    public void deleteMovie() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete deleteCriteria = builder.createCriteriaDelete(Movie.class);
        Root<Movie> updateRoot = deleteCriteria.from(Movie.class);
        deleteCriteria.where(builder.equal(updateRoot.get(Movie_.name), "The Matrix"));
        Query q = em.createQuery(deleteCriteria);
        q.executeUpdate();
        em.flush();
    }
    
}
