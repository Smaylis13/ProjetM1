package fr.univtln.m1dapm.g3.g3vote.Interface;

/**
 * Created by chris on 15/05/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import fr.univtln.m1dapm.g3.g3vote.R;

public class CHubCreateVoteFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String[] VOTE = new String[] {"STV", "Kemeny-Young", "Jugement Majoritaire",
    "uninominal à 1 tour","uninominal à 2 tour","Condorcet","Borda"};
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CHubCreateVoteFragment newInstance(int sectionNumber) {
        CHubCreateVoteFragment fragment = new CHubCreateVoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CHubCreateVoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chub_createvote, container, false);
        //create listVote and fill it
        Spinner spin = (Spinner) rootView.findViewById(R.id.voteTypeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, VOTE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        final TextView text = (TextView) rootView.findViewById(R.id.describeVoteType);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //onItemSelected(AdapterView<?> parent, View view, int position, long id)
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                //TODO: add better descriptions to the votes types
                switch (position)
                {
                    case 0:
                        text.setText(getString(R.string.stvDescription));
                        break;
                    case 1:
                        text.setText(getString(R.string.kemenyYoungDescription));
                        break;
                    case 2:
                        text.setText(getString(R.string.jugementMajorityDescription));
                        break;
                    case 3:
                        text.setText(getString(R.string.uninomialOneTurnVoteDescription));
                        break;
                    case 4:
                        text.setText(getString(R.string.uninominalTwoTurnVoteDescription));
                        break;
                    case 5:
                        text.setText(getString(R.string.CondorcetDescription));
                        break;
                    case 6:
                        text.setText(getString(R.string.BordaDescription));
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });
        return rootView;
    }


    //TODO: handle the clik on validate to go to the right vote conf interface with the data




}