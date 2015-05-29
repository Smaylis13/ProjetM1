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

import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
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
    private List<CCandidate> mListCandidat;

    public CCandidatAdapter(Context context, List<CCandidate> listCandidat){
        this.mContext=context;
        this.mListCandidat=listCandidat;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public List<CCandidate> getmListCandidat() {
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

        final EditText lET_choice = (EditText)layoutItem.findViewById(R.id.choiceName);
        lET_choice.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                mListCandidat.get(position).setNomCandidat(lET_choice.getText().toString());
            }

            public void afterTextChanged(Editable editable)
            {

            }
        });
        final EditText lET_Description = (EditText)layoutItem.findViewById(R.id.choiceDescription);
        //ImageButton lib_addChoiceButton = (ImageButton)layoutItem.findViewById(R.id.addChoiceButton);
        //ImageButton lib_removeChoiceButton = (ImageButton)layoutItem.findViewById(R.id.removeChoiceButton);
        lET_Description.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                mListCandidat.get(position).setDescriptionCandidat(lET_Description.getText().toString());
            }

            public void afterTextChanged(Editable editable)
            {

            }
        });

        lET_choice.setHint(R.string.lETChoiceHint);
        lET_Description.setHint(R.string.lETDescriptionHint);


        //lib_addChoiceButton.setImageResource(R.mipmap.ic_add_button);
        //lib_removeChoiceButton.setImageResource(R.mipmap.ic_remove_button);

        //ajout de listener aux boutons pour ajouter une ligne ou
        //enlever la ligne du bouton remove cliquer
        /*lib_addChoiceButton.setOnClickListener(new View.OnClickListener() {
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
        });*/

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


    /*public ArrayList<CCandidat> getListOfCandidat(View view){
        int taille = getCount();
        CCandidat candidat = new CCandidat();
        ArrayList<CCandidat> listCandidat = new ArrayList<CCandidat>();
        for (int i = 0; i < taille ; i++) {
            String selected = ((TextView)view.findViewById(R.id.choiceName)).getText().toString();
            Log.i("contenue", selected);
            candidat.setNom(selected);
            listCandidat.add(candidat);
        }
        return listCandidat;
    }*/
}
