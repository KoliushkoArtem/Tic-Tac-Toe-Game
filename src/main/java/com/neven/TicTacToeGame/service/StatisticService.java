package com.neven.TicTacToeGame.service;

import com.neven.TicTacToeGame.entity.Statistic;
import com.neven.TicTacToeGame.repository.StatisticRepository;
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
}
