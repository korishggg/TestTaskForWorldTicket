package net.worldticket.service;

import net.worldticket.dto.SheepDTO;
import net.worldticket.exception.AbsentMoneyException;

import java.util.List;

public interface ShepherdService {
    SheepDTO buyOneSheep() throws AbsentMoneyException;
    List<SheepDTO> buySheep(int amount) throws AbsentMoneyException;
}
