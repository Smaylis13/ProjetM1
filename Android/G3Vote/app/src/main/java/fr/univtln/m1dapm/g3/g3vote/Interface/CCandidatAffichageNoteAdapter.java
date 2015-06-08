package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    int midLayout;
    Context mcontext;
    private List<String> mnotepossible;
    ArrayAdapter<CharSequence> madapter;
    private List<CVoixCandidat> mNotecandidat;
    private String mnomcandidat;

    public CCandidatAffichageNoteAdapter(Context pcontext,int ptextViewResourceId,List<CVoixCandidat> pNotecandidat) {
        super(pcontext, ptextViewResourceId);
        this.mcontext = pcontext;
        midLayout = R.layout.candidatnotelayout;
        mNotecandidat=pNotecandidat;
    }




    public static class ViewHolderItem {
        TextView textViewName;
        TextView textViewDesc;
        Spinner Spinnernote;
    }


    @Override
    public View getView(final int pposition, View pconvertView, ViewGroup pparent) {
        ViewHolderItem viewHolder;
        if(pconvertView==null){
            // layout
            LayoutInflater inflater = ((Activity) mcontext).getLayoutInflater();
            pconvertView = inflater.inflate(midLayout, pparent, false);



            mnotepossible=new ArrayList<String>();
            mnotepossible.add("Excellent");
            mnotepossible.add("Tres bien");
            mnotepossible.add("Bien");
            mnotepossible.add("Assez bien");
            mnotepossible.add("Insuffisant");
            mnotepossible.add("A rejeter");

            // ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.textViewName = (TextView) pconvertView.findViewById(R.id.NomCandidat);
            viewHolder.textViewDesc = (TextView) pconvertView.findViewById(R.id.DescriptionCandidat);
            viewHolder.Spinnernote = (Spinner) pconvertView.findViewById(R.id.spinernote);
            pconvertView.setTag(viewHolder);
            madapter=ArrayAdapter.createFromResource(getContext(),
                    R.array.notepossible, android.R.layout.simple_spinner_item);
            madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            viewHolder.Spinnernote.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    mNotecandidat.get(pposition).setMvote(5-position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }else{
            viewHolder = (ViewHolderItem) pconvertView.getTag();
        }



        CCandidate lCandidate = getItem(pposition);
        if (lCandidate != null) {
            viewHolder.textViewName.setText(lCandidate.getNomCandidat());
            viewHolder.textViewDesc.setText(lCandidate.getDescriptionCandidat());
            viewHolder.Spinnernote.setAdapter(madapter);
                    }
        return pconvertView;
    }
}
