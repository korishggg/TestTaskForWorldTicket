package net.worldticket.scheduling;

import net.worldticket.data.CurrentBalance;
import net.worldticket.data.Sheep;
import net.worldticket.data.State;
import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.data.repositories.SheepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Component
public class IncomeTask {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private CurrentBalanceRepository currentBalanceRepository;
    private SheepRepository sheepRepository;

    private BigInteger amountPerSheep;

    @Autowired
    public IncomeTask(
    		CurrentBalanceRepository currentBalanceRepository,
    		SheepRepository sheepRepository,
    		@Value("${amount_per_sheep}") BigInteger amountPerSheep) {
		this.currentBalanceRepository = currentBalanceRepository;
		this.sheepRepository = sheepRepository;
		this.amountPerSheep = amountPerSheep;
	}

	@Scheduled(cron="${scheduling.current_balance_update_cron}")
    @Transactional
    public void updateCurrentBalance() {
        List<Sheep> aliveSheep = sheepRepository.findAllByState(State.HEALTHY);
        BigInteger currentBalance = currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();
        BigInteger income = BigInteger.valueOf(0L);
        for (Sheep sheep : aliveSheep) {
            income = income.add(amountPerSheep);
        }
        currentBalance = currentBalance.add(income);

        CurrentBalance updatedBalance = new CurrentBalance();
        updatedBalance.setBalance(currentBalance);

        log.info("You have cut " + aliveSheep.size() +" sheep and earn "+ income + ". Your Current Balance: " + currentBalance);

        currentBalanceRepository.save(updatedBalance);
    }
}
