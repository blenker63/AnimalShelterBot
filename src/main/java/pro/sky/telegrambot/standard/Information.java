package pro.sky.telegrambot.standard;

public enum Information {
    INFO_ABOUT_SHELTER("""
            Занимаемся отловом, лечением,
            стерилизацией и содержанием
            бездомных животных
            """,
            "/info_about_shelter"),
    CAT_SHELTER_ADDRESS("Наш адрес: 150148, Казахстан, г. Астана, ул. Свободы 89",
            "/cat_shelter_address"),
    DOG_SHELTER_ADDRESS("Наш адрес: 150148, Казахстан, г. Астана, ул. Свободы 91",
            "/dog_shelter_address"),

    CAT_SHELTER_WORK_SCHEDULE("Режим работы: 8-20",
            "/cat_shelter_work_schedule"),
    DOG_SHELTER_WORK_SCHEDULE("Режим работы: 8-20",
            "/dog_shelter_work_schedule"),

    SHELTER_SAFETY_PRECAUTIONS("""
            Итак, что мы просим наших гостей:
            1️⃣ Передвигаться по территории приюта с волонтером
            Мы понимаем ваш интерес и желание посмотреть на всех хвостатых, 
            но это можно сделать только в присутствии волонтера    
            2️⃣ Не подходить слишком близко и не совать руки в вольеры                      
            С уверенностью можно сказать, что большинство питомцев в вольерах вас встретят с виляющим хвостом 
            и просьбами взять их погулять, но некоторые готовы радостно встречать не всех гостей
            3️⃣ Не кормить животных без разрешения волонтера                       
            Кормление без разрешения может привести к проблемам со здоровьем у животного. 
            И это только одна из тех проблем, которые могут возникнуть при бесконтрольном питании ☝️       
            4️⃣ Соблюдать правила общения с животными                       
            Как и со всеми животными, с собаками нужно уметь общаться, 
            чтобы не вызывать у них чувство тревоги и страха. 
            Например, не делать резких движения, не повышать голос, не размахивать руками и
            не дразнить входит в перечень самых базовых требований при общении с четверолапыми 🐕
            """,
            "/shelter_safety_precautions"),
    DOG_SHELTER_SECURITY_CONTACTS("Контакты охраны для въезда на территорию: тел. 8-920-127-86-97",
            "/dog_shelter_security_contacts"),
    CAT_SHELTER_SECURITY_CONTACTS("Контакты охраны для въезда на территорию: тел. 8-920-127-86-97",
            "/cat_shelter_security_contacts");
    private final String description;
    private final String command;

    public String getDescription() {
        return description;
    }

    public String getCommand() {
        return command;
    }

    Information(String description, String command) {
        this.description = description;
        this.command = command;
    }
    }