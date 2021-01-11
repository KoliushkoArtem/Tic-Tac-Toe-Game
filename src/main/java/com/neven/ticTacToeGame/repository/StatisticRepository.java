package com.neven.ticTacToeGame.repository;

import com.neven.ticTacToeGame.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    Optional<Statistic> findByName(String statisticName);
}
