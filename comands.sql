UPDATE public.animal SET shelter_id = 2 WHERE id = 2

CREATE TABLE "user" (
                        chatId bigint PRIMARY KEY ,
                        "name" TEXT,
                        localDateTime TIMESTAMP,
                        animalId bigint
)