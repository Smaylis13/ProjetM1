package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidat;
import fr.univtln.m1dapm.g3.g3vote.Entite.CChoix;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 20/05/15.
 */
public class CCandidatAdapter extends BaseAdapter {
    //le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;
    //A list of Choices
    private List<CCandidat> mListCandidat;

    public CCandidatAdapter(Context context, List<CCandidat> listCandidat){
        this.mContext=context;
        this.mListCandidat=listCandidat;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public List<CCandidat> getmListCandidat() {
        return mListCandidat;
    }


    @Override
    public int getCount() {
        return mListCandidat.size();
    }

    @Override
    public Object getItem(int position) {
        return mListCandidat.get(position);
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
            layoutItem = (RelativeLayout) mInflater.inflate(R.layout.choice_layout, parent, false);
        } else {
            layoutItem = (RelativeLayout) convertView;
        }

        EditText lET_choice = (EditText)layoutItem.findViewById(R.id.choiceName);
        ImageButton lib_addChoiceButton = (ImageButton)layoutItem.findViewById(R.id.addChoiceButton);
        ImageButton lib_removeChoiceButton = (ImageButton)layoutItem.findViewById(R.id.removeChoiceButton);

        lET_choice.setHint(R.string.lETChoiceHint);
        lib_addChoiceButton.setImageResource(R.mipmap.ic_add_button);
        lib_removeChoiceButton.setImageResource(R.mipmap.ic_remove_button);

        //ajout de listener aux boutons pour ajouter une ligne ou
        //enlever la ligne du bouton remove cliquer
        lib_addChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CCandidat lCCandidat = new CCandidat();
                mListCandidat.add(lCCandidat);
                notifyDataSetChanged();
            }
        });
        lib_removeChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListCandidat.size()>2) {
                    mListCandidat.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        return layoutItem;
    }
}
