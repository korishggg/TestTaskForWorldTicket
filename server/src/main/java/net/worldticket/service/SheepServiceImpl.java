package net.worldticket.service;

import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import net.worldticket.data.repositories.SheepRepository;
import net.worldticket.dto.SheepDTO;
import net.worldticket.dto.SheepStatusesDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SheepServiceImpl implements SheepService {

    private SheepRepository sheepRepository;

    public SheepServiceImpl(SheepRepository sheepRepository) {
        this.sheepRepository = sheepRepository;
    }

    @Override
    public List<SheepDTO> getAllHealthySheep() {
        List<Sheep> healthySheep = this.sheepRepository.findAllByState(State.HEALTHY);
        return healthySheep.stream()
                .map(this::mapSheepToSheepDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SheepDTO> getAllDeadSheep() {
        List<Sheep> deadSheep = this.sheepRepository.findAllByState(State.DEAD);
        return deadSheep.stream()
                .map(this::mapSheepToSheepDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SheepStatusesDto getSheepStatusesDto() {
//        Did like that for reducing DB interactions
        List<SheepDTO> sheepDTOList = getAllSheep();
        long allSheep = sheepDTOList.size();

        int healthySheep = (int) sheepDTOList.stream()
                .filter(sheepDTO -> sheepDTO.getState().equals(State.HEALTHY.toString()))
                .count();

        int deadSheep = (int)(allSheep - healthySheep);

        return new SheepStatusesDto(healthySheep, deadSheep);
    }

    @Override
    public List<SheepDTO> getAllSheep() {
        List<Sheep> deadSheep = this.sheepRepository.findAll();
        return deadSheep.stream()
                .map(this::mapSheepToSheepDTO)
                .collect(Collectors.toList());
    }

    private SheepDTO mapSheepToSheepDTO(Sheep sheep){
        SheepDTO sheepDTO = new SheepDTO();
        sheepDTO.setState(sheep.getState().toString());
        sheepDTO.setDate(Date.from(sheep.getTimestamp()));
        return sheepDTO;
    }
}
