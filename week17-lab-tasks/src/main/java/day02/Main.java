package day02;

import day01.ActorsRepository;
import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/movies-actors?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("ciprush11");
        }
        catch (SQLException sqe) {
            throw new IllegalStateException("Can't reach database!", sqe);
        }

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        ActorsRepository actorsRepository = new ActorsRepository(dataSource);
        //actorsRepository.saveActor("Jack Doe");
        //System.out.println(actorsRepository.findActorsWithPrefix("Ja"));
        MoviesReposotory moviesReposotory = new MoviesReposotory(dataSource);
        //moviesReposotory.saveMovie("Star Wars", LocalDate.of(1988, 12, 11));
        System.out.println(moviesReposotory.findAllMovies());


    }
}