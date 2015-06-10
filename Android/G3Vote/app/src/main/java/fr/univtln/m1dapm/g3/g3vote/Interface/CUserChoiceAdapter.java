package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 29/05/15.
 */
public class CUserChoiceAdapter extends BaseAdapter {
    //A list of Users
    private List<CUser> mListUser;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    //constructeur
    public CUserChoiceAdapter(Context context, List<CUser> listUser){
        this.mContext = context;
        this.mListUser = listUser;
        this.mInflater = LayoutInflater.from(mContext);
    }
    //number of items in the list
    @Override
    public int getCount() {
        return mListUser.size();
    }


    @Override
    public Object getItem(int position) {
        return mListUser.get(position);
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
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.user_choice_layout, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        if (position%2==0){
            layoutItem.setBackgroundColor(-7829368);//gris clair
        }
        //(2) : Récupération des TextView de notre layout
        /*TextView tv_Nom = (TextView)layoutItem.findViewById(R.id.TV_Nom);
        TextView tv_Prenom = (TextView)layoutItem.findViewById(R.id.TV_Prenom);
        TextView tv_Mail = (TextView)layoutItem.findViewById(R.id.TV_Mail);

        //(3) : Renseignement des valeurs
        tv_Nom.setText(mListUser.get(position).getName());
        tv_Prenom.setText(mListUser.get(position).getFirstName());
        tv_Mail.setText(mListUser.get(position).getEmail());
        */

        final CheckedTextView ctv_mail = (CheckedTextView)layoutItem.findViewById(R.id.participantChoiceCTV);
        ctv_mail.setText(mListUser.get(position).getEmail());


        ctv_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctv_mail.isChecked())
                    ctv_mail.setChecked(false);
                else
                    ctv_mail.setChecked(true);
            }
        });
        //On retourne l'item créé.
        return layoutItem;
    }
}