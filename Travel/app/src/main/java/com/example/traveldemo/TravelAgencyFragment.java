package com.example.traveldemo;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveldemo.Adapter.AgencyPlanAdapter;
import com.example.traveldemo.Entity.Attraction;
import com.example.traveldemo.Entity.TravelPlan;
import com.example.traveldemo.ViewModel.AttractionViewModel;
import com.example.traveldemo.ViewModel.TravelPlanViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class TravelAgencyFragment extends Fragment {

    private RecyclerView recyclerView;
    private AgencyPlanAdapter agencyPlanAdapter;
    private TravelPlanViewModel travelPlanViewModel;
    private FloatingActionButton floatingActionButton;
    private AttractionViewModel attractionViewModel;
    private LiveData<List<TravelPlan>>getAgencyAllPlan;
    private List<TravelPlan>allTravelByAgency;
    private LiveData<List<Attraction>>findAttractionByPlan;
    private List<Attraction>allAttractionByPlan = new ArrayList<>();
    private List<Attraction>allAttractionSave = new ArrayList<>();
    private TravelPlan travelPlanToDelete;


    public TravelAgencyFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(Objects.requireNonNull(getView()));
        switch (item.getItemId()){
            case R.id.agency_order:
                String current_agency = getArguments().getString(getResources().getString(R.string.agency_loginname));
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.agency_loginname),current_agency);
                navController.navigate(R.id.action_travelAgencyFragment_to_travelOrderFragment,bundle);
                break;
            case R.id.agency_logout:
                navController.navigate(R.id.action_travelAgencyFragment_to_loginFragment);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.travelagency_menu,menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel_agency, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String current_agency = getArguments().getString(getResources().getString(R.string.agency_loginname));

        travelPlanViewModel = ViewModelProviders.of(requireActivity()).get(TravelPlanViewModel.class);
        attractionViewModel = ViewModelProviders.of(requireActivity()).get(AttractionViewModel.class);

        floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton_addplan);

        recyclerView = requireActivity().findViewById(R.id.recycleview_agencyplan);
        agencyPlanAdapter = new AgencyPlanAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(agencyPlanAdapter);

        getAgencyAllPlan = travelPlanViewModel.findPlanByAgency(current_agency);
        getAgencyAllPlan.observe(getViewLifecycleOwner(), new Observer<List<TravelPlan>>() {
            @Override
            public void onChanged(final List<TravelPlan> travelPlans) {
                allTravelByAgency = travelPlans;
                agencyPlanAdapter.setGetAgencyPlan(travelPlans);
                agencyPlanAdapter.notifyDataSetChanged();



                //test
                if(travelPlans.size()!=0){
                    //测试用景点...
                    LiveData<List<Attraction>>AllPlanAttraction = attractionViewModel.findAllAttraction();
                    AllPlanAttraction.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
                        @Override
                        public void onChanged(List<Attraction> attractions) {
                            if(attractions.size()==0){
                                Attraction attraction = new Attraction(travelPlans.get(0).getPlan_id(),"清水寺","06:00-18:00","《名侦探柯南》圣地巡礼","位于清水寺","1-3hours","https://b1-q.mafengwo.net/s9/M00/F6/AC/wKgBs1bmsE2AS1toAAlf1gQc9dE73.jpeg?imageMogr2%2Fthumbnail%2F%21305x183r%2Fgravity%2FCenter%2Fcrop%2F%21305x183%2Fquality%2F100",852,"https://cdn.pixabay.com/photo/2016/06/03/06/18/japan-1432858_960_720.jpg","·清水寺是京都最古老的寺院，与金阁寺、二条城并称为京都三大名胜，1994年列入世界文化遗产名录。\n" +
                                        "·春季时樱花烂漫，是京都的赏樱名所之一，秋季时红枫飒爽，又是赏枫胜地。\n" +
                                        "·清水舞台下的音羽瀑布，其三个源流分别代表着健康、学业和姻缘，很多人在这里排队接水饮用、祈福。\n" +
                                        "·在名侦探柯南剧场版《迷宫的十字路》中，和叶带小兰和园子游览京都时经过此处。");
                                Attraction attraction2 = new Attraction(travelPlans.get(0).getPlan_id(),"八坂神社","00:00-24:00","舞殿提灯点亮黑夜","位于清水寺","1-3hours","https://b1-q.mafengwo.net/s12/M00/3B/8D/wKgED1v4KHSAeP0eABG83oJMyJk83.jpeg?imageMogr2%2Fthumbnail%2F%21200x120r%2Fgravity%2FCenter%2Fcrop%2F%21200x120%2Fquality%2F100",741,"https://cdn.pixabay.com/photo/2016/06/03/06/19/japan-1432866_960_720.jpg","·位于京都市东山区的神社，是关西地区最知名且历史最悠久的神社之一。 \n" +
                                        "·神社大殿被称为“衹园造”，是日本独特的神社建筑，而舞殿上无数的提灯，让夜晚甚是美丽。\n" +
                                        "·这里有专门求签、写绘马和请御守的地方，绘马图案非常多样，祈求恋爱的心形绘马十分受欢迎。\n" +
                                        "·每年7月神社都会举行热闹的祇园祭，神社内可以品尝到各式各样的小吃，还有许多吸引眼球的节庆装饰。");
                                Attraction attraction3 = new Attraction(travelPlans.get(0).getPlan_id(),"祗园","00:00-24:00","偶遇艺妓的古老花街","位于清水寺","1-3hours","https://b1-q.mafengwo.net/s15/M00/79/3F/CoUBGV3EB0eAC3Q0AAyLKYt4xY017.jpeg?imageMogr2%2Fthumbnail%2F%21240x180r%2Fgravity%2FCenter%2Fcrop%2F%21240x180%2Fquality%2F90",912,"https://cdn.pixabay.com/photo/2016/07/04/22/38/gion-1497659_960_720.jpg","·祇园是现代日本最著名的艺伎的“花街”。\n" +
                                        "·祇园位于京都鸭川以东的东山区，分为祇园东和祇园甲部两片，据说最初江户幕府允许茶屋在这里营业是在1665年，至今已有三百多年的历史。在最繁华的十九世纪初，祇园的艺伎多达三千多人。\n" +
                                        "·祇园的艺伎馆、茶屋现今还保留了许多当时的建筑，在1999年被日本政府指定为历史景观保护地区。祇园的代表性建筑是祇园歌舞练场，这是艺伎馆共同出资建造的歌舞剧场，每年的四月艺伎在这里表演“京都舞”，向世人展现艺伎的风采和日本古典歌舞艺术。");
                                Attraction attraction4 = new Attraction(travelPlans.get(0).getPlan_id(),"岚山","00:00-24:00","看四季美景","位于岚山","1day","https://p1-q.mafengwo.net/s11/M00/31/0D/wKgBEFttU3yAFS_bABaZIbw_uqU15.jpeg?imageMogr2%2Fthumbnail%2F%21296x156r%2Fgravity%2FCenter%2Fcrop%2F%21296x156%2Fquality%2F100",699,"https://cdn.pixabay.com/photo/2016/06/05/12/50/arashiyama-1437275_960_720.jpg","·岚山保留下了许多平安王朝的遗韵。" +
                                        "·岚山景区集中了许多古刹和神社，天龙寺更是世界遗产级别的古迹，由野宫神社向大河内山庄之间的竹林小径则是李安导演的电影《卧虎藏龙》的取景地。" +
                                        "·作为赏樱赏枫的圣地，岚山每到春秋两季吸引了大量游人，嵯峨野小火车往往一票难求，而冬夏时节则十分静谧，适合喜欢清幽的旅行者。" +
                                        "·岚山地区有不少餐饮老铺、和果子屋以及可爱的杂货店，还可以租赁和服，是个非常适合放慢节奏来发现的地方。");
                                Attraction attraction5 = new Attraction(travelPlans.get(0).getPlan_id(),"心斋桥","11:00-21:00","流光溢彩的商业街","位于心斋桥","1-3hours","https://p1-q.mafengwo.net/s14/M00/CA/B0/wKgE2l1MTEuAB9jUABnglRw0Kss50.jpeg?imageMogr2%2Fthumbnail%2F%21690x450r%2Fgravity%2FCenter%2Fcrop%2F%21690x450%2Fquality%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cHM6Ly9iMS1xLm1hZmVuZ3dvLm5ldC9zMTEvTTAwLzQ0LzlCL3dLZ0JFRnNQNVJ5QUR2N3BBQUFIWlpVUFJsUTk5MC5wbmc%3D%2Fgravity%2FSouthEast%2Fdx%2F10%2Fdy%2F11",865,"https://p1-q.mafengwo.net/s11/M00/12/D3/wKgBEFtmJSWAb8mqABARS5XaRt489.jpeg?imageMogr2%2Fthumbnail%2F%21690x450r%2Fgravity%2FCenter%2Fcrop%2F%21690x450%2Fquality%2F90%7Cwatermark%2F1%2Fimage%2FaHR0cHM6Ly9iMS1xLm1hZmVuZ3dvLm5ldC9zMTEvTTAwLzQ0LzlCL3dLZ0JFRnNQNVJ5QUR2N3BBQUFIWlpVUFJsUTk5MC5wbmc%3D%2Fgravity%2FSouthEast%2Fdx%2F10%2Fdy%2F11","·大阪最适合血拼的胜地，临近道顿堀，购物美食两不误");
                                Attraction attraction6 = new Attraction(travelPlans.get(0).getPlan_id(),"金阁寺","09:00-17:00","《聪明的一休》原型庄园","位于北京都","1-3hours","https://b1-q.mafengwo.net/s10/M00/7F/7C/wKgBZ1uGCp6ADpKSAA3ADupDJws35.jpeg?imageMogr2%2Fthumbnail%2F%21305x183r%2Fgravity%2FCenter%2Fcrop%2F%21305x183%2Fquality%2F100",422,"https://cdn.pixabay.com/photo/2019/02/02/07/00/kinkaku-ji-3970247_960_720.jpg","·建于1379年，原为足利义满将军（动画聪明的一休中利将军原型）的山庄，后改为禅寺“菩提所”。\n" +
                                        "·这座高调华丽的禅寺，与前方的镜湖池交相辉映，成为京都最经典的画面之一。\n" +
                                        "·金阁寺是3层的楼阁，第一层为法水院；第二层为潮音洞，供奉着观音；第三层是正方形的佛堂，供奉着三尊弥陀佛。\n" +
                                        "·游客拿到的门票是写有祝福话语的朱印，院中的不动堂旁边还有中文和韩文的神签可供占卜。");
                                Attraction attraction7 = new Attraction(travelPlans.get(0).getPlan_id(),"若草山","00:00-24:00","俯瞰奈良街景","位于奈良公园","3hours+","https://n1-q.mafengwo.net/s11/M00/B3/9B/wKgBEFq_mquANu3LACOSGs28b5w79.jpeg?imageMogr2%2Fthumbnail%2F%21305x183r%2Fgravity%2FCenter%2Fcrop%2F%21305x183%2Fquality%2F100",688,"https://n1-q.mafengwo.net/s11/M00/00/53/wKgBEFrAWgaAL1BUACFqZUsKQDc42.jpeg?imageMogr2%2Fthumbnail%2F%21690x370r%2Fgravity%2FCenter%2Fcrop%2F%21690x370%2Fquality%2F100","·若草山位于东大寺和春日大社附近，高342米。分为三个山坡，从低到高：一重目，二重目，三重目。从二重目开始就能俯瞰整个奈良的风景。\n" +
                                        "·若草山最有名的是“若草烧山”的节日，时间是每年一月份的第四个星期六。由中午正式开始，18:15有烟花表演，最精彩的节目要数晚上18:30开始的烧山仪式。\n" +
                                        "·登山路线有两种，从南门或北门，从南门登山由于台阶较少，会比较容易。");
                                attractionViewModel.insertAttraction(attraction,attraction2,attraction3,attraction4,attraction5,attraction6,attraction7);
                            }
                        }
                    });
                }


            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_agency = getArguments().getString(getContext().getResources().getString(R.string.agency_loginname));
                Bundle bundle = new Bundle();
                bundle.putString(getContext().getResources().getString(R.string.agency_loginnameadd),current_agency);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_travelAgencyFragment_to_addPlanFragment,bundle);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                travelPlanToDelete = allTravelByAgency.get(viewHolder.getAdapterPosition());
                findAttractionByPlan = attractionViewModel.findAttractionByPlan(travelPlanToDelete.getPlan_id());
                findAttractionByPlan.observe(getViewLifecycleOwner(), new Observer<List<Attraction>>() {
                    @Override
                    public void onChanged(List<Attraction> attractions) {
                        allAttractionByPlan = attractions;
                    }
                });
                if(allAttractionByPlan.size()!=0){
                    allAttractionSave = allAttractionByPlan;
                    for(int i=0;i<allAttractionByPlan.size();i++){
                        attractionViewModel.deleteAttraction(allAttractionByPlan.get(i));
                    }
                    allAttractionByPlan = new ArrayList<>();
                }

                travelPlanViewModel.deleteTravlePlan(travelPlanToDelete);




                Snackbar.make(requireActivity().findViewById(R.id.travelAgencyFragmentView),"删除了一个方案",Snackbar.LENGTH_SHORT)
                        .setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                travelPlanViewModel.insertTravlePlan(travelPlanToDelete);
                                if(allAttractionSave.size()!=0){
                                    for(int i=0;i<allAttractionSave.size();i++){
                                        int j = allAttractionSave.size()-1;
                                        attractionViewModel.insertAttraction(allAttractionSave.get(j));
                                    }
                                    allAttractionSave = new ArrayList<>();
                                }

                            }
                        })
                        .show();
            }
            Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_delete_forever_white_24dp);
            Drawable background = new ColorDrawable(Color.RED);

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight());

                int iconLeft,iconRight,iconTop,iconBottom;
                int backgroundLeft,backgroundRight,backgroundTop,backgroundBottom;
                backgroundTop = itemView.getTop() + 16;
                backgroundBottom = itemView.getBottom();
                iconTop = itemView.getTop() + iconMargin - 64;
                iconBottom = iconTop + icon.getIntrinsicHeight();
                if(dX>0){
                    backgroundLeft = itemView.getLeft();
                    backgroundRight = itemView.getLeft() + (int)dX;
                    background.setBounds(backgroundLeft,backgroundTop,backgroundRight,backgroundBottom);
                    iconLeft = itemView.getLeft() + iconMargin;
                    iconRight = iconLeft + icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else if(dX<0){
                    backgroundRight = itemView.getRight();
                    backgroundLeft = backgroundRight + (int)dX;
                    background.setBounds(backgroundLeft,backgroundTop,backgroundRight,backgroundBottom);
                    iconRight = itemView.getRight() - iconMargin;
                    iconLeft = iconRight - icon.getIntrinsicWidth();
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else{
                    background.setBounds(0,0,0,0);
                    icon.setBounds(0,0,0,0);
                }
                background.draw(c);
                icon.draw(c);
            }
        }).attachToRecyclerView(recyclerView);







    }
}
