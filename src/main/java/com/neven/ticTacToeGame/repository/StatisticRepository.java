package com.neven.ticTacToeGame.repository;

import com.neven.ticTacToeGame.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is a repository for {@link Statistic} class.
 * <br/> This interface gives an opportunity to make different operations with {@link Statistic} class in
 * database(CRUD and a lot of more).
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html">JPA Repositories</a>
 */
@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    /**
     * This method allow to find statistic in database by statistic name.
     *
     * @param statisticName {@code String} value for the name of statistic.
     * @return {@link Optional} with the result of the statistic seeking in the database by statistic name.
     */
    Optional<Statistic> findByName(String statisticName);
}