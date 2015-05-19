package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 15/05/15.
 */
public class CHubMyVotesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CHubMyVotesFragment newInstance(int sectionNumber) {
        CHubMyVotesFragment fragment = new CHubMyVotesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CHubMyVotesFragment() {
    }

    //TODO: add 2 tabs one "Participant" and one "Admin"
    //TODO: create 2 ListView that will be displayed in each tab showing the corresponding vote
    //TODO: create an adapter that will pull content from a DB Query to gather the votes of the user
    //TODO: Fill the ListViews with the adapter
    //TODO: make every element of the ListView clikable, then add interface to handle the votes
    //TODO: add a button in the action bar that will refresh the content of the ListViews

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chub_myvote, container, false);
        //Récupération du composant ListView
        ListView list = (ListView)rootView.findViewById(R.id.lListViewMyVote);

        //Récupération de la liste des personnes
        ArrayList<CVote> listVote = CVote.getAListOfVote();

        //Création et initialisation de l'Adapter pour les personnes
        CVoteAdapter adapter = new CVoteAdapter(rootView.getContext(), listVote);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);
        return rootView;
    }
}
