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
    private final ComponentMap componentMap;
    private Font smallFont;
    private Font normalFont;
    private Font boldFont;

    public PdfGenerator(ComponentMap componentMap) throws IOException, DocumentException {
        this.componentMap = componentMap;
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
        doc.add(new Phrase("           "+ Utils.dateTimeFormat.format(new Date()) +"                                              МРЭО ГИБДД МВД по РД(дислокация г.Избербаш)", smallFont));
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
        doc.close();
    }

    private Element genT1() {
        Phrase phrase = null;
        PdfPTable t = new PdfPTable(1);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase(StrConst.zayavlenie_nomer + " " + componentMap.getFieldText(StrConst.zayavlenie_nomer), boldFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setBorderWidthBottom(0);
        t.addCell(c);

        c = new PdfPCell(new Phrase("В Госавтоинспекцию МРЭО ГИБДД МВД по РД (дислокация г.Избербаш)", normalFont));
        c.setBorderWidthTop(0);
        t.addCell(c);

        phrase = new Phrase("Я, ", normalFont);
        phrase.add(new Chunk(componentMap.getFieldText(StrConst.vladelec), boldFont));
        phrase.add(new Chunk(", прошу", normalFont));
        c = new PdfPCell(phrase);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        t.addCell(new Phrase("внести изменения в регистрационные данные в связи с изменением собственика", normalFont));
        t.addCell(new Phrase("с выдачей СТС, ГРС, с внесением изменений в ПТС", normalFont));

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
        t.addCell(new Phrase(componentMap.getFieldText(StrConst.marka) + " " + componentMap.getFieldText(StrConst.model) + ", " + componentMap.getFieldText(StrConst.god_vipuska), normalFont));

        t.addCell(new Phrase("VIN идентификационный номер", normalFont));
        t.addCell(new Phrase("XTA29385912385912", normalFont));

        t.addCell(new Phrase("Регистр. знак(при наличии)", normalFont));
        t.addCell(new Phrase("С123Е1292", normalFont));
        return t;
    }

    private Element genT3() {
        PdfPTable t = new PdfPTable(new float[]{40, 60});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        t.addCell(new Phrase("СВЕДЕНИЯ О СОБСТВЕННИКЕ ТС", boldFont));
        t.addCell(new Phrase("МАГАМАЕВ РАДЖАБ ГУСЕЙНОВИЧ", boldFont));

        t.addCell(new Phrase("Документ, удостоверяющий личность", normalFont));
        t.addCell(new Phrase("Паспорт(Россия), 12 54 235126, выдан 25.02.2007, ДАЗАДАЕВСКИМ РОВД", normalFont));

        t.addCell(new Phrase("ИНН(при наличии)", normalFont));
        t.addCell(new Phrase("", normalFont));

        t.addCell(new Phrase("Адрес регистрации", normalFont));
        t.addCell(new Phrase("395713, Россия, Республика Дагестан, Дахадаевский р-н, с. Худуц", normalFont));

        t.addCell(new Phrase("Телефон", normalFont));
        t.addCell(new Phrase("Адрес эл. почты(при наличии)", normalFont));
        return t;
    }

    private Element genT4() {
        PdfPTable t = new PdfPTable(new float[]{33, 33, 34});
        t.setSpacingBefore(5);
        t.setWidthPercentage(90);

        PdfPCell c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.date), normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase("v", normalFont));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(c);

        c = new PdfPCell(new Phrase(Utils.cutFio(componentMap.getFieldText(StrConst.vladelec)), normalFont));
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
        c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.marka) + " " + componentMap.getFieldText(StrConst.model) + ", " + componentMap.getFieldText(StrConst.god_vipuska), normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("соответствует / \n не соответствует  \n (ненужное зачеркнуть)", normalFont));
        c.setVerticalAlignment(Element.ALIGN_BOTTOM);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setRowspan(9);
        t.addCell(c);

        c = new PdfPCell(new Phrase("Тип/категория ТС", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.kategoriya), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Цвет", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("СЕРЕБРИСТО-ГОЛУБОЙ", normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Регистрационный знак", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("ВЛ238ВЛ84", normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase(StrConst.VIN_id, normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.VIN_id + "1") + " / " + componentMap.getFieldText(StrConst.VIN_id + "2"), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Номер кузова", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.nomer_cuzova), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Номер шасси", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase(componentMap.getFieldText(StrConst.nomer_shassi), normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Мощн. двиг. л.с.(кВт), раб. объем двиг.(куб/см), экологичный класс", normalFont));
        t.addCell(c);
        String power = null;
        String power2 = null;
        try {
            power = componentMap.getFieldText(StrConst.moshnost_dvigatelya);
            power2 = String.format("%.2f", Double.parseDouble(power) * 0.735499);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        c = new PdfPCell(new Phrase(power + "(" + power2 + ")" + ", 3954, ТРЕТИЙ", normalFont));
        t.addCell(c);

        c = new PdfPCell(new Phrase("Разреш. макс. масса(кг), масса без нагруз.(кг)", normalFont));
        t.addCell(c);
        c = new PdfPCell(new Phrase("5345, 9435", normalFont));
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
        c = new PdfPCell(new Phrase("238ВЛ848", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Паспорт транспортного средства(серия, №)", normalFont)));
        c = new PdfPCell(new Phrase("59 ПО 49348234, 31.08.2007, ОАО \"АВТОВАЗ\"", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Документ удостоверяющий право собственности", normalFont)));
        c = new PdfPCell(new Phrase("Договор, совершенный в простой письменной форме, 25.08.2016, ВОЛГОГРАД", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Страховой полис(№, когда и кем выдан)", normalFont)));
        c = new PdfPCell(new Phrase("ЕЕЕ 34050345349183, 04.10.2013, ООО СК \"Согласие\"", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Иные документы представленные заявителем", normalFont)));
        c = new PdfPCell(new Phrase("СТС 1230912-30912, 20.11.2013, РЭО ГИБДД МО МВД РФ \"ИЛОВЛИНСКИЙ\"", normalFont));
        c.setColspan(2);
        t.addCell(c);

        t.addCell(new PdfPCell(new Phrase("Квитанция №(при наличии)", normalFont)));
        c = new PdfPCell(new Phrase("123810283, 20.11.2013, 1300 руб.", normalFont));
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
}

        /*
        PdfPTable t = new PdfPTable(3);
        t.setSpacingBefore(0);
        t.setSpacingAfter(10);
        PdfPCell c1 = new PdfPCell(new Phrase("Йцйуйцуяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяя", simpleFont));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
        c2.setColspan(2);
        t.addCell(c2);
        t.addCell("1.1");
        t.addCell("1.2");
        PdfPCell c3 = new PdfPCell(new Phrase("Йцйуйцуяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяяя", simpleFont));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c3.setRowspan(2);
        t.addCell(c3);
        t.addCell("2.1");
        t.addCell("2.2");

        t.addCell("3.1");
        t.addCell("3.2");
        t.addCell("3.3");
        doc.add(t);
        doc.add(t);
        /*
        Image image1 = Image.getInstance("bg.png");
        image1.setAbsolutePosition(0, 0);
        doc.add(image1);
        doc.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+ "Жених", boldFont));
        doc.add(new Paragraph("ФИО: " + n.he, simpleFont));
        doc.add(new Paragraph("Дата и место рождения: " + date2str(n.heBorn) + " " + n.heLocation , simpleFont));
        doc.add(new Paragraph("Невеста", boldFont));
        doc.add(new Paragraph("ФИО: " + n.she, simpleFont));
        doc.add(new Paragraph("Дата и место рождения: " + date2str(n.sheBorn) + " " + n.sheLocation , simpleFont));

        doc.add(new Paragraph("Уали", boldFont));
        doc.add(new Paragraph(n.wali, simpleFont));

        doc.add(new Paragraph("Свидетели", boldFont));
        doc.add(new Paragraph("1. " + n.witness1, simpleFont));
        doc.add(new Paragraph("2. " + n.witness2, simpleFont));

        doc.add(new Paragraph("Имам", boldFont));
        doc.add(new Paragraph(n.imam, simpleFont));

        doc.add(new Paragraph("Место заключения брака", boldFont));
        doc.add(new Paragraph(n.location, simpleFont));

        doc.add(new Paragraph("Дата", boldFont));
        doc.add(new Paragraph(date2str(n.date), simpleFont));

        doc.add(new Paragraph("ЭЦП:", boldFont));
        doc.add(new Paragraph(n.sign, simpleFont));
        */