CREATE TABLE "buddie_user" ("id" serial,
                            "first_name" text,
                            "last_name" text,
                            "country_code" text,
                            "phone_number" text,
                            "email" text,
                            PRIMARY KEY ("id")
);

INSERT INTO "buddie_user"("id","first_name","last_name","country_code","phone_number","email")
VALUES
(1,E'Sean',E'Tester',E'+1',E'9734592514',E'tester@gmail.com'),
(2,E'SeanTwo',E'Scalabriin',E'+49',E'01788433660',E'tester123@gmail.com'),
(3,E'Sean',E'Scal',E'+49',E'8567657567',E'sean@gmail.com'),
(4,E'Mattoe',E'Gambe',E'+49',E'12344422332',E'scal@gmail.com')

