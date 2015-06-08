package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by sebastien on 08/06/15.
 */
public class CResultMultipleCandidatAdapter extends BaseAdapter{
    //le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;
    //A list of Choices
    private List<CCandidate> mListCandidate;

    public CResultMultipleCandidatAdapter(Context context, List<CCandidate> listCandidat){
        this.mContext = context;
        this.mListCandidate = listCandidat;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public List<CCandidate> getmListCandidat() {
        return mListCandidate;
    }


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
    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layoutItem;
        //re-use of layout
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.candidat_layout, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        if (position%2==0){
            layoutItem.setBackgroundColor(-7829368);//gris clair
        }
        //(2) : Récupération des TextView de notre layout
        TextView tv_CandidateName = (TextView)layoutItem.findViewById(R.id.NomCandidat);
        TextView tv_CandidateDescription = (TextView)layoutItem.findViewById(R.id.DescriptionCandidat);

        //(3) : Renseignement des valeurs
        tv_CandidateName.setText(mListCandidate.get(position).getNomCandidat());
        tv_CandidateDescription.setText(mListCandidate.get(position).getDescriptionCandidat());

        //On retourne l'item créé.
        return layoutItem;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
}
