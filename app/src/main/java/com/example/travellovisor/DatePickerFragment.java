package com.example.travellovisor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    EditText editDate;
    private final String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    boolean spin=false;


    DatePickerFragment(EditText editDate, boolean spin){
        this.editDate=editDate;
        this.spin=spin;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),(spin)? R.style.DialogThemeSpin:R.style.DialogThemeCal,this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        editDate.setText(""+day+"-"+months[month]+"-"+year);
    }


}
