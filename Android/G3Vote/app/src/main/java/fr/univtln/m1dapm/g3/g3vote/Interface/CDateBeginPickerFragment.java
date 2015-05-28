package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 15/05/15.
 */
public class CDateBeginPickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance(Locale.FRANCE);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Log.i("date",c.toString());
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // TODO: stock the date to be send to the server at the end of the vote creation

        //change the button so it shows the selected date
        final Button button = (Button) getActivity().findViewById(R.id.bVoteDateBegin);
        //dans le calendrier les mois vont de 0 a 11 il faut ajouter 1 pour avoir le bon affichage
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        GregorianCalendar cal=new GregorianCalendar();
        cal.set(year, month, day);

        String date = dateFormat.format(cal.getTime());
        button.setText(date);
    }


}
