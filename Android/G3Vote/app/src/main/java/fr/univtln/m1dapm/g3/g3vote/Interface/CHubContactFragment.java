package fr.univtln.m1dapm.g3.g3vote.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.m1dapm.g3.g3vote.Entite.CCandidate;
import fr.univtln.m1dapm.g3.g3vote.Entite.CUser;
import fr.univtln.m1dapm.g3.g3vote.Entite.CVote;
import fr.univtln.m1dapm.g3.g3vote.R;

/**
 * Created by chris on 15/05/15.
 */
public class CHubContactFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static List<CUser> sContacts=new ArrayList<>();
    private CUserAdapter adapter;

    public static List<CUser> getsContacts() {
        return sContacts;
    }

    public static void setsContacts(List<CUser> pUsers) {
        sContacts.clear();
        for (CUser lUser:pUsers) {
            sContacts.add(lUser);
        }
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CHubContactFragment newInstance(int sectionNumber) {
        CHubContactFragment fragment = new CHubContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CHubContactFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_chub_contact, container, false);

        //Récupération du composant ListView
        ListView list = (ListView)rootView.findViewById(R.id.ListContact);

        //Récupération de la liste des personnes
        ArrayList<CUser> lContacts = CUser.getAListOfUser();

        //Création et initialisation de l'Adapter pour les personnes
        adapter = new CUserAdapter(rootView.getContext(), sContacts);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}