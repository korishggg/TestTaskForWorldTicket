package net.worldticket.model;

import java.util.List;

public class SheepList {
    private List<SheepDTO> sheepDTOS;

    public SheepList() {
    }

    public SheepList(List<SheepDTO> sheepDTOS) {
        this.sheepDTOS = sheepDTOS;
    }

    public List<SheepDTO> getSheepDTOS() {
        return sheepDTOS;
    }

    public void setSheepDTOS(List<SheepDTO> sheepDTOS) {
        this.sheepDTOS = sheepDTOS;
    }
}
