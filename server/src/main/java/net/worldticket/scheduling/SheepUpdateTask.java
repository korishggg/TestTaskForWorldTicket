package net.worldticket.scheduling;

import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import net.worldticket.data.repositories.SheepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SheepUpdateTask {
    private SheepRepository sheepRepository;

    private int chanceSheepDies;

    @Autowired
    public SheepUpdateTask(
    		SheepRepository sheepRepository,
    		@Value("${chance.sheep.dies}") int chanceSheepDies) {
		this.sheepRepository = sheepRepository;
		this.chanceSheepDies = chanceSheepDies;
	}

	@Scheduled(cron="${scheduling.update_sheep_cron}")
    @Transactional
    public void updateSheepStates() {
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        for (Sheep sheep : aliveSheep) {
            int rolled = (int) (Math.random() * 100);
            if (rolled < chanceSheepDies) sheep.setState(State.DEAD);
            sheepRepository.save(sheep);
        }
    }
}