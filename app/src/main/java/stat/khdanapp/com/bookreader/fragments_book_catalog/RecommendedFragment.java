package stat.khdanapp.com.bookreader.fragments_book_catalog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stat.khdanapp.com.bookreader.ActiveBookActivity;
import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.adapter.CustomRVAdapter;
import stat.khdanapp.com.bookreader.controler.DataController;
import stat.khdanapp.com.bookreader.dialog.CustomDialogFragment;
import stat.khdanapp.com.bookreader.model.BookCardView;


public class RecommendedFragment extends Fragment implements CustomRVAdapter.CustomRVAdapterListener {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    private static final int REQUEST_WEIGHT = 1;
    private static final int REQUEST_ANOTHER_ONE = 2;

    public RecyclerView rv;
    public List<BookCardView> mCustModelCardsList;
    public CustomRVAdapter customRVAdapter;

//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    @Override
    public void detailedActivityOpen(int imageId, String bookTitle) {
        Intent intent = new Intent(getContext(), ActiveBookActivity.class);
        intent.putExtra(ActiveBookActivity.IMAGE,imageId);
        intent.putExtra(ActiveBookActivity.TITLE,bookTitle);
        getContext().startActivity(intent);
    }

    public int onOrderToDelete;

    @Override
    public void showDialogOnLongTap(int position) {
        onOrderToDelete = position;
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.setTargetFragment(this, REQUEST_WEIGHT);
        dialog.show(getFragmentManager(), dialog.getClass().getName());
    }

    @Override
    public void favoriteButtonClickedOnItem(int position, int tag) {

        if(tag == R.drawable.ic_favorite_border_black_24dp) {
            mCustModelCardsList.get(position).setFavoriteId(R.drawable.ic_favorite_black_24dp);
            DataController.writeSharedPref(getActivity(),mCustModelCardsList.get(position).getAuthor(),mCustModelCardsList.get(position).getBookTitle());
            //DataController.readSharedPref(getActivity());
        }
        else {
            mCustModelCardsList.get(position).setFavoriteId(R.drawable.ic_favorite_border_black_24dp);
            DataController.removeSharedPref(getActivity(),mCustModelCardsList.get(position).getBookTitle());
        }
        customRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_WEIGHT:
//                    int weight = data.getIntExtra(CustomDialogFragment.TAG_WEIGHT_SELECTED, -1)
//                    //используем полученные результаты
//                    //...
//                    break;
//                case REQUEST_ANOTHER_ONE:
//                    //...
//                    break;
//                //обработка других requestCode
//            }
//            //updateUI();
            mCustModelCardsList.remove(onOrderToDelete);
            customRVAdapter.notifyDataSetChanged();

        }
    }


    private OnFragmentInteractionListener mListener;

    public RecommendedFragment() {
        // Required empty public constructor
    }

    public static RecommendedFragment newInstance() {
        //RecommendedFragment fragment = new RecommendedFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return new RecommendedFragment();
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

        mCustModelCardsList = new ArrayList<>();
        int imgID;
        for (int i = 1; i <= 9; i++) {
            if (i%2 == 0){
                imgID = R.drawable.a3;
            }else{
                imgID = R.drawable.a4;
            }
            int favoriteIdBuf =  R.drawable.ic_favorite_border_black_24dp;
            String bookTitle = "Название книги " + i;
            if (DataController.containsSharedPref(getActivity(),bookTitle)) favoriteIdBuf = R.drawable.ic_favorite_black_24dp;
            mCustModelCardsList.add(new BookCardView(imgID,bookTitle, "Автор " + i,favoriteIdBuf));
        }

        customRVAdapter = new CustomRVAdapter(mCustModelCardsList,this);
        rv.setAdapter(customRVAdapter);

        return rootView;
    }

    public static Uri resourceToUri(Context context, int resID) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                context.getResources().getResourcePackageName(resID) + '/' +
                context.getResources().getResourceTypeName(resID) + '/' +
                context.getResources().getResourceEntryName(resID) );
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(
                getContext().getContentResolver().openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(
                getContext().getContentResolver().openInputStream(selectedImage), null, o2);
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
