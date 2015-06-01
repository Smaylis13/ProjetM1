package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 01/06/15.
 */
public class CCandidateUniqueChoiceAdapter extends BaseAdapter {
    //A list of Users
    private List<CCandidate> mListCandidate;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    //constructeur
    public CCandidateUniqueChoiceAdapter(Context context, List<CCandidate> listUser){
        this.mContext = context;
        this.mListCandidate = listUser;
        this.mInflater = LayoutInflater.from(mContext);
    }
    //number of items in the list
    @Override
    public int getCount() {
        return mListCandidate.size();
    }


    @Override
    public Object getItem(int position) {
        return mListCandidate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;
        //re-use of layout
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.candidate_uninominal_choice, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        if (position%2==0){
            layoutItem.setBackgroundColor(-7829368);//gris clair
        }
        //(2) : Récupération des TextView de notre layout
        TextView tv_CandidateName = (TextView)layoutItem.findViewById(R.id.TV_CandidateName);
        TextView tv_CandidateDescription = (TextView)layoutItem.findViewById(R.id.TV_CandidateDesctiption);

        //(3) : Renseignement des valeurs
        tv_CandidateName.setText(mListCandidate.get(position).getNomCandidat());
        tv_CandidateDescription.setText(mListCandidate.get(position).getDescriptionCandidat());

        //On retourne l'item créé.
        return layoutItem;
    }
}
