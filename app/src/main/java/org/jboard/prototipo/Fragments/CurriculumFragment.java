package org.jboard.prototipo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jboard.prototipo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {


    public CurriculumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curriculum, container, false);
    }

}
