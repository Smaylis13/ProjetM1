package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by Pierre on 28/05/2015.
 */
public class CCandidatAffichageAdapter extends ArrayAdapter<CCandidate> {

    int midLayout;
    Context mcontext;


    public CCandidatAffichageAdapter(Context pcontext,int ptextViewResourceId) {
        super(pcontext,ptextViewResourceId);
        this.mcontext = pcontext;
        midLayout = R.layout.candidat_layout;
    }




    public static class ViewHolderItem {
        TextView textViewName;
        TextView textViewDesc;
        ImageView imageView;
    }


    @Override
    public View getView(int pposition, View pconvertView, ViewGroup pparent) {
        ViewHolderItem viewHolder;
        if(pconvertView==null){
            // layout
            LayoutInflater inflater = ((Activity) mcontext).getLayoutInflater();
            pconvertView = inflater.inflate(midLayout, pparent, false);

            // ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.textViewName = (TextView) pconvertView.findViewById(R.id.NomCandidat);
            viewHolder.textViewDesc = (TextView) pconvertView.findViewById(R.id.DescriptionCandidat);
            pconvertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderItem) pconvertView.getTag();
        }

        CCandidate lCandidate = getItem(pposition);
        if (lCandidate != null) {
            viewHolder.textViewName.setText(lCandidate.getNomCandidat());
            viewHolder.textViewDesc.setText(lCandidate.getDescriptionCandidat());
        }
        return pconvertView;
    }
}
