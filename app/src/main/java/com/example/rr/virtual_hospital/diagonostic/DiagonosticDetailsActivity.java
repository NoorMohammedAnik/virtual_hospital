package com.example.rr.virtual_hospital.diagonostic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rr.virtual_hospital.R;
import com.example.rr.virtual_hospital.info.model.Diagonostics;

public class DiagonosticDetailsActivity extends AppCompatActivity {
    ImageView place_img;
    TextView place_name,place_desc,place_location,place_contactNumbertext,place_division;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_details);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Diagnostics Details List");


        place_img = (ImageView) findViewById(R.id.place_img);
        place_name = (TextView) findViewById(R.id.dianame);
        place_desc = (TextView) findViewById(R.id.diaDescrptn);
        place_location = (TextView) findViewById(R.id.LoctText);
        place_contactNumbertext = (TextView) findViewById(R.id.diaNumber);
        place_division= (TextView) findViewById(R.id.diag_division);

        Diagonostics diagName = getIntent().getExtras().getParcelable("docName");



            place_img.setImageResource(R.drawable.firstaiddet);

        if(!diagName.getDiag_title().isEmpty()){
            place_name.setText(diagName.getDiag_title());
        }else{
            place_name.setText("Not Available");
        }
        if(!diagName.getDiag_facilities().isEmpty()){
            place_desc.setText(diagName.getDiag_facilities());
        }else{
            place_desc.setText("Not Available");
        }
        if(!diagName.getDiag_division().isEmpty()){
            place_division.setText(diagName.getDiag_division());
        }else{
            place_division.setText("Not Available");
        }
        if(!diagName.getDiag_location().isEmpty()){
            place_location.setText(diagName.getDiag_location());
        }else{
            place_location.setText("Not Available");
        }
        if(!diagName.getDiac_contact().isEmpty()){
            place_contactNumbertext.setText(diagName.getDiac_contact());
        }else{
            place_contactNumbertext.setText("Not Available");
        }




/*
        if (diagName.equals("BANGLADESH DIAGNOSTIC ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("106/A, K.B. Fazlul Quader Road, Chittagong ");
            place_contactNumbertext.setText("031-653010.");


        }


        if (diagName.equals("BIOPATH LABORATORY")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("84, Jamal Khan Road, Chittagong");
            place_contactNumbertext.setText("031-618533");


        }

        if (diagName.equals("BASIC LAB")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("20, K.B. Fazlul Kader Road, Chittagong ");
            place_contactNumbertext.setText("031-652652");


        }

        if (diagName.equals("BIOCHEMISTRY LAB")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("106/A, K.B. Fazlul Quader Road, Chittagong ");
            place_contactNumbertext.setText("0607 4304303 ");


        }

        if (diagName.equals("CARE INVESTIGATION ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("106/A, K.B. Fazlul Quader Road, Chittagong");
            place_contactNumbertext.setText("031-651522,031-2550253");


        }
        if (diagName.equals("CENTRAL LAB ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("417, M. M. Ali Road, Dampara Police Line,\n" +
                    "Chittagong ");
            place_contactNumbertext.setText("0191883053 ");


        }
        if (diagName.equals("CHATTAGRAM METROPOLITAN HOSPITAL (PVT) LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("487/B, O.R. Nizam Road, Chittagong ");
            place_contactNumbertext.setText("031 620634.Pabx 031 654732, 031 655791, 031 651242, Ext 104/114/115.\n" +
                    "07. ");


        }

        if (diagName.equals("CHECK UP MEDICAL SERVICES LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("132, Panchlaish R/A, Chittagong");
            place_contactNumbertext.setText("031 655219, 031 657919, 031 657920");


        }

        if (diagName.equals("CHEVRON CLINICAL LABORATORY (PVT.) LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("12/12, O. R. Nizam Road. Panchlaish,\n" +
                    "Chittagong ");
            place_contactNumbertext.setText("031 652963, 031 652860, 031 652533. ");


        }
        if (diagName.equals("CHITTAGONG BELLEVUE LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("1530/A, O.R. Nizam Road. & 79/A, Jamal Khan Road, Chittagong ");
            place_contactNumbertext.setText("031-651590,031-652378,031-635018, 031-623310.");


        }
        if (diagName.equals("CHITTAGONG DOCTORS LAB (PVT.) LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("131, K.B. Fazlul Quader Road, Chittagong ");
            place_contactNumbertext.setText(" 031-650868, 031-650869. ");


        }
        if (diagName.equals("CHITTAGONG LAB LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("20. K.B. Fazlul Kader Road. Panchlaish. Chittagong ");
            place_contactNumbertext.setText("031-653135, 031-650702");


        }
        if (diagName.equals("CHITTAGONG POLY CLINIC (PVT) LTD")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("33, Panchlaish R/A, Chittagong ");
            place_contactNumbertext.setText("031-656041,031-657345, 031-650911");


        }
        if (diagName.equals("CITY HEALTH CLINIC ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("Chattashari Road, Chittagong ");
            place_contactNumbertext.setText("031 619773, 031 619820");


        }
        if (diagName.equals("CRESCENT DIAGNOSIS ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("57, K.B. Fazlul Quader Road, Chittagong ");
            place_contactNumbertext.setText("031 654185, 031 656562");


        }
        if (diagName.equals("C.T. IMAGING & DIAGNOSTIC CENTRE LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("50, K.B. Fazlul Quader Road, Chittagong");
            place_contactNumbertext.setText(" 031 652342. ");


        }
        if (diagName.equals("CHITTAGONG METROPOLITAN HASPATAL DIAGNOSTIC LABORATORY")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("487/B, O.R. Nizam Road, Chittagong ");
            place_contactNumbertext.setText(" 031-620634, 031-652750, 031-654732, 031-651242");


        }
        if (diagName.equals("DIASONIC DIAGNOSTIC CENTRE")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("Minhaz Complex. 12, Jamal Khan Road,\n" +
                    "Chittagong ");
            place_contactNumbertext.setText("031-632980,031-620323 ");


        }
        if (diagName.equals("DIVINE DIAGNOSTIC LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("65, Jamal Khan Road, Chittagong ");
            place_contactNumbertext.setText("031 639757 ");


        }
        if (diagName.equals("DR. MAHFUZUR RAHMAN'S LAB ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("58, Chatteswari Road. Chawkbazar. Chittagong ");
            place_contactNumbertext.setText("031-619057, 01819339286");


        }
        if (diagName.equals("DR. MUSH DENTAL CLINIC ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("40, Momin Road, Kadammobarak");
            place_contactNumbertext.setText("031-634335");


        }
        if (diagName.equals("EAGLES EYE DIAGNOSTIC ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("94, K. B. Fazlul Kader Road. Chawk Bazar, Chittagong");
            place_contactNumbertext.setText("031 658911, 031 657655");


        }
        if (diagName.equals("EVERGREEN CLINIC (PVT) LTD. ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("23/A. M.M. Ali Road.Mahdibag,Chittagong");
            place_contactNumbertext.setText("031 620217. 031 623687");


        }
        if (diagName.equals("HEALTH HOME PVT. LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("53, Panchlaish RIA, Chittagong ");
            place_contactNumbertext.setText("031-651568,031-651571");


        }
        if (diagName.equals("HEALTH SENSE DIAGNOSTIC CENTRE")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("14, Panchlaish RlA. Chittagong ");
            place_contactNumbertext.setText("031-656592, 031-653913");


        }
        if (diagName.equals("KIDNEY DIAGNOSTIC COMPLEX ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("100/101, Jame Maszid Market, Anderkilla, Chittagong");
            place_contactNumbertext.setText(" 01819 801753");


        }
        if (diagName.equals("LAB EXPERTS (PVT) LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("83, Jamal Khan Road, Chittagong ");
            place_contactNumbertext.setText("031-616899 ");


        }
        if (diagName.equals("LANCET ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("106/B, K. B. Fazlul Kader Road, Panchlaish,\n" +
                    "Chittagong ");
            place_contactNumbertext.setText("031-657319, 0189395116 ");


        }
        if (diagName.equals("LIFE CARE CENTRE ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("129, Panchlaish R/A, Chittagong");
            place_contactNumbertext.setText("031 654588, 031 654468");


        }
        if (diagName.equals("MAGNUM DIAGNOSTIC COMPLEX ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("39, Momin Road, Chittagong ");
            place_contactNumbertext.setText("031 617060, 031 613117");


        }
        if (diagName.equals("MAHANAGAR CLINIC (PVT) LTD.")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("20, O.R. Nizam Road. Panchlaish R/A, Chittagong");
            place_contactNumbertext.setText("031 650928, 031 650457");


        }
        if (diagName.equals("MEDI AID COMPLEX (PVT) LTD")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("20, K.B. Fazlul Kader Road Chittagong ");
            place_contactNumbertext.setText("031 654046, 031 654068.");


        }
        if (diagName.equals("MEDICAL CENTRE (PVT) CLINIC ")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("953, O.R. Nizam Road\n" +
                    "Chittagong ");
            place_contactNumbertext.setText("031-651054,651944, 658501-04");


        }
        if (diagName.equals("MEDICAL POINT")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("19, K.B. Fazlul Kader Road, Chittagong ");
            place_contactNumbertext.setText("031 650906, 031 652769, ");


        }
        if (diagName.equals("METRO DIAGNOSTI CENTER LIMITED")) {

            place_img.setImageResource(R.drawable.firstaiddet);
            place_name.setText(diagName);
            place_desc.setText("Diagnostic Services; \n" +
                    "Clinic; \n" +
                    "Cardiac Imaging Services; \n" +
                    "Gastroenterology Service; \n" +
                    "Laboratory Services; \n" +
                    "Pulmonology Service; \n" +
                    "Radiology and Imaging; ");
            place_location.setText("\n" +
                    "Gol Pahar Moor Chittagong,mehdibagh\n" +
                    "Chittagong\n");
            place_contactNumbertext.setText("031-2850630\n" +
                    "031-2850631\n" +
                    "031-2851677");


        }
*/
    }



    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
