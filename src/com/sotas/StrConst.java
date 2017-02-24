package com.sotas;

public class StrConst {
    //public static final String zayavlenie_nomer = "Заявляение №";
    public static final String date = "date";

    //left side
    public static final String nomer = "Номер";
    public static final String nomer_cuzova = "Номер кузова";
    public static final String nomer_shassi = "Номер шасси";
    public static final String moshnost_dvigatelya = "Мощн. двигателя";
    public static final String moshnost_dvigatelya_kvt = "Мощн. двигателя(квт)";
    public static final String Vdvigatelya = "Объем двигателя";
    public static final String eco_class = "Экологический класс";
    public static final String max_massa= "Разрешается макс. масса(КГ)";
    public static final String massa_bez_nagruzki = "Масса без нагрузки(КГ)";
    public static final String VIN_id = "VIN идентификационный номер";
    public static final String kategoriya = "Категория";
    public static final String god_vipuska = "Год выпуска";
    public static final String marka = "Марка";
    public static final String model = "Модель";
    public static final String color = "Цвет";
    public static final String перевозка_крупногабаритного_груза = "Перевозка крупногабаритного груза";
    public static final String оборудование_системы_ГЛОНАСС = "Оборудование системы ГЛОНАСС";

    public static String свид_утрачено = "Свид. утрачено";
    public static String технологическая_операция = "Технологическая операция";

    public static class Страховой_полис {
        public static String _name = "Страховой полис";
        public static String серия_и_номер = "Серия и номер";
        public static String дата_выдачи = "Дата выдачи";
        public static String срок_действия = "Срок действия";
        public static String страховщик = "Страховщик";
    }

    public static class Квитанция_об_оплате {
        public static String _name = "Квитанция_об_оплате";
        public static String сумма_платежа = "Сумма платежа";
        public static String дата_платежа = "дата платежа";
        public static String номер_платежа = "номер платежа";
    }

    public static class Владелец {
        public static final String _name = "Владелец";
        public static final String famil = "Фамилия";
        public static final String name = "Имя";
        public static final String otchestvo = "Отчество";
        public static final String bornDate = "Дата рождения";
        public static final String inn = "ИНН";
        public static final String tipDUL = "Тип документа удостоверяющий личность";
        public static final String seriaNomerDUL = "Серия и номер документа, удостоверяющего личность";
        public static final String kemVidanDUL = "Кем выдан документ, удостоверяющий личность";
        public static final String dataVidachiDUL = "Дата выдачи документа, удостоверяющего личность";
        public static final String mobilePhone = "Мобильный телефон";
        public static final String mail = "Электронная почта";

        public static class adresReg {
            public static final String prefix = "Адрес регистрации";
            public static final String strana = "Страна";
            public static final String subectRf = "Субъект Российской Федерации";
            public static final String rayon = "Район";
            public static final String naseleniyPunktPriRegVRf = "Населенный пункт(при регистрации в России)";
            public static final String naseleniyPunktPriRegNeVRF = "Населенный пункт(при регистрации за пределами России)";
            public static final String ulicaPriRegVRf = "Улица(при регистрации в России)";
            public static final String ulicaPriRegNeVRf = "Улица(при регистрации за пределами России)";
            public static final String dom = "Дом";
            public static final String korpus = "Корпус";
            public static final String stroyenie = "Строение";
            public static final String kvartira = "Квартира";
            public static final String mailIndex = "Почтовый индекс";
        }
    }

    public static class Свидетельство_о_регистрации {
        public static final String _name = "Свид. о регистрации";
        public static final String серия_и_номер = "Серия и номер";
        public static final String тип = "Тип";
        public static final String дата_выдачи = "Дата выдачи";
        public static final String кем_выдан = "Кем выдан";
        public static final String особые_отметки = "Особые отметки";
    }

    public static class PTS {
        public static final String _name = "ПТС";
        public static final String серия_и_номер = "Серия и номер";
        public static final String тип = "Тип";
        public static final String дата_выдачи = "Дата выдачи";
        public static final String кем_выдан = "Кем выдан";
        public static final String особые_отметки = "Особые отметки";
    }

    public static class ДокументОснование {
        public static final String _name = "Документ-основание";
        public static final String серия_и_номер = "Серия и номер";
        public static final String тип = "Тип";
        public static final String дата_выдачи = "Дата выдачи";
        public static final String кем_выдан = "Кем выдан";
    }
}
