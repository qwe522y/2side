package com.sotas;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PdfGenerator {
    private static final Logger log = Logger.getLogger(PdfGenerator.class);
    private static String path = "temp.pdf";
    private final String vladelecFio;
    private Prms prms;
    private Font smallFont;
    private Font normalFont;
    private Font boldFont;

    public PdfGenerator(Prms prms) throws IOException, DocumentException {
        this.prms = prms;
        vladelecFio = (prms.vladelec(StrConst.vladelec.famil) + " " + prms.vladelec(StrConst.vladelec.name) + " " + prms.vladelec(StrConst.vladelec.otchestvo)).toUpperCase();
        BaseFont times = BaseFont.createFont("normal.ttf", "cp1251", BaseFont.EMBEDDED);
        smallFont = new Font(times, 6);
        normalFont = new Font(times, 9);
        boldFont = new Font(times, 9);
        boldFont.setStyle(Font.BOLD);
    }

    public void gen() throws Exception {
        Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();
        doc.add(new Phrase("                     "+ Utils.dateTimeFormat.format(new Date()) +"                                              МРЭО ГИБДД МВД по РД(дислокация г.Избербаш)", smallFont));
        doc.add(genT1());
        doc.add(genT2());
        doc.add(genT3());
        doc.add(genT4());
        doc.add(genT5());
        doc.add(genT6());
        doc.add(genT7());
        doc.add(genT8());
        doc.add(genT9());
        doc.add(genT10());
        Paragraph paragrath = new Paragraph();
        paragrath.setAlignment(Element.ALIGN_RIGHT);
        paragrath.add(new Phrase(prms.main("id") + "                     ", smallFont));
        doc.add(paragrath);
        doc.close();
    }

    private Element genT1() {
        Phrase phrase = null;
        PdfPTable t = new PdfPTable(1);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("Заявляение №", boldFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setBorderWidthBottom(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase("В Госавтоинспекцию МРЭО ГИБДД МВД по РД (дислокация г.Избербаш)", normalFont));
        c.setBorderWidthTop(0);
        t.addCell(c);

        phrase = new Phrase("Я, ", normalFont);
        phrase.add(new Chunk(vladelecFio, boldFont));
        phrase.add(new Chunk(", прошу", normalFont));
        c = new PdfPCell(phrase);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        t.addCell(new Phrase(prms.main(StrConst.технологическая_операция), normalFont));
        //t.addCell(new Phrase("с выдачей СТС, ГРС, с внесением изменений в ПТС", normalFont));

        return t;
    }

    private Element genT2() {
        PdfPTable t = new PdfPTable(new float[]{40, 60});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("ТРАНСПОРТНОЕ СРЕДСТВО", boldFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new Phrase("Марка, модель, год выпуска", normalFont));
        t.addCell(new Phrase(prms.main(StrConst.marka) + " " + prms.main(StrConst.model) + ", " + prms.main(StrConst.god_vipuska), normalFont));

        t.addCell(new Phrase("VIN идентификационный номер", normalFont));
        t.addCell(new Phrase(prms.main(StrConst.VIN_id + "1"), normalFont));

        t.addCell(new Phrase("Регистр. знак(при наличии)", normalFont));
        t.addCell(new Phrase(prms.main(StrConst.nomer).toUpperCase(), normalFont));
        return t;
    }

    private Element genT3() {
        PdfPTable t = new PdfPTable(new float[]{40, 60});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        t.addCell(new Phrase("СВЕДЕНИЯ О СОБСТВЕННИКЕ ТС", boldFont));
        t.addCell(new Phrase(vladelecFio, boldFont));

        t.addCell(new Phrase(StrConst.vladelec.bornDate, normalFont));
        t.addCell(new Phrase(prms.vladelec(StrConst.vladelec.bornDate), normalFont));

        t.addCell(new Phrase("Документ, удостоверяющий личность", normalFont));
        t.addCell(new Phrase(prms.vladelec(StrConst.vladelec.tipDUL) +  ", " +
                prms.vladelec(StrConst.vladelec.seriaNomerDUL) + ", выдан " +
                prms.vladelec(StrConst.vladelec.dataVidachiDUL) + ", " +
                prms.vladelec(StrConst.vladelec.kemVidanDUL), normalFont));

        t.addCell(new Phrase("ИНН(при наличии)", normalFont));
        t.addCell(new Phrase(prms.vladelec(StrConst.vladelec.inn), normalFont));

        t.addCell(new Phrase("Адрес регистрации", normalFont));
        StringBuilder sb = new StringBuilder();
        for(String s : new String[]{
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.mailIndex),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.strana),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.subectRf),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.rayon),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.naseleniyPunktPriRegVRf),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.naseleniyPunktPriRegNeVRF),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.ulicaPriRegVRf),
            prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.ulicaPriRegNeVRf),
            addPrefix("дом ", prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.dom)),
            addPrefix("корпус ", prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.korpus)),
            addPrefix("стр-е", prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.stroyenie)),
            addPrefix("кв. ", prms.vladelec(StrConst.vladelec.adresReg.prefix + "_" + StrConst.vladelec.adresReg.kvartira))
        }) {
            if(s != null && s.length() > 0) sb.append(", ").append(s);
        }
        if(sb.length()>0) sb.delete(0, 1);
        t.addCell(new Phrase(sb.toString(), normalFont));

        t.addCell(new Phrase("Телефон    " + prms.vladelec(StrConst.vladelec.mobilePhone), normalFont));
        t.addCell(new Phrase("Адрес эл. почты(при наличии)    " + prms.vladelec(StrConst.vladelec.mail), normalFont));
        return t;
    }

    private Element genT4() {
        PdfPTable t = new PdfPTable(new float[]{33, 33, 34});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase(prms.main(StrConst.date), normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("v", normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase(Utils.cutFio(prms.main(vladelecFio)), normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("И.О. Фамилия заявителя", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private Element genT5() {
        PdfPTable t = new PdfPTable(new float[]{40, 35, 25});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("СВЕДЕНИЯ О ТРАНСПОРТНОМ СРЕДСТВЕ", boldFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("Из паспорта ТС и (или) регистрационного документа", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("Результат осмотра", normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Марка, модель, год выпуска", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.marka) + " " + prms.main(StrConst.model) + ", " + prms.main(StrConst.god_vipuska), normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("соответствует / \n не соответствует  \n (ненужное зачеркнуть)", normalFont));
        c.setVerticalAlignment(Element.ALIGN_BOTTOM);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setRowspan(9);
        t.addCell(c);

        c = new PdfPCell(new Phrase("Тип/категория ТС", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.kategoriya), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Цвет", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.color), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Регистрационный знак", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.nomer).toUpperCase(), boldFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase(StrConst.VIN_id, normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.VIN_id + "1"), boldFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Номер кузова", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.nomer_cuzova), boldFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Номер шасси", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.nomer_shassi), boldFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Мощн. двиг. л.с.(кВт), раб. объем двиг.(куб/см), экологичный класс", normalFont));
        t.addCell(c);
        String power = prms.main(StrConst.moshnost_dvigatelya);
        String power2 = prms.main(StrConst.moshnost_dvigatelya_kvt);
        String V = prms.main(StrConst.Vdvigatelya);
        c = new PdfPCell(new Phrase(power + "(" + power2 + ")" + ", " + V + ", " + prms.main(StrConst.eco_class), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Разреш. макс. масса(кг), масса без нагруз.(кг)", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(prms.main(StrConst.max_massa) + ", " + prms.main(StrConst.massa_bez_nagruzki), normalFont));
        t.addCell(c);

        t.addCell(" ");
        t.addCell(" ");
        t.addCell(" ");

        c = new PdfPCell(new Phrase("(время, дата осмотра)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("И.О. Фамилия сотрудника", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private Element genT6() {
        PdfPTable t = new PdfPTable(new float[]{33, 33, 34});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("РЕШЕНИЕ ПО АВТОМАТИЗИРОВАННЫМ УЧЕТАМ", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(" ");

        t.addCell(" ");
        t.addCell(" ");
        t.addCell(" ");

        c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("И.О. Фамилия сотрудника", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private Element genT7() {
        PdfPTable t = new PdfPTable(new float[]{33, 33, 34});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("РЕШЕНИЕ ПО ЗАЯВЛЕНИЮ", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(" ");

        t.addCell(" ");
        t.addCell(" ");
        t.addCell(" ");

        c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("И.О. Фамилия сотрудника", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private Element genT8() {
        PdfPTable t = new PdfPTable(new float[]{40, 35, 25});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("ПРИНЯТО ОТ ЗАЯВИТЕЛЯ", normalFont));
        c.setColspan(3);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Регистрационные знаки или знаки \"ТРАНЗИТ\"", normalFont)));
        c = new PdfPCell(new Phrase("", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Паспорт транспортного средства(серия, №)", normalFont)));
        c = new PdfPCell(new Phrase(prms.getVal("ПТС", StrConst.PTS.серия_и_номер) + ", " + prms.getVal("ПТС", StrConst.PTS.дата_выдачи), normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Документ удостоверяющий право собственности", normalFont)));
        c = new PdfPCell(new Phrase("", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Страховой полис(№, когда и кем выдан)", normalFont)));
        c = new PdfPCell(new Phrase("", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Иные документы представленные заявителем", normalFont)));
        c = new PdfPCell(new Phrase(prms.getVal("свид. о регистрации", StrConst.PTS.серия_и_номер), normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Квитанция №(при наличии)", normalFont)));
        c = new PdfPCell(new Phrase("", normalFont));
        c.setColspan(2);
        t.addCell(c);

        c = new PdfPCell(new Phrase(" ", normalFont));
        c.setBorderWidthRight(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase(" ", normalFont));
        c.setBorderWidthLeft(0);
        c.setBorderWidthRight(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase(" ", normalFont));
        c.setBorderWidthLeft(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setBorderWidthRight(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setBorderWidthLeft(0);
        c.setBorderWidthRight(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(И.О. Фамилия сотрудника)", smallFont));
        c.setBorderWidthLeft(0);
        t.addCell(c);

        return t;
    }

    private Element genT9() {
        PdfPTable t = new PdfPTable(new float[]{50, 50});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase("ВЫДАНО ЗАЯВИТЕЛЮ", normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Регистрационные знаки или знаки \"ТРАНЗИТ\"", normalFont)));
        c = new PdfPCell(new Phrase(" ", normalFont));
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Паспорт транспортного средства(серия, №)", normalFont)));
        c = new PdfPCell(new Phrase(" ", normalFont));
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Свидетельство о регистрации ТС(серия, №)", normalFont)));
        c = new PdfPCell(new Phrase(" ", normalFont));
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Иные документы", normalFont)));
        c = new PdfPCell(new Phrase(" ", normalFont));
        t.addCell(c);

        t.addCell(" ");
        t.addCell(" ");

        c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись заявителя в получении)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private Element genT10() {
        PdfPTable t = new PdfPTable(new float[]{33, 33, 34});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        t.addCell(" ");
        t.addCell(" ");
        t.addCell(" ");

        PdfPCell c = new PdfPCell(new Phrase("(дата)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(подпись)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("(И.О. Фамилия сотрудника)", smallFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        return t;
    }

    private String addPrefix(String prefix, String val) {
        if(val != null && val.length() > 0) return prefix + val;
        return val;
    }
}