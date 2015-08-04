package net.fabricemk.android.mycv.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.SkillListAdapter;
import net.fabricemk.android.mycv.models.Skill;

public class SkillDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "net.fabricemk.android.mycv.title";
    private static final String EXTRA_DESCRIPTION = "net.fabricemk.android.mycv.description";
    private static final String EXTRA_ICON = "net.fabricemk.android.mycv.icon";

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static void navigate(AppCompatActivity activity, View transitionImage, Skill skill) {
        Intent intent = new Intent(activity, SkillDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, skill.getName());
        intent.putExtra(EXTRA_DESCRIPTION, skill.getDescription());
        intent.putExtra(EXTRA_ICON, skill.getIcon());

        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_ICON);
        //ActivityCompat.startActivity(activity, intent, options.toBundle());
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initActivityTransitions();

        setContentView(R.layout.activity_skill_details);

        //ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_ICON);
        //supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        ImageView iconView = (ImageView) findViewById(R.id.icon);
        TextView titleView = (TextView) findViewById(R.id.title);
        TextView descriptionView = (TextView) findViewById(R.id.description);

        int resourceId = SkillListAdapter.mappingIconIdFromName(getIntent().getStringExtra(EXTRA_ICON));

        Glide.with(this)
                .load(resourceId)
                .centerCrop()
                .crossFade()
                .into(iconView);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        titleView.setText(title);

        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        descriptionView.setText(description);
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        //supportStartPostponedEnterTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
