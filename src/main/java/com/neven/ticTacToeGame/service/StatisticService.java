package com.neven.ticTacToeGame.service;

import com.neven.ticTacToeGame.entity.Statistic;
import com.neven.ticTacToeGame.model.Game;
import com.neven.ticTacToeGame.repository.StatisticRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }


    public Statistic getStatistic() {
        Optional<Statistic> result = statisticRepository.findByName(Statistic.STATISTIC_NAME);

        return result.orElseGet(() -> statisticRepository.save(new Statistic()));
    }

    public void updateStatistic(Game game) {
        Statistic statisticToUpdate = this.getStatistic();

        if (game.isPlayer1win()){
            statisticToUpdate.setPlayerWin(statisticToUpdate.getPlayerWin() + 1);
        }

        if (game.isPlayer2win()){
            statisticToUpdate.setAlgorithmWin(statisticToUpdate.getAlgorithmWin() + 1);
        }

        if (game.isDraw()){
            statisticToUpdate.setDraw(statisticToUpdate.getDraw() + 1);
        }

        statisticRepository.save(statisticToUpdate);
    }
}
