package net.fabricemk.android.mycv.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.adapters.CareerTimelineAdapter;
import net.fabricemk.android.mycv.models.CareerItem;
import net.fabricemk.android.mycv.tools.CommunicationTools;
import net.fabricemk.android.mycv.tools.resources.CareerMapper;

/**
 * Activity used to host and display the Details of a Career experience/item
 *
 * Note: We fetch the infos directly from the Intent Extras.
 * The fields count is not too high and they are all simple.
 *
 * For more complicated objects, a implementation of the Parcelable interface
 * {@link android.os.Parcelable} or Serializable {@link java.io.Serializable}
 * would have been used to pass them between Activities.
 *
 */
public class CareerDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_COMPANY = "net.fabricemk.android.mycv.company";
    private static final String EXTRA_POSITION = "net.fabricemk.android.mycv.position";
    private static final String EXTRA_START_DATE = "net.fabricemk.android.mycv.startDate";
    private static final String EXTRA_END_DATE = "net.fabricemk.android.mycv.endDate";
    private static final String EXTRA_DESCRIPTION = "net.fabricemk.android.mycv.description";
    private static final String EXTRA_ICON = "net.fabricemk.android.mycv.icon";
    private static final String EXTRA_HEADER = "net.fabricemk.android.mycv.header";
    private static final String EXTRA_DETAILS = "net.fabricemk.android.mycv.details";
    private static final String EXTRA_WEBSITE = "net.fabricemk.android.mycv.website";

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static void navigate(AppCompatActivity activity, View transitionImage, CareerItem career) {
        Intent intent = new Intent(activity, CareerDetailsActivity.class);
        intent.putExtra(EXTRA_COMPANY, career.getCompany());
        intent.putExtra(EXTRA_POSITION, career.getPosition());
        intent.putExtra(EXTRA_START_DATE, career.getStartDate());
        intent.putExtra(EXTRA_END_DATE, career.getEndDate());
        intent.putExtra(EXTRA_DESCRIPTION, career.getDescription());
        intent.putExtra(EXTRA_ICON, career.getIcon());
        intent.putExtra(EXTRA_HEADER, career.getHeader());
        intent.putExtra(EXTRA_DETAILS, career.getDetails());
        intent.putExtra(EXTRA_WEBSITE, career.getWebsite());

        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_ICON);
        //ActivityCompat.startActivity(activity, intent, options.toBundle());
        ActivityCompat.startActivity(activity, intent, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.career_details);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra(EXTRA_COMPANY);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        int headerId = CareerMapper.mappingHeaderIdFromName(getIntent().getStringExtra(EXTRA_HEADER));
        //collapsingToolbarLayout.setBackgroundResource(headerId);

        ImageView headerView = (ImageView) findViewById(R.id.header);
        TextView companyView = (TextView) findViewById(R.id.career_details_company);
        TextView positionView = (TextView) findViewById(R.id.career_details_position);
        TextView dateView = (TextView) findViewById(R.id.career_details_date);
        TextView descriptionView = (TextView) findViewById(R.id.career_details_description);

        headerView.setImageResource(headerId);
        headerView.setAlpha(0.8f);

        String company = getIntent().getStringExtra(EXTRA_COMPANY);
        companyView.setText(company);

        String position = getIntent().getStringExtra(EXTRA_POSITION);
        positionView.setText(position);

        String date = String.format(getString(R.string.career_date),
                getIntent().getStringExtra(EXTRA_START_DATE),
                getIntent().getStringExtra(EXTRA_END_DATE));
        dateView.setText(date);

        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        //descriptionView.setText(description);
        descriptionView.setText(getString(R.string.lorem_ipsum));

        final String website = getIntent().getStringExtra(EXTRA_WEBSITE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (website != null && ! website.isEmpty()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommunicationTools.launchURL(CareerDetailsActivity.this, website);
                }
            });
        } else {
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            p.setAnchorId(View.NO_ID);
            fab.setLayoutParams(p);
            fab.setVisibility(View.GONE);
        }

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
