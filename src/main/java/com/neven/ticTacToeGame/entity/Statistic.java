package com.neven.ticTacToeGame.entity;

import com.neven.ticTacToeGame.service.StatisticService;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class {@code Statistic} is an entity which contain an information about results of the games with an Algorithm MiniMax.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Entity
@Data
public class Statistic {

    /**
     * Constant wit a name of statistic. Used to set the {@link Statistic#name} and in {@link StatisticService#getStatistic()} method
     * to find statistic in database.
     */
    public static final String STATISTIC_NAME = "statistic";
    /**
     * Field with a name of statistic. Name can't be changed.
     */
    private final String name = STATISTIC_NAME;
    /**
     * Field with an id which statistic has in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Field with amount of player wining against an algorithm. Default value 0.
     */
    private long playerWin = 0;

    /**
     * Field with amount of algorithm wining against a player. Default value 0.
     */
    private long algorithmWin = 0;

    /**
     * Field with amount of draws in games player against an algorithm. Default value 0.
     */
    private long draw = 0;

    /**
     * Method takes {@link Statistic#playerWin}, {@link Statistic#algorithmWin}, {@link Statistic#draw} and return theirs sum.
     *
     * @return {@link Long} with a total amount of played games against an algorithm.
     */
    public Long getTotalGames() {
        return playerWin + algorithmWin + draw;
    }
}