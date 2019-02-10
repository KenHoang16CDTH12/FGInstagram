package kenhoang.dev.app.fginstagramapp.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kenhoang.dev.app.fginstagramapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditImageFragment extends Fragment {


    public EditImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_image, container, false);
    }

}
