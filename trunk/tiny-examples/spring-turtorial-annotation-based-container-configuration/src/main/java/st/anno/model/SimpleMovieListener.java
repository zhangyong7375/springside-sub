/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package st.anno.model;

import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author jeff.huang
 */
public class SimpleMovieListener {
    private MovieFinder movieFinder;

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }

    @Required
    @Inject
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }


}
