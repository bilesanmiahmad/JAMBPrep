package com.example.ahmad.jambprepguide.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmad.jambprepguide.Global;
import com.example.ahmad.jambprepguide.R;
import com.example.ahmad.jambprepguide.adapter.SubjectAdapter;
import com.example.ahmad.jambprepguide.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SyllabusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SyllabusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SyllabusFragment extends Fragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    List<Subject> subjects;
    SubjectAdapter adapter;
    ListView subjectView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SyllabusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SyllabusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SyllabusFragment newInstance(String param1, String param2) {
        SyllabusFragment fragment = new SyllabusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_syllabus, container, false);
        subjects = Global.subjects;
        Log.e("sub", "Subject"+Global.subjectData);

        try {
            JSONObject jsonObject = new JSONObject(Global.subjectData);
            JSONArray jsonArray= jsonObject.getJSONArray("data");
            Log.e("Subz", "Sub: "+jsonArray);
            Global.subjects.clear();
            for(int i=0;i<jsonArray.length();i++)
            {
                Global.subjects.add(new Subject(jsonArray.getString(i)));
                Log.e("Subz", "Sub: "+Global.subjects);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        subjectView = (ListView) rootView.findViewById(R.id.mysubjetlist);
        adapter = new SubjectAdapter(getActivity(), R.layout.list_row, R.id.name, Global.subjects);
        subjectView.setAdapter(adapter);
        subjectView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //RelativeLayout layout = (RelativeLayout) view;
                //TextView txt = (TextView) layout.getChildAt(1);
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), choice, Toast.LENGTH_SHORT).show();
                Global.getTopicsFromApi("http://154.113.0.202:8999/utme/rest/api/v1.0/utme/topics?lastid=1&subject=", getContext(), choice);
            }
        });
        //setListAdapter(adapter);


        //String[] datasource = {"Agricultural Science", "Biology", "Chemistry", "Commerce", "Computer Science", "English", "Physics", "Nutrition", "Home Economics"};

        //setRetainInstance(true);
        return rootView;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Subject subject = this.subjects.get(position);
        Toast.makeText(getContext(), subject.getTitle(), Toast.LENGTH_SHORT).show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
