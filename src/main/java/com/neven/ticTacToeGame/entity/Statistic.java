package com.neven.ticTacToeGame.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Statistic {

    public static final String STATISTIC_NAME = "statistic";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private final String name = STATISTIC_NAME;

    private long playerWin = 0;

    private long algorithmWin = 0;

    private long draw = 0;
}
