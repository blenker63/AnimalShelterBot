package pro.sky.telegrambot.service;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.repository.R_PetReport;

import java.sql.Date;
import java.time.LocalDate;
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

//    public Collection<PetReport> readPetReportDateBetween(LocalDateTime begin, LocalDateTime end) {
    public Collection<PetReport> readPetReportDate(LocalDate date) {
//        return petReportRepository.findByDateBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
//        return petReportRepository.findByDate(Date.valueOf(date));
        return petReportRepository.findAllByDate(Date.valueOf(date));
    }

    public Collection<PetReport> readById(long id) {
        return petReportRepository.findById(id);
    }

    public Collection<PetReport> readCheck(boolean check) {
        return petReportRepository.findByCheck(check);
    }
}
