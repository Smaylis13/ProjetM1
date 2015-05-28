package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 15/05/15.
 */
public class CDateEndPickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // TODO: stock the date to be send to the server at the end of the vote creation
        //change the button so it shows the selected date
        final Button button = (Button) getActivity().findViewById(R.id.bVoteDateEnd);
        //dans le calendrier les mois vont de 0 a 11 il faut ajouter 1 pour avoir le bon affichage
        String date = day+"/"+(month+1)+"/"+year;
        button.setText(date);
    }


}