package com.example.damon.healthkeep;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dinuscxj.progressbar.CircleProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        CircleProgressBar circleProgressBar=(CircleProgressBar)view.findViewById(R.id.home_grade_bar);
        circleProgressBar.setProgressFormatter(new MyProgressFormatter());
        return view;
    }
    private static final class MyProgressFormatter implements CircleProgressBar.ProgressFormatter {
        private static final String DEFAULT_PATTERN = "%d";

        @Override
        public CharSequence format(int progress, int max) {
            return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
        }
    }

}


