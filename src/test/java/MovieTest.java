import com.kaisheng.Util.SqlSessionFactoryUtil;
import com.kaisheng.entity.Movie;
import com.kaisheng.mapper.MovieMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MovieTest {
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void init(){
        sqlSession = SqlSessionFactoryUtil.getSqlSession(true);
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @Test
    public void findById(){
        Movie movie = movieMapper.findById(118);
        System.out.println(movie);
    }

    @Test
    public void findAll(){
        List<Movie> movieList = movieMapper.findAll();
        for(Movie movie : movieList){
            System.out.println(movie);
        }
    }

    @Test
    public void Insert(){
        Movie movie = new Movie();
        movie.setTitle("天使的翅膀2");
        movie.setRate(10);
        movie.setReleaseyear("1996");
        movie.setDirector("刘文龙");
        movieMapper.save(movie);
        int num = movie.getId();
        System.out.println("受影响的行数："+num);
    }

    @Test
    public void update(){
        Movie movie = movieMapper.update(780);
        movie.setRate(9);

    }


    @After
    public void destory(){
        sqlSession.close();
    }

}
