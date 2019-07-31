package org.incoder.uiwidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.incoder.uiwidget.button.ButtonFragment;
import org.incoder.uiwidget.button.CheckBoxFragment;
import org.incoder.uiwidget.button.FloatingActionButtonFragment;
import org.incoder.uiwidget.button.ImageButtonFragment;
import org.incoder.uiwidget.button.RadioButtonFragment;
import org.incoder.uiwidget.button.RadioGroupFragment;
import org.incoder.uiwidget.button.SwitchFragment;
import org.incoder.uiwidget.button.ToggleButtonFragment;
import org.incoder.uiwidget.containers.AppBarLayoutFragment;
import org.incoder.uiwidget.containers.BottomNavigationFragment;
import org.incoder.uiwidget.containers.CardViewFragment;
import org.incoder.uiwidget.containers.IncludeFragment;
import org.incoder.uiwidget.containers.NavigationViewFragment;
import org.incoder.uiwidget.containers.RecyclerViewFragment;
import org.incoder.uiwidget.containers.RequestFocusFragment;
import org.incoder.uiwidget.containers.ScrollViewFragment;
import org.incoder.uiwidget.containers.SpinnerFragment;
import org.incoder.uiwidget.containers.TabLayoutFragment;
import org.incoder.uiwidget.containers.TabsFragment;
import org.incoder.uiwidget.containers.ToolbarFragment;
import org.incoder.uiwidget.containers.ViewFragment;
import org.incoder.uiwidget.containers.ViewPagerFragment;
import org.incoder.uiwidget.containers.ViewStubFragment;
import org.incoder.uiwidget.containers.fragmentFragment;
import org.incoder.uiwidget.google.AdViewFragment;
import org.incoder.uiwidget.google.MapViewFragment;
import org.incoder.uiwidget.layout.ConstraintLayoutFragment;
import org.incoder.uiwidget.layout.FrameLayoutFragment;
import org.incoder.uiwidget.layout.GuidelineFragment;
import org.incoder.uiwidget.layout.LinearLayoutFragment;
import org.incoder.uiwidget.layout.SpaceFragment;
import org.incoder.uiwidget.layout.TableLayoutFragment;
import org.incoder.uiwidget.layout.TableRowFragment;
import org.incoder.uiwidget.legacy.GridLayoutFragment;
import org.incoder.uiwidget.legacy.GridViewFragment;
import org.incoder.uiwidget.legacy.ListViewFragment;
import org.incoder.uiwidget.legacy.RelativeLayoutFragment;
import org.incoder.uiwidget.legacy.TabHostFragment;
import org.incoder.uiwidget.statusbar.StatusBarActivity;
import org.incoder.uiwidget.text.AutoCompleteTextViewFragment;
import org.incoder.uiwidget.text.CheckedTextViewFragment;
import org.incoder.uiwidget.text.DateFragment;
import org.incoder.uiwidget.text.EmailFragment;
import org.incoder.uiwidget.text.MultiAutoCompleteTextViewFragment;
import org.incoder.uiwidget.text.MultilineTextFragment;
import org.incoder.uiwidget.text.NumberFragment;
import org.incoder.uiwidget.text.PasswordFragment;
import org.incoder.uiwidget.text.PhoneFragment;
import org.incoder.uiwidget.text.PlainTextFragment;
import org.incoder.uiwidget.text.PostalAddressFragment;
import org.incoder.uiwidget.text.TextInputLayoutFragment;
import org.incoder.uiwidget.text.TextViewFragment;
import org.incoder.uiwidget.text.TimeFragment;
import org.incoder.uiwidget.widgets.CalendarViewFragment;
import org.incoder.uiwidget.widgets.DividerFragment;
import org.incoder.uiwidget.widgets.ImageViewFragment;
import org.incoder.uiwidget.widgets.ProgressBarFragment;
import org.incoder.uiwidget.widgets.RatingBarFragment;
import org.incoder.uiwidget.widgets.SearchViewFragment;
import org.incoder.uiwidget.widgets.SeekBarFragment;
import org.incoder.uiwidget.widgets.SurfaceViewFragment;
import org.incoder.uiwidget.widgets.TextureViewFragment;
import org.incoder.uiwidget.widgets.VideoViewFragment;
import org.incoder.uiwidget.widgets.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity
 *
 * @author Jerry xu
 * @date 4/6/2018 1:00 AM.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private String[] mTexts = {"TextView", "Plain Text", "Password", "E-mail", "Phone", "Postal Address",
            "Multiline Text", "Time", "Date", "Number", "AutoCompleteTextView", "MultiAutoCompleteTextView", "CheckedTextView", "TextInputLayout"};
    private String[] mButtons = {"Button", "ImageButton", "CheckBox", "RadioGroup", "RadioButton", "ToggleButton",
            "Switch", "FloatingActionButton"};
    private String[] mWidgets = {"View", "ImageView", "WebView", "VideoView", "CalendarView", "ProgressBar",
            "SeekBar", "RatingBar", "SearchView", "TextureView", "SurfaceView", "Divider"};
    private String[] mLayouts = {"ConstraintLayout", "Guideline", "LinearLayout", "FrameLayout", "TableLayout", "TableRow",
            "Space"};
    private String[] mContainers = {"Spinner", "RecyclerView", "ScrollView", "ViewPager", "CardView", "Tabs",
            "AppBarLayout", "NavigationView", "BottomNavigation", "Toolbar", "TabLayout", "ViewStub", "<include>", "<fragment>", "<view>", "<requestFocus>"};
    private String[] mGoogles = {"AdView", "MapView"};
    private String[] mLegacies = {"GridLayout", "ListView", "TabHost", "RelativeLayout", "GridView"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        setTopTab(mTexts);
    }

    private void setTopTab(String[] mTabTitle) {
        fragments = new ArrayList<>();

        addFragment(mTabTitle);
        viewPager.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), (ArrayList<Fragment>) fragments, mTabTitle));
        viewPager.setOffscreenPageLimit(fragments.size());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addFragment(String[] mTabTitle) {

        int mTextLength = 14;
        int mButtonLength = 8;
        int mWidgetLength = 12;
        int mLayoutLength = 7;
        int mContainersLength = 16;
        int mGoogleLength = 2;
        int mLegacyLength = 5;

        if (mTabTitle.length == mTextLength) {
            fragments.add(new TextViewFragment());
            fragments.add(new PlainTextFragment());
            fragments.add(new PasswordFragment());
            fragments.add(new EmailFragment());
            fragments.add(new PhoneFragment());
            fragments.add(new PostalAddressFragment());
            fragments.add(new MultilineTextFragment());
            fragments.add(new TimeFragment());
            fragments.add(new DateFragment());
            fragments.add(new NumberFragment());
            fragments.add(new AutoCompleteTextViewFragment());
            fragments.add(new MultiAutoCompleteTextViewFragment());
            fragments.add(new CheckedTextViewFragment());
            fragments.add(new TextInputLayoutFragment());
        } else if (mTabTitle.length == mButtonLength) {
            fragments.add(new ButtonFragment());
            fragments.add(new ImageButtonFragment());
            fragments.add(new CheckBoxFragment());
            fragments.add(new RadioGroupFragment());
            fragments.add(new RadioButtonFragment());
            fragments.add(new ToggleButtonFragment());
            fragments.add(new SwitchFragment());
            fragments.add(new FloatingActionButtonFragment());
        } else if (mTabTitle.length == mWidgetLength) {
            fragments.add(new ViewFragment());
            fragments.add(new ImageViewFragment());
            fragments.add(new WebViewFragment());
            fragments.add(new VideoViewFragment());
            fragments.add(new CalendarViewFragment());
            fragments.add(new ProgressBarFragment());
            fragments.add(new SeekBarFragment());
            fragments.add(new RatingBarFragment());
            fragments.add(new SearchViewFragment());
            fragments.add(new TextureViewFragment());
            fragments.add(new SurfaceViewFragment());
            fragments.add(new DividerFragment());
        } else if (mTabTitle.length == mLayoutLength) {
            fragments.add(new ConstraintLayoutFragment());
            fragments.add(new GuidelineFragment());
            fragments.add(new LinearLayoutFragment());
            fragments.add(new FrameLayoutFragment());
            fragments.add(new TableLayoutFragment());
            fragments.add(new TableRowFragment());
            fragments.add(new SpaceFragment());

        } else if (mTabTitle.length == mContainersLength) {
            fragments.add(new SpinnerFragment());
            fragments.add(new RecyclerViewFragment());
            fragments.add(new ScrollViewFragment());
            fragments.add(new ViewPagerFragment());
            fragments.add(new CardViewFragment());
            fragments.add(new TabsFragment());
            fragments.add(new AppBarLayoutFragment());
            fragments.add(new NavigationViewFragment());
            fragments.add(new BottomNavigationFragment());
            fragments.add(new ToolbarFragment());
            fragments.add(new TabLayoutFragment());
            fragments.add(new ViewStubFragment());
            fragments.add(new IncludeFragment());
            fragments.add(new fragmentFragment());
            fragments.add(new ViewFragment());
            fragments.add(new RequestFocusFragment());
        } else if (mTabTitle.length == mGoogleLength) {
            fragments.add(new AdViewFragment());
            fragments.add(new MapViewFragment());

        } else if (mTabTitle.length == mLegacyLength) {
            fragments.add(new GridLayoutFragment());
            fragments.add(new ListViewFragment());
            fragments.add(new TabHostFragment());
            fragments.add(new RelativeLayoutFragment());
            fragments.add(new GridViewFragment());

        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "onClick Settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_text:
                setTopTab(mTexts);
                break;
            case R.id.nav_button:
                setTopTab(mButtons);
                break;
            case R.id.nav_widgets:
                setTopTab(mWidgets);
                break;
            case R.id.nav_layout:
                setTopTab(mLayouts);
                break;
            case R.id.nav_containers:
                setTopTab(mContainers);
                break;
            case R.id.nav_google:
                setTopTab(mGoogles);
                break;
            case R.id.nav_legacy:
                setTopTab(mLegacies);
                break;
            /*// 自定义控件
            case R.id.nav_custom:
                break;*/
            case R.id.nav_bar:
                startActivity(new Intent(this, StatusBarActivity.class));
                break;
            case R.id.nav_sheet:
                break;
            default:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
