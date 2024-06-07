package com.app.dbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dbug.activity.SplashActivity;
import com.app.dbug.activity.TvInCategoryActivity;
import com.bumptech.glide.Glide;
import com.app.dbug.R;
import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.model.Category;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ChannelView> implements InterAdClickInterFace {

    List<Category.Datum> itemModelsList;
    Context context;
    boolean isDark;


    SessionAds sessionAds;
    SharedPreferences sharedPreferences;

    public AllCategoryAdapter(Context context, List<Category.Datum> itemModelsList) {
        this.itemModelsList = itemModelsList;
        this.context = context;
        this.sessionAds = new SessionAds(context, this);
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);


    }


    @NonNull
    @Override
    public ChannelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        int spanCout = sharedPreferences.getInt("spanCout", 3);
        if (spanCout == 1){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_catagory_varti, parent, false);
        }else if (spanCout ==3){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcategoryitem, parent, false);
        }

        // return itemView
        return new ChannelView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelView holder, int position) {
        Log.d("fdsfsd", "onBindViewHolder: "+isDark);

        Category.Datum categoryItem = itemModelsList.get(position);
        holder.categoryViewTitle.setText(categoryItem.cat_name);


        String s = categoryItem.updated_at.toString();
        int p = s.indexOf('G');
        if (p >= 0) {
            String left = s.substring(0, p);
//            holder.categoryViewCategory.setText(left);
        }

        int spanCout = sharedPreferences.getInt("spanCout", 3);

        if (spanCout == 3){
            Glide.with(holder.itemView)
                    .load(ADMIN_PANEL_URL + IMAGE_URL + categoryItem.image)
                    .fitCenter()
                    .thumbnail(0.5f).placeholder(R.drawable.placeholder)
                    .into(holder.categoryImageView);
        }else {
            Glide.with(holder.itemView)
                    .load(ADMIN_PANEL_URL + IMAGE_URL + categoryItem.image)
                    .fitCenter()
                    .thumbnail(0.5f).placeholder(R.drawable.placeholder)
                    .into(holder.circleImageView);
        }





        holder.itemView.setOnClickListener(v -> {
            if (categoryItem.category_type == 0) {
                showBottomSheetDialog(context, categoryItem.image, categoryItem.cat_name, categoryItem.pass, categoryItem.id);
            } else {
                Intent intent = new Intent(v.getContext(), TvInCategoryActivity.class);
                intent.putExtra("cid", categoryItem.id + "");
                intent.putExtra("cName", categoryItem.cat_name + "");
                v.getContext().startActivity(intent);
                new AnimIntent.Builder(v.getContext()).performSlideToLeft();
            }

            sessionAds.show();

        });
    }

    @Override
    public int getItemCount() {
        return itemModelsList.size();
    }

    @Override
    public void onAdClick() {
        Log.d("TAG", "onAdClick: ");
    }

    @Override
    public void onAdFailed() {
        Log.d("TAG", "onAdFailed: ");
    }

    public class ChannelView extends RecyclerView.ViewHolder {
        ImageView categoryImageView;
        TextView categoryViewTitle;
//        TextView categoryViewCategory;
        CircleImageView circleImageView;


        public ChannelView(@NonNull View itemView) {
            super(itemView);

            categoryViewTitle = itemView.findViewById(R.id.categoryViewTitle);
//            categoryViewCategory = itemView.findViewById(R.id.categoryViewCategory);
            int spanCout = sharedPreferences.getInt("spanCout", 3);

            if (spanCout == 3){
                categoryImageView = itemView.findViewById(R.id.categoryImageView);
            }else {
                circleImageView = itemView.findViewById(R.id.categoryImageView);
            }
        }
    }

    private void showBottomSheetDialog(Context context, String url, String name, String pass, int catId) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);



        ImageView imageView = bottomSheetDialog.findViewById(R.id.button_sheet_imageView);
        TextView catName = bottomSheetDialog.findViewById(R.id.category_name_button_sheet);
        EditText passEdit = bottomSheetDialog.findViewById(R.id.passButtonSheetEditText);
        Button passButton = bottomSheetDialog.findViewById(R.id.buttonSheetPassButton);

        passEdit.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String enterPass = passEdit.getText().toString().trim();
                if (enterPass.equals(pass)) {
                    Intent intent = new Intent(v.getContext(), TvInCategoryActivity.class);
                    intent.putExtra("cid", catId + "");
                    intent.putExtra("cName", name + "");
                    v.getContext().startActivity(intent);
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
                }


                return true;
            }
            return false;
        });

        Glide.with(context).load(ADMIN_PANEL_URL + IMAGE_URL + url)
                .fitCenter()
                .thumbnail(0.2f).placeholder(R.drawable.placeholder)
                .into(imageView);
        catName.setText(name);
        passButton.setOnClickListener(v -> {

            String enterPass = passEdit.getText().toString().trim();
            if (enterPass.equals(pass)) {
                Intent intent = new Intent(v.getContext(), TvInCategoryActivity.class);
                intent.putExtra("cid", catId + "");
                intent.putExtra("cName", name + "");
                v.getContext().startActivity(intent);
                bottomSheetDialog.dismiss();
            } else {
                Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
            }
        });


        bottomSheetDialog.show();
    }

}
