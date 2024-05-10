package com.example.bae.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bae.R;
import com.example.bae.data.FirebaseCustome;
import com.example.bae.data.RequestCustome;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.Voucher.ChooseBranch.ChooseBranchActivity;
import com.example.bae.ui.Voucher.VoucherOfUser.VoucherOfUserActivity;
import com.example.bae.ui.home.Adapter.DevelopmentAdapter;
import com.example.bae.ui.home.Adapter.DevelopmentData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {

    private HomeViewModel mViewModel;
    private View cv_change_voucher_home , cv_voucher_of_user_home ;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    private View view ;

    private RecyclerView.Adapter adapter ;
    private RecyclerView recyclerView ;
    private TextView textHi ;
    private ImageView imageView ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        UserData userData = DataLocalManager.getUser() ;
        cv_change_voucher_home = view.findViewById(R.id.cv_change_voucher_home);
        cv_voucher_of_user_home = view.findViewById(R.id.cv_voucher_of_user_home);
        textHi = view.findViewById(R.id.textHi);
        imageView = view.findViewById(R.id.image_home);

        cv_change_voucher_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userData != null){
                    startActivity(new Intent(getActivity() , ChooseBranchActivity.class));
                }
                else {
                    Toast.makeText(getContext() , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
                }
            }
        });
        cv_voucher_of_user_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userData != null){
                    startActivity(new Intent(getActivity() , VoucherOfUserActivity.class));
                }
                else {
                    Toast.makeText(getContext() , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
                }
            }
        });



        if(userData != null){
            String[] arrayName = userData.getName().split(" ");
            textHi.setText("Hi, "+arrayName[arrayName.length - 1]);
            String img = FirebaseCustome.getInstance().getUrlFirebaseStorage() +userData.getCccd()+"%2Favatar.png?alt=media";
            Picasso.with(getContext()).load(img).into(imageView);
        }


        initRecyclerView();


        return view ;
    }
    private void initRecyclerView() {
        ArrayList<DevelopmentData> items = new ArrayList<>() ;
        items.add(new DevelopmentData("Chung tay bảo vệ môi trường\n" , "Chung tay bảo vệ môi trường: Đây là một mục tiêu rất quan trọng của chúng ta trong thời đại hiện nay. Bảo vệ môi trường không chỉ giúp giảm thiểu các tác động xấu đến môi trường như ô nhiễm, mất rừng, mất đất, mất nước mà còn giúp cải thiện chất lượng cuộc sống của con người.\n" , "item1"));
        items.add(new DevelopmentData("Nâng cao nhận thức của con người\n" , "Việc nâng cao nhận thức của con người về các vấn đề môi trường là rất quan trọng. Chúng ta cần giúp mọi người hiểu rõ hơn về tác động của các hoạt động của con người đến môi trường, cũng như cách để giảm thiểu tác động này. Việc tăng cường giáo dục và truyền thông về môi trường không chỉ giúp nâng cao nhận thức của mọi người.\n"  , "item2"));
        items.add(new DevelopmentData("Hợp tác cùng chúng tôi\n" , "Để đạt được những mục tiêu bảo vệ môi trường, chúng ta cần hợp tác với nhau. Chúng tôi luôn hoan nghênh mọi sự đóng góp và hỗ trợ từ mọi người để cùng nhau tạo ra một môi trường sống tốt đẹp hơn. Chúng tôi sẵn sàng hợp tác với các tổ chức, cộng đồng và cá nhân để thực hiện các hoạt động bảo vệ môi trường.\n"  , "item3"));
        items.add(new DevelopmentData("Chúng tôi cần sự giúp đỡ của bạn\n" , "Việc bảo vệ môi trường không thể thành công nếu chỉ có chúng tôi làm một mình. Chúng tôi cần sự hỗ trợ của mọi người để có thể đạt được mục tiêu bảo vệ môi trường. Nếu bạn quan tâm đến các vấn đề môi trường và muốn đóng góp vào công cuộc bảo vệ môi trường, hãy tham gia các hoạt động của chúng tôi.\n" , "item4"));

        recyclerView = view.findViewById(R.id.listDevelopment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));

        adapter = new DevelopmentAdapter(items);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

}