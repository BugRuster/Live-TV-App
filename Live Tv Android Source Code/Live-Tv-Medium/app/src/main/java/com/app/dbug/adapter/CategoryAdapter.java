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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.dbug.activity.SplashActivity;
import com.app.dbug.activity.TvInCategoryActivity;
import com.app.dbug.utils.ItemAnimation;
import com.bumptech.glide.Glide;
import com.app.dbug.R;
import com.app.dbug.ads.InterAdClickInterFace;
import com.app.dbug.ads.SessionAds;
import com.app.dbug.model.Category;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import alzaichsank.com.intentanimation.AnimIntent;

import static android.content.Context.MODE_PRIVATE;
import static com.app.dbug.config.Constant.ADMIN_PANEL_URL;
import static com.app.dbug.config.Constant.IMAGE_URL;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ChannelView> implements InterAdClickInterFace {

    List<Category.Datum> itemModelsList;
    Context context;
    SessionAds sessionAds;
    SharedPreferences sharedPreferences;
    boolean isDark;

    private int lastPosition = -1;
    private boolean on_attach = true;
    private int animation_type = 2;

    public CategoryAdapter(Context context,List<Category.Datum> itemModelsList) {
        this.itemModelsList = itemModelsList;
        this.context = context;
        this.sessionAds = new SessionAds(context, this);
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);


    }




    @NonNull
    @Override
    public ChannelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitem, parent, false);

        return new ChannelView(itemView);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {

            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelView holder, int position) {
        final  Category.Datum categoryItem = itemModelsList.get(position);
        sharedPreferences = context.getSharedPreferences(SplashActivity.MYPREFERENCE, MODE_PRIVATE);
        isDark = sharedPreferences.getBoolean("dark", false);
        if (isDark) {
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.maincolor));
        } else {
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

        holder.categoryViewTitle.setText(categoryItem.cat_name);

        String s = categoryItem.updated_at.toString();
        int p = s.indexOf('G');
        if (p >= 0) {
            String left = s.substring(0, p);
            holder.categoryViewCategory.setText(left);
        }



        Glide.with(holder.itemView)
                .load(ADMIN_PANEL_URL+IMAGE_URL+categoryItem.image)
                .fitCenter()
                .thumbnail(0.2f)
                .into(holder.categoryImageView);

        holder.itemView.setOnClickListener(v -> {
            if (categoryItem.category_type == 0){
                showBottomSheetDialog(context,categoryItem.image,categoryItem.cat_name,categoryItem.pass,categoryItem.id);
            }else {
                Intent intent = new Intent(v.getContext(), TvInCategoryActivity.class);
                intent.putExtra("cid",categoryItem.id+"");
                intent.putExtra("cName",categoryItem.cat_name+"");
                v.getContext().startActivity(intent);
                new AnimIntent.Builder(v.getContext()).performSlideToLeft();
            }
            sessionAds.show();
        });

        setAnimation(holder.itemView, position);
    }


    private void showBottomSheetDialog(Context context,String url,String name,String pass,int cat_id) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
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
                    intent.putExtra("cid", cat_id + "");
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
        if (isDark){
            passEdit.setTextColor(ContextCompat.getColor(context, R.color.white));
            passEdit.setHintTextColor(ContextCompat.getColor(context, R.color.white));
        }else {
            passEdit.setTextColor(ContextCompat.getColor(context, R.color.maincolor));
            passEdit.setHintTextColor(ContextCompat.getColor(context, R.color.maincolor));
        }
        Glide.with(context).load(ADMIN_PANEL_URL+IMAGE_URL+url)
                .fitCenter()
                .thumbnail(0.2f)
                .into(imageView);
        catName.setText(name);
        passButton.setOnClickListener(v -> {

           String enterPass = passEdit.getText().toString().trim();
           if (enterPass.equals(pass)){
               Intent intent = new Intent(v.getContext(), TvInCategoryActivity.class);
               intent.putExtra("cid",cat_id+"");
               intent.putExtra("cName",name+"");
               v.getContext().startActivity(intent);
               bottomSheetDialog.dismiss();

           }else {
               Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
           }
        });


        bottomSheetDialog.show();
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
        TextView categoryViewCategory;
        CardView cardview;
        public ChannelView(@NonNull View itemView) {
            super(itemView);
            categoryImageView = itemView.findViewById(R.id.categoryImageView);
            categoryViewTitle = itemView.findViewById(R.id.categoryViewTitle);
            categoryViewCategory = itemView.findViewById(R.id.categoryViewCategory);
            cardview = itemView.findViewById(R.id.cardview);



        }
    }

}
