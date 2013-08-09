package jp.co.ctc_g.sample.rest;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import static junit.framework.TestCase.assertNotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTest {

     private Context  ctx;
     private EJBContainer    ejbContainer;

    @Before
    public  void setUp() {
        ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Opening the container" );
        ctx = ejbContainer.getContext();
    }

    @After
    public  void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the container" );
    }

    @Test
    public void testApp() {
    
        
        try {
            MovieBean converter = (MovieBean) ctx.lookup("java:global/classes/MovieBean");
            assertNotNull(converter);
            List<Movie> result = converter.listMovies();
            System.out.println(result.size());
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    
}