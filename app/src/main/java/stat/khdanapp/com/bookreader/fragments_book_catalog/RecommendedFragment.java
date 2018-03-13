package stat.khdanapp.com.bookreader.fragments_book_catalog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.adapter.CustomRVAdapter;
import stat.khdanapp.com.bookreader.model.BookCardView;


public class RecommendedFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    RecyclerView rv;


//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecommendedFragment() {
        // Required empty public constructor
    }

    public static RecommendedFragment newInstance() {
        RecommendedFragment fragment = new RecommendedFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recommended, container, false);

        rv = rootView.findViewById(R.id.mRecyclerViewRecommended);
        if (rv != null){
            rv.setHasFixedSize (true);
        }

        GridLayoutManager layoutManager = new GridLayoutManager (getContext(),2);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        List<BookCardView> mCustModelCardsList = new ArrayList<>();
        int imgID;
        for (int i = 1; i <= 9; i++) {
            if (i%2 == 0){
                imgID = R.drawable.a1;
            }else{
                imgID = R.drawable.a2;
            }
            mCustModelCardsList.add(new BookCardView(imgID,"Название книги " + i, "Автор " + i));
        }

        CustomRVAdapter customRVAdapter = new CustomRVAdapter(mCustModelCardsList);
        rv.setAdapter(customRVAdapter);

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
