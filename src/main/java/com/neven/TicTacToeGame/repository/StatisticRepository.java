package com.neven.TicTacToeGame.repository;

import com.neven.TicTacToeGame.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, String> {

    Optional<Statistic> findByName(String statisticName);
}
