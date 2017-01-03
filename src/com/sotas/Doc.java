package com.sotas;

public class Doc {
    public String zayavlenie_nomer;
    public String date;
    //left side
    public VladelecClass vladelec;
    public PredstavitelClass predstavitelClass;
    public String nomer_cuzova;
    public String nomer_shassi;
    public String moshnost_dvigatelya;
    public String VIN_id;
    public String kategoriya;
    public String god_vipuska;
    public String marka;
    public String model;

    public Doc(ComponentMap rootCM, ComponentMap vladelecCM, ComponentMap prenstavitelCM) {

    }

    public static class VladelecClass {
        public String familiya;
        public String translitFamil;
        public String name;
        public String translitName;
        public String otchestvo;
        public String bornDate;
        public String sex;
        public String regionBornPlace;
        public String bornPlace;
        public String inn;
        public String whoGaveInn;
        public String individualEntrepreneur;
        public String OGRNIP;
        public String whoGaveOGRNIP;
        public String grajdanstvo;
        public String tipDUL;
        public String serialAndNuberDUL;
        public String dataVidachiDUL;
        public String kodPodrazdeleniya;
        public String whoGaveDUL;
        public String stranaDUL;
        public String adresRegistracii;
        public String mestoRaboti;
        public String doljnost;
        public String homePhone;
        public String mobilePhone;
        public String email;
    }

    public static class PredstavitelClass {
        public String familiya;
        public String translitFamil;
        public String name;
        public String translitName;
        public String otchestvo;
        public String bornDate;
        public String sex;
        public String regionBornPlace;
        public String bornPlace;
        public String grajdanstvo;
        public String tipDUL;
        public String serialAndNuberDUL;
        public String dataVidachiDUL;
        public String kodPodrazdeleniya;
        public String whoGaveDUL;
        public String stranaDUL;
        public String adresRegistracii;
        public String homePhone;
        public String mobilePhone;
        public String tipDoverenosti;
        public String seriaAndNomer;
        public String dataVidachi;
        public String dataOconchDeystviya;
        public String kemVidanaDoverenost;
    }
}
