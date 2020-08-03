package net.worldticket.service;

import net.worldticket.data.CurrentBalance;
import net.worldticket.data.repositories.CurrentBalanceRepository;
import net.worldticket.dto.CurrentBalanceDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CurrentBalanceServiceImpl implements CurrentBalanceService{

    private CurrentBalanceRepository currentBalanceRepository;

    public CurrentBalanceServiceImpl(CurrentBalanceRepository currentBalanceRepository) {
        this.currentBalanceRepository = currentBalanceRepository;
    }

    @Override
    public CurrentBalanceDto getCurrentBalance() {
        return mapCurrentBalanceToCurrentBalanceDto(currentBalanceRepository.findFirstByOrderByTimestampDesc());
    }

    private CurrentBalanceDto mapCurrentBalanceToCurrentBalanceDto(CurrentBalance currentBalance){
        CurrentBalanceDto currentBalanceDto = new CurrentBalanceDto();
        currentBalanceDto.setBalance(currentBalance.getBalance());
        currentBalanceDto.setDate(Date.from(currentBalance.getTimestamp()));
        return currentBalanceDto;
    }
}
