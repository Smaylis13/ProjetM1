/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.R;

public class CStableArrayAdapter extends BaseAdapter {

    final int INVALID_ID = -1;
    //A list of Users
    private List<CCandidate> mListCandidate;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    HashMap<CCandidate, Integer> mIdMap = new HashMap<>();

    public CStableArrayAdapter(Context pContext, List<CCandidate> pListUser) {
        this.mContext = pContext;
        this.mListCandidate = pListUser;
        this.mInflater = LayoutInflater.from(mContext);

        for (int i = 0; i < pListUser.size(); ++i) {
            mIdMap.put(pListUser.get(i), i);
        }
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
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        CCandidate item = (CCandidate) getItem(position);
        return item.getIdCandidat();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
}