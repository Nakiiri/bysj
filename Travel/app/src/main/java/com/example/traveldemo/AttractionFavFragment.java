package com.example.traveldemo;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.Favourite;
import com.example.traveldemo.ViewModel.AttractionViewModel;
import com.example.traveldemo.ViewModel.CurrentUserViewModel;
import com.example.traveldemo.ViewModel.FavouriteViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionFavFragment extends Fragment {
    private ImageView imageView_huge_image,imageView_fav;
    private TextView textView_des_detail;
    private AttractionViewModel attractionViewModel;
    private LiveData<List<Attraction>>findAttractionByID;
    private FavouriteViewModel favouriteViewModel;
    private LiveData<List<Favourite>>findFavByUserAndAttraction;
    private CurrentUserViewModel currentUserViewModel;
    private List<Attraction>getCurrentAttraction = new ArrayList<>();


    public AttractionFavFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attraction_fav, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int attraction_id = getArguments().getInt("attraction_id");

        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);
        favouriteViewModel = ViewModelProviders.of(requireActivity()).get(FavouriteViewModel.class);
        currentUserViewModel = ViewModelProviders.of(requireActivity(),new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity())).get(CurrentUserViewModel.class);
        final String Current_user = currentUserViewModel.loaduser();

        imageView_huge_image = requireActivity().findViewById(R.id.imageView_attraction_huge_image);
        imageView_fav = requireActivity().findViewById(R.id.imageView_attraction_favourite);
        textView_des_detail = requireActivity().findViewById(R.id.textView_attraction_des_detail);

        findAttractionByID = attractionViewModel.findAttractionByID(attraction_id);
        findAttractionByID.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
            @Override
            public void onChanged(List<Attraction> attractions) {
                getCurrentAttraction = attractions;
                Glide.with(requireActivity())
                        .load(attractions.get(0).getHuge_image())
                        .placeholder(R.drawable.ic_launcher_background)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(imageView_huge_image);
                textView_des_detail.setText(attractions.get(0).getDes_detail());
            }
        });

        findFavByUserAndAttraction = favouriteViewModel.findFavByUserAndAttraction(Current_user,attraction_id);
        findFavByUserAndAttraction.observe(getViewLifecycleOwner(), new Observer<List<Favourite>>() {
            @Override
            public void onChanged(final List<Favourite> favourites) {
                if(favourites.size()!=0){
                    imageView_fav.setImageResource(R.drawable.ic_favorite_red_24dp);
                    imageView_fav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageView_fav.setImageResource(R.drawable.ic_favorite_white_24dp);
                            favouriteViewModel.deleteFavourite(favourites.get(0));
                            Toast.makeText(requireActivity(),"取消收藏",Toast.LENGTH_SHORT).show();
                            attractionViewModel.updateMinusAttraction(getCurrentAttraction.get(0));
                        }
                    });
                }else{
                    imageView_fav.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageView_fav.setImageResource(R.drawable.ic_favorite_red_24dp);
                            Favourite favourite = new Favourite(Current_user,attraction_id);
                            favouriteViewModel.insertFavourite(favourite);
                            Toast.makeText(requireActivity(),"收藏成功",Toast.LENGTH_SHORT).show();
                            attractionViewModel.updateAddAttraction(getCurrentAttraction.get(0));
                        }
                    });
                }
            }
        });






    }
}
