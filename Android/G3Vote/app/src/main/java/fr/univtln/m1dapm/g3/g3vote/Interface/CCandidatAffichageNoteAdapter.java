package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVoixCandidat;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by Pierre on 04/06/2015.
 */
public class CCandidatAffichageNoteAdapter extends ArrayAdapter<CCandidate> {

    int mIdLayout;
    Context mContext;
    private List<String> mNotePossible;
    ArrayAdapter<CharSequence> mAdapter;
    private List<CVoixCandidat> mNoteCandidat;

    public CCandidatAffichageNoteAdapter(Context pContext,int pTextViewResourceId, List<CVoixCandidat> pNoteCandidat) {
        super(pContext, pTextViewResourceId);
        this.mContext = pContext;
        mIdLayout = R.layout.candidatnotelayout;
        mNoteCandidat=  pNoteCandidat;
    }

    public static class ViewHolderItem {
        TextView textViewName;
        TextView textViewDesc;
        Spinner SpinnerNote;
    }


    @Override
    public View getView(final int pPosition, View pConvertView, ViewGroup pParent) {
        ViewHolderItem viewHolder;
        if(pConvertView == null){
            // layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            pConvertView = inflater.inflate(mIdLayout, pParent, false);

            mNotePossible = new ArrayList<>();
            mNotePossible.add("Excellent");
            mNotePossible.add("Tres bien");
            mNotePossible.add("Bien");
            mNotePossible.add("Assez bien");
            mNotePossible.add("Insuffisant");
            mNotePossible.add("A rejeter");

            // ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.textViewName = (TextView) pConvertView.findViewById(R.id.NomCandidat);
            viewHolder.textViewDesc = (TextView) pConvertView.findViewById(R.id.DescriptionCandidat);
            viewHolder.SpinnerNote = (Spinner) pConvertView.findViewById(R.id.spinerNote);
            pConvertView.setTag(viewHolder);
            mAdapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.notepossible, android.R.layout.simple_spinner_item);
            mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            viewHolder.SpinnerNote.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    mNoteCandidat.get(pPosition).setMvote(5-position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }else{
            viewHolder = (ViewHolderItem) pConvertView.getTag();
        }



        CCandidate lCandidate = getItem(pPosition);
        if (lCandidate != null) {
            viewHolder.textViewName.setText(lCandidate.getNomCandidat());
            viewHolder.textViewDesc.setText(lCandidate.getDescriptionCandidat());
            viewHolder.SpinnerNote.setAdapter(mAdapter);
        }
        return pConvertView;
    }
}
