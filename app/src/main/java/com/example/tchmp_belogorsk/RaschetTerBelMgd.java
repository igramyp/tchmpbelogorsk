package com.example.tchmp_belogorsk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RaschetTerBelMgd extends AppCompatActivity {

    private EditText numVes, numOsi, vvodStoianok, vvodKolvaOgr, vvodgorprost;
    private TextView nagrOs, koefOs, tv_peregon, normaRezylt, normaSost;
    private Spinner st_otpr, st_prib, kolvo_Sek;

    private static String[] Stations_list = {"Сковородино", "Б.Невер", "Ковали", "Ульручьи", "Ангарич", "Джиктанда", "Талдан", "Гудачи", "Гонжа", "Нюкжа", "Магдагачи", "Красная Падь", "Дактуй", "Тыгда", "Чалганы", "Ушумун", "Сиваки", "Ту", "Мухинская", "Берея", "Шимановск", "Селеткан", "Ледяная", "Усть Пера", "Свободный", "Мих.-Чеснок.", "Арга", "Серышево", "Украина", "Белогорск-1", "Белогорск-2", "Возжаевка", "Поздеевка", "Екатеринославка", "Тур", "Завитая", "Тюкан", "Бурея", "Домикан", "Архара", "Татакан", "К.-Карьер", "Богучан", "Рачи", "Урил", "Тарманчукан", "Тоннельный", "Кундур", "Казачий", "Ядрин", "Облучье"};
    private static String[] kol_vo_Sek = {"Э5к","2ЭС5к","3ЭС5к","2х2ЭС5к"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raschet_ter_bel_mgd);
        addListenerOnButton();
    } // конец onCreate

    public void addListenerOnButton() {
        numVes = findViewById(R.id.vvod_vesa_poezda); // ВВОД ВЕСА ПОЕЗДА
        numOsi = findViewById(R.id.vvod_osei_poezda); // ВВОД КОЛ-ВА ОСЕЙ
        vvodStoianok = findViewById(R.id.et_vvodStoianok); // ВВОД КОЛ-ВА СТОЯНОК
        vvodKolvaOgr = findViewById(R.id.et_vvodKolvaOgr); //ВВОД КОЛ-ВА ОГРАНИЧЕНИЙ
        tv_peregon = findViewById(R.id.tv_peregon); // ПОКАЗЫВАЕТ УЧАСТОК
        nagrOs = findViewById(R.id.tx_nagryzka_naos); // ВЫВОДИТ НАГРУЗКУ НА ОСЬ
        koefOs = findViewById(R.id.tx_koef_naos); // ВЫВОДИТ КОЭФИЦИЕНТ
        Button btn_sdelat = findViewById(R.id.btn_raschet_koef_na_os); // КНОПКА СДЕЛАТЬ КРАСИВО
        st_otpr = findViewById(R.id.sp_stan_otprav); // СТАНЦИЯ ОТПРАВЛЕНИЯ СПИНЕР
        st_prib = findViewById(R.id.sp_stan_prib);// СТАНЦИЯ ПРИБЫТИЯ СПИНЕР
        normaSost = findViewById(R.id.tv_normaSostavl);
        normaRezylt = findViewById(R.id.tv_normaRezylt);
        vvodgorprost = findViewById(R.id.et_vvodgorprost);
        kolvo_Sek = findViewById(R.id.kolvoSek);

        btn_sdelat.setOnClickListener( //обработчик кнопки btn_sdelat

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (TextUtils.isEmpty(numVes.getText().toString()) || TextUtils.isEmpty(numOsi.getText().toString())) {
                            return;
                        }

                        if (TextUtils.isEmpty(vvodStoianok.getText().toString())) { vvodStoianok.setText("0"); }
                        if (TextUtils.isEmpty(vvodKolvaOgr.getText().toString())) { vvodKolvaOgr.setText("0"); }
                        if (TextUtils.isEmpty(vvodgorprost.getText().toString())) { vvodgorprost.setText("0"); }

                        double VesPoezda = Double.parseDouble(numVes.getText().toString()); // из переменной NumVes берется текст в строку и присваивается VesPoezda
                        double OsPoezda = Double.parseDouble(numOsi.getText().toString());// из переменной numOsi берется текст в строку и присваивается OsPoezda
                        int kolvoStoianok = Integer.parseInt(vvodStoianok.getText().toString());
                        int kolvoOgr = Integer.parseInt(vvodKolvaOgr.getText().toString());
                        double gorProst_T = Double.parseDouble(vvodgorprost.getText().toString());
                        int kol_voSek = 0;
                        if (OsPoezda == 0) {
                            Toast.makeText(RaschetTerBelMgd.this, "ВВЕДИТЕ КОРРЕКТНОЕ КОЛ-ВО ОСЕЙ!", Toast.LENGTH_LONG).show();
                            return;
                        } // проверка деления на нуль, вывод сообщения

                        double NagrNaOsD = VesPoezda / OsPoezda; // вычисление нагрузки на ось
                        int NagrNaOsInt = (int) Math.round(NagrNaOsD); // находим нагрузку на ось
                        double koefVesa = (int) Math.ceil(VesPoezda/1000);
                        double vziatSmesta = 100*kolvoStoianok + kolvoStoianok*koefVesa*20;
                        nagrOs.setText("" + (NagrNaOsInt) + " т"); // значение NagrNaOs преобразовать в строку и присвоить текствиву NagrOs

                        String st_otprSt = String.valueOf(st_otpr.getSelectedItem()); // получение станции
                        String st_pribSt = String.valueOf(st_prib.getSelectedItem()); // в строку
                        String kolvoSek = String.valueOf(kolvo_Sek.getSelectedItem());

                        if (kolvoSek.equals(kol_vo_Sek[0])) { kol_voSek = 1; }
                        if (kolvoSek.equals(kol_vo_Sek[1])) { kol_voSek = 2; }
                        if (kolvoSek.equals(kol_vo_Sek[2])) { kol_voSek = 3; }
                        if (kolvoSek.equals(kol_vo_Sek[3])) { kol_voSek = 4; }

                        double gorProstSum = (kol_voSek*50*(gorProst_T/60));

                        int st_otpr_km = 0;
                        int st_prib_km = 0;

                        if (st_otprSt.equals(Stations_list[10])) { st_otpr_km = 7494; }
                        if (st_otprSt.equals(Stations_list[11])) { st_otpr_km = 7511; }
                        if (st_otprSt.equals(Stations_list[12])) { st_otpr_km = 7528; }
                        if (st_otprSt.equals(Stations_list[13])) { st_otpr_km = 7559; }
                        if (st_otprSt.equals(Stations_list[14])) { st_otpr_km = 7579; }
                        if (st_otprSt.equals(Stations_list[15])) { st_otpr_km = 7602; }
                        if (st_otprSt.equals(Stations_list[16])) { st_otpr_km = 7626; }
                        if (st_otprSt.equals(Stations_list[17])) { st_otpr_km = 7650; }
                        if (st_otprSt.equals(Stations_list[18])) { st_otpr_km = 7677; }
                        if (st_otprSt.equals(Stations_list[19])) { st_otpr_km = 7699; }
                        if (st_otprSt.equals(Stations_list[20])) { st_otpr_km = 7724; }
                        if (st_otprSt.equals(Stations_list[21])) { st_otpr_km = 7742; }
                        if (st_otprSt.equals(Stations_list[22])) { st_otpr_km = 7765; }
                        if (st_otprSt.equals(Stations_list[23])) { st_otpr_km = 7800; }
                        if (st_otprSt.equals(Stations_list[24])) { st_otpr_km = 7806; }
                        if (st_otprSt.equals(Stations_list[25])) { st_otpr_km = 7812; }
                        if (st_otprSt.equals(Stations_list[26])) { st_otpr_km = 7822; }
                        if (st_otprSt.equals(Stations_list[27])) { st_otpr_km = 7845; }
                        if (st_otprSt.equals(Stations_list[28])) { st_otpr_km = 7857; }
                        if (st_otprSt.equals(Stations_list[29])) { st_otpr_km = 7866; }
                        if (st_otprSt.equals(Stations_list[30])) { st_otpr_km = 7873; }

                        if (st_pribSt.equals(Stations_list[10])) { st_prib_km = 7494; }
                        if (st_pribSt.equals(Stations_list[11])) { st_prib_km = 7511; }
                        if (st_pribSt.equals(Stations_list[12])) { st_prib_km = 7528; }
                        if (st_pribSt.equals(Stations_list[13])) { st_prib_km = 7559; }
                        if (st_pribSt.equals(Stations_list[14])) { st_prib_km = 7579; }
                        if (st_pribSt.equals(Stations_list[15])) { st_prib_km = 7602; }
                        if (st_pribSt.equals(Stations_list[16])) { st_prib_km = 7626; }
                        if (st_pribSt.equals(Stations_list[17])) { st_prib_km = 7650; }
                        if (st_pribSt.equals(Stations_list[18])) { st_prib_km = 7677; }
                        if (st_pribSt.equals(Stations_list[19])) { st_prib_km = 7699; }
                        if (st_pribSt.equals(Stations_list[20])) { st_prib_km = 7724; }
                        if (st_pribSt.equals(Stations_list[21])) { st_prib_km = 7742; }
                        if (st_pribSt.equals(Stations_list[22])) { st_prib_km = 7765; }
                        if (st_pribSt.equals(Stations_list[23])) { st_prib_km = 7800; }
                        if (st_pribSt.equals(Stations_list[24])) { st_prib_km = 7806; }
                        if (st_pribSt.equals(Stations_list[25])) { st_prib_km = 7812; }
                        if (st_pribSt.equals(Stations_list[26])) { st_prib_km = 7822; }
                        if (st_pribSt.equals(Stations_list[27])) { st_prib_km = 7845; }
                        if (st_pribSt.equals(Stations_list[28])) { st_prib_km = 7857; }
                        if (st_pribSt.equals(Stations_list[29])) { st_prib_km = 7866; }
                        if (st_pribSt.equals(Stations_list[30])) { st_prib_km = 7873; }

                        int st_rez_km = st_otpr_km - st_prib_km;

                        tv_peregon.setText(st_otprSt + " " + st_otpr_km + " - " + st_pribSt + " " +st_prib_km + " = " + st_rez_km + " км");

                        if (st_rez_km == 0) {
                            Toast.makeText(RaschetTerBelMgd.this, "ПРОВЕРЬТЕ УЧАСТОК", Toast.LENGTH_LONG).show();
                            return;
                        } // проверка перегона

                        if (st_rez_km > 0) { // НЕЧЕТНЫЙ
                            if (NagrNaOsInt <= 4) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком маленький коэфф., проверьте введенные данные");
                            }
                            if (NagrNaOsInt == 5) {
                                koefOs.setText("Коэф.=218");
                                normaRezylt.setText("Норма составляет: " + (int) ((218 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr + gorProstSum) +" кВт");
                            }
                            if (NagrNaOsInt == 6) {
                                koefOs.setText("Коэф.=192,5");
                                normaRezylt.setText("Норма составляет: " + (int) ((192.5 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 7) {
                                koefOs.setText("Коэф.=174,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((174.4 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 8) {
                                koefOs.setText("Коэф.=160,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((160.8 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 9) {
                                koefOs.setText("Коэф.=150,2");
                                normaRezylt.setText("Норма составляет: " + (int) ((150.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 10) {
                                koefOs.setText("Коэф.=141,7");
                                normaRezylt.setText("Норма составляет: " + (int) ((141.7 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 11) {
                                koefOs.setText("Коэф.=134,7");
                                normaRezylt.setText("Норма составляет: " + (int) ((134.7 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 12) {
                                koefOs.setText("Коэф.=129,1");
                                normaRezylt.setText("Норма составляет: " + (int) ((129.1 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 13) {
                                koefOs.setText("Коэф.=124");
                                normaRezylt.setText("Норма составляет: " + (int) ((124 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 14) {
                                koefOs.setText("Коэф.=119,9");
                                normaRezylt.setText("Норма составляет: " + (int) ((119.9 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 15) {
                                koefOs.setText("Коэф.=116,3");
                                normaRezylt.setText("Норма составляет: " + (int) ((116.3 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 16) {
                                koefOs.setText("Коэф.=113,1");
                                normaRezylt.setText("Норма составляет: " + (int) ((113.1 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 17) {
                                koefOs.setText("Коэф.=110,3");
                                normaRezylt.setText("Норма составляет: " + (int) ((110.3 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 18) {
                                koefOs.setText("Коэф.=109,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((109.8 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 19) {
                                koefOs.setText("Коэф.=106,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((106.8 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 20) {
                                koefOs.setText("Коэф.=103,5");
                                normaRezylt.setText("Норма составляет: " + (int) ((103.5 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 21) {
                                koefOs.setText("Коэф.=101,7");
                                normaRezylt.setText("Норма составляет: " + (int) ((101.7 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 22) {
                                koefOs.setText("Коэф.=100,1");
                                normaRezylt.setText("Норма составляет: " + (int) ((100.1 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 23) {
                                koefOs.setText("Коэф.=98,6");
                                normaRezylt.setText("Норма составляет: " + (int) ((98.6 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 24) {
                                koefOs.setText("Коэф.=97,2");
                                normaRezylt.setText("Норма составляет: " + (int) ((97.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 25) {
                                koefOs.setText("Коэф.=95,9");
                                normaRezylt.setText("Норма составляет: " + (int) ((95.9 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt >= 26) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком большой коэфф., проверьте введенные данные");
                            }
                        } else { // ЧЕТНЫЙ
                            if (NagrNaOsInt <= 4) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком маленький коэфф., проверьте введенные данные");
                            }
                            if (NagrNaOsInt == 5) {
                                koefOs.setText("Коэф.=117,1");
                                normaRezylt.setText("Норма составляет: " + (int) ((117.1 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 6) {
                                koefOs.setText("Коэф.=106,2");
                                normaRezylt.setText("Норма составляет: " + (int) ((106.2 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 7) {
                                koefOs.setText("Коэф.=98,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((98.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 8) {
                                koefOs.setText("Коэф.=92,6");
                                normaRezylt.setText("Норма составляет: " + (int) ((92.6 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 9) {
                                koefOs.setText("Коэф.=88");
                                normaRezylt.setText("Норма составляет: " + (int) ((88 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 10) {
                                koefOs.setText("Коэф.=84,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((84.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 11) {
                                koefOs.setText("Коэф.=81,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((81.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 12) {
                                koefOs.setText("Коэф.=78,9");
                                normaRezylt.setText("Норма составляет: " + (int) ((78.9 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 13) {
                                koefOs.setText("Коэф.=77,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((77.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 14) {
                                koefOs.setText("Коэф.=76");
                                normaRezylt.setText("Норма составляет: " + (int) ((76 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 15) {
                                koefOs.setText("Коэф.=74,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((74.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 16) {
                                koefOs.setText("Коэф.=73");
                                normaRezylt.setText("Норма составляет: " + (int) ((73 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 17) {
                                koefOs.setText("Коэф.=71,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((71.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 18) {
                                koefOs.setText("Коэф.=70,7");
                                normaRezylt.setText("Норма составляет: " + (int) ((70.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 19) {
                                koefOs.setText("Коэф.=69,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((69.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 20) {
                                koefOs.setText("Коэф.=68,9");
                                normaRezylt.setText("Норма составляет: " + (int) ((68.9 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 21) {
                                koefOs.setText("Коэф.=68,1");
                                normaRezylt.setText("Норма составляет: " + (int) ((68.1 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 22) {
                                koefOs.setText("Коэф.=67,4");
                                normaRezylt.setText("Норма составляет: " + (int) ((67.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 23) {
                                koefOs.setText("Коэф.=66,7");
                                normaRezylt.setText("Норма составляет: " + (int) ((66.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 24) {
                                koefOs.setText("Коэф.=65,3");
                                normaRezylt.setText("Норма составляет: " + (int) ((65.3 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 25) {
                                koefOs.setText("Коэф.=64,8");
                                normaRezylt.setText("Норма составляет: " + (int) ((64.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt >= 26) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком большой коэфф., проверьте введенные данные");
                            }
                        }

                        Toast.makeText(RaschetTerBelMgd.this, "РАСЧЕТ НОРМЫ ВЫПОЛНЕН", Toast.LENGTH_LONG).show();
                    } //onClick
                } // конец View.OnClickListener
        ); // btn_sdelat.setOnClickListener
    } // конец addListenerOnButton
}// конец activity

