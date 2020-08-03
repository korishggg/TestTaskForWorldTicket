package net.worldticket.service;

import net.worldticket.dto.SheepDTO;
import net.worldticket.dto.SheepStatusesDto;

import java.util.List;

public interface SheepService {
    List<SheepDTO> getAllHealthySheep();
    List<SheepDTO> getAllDeadSheep();
    SheepStatusesDto getSheepStatusesDto();
    List<SheepDTO> getAllSheep();
}
