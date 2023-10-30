package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.PetReport;
import pro.sky.telegrambot.repository.R_PetReport;

import java.time.LocalDate;
import java.util.Collection;


@Service
public class PetReportService {
    private final R_PetReport petReportRepository;
    private final AddService addService;
//    private Logger logger;

    public PetReportService(R_PetReport petReportRepository, AddService addService) {
        this.petReportRepository = petReportRepository;
        this.addService = addService;
//        this.logger = logger;
    }

    public Collection<PetReport> readAllReport() {
//        logger.info("Invoked readAll petReport  method");
        return  petReportRepository.findAll();
    }

    public Collection<PetReport> readPetReportDate(LocalDate date) {
        return petReportRepository.findByDateBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    public Collection<PetReport> readById(long id) {
        return petReportRepository.findById(id);
    }

    public Collection<PetReport> readCheck(boolean check) {
        return petReportRepository.findByCheck(check);
    }
}
