package com.neven.ticTacToeGame.service;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.repository.StatisticRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Class {@code StatisticService} uses {@link StatisticRepository} for providing CRUD operations on {@link Statistic}.
 *
 * @author Arterm Koliushko
 * @author https://www.linkedin.com/in/artem-koliushko/
 */
@Service
@Transactional
public class StatisticService {

    /**
     * Field with injected in constructor {@link StatisticRepository}.
     */
    private final StatisticRepository statisticRepository;

    /**
     * Constructor for class {@link StatisticService}.<br/>Initialize {@link StatisticService#statisticRepository} by
     * incoming object of {@link StatisticRepository}.
     *
     * @param statisticRepository injection of {@link StatisticRepository}.
     */
    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    /**
     * This method use {@link StatisticRepository} to find statistic with a name {@link Statistic#STATISTIC_NAME}.
     *
     * @return {@link Statistic} founded in database, if result of the seeking will be null, wil lne returned new  {@link Statistic}.
     */
    public Statistic getStatistic() {
        Optional<Statistic> result = statisticRepository.findByName(Statistic.STATISTIC_NAME);

        return result.orElseGet(() -> statisticRepository.save(new Statistic()));
    }

    /**
     * This method receive {@link Game} with game result and update the statistic with a name {@link Statistic#STATISTIC_NAME}
     * and save the result in database.
     *
     * @param game finished {@link Game} with game result.
     */
    public void updateStatistic(Game game) {
        Statistic statisticToUpdate = this.getStatistic();

        if (game.isPlayer1win()) {
            statisticToUpdate.setPlayerWin(statisticToUpdate.getPlayerWin() + 1);
        }

        if (game.isPlayer2win()) {
            statisticToUpdate.setAlgorithmWin(statisticToUpdate.getAlgorithmWin() + 1);
        }

        if (game.isDraw()) {
            statisticToUpdate.setDraw(statisticToUpdate.getDraw() + 1);
        }

        statisticRepository.save(statisticToUpdate);
    }
}