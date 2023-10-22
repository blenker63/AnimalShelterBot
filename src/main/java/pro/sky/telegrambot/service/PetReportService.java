package pro.sky.telegrambot.service;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.repository.R_PetReport;

import java.util.Collection;


@Service
public class PetReportService {
    private final R_PetReport petReportRepository;
    private Logger logger;

    public PetReportService(R_PetReport petReportRepository) {
        this.petReportRepository = petReportRepository;
    }

    public Collection<PetReport> readAllReport() {
//        logger.info("Invoked readAll petReport  method");
        return  petReportRepository.findAll();
    }
}
