package net.worldticket.service;

import net.worldticket.data.CurrentBalance;
import net.worldticket.data.Sheep;
import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.data.repositories.SheepRepository;
import net.worldticket.dto.SheepDTO;
import net.worldticket.exception.AbsentMoneyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShepherdServiceImpl implements ShepherdService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private SheepRepository sheepRepository;
    private CurrentBalanceRepository currentBalanceRepository;

    private Integer priceOfSheep;

    @Autowired
    public ShepherdServiceImpl(
            SheepRepository sheepRepository,
            CurrentBalanceRepository currentBalanceRepository,
            @Value("${price_of_new_sheep}") Integer priceOfSheep) {
        this.sheepRepository = sheepRepository;
        this.currentBalanceRepository = currentBalanceRepository;
        this.priceOfSheep = priceOfSheep;
    }


    @Override
    public SheepDTO buyOneSheep() throws AbsentMoneyException {
        BigInteger currentBalance = currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();
        BigInteger pricePerOneSheep = BigInteger.valueOf(priceOfSheep);

        isAllowedToBuySheep(currentBalance, pricePerOneSheep);

        currentBalance = currentBalance.subtract(pricePerOneSheep);

        CurrentBalance updatedBalance = new CurrentBalance();
        updatedBalance.setBalance(currentBalance);
        currentBalanceRepository.save(updatedBalance);

        log.info("You have bought a new sheep. Your Current Balance " + currentBalance);

        Sheep createdSheep = sheepRepository.save(new Sheep());
        return mapSheepToSheepDTO(createdSheep);
    }

    @Override
    public List<SheepDTO> buySheep(int amount) throws AbsentMoneyException {
        BigInteger currentBalance = currentBalanceRepository.findFirstByOrderByTimestampDesc().getBalance();

        BigInteger sheepPrice = BigInteger.valueOf(priceOfSheep * amount);

        isAllowedToBuySheep(currentBalance, sheepPrice);

        currentBalance = currentBalance.subtract(sheepPrice);

        CurrentBalance updatedBalance = new CurrentBalance();
        updatedBalance.setBalance(currentBalance);
        currentBalanceRepository.save(updatedBalance);

        log.info("You have bought " + amount+ " of new sheep. Your Current Balance " + currentBalance);

        List<Sheep> sheepList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            sheepList.add(new Sheep());
        }

        return sheepRepository.save(sheepList)
                .stream()
                    .map(this::mapSheepToSheepDTO)
                    .collect(Collectors.toList());
    }

    private boolean isAllowedToBuySheep(BigInteger currentBalance, BigInteger sheepPrice) throws AbsentMoneyException {
        if (currentBalance.compareTo(sheepPrice) == -1){
            log.info("--------");
            log.info("You have`nt enough money to buy new sheep. Your Current Balance: " + currentBalance);
            log.info("--------");
            throw new AbsentMoneyException("You have`nt enough money to buy new sheep. Your Current Balance: " + currentBalance);
        }
        return true;
    }

    private SheepDTO mapSheepToSheepDTO(Sheep sheep){
        SheepDTO sheepDTO = new SheepDTO();
        sheepDTO.setState(sheep.getState().toString());
        sheepDTO.setDate(Date.from(sheep.getTimestamp()));
        return sheepDTO;
    }


}