package pro.sky.telegrambot.standard;

public enum Commands {


    START("запуск бота", "/start"),
    HELP("описание команд бота", "/help"),
    CAT_SHELTER("приют для кошек", "/cat_shelter"),
    DOG_SHELTER("приют для собак", "/dog_shelter"),
    INFO_CAT_SHELTER("информация о приюте для кошек", "/info_cat_shelter"),
    INFO_DOG_SHELTER("информация о приюте для собак", "/info_dog_shelter"),
    INFO_TAKE_ANIMAL("информация как взять животное из приюта", "/info_take_animal"),
    REPORT_SEND_ANIMAL("прислать отчет о питомце", "/report_send_animal"),
    CALL_VOLUNTEER("позвать волонтера", "/call_volunteer"),
    SHELTER_CONTACT_INFO("расписание работы приюта для животных, адрес и схема проезда", "/shelter_contact_info"),
    CAT_SHELTER_CONTACT_INFO("расписание работы приюта для кошек, адрес и схема проезда", "/cat_shelter_contact_info"),
    DOG_SHELTER_CONTACT_INFO("расписание работы приюта для собак, адрес и схема проезда", "/dog_shelter_contact_info"),
    SHELTER_DATA_SECURITY_PASS("контакты для оформления пропуска на машину в приют", "/cat_shelter_data_security_pass"),
    CAT_SHELTER_DATA_SECURITY_PASS("контакты для оформления пропуска на машину в приют кошек", "/cat_shelter_data_security_pass"),
    DOG_SHELTER_DATA_SECURITY_PASS("контакты для оформления пропуска на машину в приют собак", "/dog_shelter_data_security_pass"),
    SHELTER_SAFETY_RECOMMENDATIONS("техника безопасности на территории приюта", "/shelter_safety_recommendations"),
    CAT_SHELTER_SAFETY_RECOMMENDATIONS("техника безопасности на территории приюта для кошек", "/cat_shelter_safety_recommendations"),
    DOG_SHELTER_SAFETY_RECOMMENDATIONS("техника безопасности на территории приюта для кошек", "/dog_shelter_safety_recommendations"),
    RECORD_DATA_FOR_COMMUNICATION("записать контактные данные для связи", "/record_data_for_communication"),
    RULES_GETTING_KNOW_ANIMALS("правила знакомства с животным", "/rules_getting_know_animals"),
    DOCUMENTS_TAKE_ANIMAL("документы чтобы взять животное", "documents_take_animal"),
    TRANSPORTATION_RECOMMENDATION("рекомендации по транспортировке животного", "/transportation_recommendations"),
    RECOMMENDATION_HOUSE_CUB("рекомендации по обустройству дома для щенка/котенка", "/recommendations_house_cub"),
    RECOMMENDATION_HOUSE_ADULT_ANIMAL("рекомендации по обустройству дома для взрослого животного", "/recommendations_house_adult_animal"),
    RECOMMENDATION_HOUSE_ANIMAL_WITH_DISABILITIES("рекомендации по обустройству дома для животного с ограниченными возможностями", "/recommendations_house_animal_with_disabilities"),
    TIPS_FROM_DOG_HANDLER("советы кинолога по первоначальному общению с собакой", "/tips_from_dog_handler"),
    LIST_REASONS_REFUSING_GIVE_DOG("причины отказа отдать животное", "/list_reasons_refusing_give_dog"),
    SEND_DAILY_REPORT_FORM("выслать форму ежедневного отчета", "/send_daily_report_form"),
    SEND_TEXT_DAILY_REPORT("выслать текст ежедневного отчета", "/send_text_daily_report"),
    SEND_PHOTO_DAILY_REPORT("выслать фотографию животного для ежедневного отчета", "/send_photo_daily_report"),
    WARNING_REPORT_BAD("предупреждение, что отчет плохой", "/warning_report_bad"),
    PASSED_PROBATION_PERIOD("прошел испытательный срок", "/passed_probation_period"),
    NOT_PASSED_PROBATION_PERIOD("не прошел испытательный срок", "/not_passed_probation_period"),
    ADDITIONAL_FOURTEEN_DAYS_ARE_SCHEDULED("назначено дополнительное время испытательного срока 14 дней",
            "/additional_fourteen_days_are_scheduled"),
    ADDITIONAL_THIRTY_DAYS_ARE_SCHEDULED("назначено дополнительное время испытательного срока 30 дней",
            "/additional_thirty_days_are_scheduled");

    private final String description;
    private final String command;

    public String getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    Commands(String description, String command) {
        this.description = description;
        this.command = command;
    }

    public static Commands fromCommand(String command) {
        for (Commands commands : Commands.values()) {
            if (commands.getCommand().equals(command)) {
                return commands;
            }
        }
        return null;
    }
}
