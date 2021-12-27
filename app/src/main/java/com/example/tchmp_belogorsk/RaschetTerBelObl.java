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


public class RaschetTerBelObl extends AppCompatActivity {

    private EditText numVes, numOsi, vvodStoianok, vvodKolvaOgr, vvodgorprost;
    private TextView nagrOs, koefOs, tv_peregon, normaRezylt, normaSost;
    private Spinner st_otpr, st_prib, kolvo_Sek;

    private static String[] Stations_list = {"Сковородино", "Б.Невер", "Ковали", "Ульручьи", "Ангарич", "Джиктанда", "Талдан", "Гудачи", "Гонжа", "Нюкжа", "Магдагачи", "Красная Падь", "Дактуй", "Тыгда", "Чалганы", "Ушумун", "Сиваки", "Ту", "Мухинская", "Берея", "Шимановск", "Селеткан", "Ледяная", "Усть Пера", "Свободный", "Мих.-Чеснок.", "Арга", "Серышево", "Украина", "Белогорск-1", "Белогорск-2", "Возжаевка", "Поздеевка", "Екатеринославка", "Тур", "Завитая", "Тюкан", "Бурея", "Домикан", "Архара", "Татакан", "К.-Карьер", "Богучан", "Рачи", "Урил", "Тарманчукан", "Тоннельный", "Кундур", "Казачий", "Ядрин", "Облучье"};
    private static String[] kol_vo_Sek = {"Э5к","2ЭС5к","3ЭС5к","2х2ЭС5к"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raschet_ter_bel_obl);
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

                        if (TextUtils.isEmpty(vvodStoianok.getText().toString())) {
                            vvodStoianok.setText("0");
                        }
                        if (TextUtils.isEmpty(vvodKolvaOgr.getText().toString())) {
                            vvodKolvaOgr.setText("0");
                        }
                        if (TextUtils.isEmpty(vvodgorprost.getText().toString())) {
                            vvodgorprost.setText("0");
                        }


                        double VesPoezda = Double.parseDouble(numVes.getText().toString()); // из переменной NumVes берется текст в строку и присваивается VesPoezda
                        double OsPoezda = Double.parseDouble(numOsi.getText().toString());// из переменной numOsi берется текст в строку и присваивается OsPoezda
                        int kolvoStoianok = Integer.parseInt(vvodStoianok.getText().toString());
                        int kolvoOgr = Integer.parseInt(vvodKolvaOgr.getText().toString());
                        double gorProst_T = Double.parseDouble(vvodgorprost.getText().toString());
                        int kol_voSek = 0;
                        if (OsPoezda == 0) {
                            Toast.makeText(RaschetTerBelObl.this, "ВВЕДИТЕ КОРРЕКТНОЕ КОЛ-ВО ОСЕЙ!", Toast.LENGTH_LONG).show();
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

                        if (st_otprSt.equals(Stations_list[29])) { st_otpr_km = 7866; }
                        if (st_otprSt.equals(Stations_list[30])) { st_otpr_km = 7873; }
                        if (st_otprSt.equals(Stations_list[31])) { st_otpr_km = 7893; }
                        if (st_otprSt.equals(Stations_list[32])) { st_otpr_km = 7914; }
                        if (st_otprSt.equals(Stations_list[33])) { st_otpr_km = 7944; }
                        if (st_otprSt.equals(Stations_list[34])) { st_otpr_km = 7974; }
                        if (st_otprSt.equals(Stations_list[35])) { st_otpr_km = 7985; }
                        if (st_otprSt.equals(Stations_list[36])) { st_otpr_km = 8008; }
                        if (st_otprSt.equals(Stations_list[37])) { st_otpr_km = 8030; }
                        if (st_otprSt.equals(Stations_list[38])) { st_otpr_km = 8051; }
                        if (st_otprSt.equals(Stations_list[39])) { st_otpr_km = 8080; }
                        if (st_otprSt.equals(Stations_list[40])) { st_otpr_km = 8091; }
                        if (st_otprSt.equals(Stations_list[42])) { st_otpr_km = 8101; }
                        if (st_otprSt.equals(Stations_list[44])) { st_otpr_km = 8121; }
                        if (st_otprSt.equals(Stations_list[45])) { st_otpr_km = 8137; }
                        if (st_otprSt.equals(Stations_list[47])) { st_otpr_km = 8155; }
                        if (st_otprSt.equals(Stations_list[48])) { st_otpr_km = 8164; }
                        if (st_otprSt.equals(Stations_list[49])) { st_otpr_km = 8184; }
                        if (st_otprSt.equals(Stations_list[50])) { st_otpr_km = 8191; }

                        if (st_pribSt.equals(Stations_list[29])) { st_prib_km = 7866; }
                        if (st_pribSt.equals(Stations_list[30])) { st_prib_km = 7873; }
                        if (st_pribSt.equals(Stations_list[31])) { st_prib_km = 7893; }
                        if (st_pribSt.equals(Stations_list[32])) { st_prib_km = 7914; }
                        if (st_pribSt.equals(Stations_list[33])) { st_prib_km = 7944; }
                        if (st_pribSt.equals(Stations_list[34])) { st_prib_km = 7974; }
                        if (st_pribSt.equals(Stations_list[35])) { st_prib_km = 7985; }
                        if (st_pribSt.equals(Stations_list[36])) { st_prib_km = 8008; }
                        if (st_pribSt.equals(Stations_list[37])) { st_prib_km = 8030; }
                        if (st_pribSt.equals(Stations_list[38])) { st_prib_km = 8051; }
                        if (st_pribSt.equals(Stations_list[39])) { st_prib_km = 8080; }
                        if (st_pribSt.equals(Stations_list[40])) { st_prib_km = 8091; }
                        if (st_pribSt.equals(Stations_list[42])) { st_prib_km = 8101; }
                        if (st_pribSt.equals(Stations_list[44])) { st_prib_km = 8121; }
                        if (st_pribSt.equals(Stations_list[45])) { st_prib_km = 8137; }
                        if (st_pribSt.equals(Stations_list[47])) { st_prib_km = 8155; }
                        if (st_pribSt.equals(Stations_list[48])) { st_prib_km = 8164; }
                        if (st_pribSt.equals(Stations_list[49])) { st_prib_km = 8184; }
                        if (st_pribSt.equals(Stations_list[50])) { st_prib_km = 8191; }

                        int st_rez_km = st_otpr_km - st_prib_km;

                        tv_peregon.setText(st_otprSt +" "+ st_otpr_km +" - " + st_pribSt + " " + st_prib_km +" = " + st_rez_km + " км");

                        if (st_rez_km == 0) {
                            Toast.makeText(RaschetTerBelObl.this, "ПРОВЕРЬТЕ УЧАСТОК", Toast.LENGTH_LONG).show();
                            return;
                        } // проверка перегона

                        if (st_rez_km > 0) { // НЕЧЕТНЫЙ
                            if (NagrNaOsInt <= 4) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком маленький коэфф., проверьте введенные данные");
                            }
                            if (NagrNaOsInt == 5) {
                                koefOs.setText("Коэф.=134");
                                normaRezylt.setText("Норма составляет: " + (int) ((134 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr + gorProstSum) +" кВт");
                            }
                            if (NagrNaOsInt == 6) {
                                koefOs.setText("Коэф.=120.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((120.5 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 7) {
                                koefOs.setText("Коэф.=110.9");
                                normaRezylt.setText("Норма составляет: " + (int) ((110.9 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 8) {
                                koefOs.setText("Коэф.=103.8");
                                normaRezylt.setText("Норма составляет: " + (int) ((103.8 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 9) {
                                koefOs.setText("Коэф.=98.3");
                                normaRezylt.setText("Норма составляет: " + (int) ((98.3 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 10) {
                                koefOs.setText("Коэф.=93.2");
                                normaRezylt.setText("Норма составляет: " + (int) ((93.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 11) {
                                koefOs.setText("Коэф.=90.3");
                                normaRezylt.setText("Норма составляет: " + (int) ((90.3 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 12) {
                                koefOs.setText("Коэф.=87.2");
                                normaRezylt.setText("Норма составляет: " + (int) ((87.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 13) {
                                koefOs.setText("Коэф.=84.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((84.7 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 14) {
                                koefOs.setText("Коэф.=82.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((82.5 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 15) {
                                koefOs.setText("Коэф.=80.6");
                                normaRezylt.setText("Норма составляет: " + (int) ((80.6 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 16) {
                                koefOs.setText("Коэф.=78.9");
                                normaRezylt.setText("Норма составляет: " + (int) ((78.9 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 17) {
                                koefOs.setText("Коэф.=75.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((75.5 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 18) {
                                koefOs.setText("Коэф.=76.2");
                                normaRezylt.setText("Норма составляет: " + (int) ((76.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 19) {
                                koefOs.setText("Коэф.=75");
                                normaRezylt.setText("Норма составляет: " + (int) ((75 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 20) {
                                koefOs.setText("Коэф.=74.1");
                                normaRezylt.setText("Норма составляет: " + (int) ((74.1 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 21) {
                                koefOs.setText("Коэф.=73");
                                normaRezylt.setText("Норма составляет: " + (int) ((73 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 22) {
                                koefOs.setText("Коэф.=72.2");
                                normaRezylt.setText("Норма составляет: " + (int) ((72.2 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 23) {
                                koefOs.setText("Коэф.=71.4");
                                normaRezylt.setText("Норма составляет: " + (int) ((71.4 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 24) {
                                koefOs.setText("Коэф.=70.6");
                                normaRezylt.setText("Норма составляет: " + (int) ((70.6 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 25) {
                                koefOs.setText("Коэф.=70");
                                normaRezylt.setText("Норма составляет: " + (int) ((70 * st_rez_km * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
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
                                koefOs.setText("Коэф.=128.6");
                                normaRezylt.setText("Норма составляет: " + (int) ((128.6 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 6) {
                                koefOs.setText("Коэф.=116.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((116.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 7) {
                                koefOs.setText("Коэф.=108.1");
                                normaRezylt.setText("Норма составляет: " + (int) ((108.1 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 8) {
                                koefOs.setText("Коэф.=101.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((101.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 9) {
                                koefOs.setText("Коэф.=96.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((96.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 10) {
                                koefOs.setText("Коэф.=92.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((92.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 11) {
                                koefOs.setText("Коэф.=89.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((89.5 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 12) {
                                koefOs.setText("Коэф.=86.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((86.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 13) {
                                koefOs.setText("Коэф.=84.4");
                                normaRezylt.setText("Норма составляет: " + (int) ((84.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 14) {
                                koefOs.setText("Коэф.=82.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((82.5 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 15) {
                                koefOs.setText("Коэф.=80.8");
                                normaRezylt.setText("Норма составляет: " + (int) ((80.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 16) {
                                koefOs.setText("Коэф.=79.3");
                                normaRezylt.setText("Норма составляет: " + (int) ((79.3 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 17) {
                                koefOs.setText("Коэф.=77.9");
                                normaRezylt.setText("Норма составляет: " + (int) ((77.9 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 18) {
                                koefOs.setText("Коэф.=76.8");
                                normaRezylt.setText("Норма составляет: " + (int) ((76.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 19) {
                                koefOs.setText("Коэф.=81.5");
                                normaRezylt.setText("Норма составляет: " + (int) ((81.5 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 20) {
                                koefOs.setText("Коэф.=75.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((75.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 21) {
                                koefOs.setText("Коэф.=74.7");
                                normaRezylt.setText("Норма составляет: " + (int) ((74.7 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 22) {
                                koefOs.setText("Коэф.=73.1");
                                normaRezylt.setText("Норма составляет: " + (int) ((73.1 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 23) {
                                koefOs.setText("Коэф.=72.4");
                                normaRezylt.setText("Норма составляет: " + (int) ((72.4 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 24) {
                                koefOs.setText("Коэф.=71.8");
                                normaRezylt.setText("Норма составляет: " + (int) ((71.8 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt == 25) {
                                koefOs.setText("Коэф.=71.2");
                                normaRezylt.setText("Норма составляет: " + (int) ((71.2 * Math.abs(st_rez_km) * VesPoezda) / 10000 + vziatSmesta + 30 * kolvoOgr+ gorProstSum) + " кВт");
                            }
                            if (NagrNaOsInt >= 26) {
                                koefOs.setText("Еррор");
                                normaSost.setText("");
                                normaRezylt.setText("Слишком большой коэфф., проверьте введенные данные");
                            }
                        }

                        Toast.makeText(RaschetTerBelObl.this, "РАСЧЕТ НОРМЫ ВЫПОЛНЕН", Toast.LENGTH_LONG).show();
                    } //onClick
                } // конец View.OnClickListener
        ); // btn_sdelat.setOnClickListener
    } // конец addListenerOnButton
}// конец main_activity

