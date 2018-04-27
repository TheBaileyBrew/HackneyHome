package com.hthackney.hackneyhome;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import com.github.florent37.materialtextfield.MaterialTextField;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ExpandableLayout WhoAreWeLayout;
    private ExpandableLayout WhereAreWeLayout;
    private ExpandableLayout ContactUsLayout;
    TextSwitcher mainHeaderText;
    TextView WhoWeAreText;
    TextView WhereWeAreText;
    TextView ContactUsText;
    TextView warehouseName;
    TextView warehouseAddress;
    TextView warehousePhone;
    TextView warehouseNameTwo;
    TextView warehouseAddressTwo;
    TextView warehousePhoneTwo;
    ListView stateListView;
    View displayBreak;
    Button emailUs;
    Button callUs;
    CheckBox email;
    CheckBox call;
    CheckBox textMessage;
    MaterialTextField userNameTextField;
    EditText userName;
    String userNameEntry;

    //Declares the String Array values for StateList and Location details
    String stateList[] = {"Alabama", "Arkansas", "Florida", "Georgia", "Illinois", "Indiana", "Iowa", "Kentucky", "Louisiana", "Maryland", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "North Carolina", "Ohio", "Pennsylvania", "South Carolina", "Tennessee", "Virginia", "West Virginia", "Wisconsin"};
    String Blank[] = {" ", " ", " "};
    String Charleston[] = {"Charleston", "1 James River Turnpike \n Milton, WV 25541", "(304)390-8025"};
    String Columbus[] = {"Columbus", "875 Taylor Station Road \n Gahanna, OH 43230", "(614)751-5100"};
    String Covington[] = {"Covington", "401 Delfield Drive \n Covington, TN 38019", "(901)476-2491"};
    String Gainesville[] = {"Gainesville", "3500 NE 4th Street \n Gainesville, FL 32609", "(352)377-3933"};
    String GrandRapids[] = {"Grand Rapids", "1180 58th St. SW. \n Grand Rapids, MI 49509", "(616)530-6600"};
    String Greenville[] = {"Greenville", "109 Sulphur Springs Road \n Greenville, SC 29617", "(864)246-3311"};
    String Huntsville[] = {"Huntsville", "2836 Dug Hill Road \n Huntsville, AL 35811", "(256)859-3770"};
    String Indianapolis[] = {"Indianapolis", "9420A E. 33rd Street \n Indianapolis, IN 46235", "(317)897-0203"};
    String JohnsonCity[] = {"Johnson City", "408 Lafe Cox Drive \n Johnson City, TN 37605", "(423)926-6175"};
    String LakeCity[] = {"Lake City", "1879 Forest Pkwy. \n Lake City, GA 30260", "(404)366-9073"};
    String Lynchburg[] = {"Lynchburg", "118 Enterprise Drive \n Madison Heights, VA 24572", "(434)929-6515"};
    String Miami[] = {"Miami", "3580 NW 119th St. \n Miami, FL 33167", "(305)685-6232"};
    String Milton[] = {"Milton", "5601 East Milton Rd. \n Milton, FL 32583", "(850)981-5700"};
    String Newton[] = {"Newton", "1200 Burris Road \n Newton, NC 28658", "(828)464-1010"};
    String Opp[] = {"Opp", "601 Highway 52 East \n Opp, AL 36467", "(334)493-3255"};
    String Orangeburg[] = {"Orangeburg", "368 Millennium Drive \n Orangeburg, SC 29115", "(803)534-4532"};
    String Paducah[] = {"Paducah", "5550 Commerce Drive \n Paducah, KY 42001", "(270)444-0695"};
    String Pikeville[] = {"Pikeville", "32 Lakeview Lane \n Harold, KY 41635", "(606)478-9591"};
    String Roane[] = {"Roane County", "2500 Buttermilk Road \n Lenoir City, TN 37771", "(865)717-6800"};
    String Waynesville[] = {"Waynesville", "218 Depot Street \n Waynesville, NC 28786", "(828)456-8692"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request permission to place calls
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);

        //Declares the ID association for ExpandableLayouts and the corresponding TextViews
        //Sets the onClickListener
        WhoAreWeLayout = findViewById(R.id.who_are_we_expandable_layout);
        WhereAreWeLayout = findViewById(R.id.where_are_we_expandable_layout);
        ContactUsLayout = findViewById(R.id.contact_us_expandable_layout);
        WhoWeAreText = findViewById(R.id.who_we_are_text_header);
        WhereWeAreText = findViewById(R.id.where_we_are_text_header);
        ContactUsText = findViewById(R.id.contact_us_text_header);
        WhoWeAreText.setOnClickListener(this);
        WhereWeAreText.setOnClickListener(this);
        ContactUsText.setOnClickListener(this);

        //Declared the button ID association
        //Sets the onClickListener
        emailUs = findViewById(R.id.email_us_button);
        callUs = findViewById(R.id.call_us_button);
        emailUs.setOnClickListener(this);
        callUs.setOnClickListener(this);

        //Declares the EditText field entry
        userNameTextField = findViewById(R.id.material_text_field);
        userName = findViewById(R.id.user_name_for_email_intent);

        //Sets the parameters for the TextSwitcher
        mainHeaderText = findViewById(R.id.header_tagline_text);
        mainHeaderText.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView headerText = new TextView(MainActivity.this);
                headerText.setTextSize(22);
                headerText.setTextColor(Color.BLACK);
                headerText.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                return headerText;
            }
        });

        //Sets up the TextSwitcher animation
        Animation textIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        mainHeaderText.setInAnimation(textIn);
        Animation textOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        mainHeaderText.setOutAnimation(textOut);
        mainHeaderText.setCurrentText(getString(R.string.deliver_satisfaction));

        /*
         * Sets up the ArrayAdapter & ListView from String values
         * Determines the actions when an item from the ArrayAdapter is clicked
         * Switch/Case statements determine what text is updated by pulling values from String location arrays
         */
        displayBreak = findViewById(R.id.location_view_breakline);
        stateListView = findViewById(R.id.stateListView);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listview, R.id.textView, stateList);
        stateListView.setAdapter(adapter);
        stateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                warehouseName = findViewById(R.id.warehouse_name);
                warehouseAddress = findViewById(R.id.warehouse_address);
                warehousePhone = findViewById(R.id.warehouse_phone);
                warehouseNameTwo = findViewById(R.id.warehouse_name_two);
                warehouseAddressTwo = findViewById(R.id.warehouse_address_two);
                warehousePhoneTwo = findViewById(R.id.warehouse_phone_two);
                String selection = (String) stateListView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "You selected: " + selection, Toast.LENGTH_SHORT).show();
                switch (selection) {
                    case "Alabama": //Opp & Huntsville
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Opp[0]);
                        warehouseAddress.setText(Opp[1]);
                        warehousePhone.setText(Opp[2]);
                        warehouseNameTwo.setText(Huntsville[0]);
                        warehouseAddressTwo.setText(Huntsville[1]);
                        warehousePhoneTwo.setText(Huntsville[2]);
                        break;
                    case "Arkansas": //Covington & Paducah
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Covington[0]);
                        warehouseAddress.setText(Covington[1]);
                        warehousePhone.setText(Covington[2]);
                        warehouseNameTwo.setText(Paducah[0]);
                        warehouseAddressTwo.setText(Paducah[1]);
                        warehousePhoneTwo.setText(Paducah[2]);
                        break;
                    case "Florida": //Miami & Gainseville
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Miami[0]);
                        warehouseAddress.setText(Miami[1]);
                        warehousePhone.setText(Miami[2]);
                        warehouseNameTwo.setText(Gainesville[0]);
                        warehouseAddressTwo.setText(Gainesville[1]);
                        warehousePhoneTwo.setText(Gainesville[2]);
                        break;
                    case "Georgia": //Lake City & Orangeburg
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(LakeCity[0]);
                        warehouseAddress.setText(LakeCity[1]);
                        warehousePhone.setText(LakeCity[2]);
                        warehouseNameTwo.setText(Orangeburg[0]);
                        warehouseAddressTwo.setText(Orangeburg[1]);
                        warehousePhoneTwo.setText(Orangeburg[2]);
                        break;
                    case "Illinois": //Indianapolis & Grand Rapids
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Indianapolis[0]);
                        warehouseAddress.setText(Indianapolis[1]);
                        warehousePhone.setText(Indianapolis[2]);
                        warehouseNameTwo.setText(GrandRapids[0]);
                        warehouseAddressTwo.setText(GrandRapids[1]);
                        warehousePhoneTwo.setText(GrandRapids[2]);
                        break;
                    case "Indiana": //Indianapolis & Grand Rapids
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Indianapolis[0]);
                        warehouseAddress.setText(Indianapolis[1]);
                        warehousePhone.setText(Indianapolis[2]);
                        warehouseNameTwo.setText(GrandRapids[0]);
                        warehouseAddressTwo.setText(GrandRapids[1]);
                        warehousePhoneTwo.setText(GrandRapids[2]);
                        break;
                    case "Iowa": // Paducah & Blank
                    case "Missouri":
                        setWarehouseTwoGone();
                        displayBreak.setVisibility(View.GONE);
                        warehouseName.setText(Paducah[0]);
                        warehouseAddress.setText(Paducah[1]);
                        warehousePhone.setText(Paducah[2]);
                        break;
                    case "Kentucky": //Paducah & Pikeville
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Paducah[0]);
                        warehouseAddress.setText(Paducah[1]);
                        warehousePhone.setText(Paducah[2]);
                        warehouseNameTwo.setText(Pikeville[0]);
                        warehouseAddressTwo.setText(Pikeville[1]);
                        warehousePhoneTwo.setText(Pikeville[2]);
                        break;
                    case "Louisiana": //Covington & Milton
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Covington[0]);
                        warehouseAddress.setText(Covington[1]);
                        warehousePhone.setText(Covington[2]);
                        warehouseNameTwo.setText(Milton[0]);
                        warehouseAddressTwo.setText(Milton[1]);
                        warehousePhoneTwo.setText(Milton[2]);
                        break;
                    case "Maryland": //Charleston & Lynchburg
                    case "West Virginia":
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Charleston[0]);
                        warehouseAddress.setText(Charleston[1]);
                        warehousePhone.setText(Charleston[2]);
                        warehouseNameTwo.setText(Lynchburg[0]);
                        warehouseAddressTwo.setText(Lynchburg[1]);
                        warehousePhoneTwo.setText(Lynchburg[2]);
                        break;
                    case "Michigan": // Grand Rapids & Blank
                    case "Minnesota":
                    case "Wisconsin":
                        setWarehouseTwoGone();
                        displayBreak.setVisibility(View.GONE);
                        warehouseName.setText(GrandRapids[0]);
                        warehouseAddress.setText(GrandRapids[1]);
                        warehousePhone.setText(GrandRapids[2]);
                        break;
                    case "Mississippi": // Opp & Milton
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Opp[0]);
                        warehouseAddress.setText(Opp[1]);
                        warehousePhone.setText(Opp[2]);
                        warehouseNameTwo.setText(Milton[0]);
                        warehouseAddressTwo.setText(Milton[1]);
                        warehousePhoneTwo.setText(Milton[2]);
                        break;
                    case "North Carolina": //Waynesville & Newton
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Waynesville[0]);
                        warehouseAddress.setText(Waynesville[1]);
                        warehousePhone.setText(Waynesville[2]);
                        warehouseNameTwo.setText(Newton[0]);
                        warehouseAddressTwo.setText(Newton[1]);
                        warehousePhoneTwo.setText(Newton[2]);
                        break;
                    case "Ohio": //Columbus & Grand Rapids
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Columbus[0]);
                        warehouseAddress.setText(Columbus[1]);
                        warehousePhone.setText(Columbus[2]);
                        warehouseNameTwo.setText(GrandRapids[0]);
                        warehouseAddressTwo.setText(GrandRapids[1]);
                        warehousePhoneTwo.setText(GrandRapids[2]);
                        break;
                    case "Pennsylvania": //Charleston & Columbus
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Charleston[0]);
                        warehouseAddress.setText(Charleston[1]);
                        warehousePhone.setText(Charleston[2]);
                        warehouseNameTwo.setText(Columbus[0]);
                        warehouseAddressTwo.setText(Columbus[1]);
                        warehousePhoneTwo.setText(Columbus[2]);
                        break;
                    case "South Carolina": //Greenville & Waynesville
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Greenville[0]);
                        warehouseAddress.setText(Greenville[1]);
                        warehousePhone.setText(Greenville[2]);
                        warehouseNameTwo.setText(Waynesville[0]);
                        warehouseAddressTwo.setText(Waynesville[1]);
                        warehousePhoneTwo.setText(Waynesville[2]);
                        break;
                    case "Tennessee": //Roane & Johnson City
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Roane[0]);
                        warehouseAddress.setText(Roane[1]);
                        warehousePhone.setText(Roane[2]);
                        warehouseNameTwo.setText(JohnsonCity[0]);
                        warehouseAddressTwo.setText(JohnsonCity[1]);
                        warehousePhoneTwo.setText(JohnsonCity[2]);
                        break;
                    case "Virginia": //Lynchburg & Newton
                        setWarehouseTwoVisible();
                        displayBreak.setVisibility(View.VISIBLE);
                        warehouseName.setText(Lynchburg[0]);
                        warehouseAddress.setText(Lynchburg[1]);
                        warehousePhone.setText(Lynchburg[2]);
                        warehouseNameTwo.setText(Newton[0]);
                        warehouseAddressTwo.setText(Newton[1]);
                        warehousePhoneTwo.setText(Newton[2]);
                        break;
                }
            }
        });



        /*
         * Performs a check to see if app is running in Portrait or Landscape mode
         * Sets the animation orientation for ExpandableLayouts
         */
        if (getResources().getDisplayMetrics().widthPixels < getResources().getDisplayMetrics().heightPixels) {
            //Portrait
            WhoAreWeLayout.setOrientation(ExpandableLayout.VERTICAL);
            WhereAreWeLayout.setOrientation(ExpandableLayout.VERTICAL);
            ContactUsLayout.setOrientation(ExpandableLayout.VERTICAL);
        } else {
            //Landscape
            WhoAreWeLayout.setOrientation(ExpandableLayout.HORIZONTAL);
            WhereAreWeLayout.setOrientation(ExpandableLayout.HORIZONTAL);
            ContactUsLayout.setOrientation(ExpandableLayout.HORIZONTAL);
        }


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     *
     * Updates the TextSwitcher text string
     * Also defines the animation for ExpandableLayouts for only one open
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.who_we_are_text_header:
                if (WhoAreWeLayout.isExpanded()) {
                    mainHeaderText.setText(getString(R.string.deliver_satisfaction));
                    WhoAreWeLayout.collapse();
                    WhereAreWeLayout.collapse();
                    ContactUsLayout.collapse();
                } else {
                    mainHeaderText.setText(getString(R.string.insistence_on_integrity));
                    WhoAreWeLayout.expand();
                    WhereAreWeLayout.collapse();
                    ContactUsLayout.collapse();
                }
                break;
            case R.id.where_we_are_text_header:
                if (WhereAreWeLayout.isExpanded()) {
                    mainHeaderText.setText(getString(R.string.deliver_satisfaction));
                    WhereAreWeLayout.collapse();
                    ContactUsLayout.collapse();
                } else {
                    mainHeaderText.setText(getString(R.string.find_us_across_the_country));
                    WhoAreWeLayout.collapse();
                    WhereAreWeLayout.expand();
                    ContactUsLayout.collapse();
                }
                break;
            case R.id.contact_us_text_header:
                if (ContactUsLayout.isExpanded()) {
                    mainHeaderText.setText(getString(R.string.deliver_satisfaction));
                    ContactUsLayout.collapse();
                } else {
                    mainHeaderText.setText(getString(R.string.contact_details));
                    WhoAreWeLayout.collapse();
                    WhereAreWeLayout.collapse();
                    ContactUsLayout.expand();
                }
                break;
            case R.id.call_us_button:
                onClickCallUsIntent();
                break;
            case R.id.email_us_button:
                onClickEmailUsIntent();
                break;
        }
    }

    public void setWarehouseTwoGone() {
        warehouseNameTwo.setVisibility(View.GONE);
        warehouseAddressTwo.setVisibility(View.GONE);
        warehousePhoneTwo.setVisibility(View.GONE);
    }

    public void setWarehouseTwoVisible() {
        warehouseNameTwo.setVisibility(View.VISIBLE);
        warehouseAddressTwo.setVisibility(View.VISIBLE);
        warehousePhoneTwo.setVisibility(View.VISIBLE);
    }

    public void onClickCallUsIntent() {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "18004061291"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Double checks for initial permission request
            // If permission NOT granted then re-request.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);
            return;
        }
        startActivity(phoneIntent);
    }

    public void onClickEmailUsIntent() {
        userNameEntry = userName.getText().toString();
        String emailContact = "";
        String phoneContact = "";
        String mPhoneNumber = "";
        String formattedPhone = "";
        String emailBodyStringData = "To Whom It May Concern, " + "\n \n My name is " + userNameEntry + ", " + "and I am interested in speaking with someone about the services you offer. \n" + "Please contact me via: \n";
        if (userNameEntry.equals("")) {
            Toast.makeText(this, "Please enter your name first", Toast.LENGTH_SHORT).show();
            userNameTextField.expand();
            userName.setFocusable(true);
        } else {
            email = findViewById(R.id.emailcheckbox);
            call = findViewById(R.id.callcheckbox);
            textMessage = findViewById(R.id.textcheckbox);
            boolean emailChecked = email.isChecked();
            if (emailChecked) {
                emailContact = "E-Mail ";
                emailBodyStringData = emailBodyStringData + emailContact;
            }
            boolean callChecked = call.isChecked();
            boolean textChecked = textMessage.isChecked();
            if (callChecked || textChecked) {
                TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // CHECK FOR PHONE NUMBER PERMISSION
                    // If Permission are NOT granted, then re-request permission
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 225);
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 225);
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_NUMBERS}, 225);
                    return;
                }
                //Format the phone number into pre-formatted #-###-###-####
                mPhoneNumber = tMgr.getLine1Number();
                formattedPhone = PhoneNumberUtils.formatNumber(mPhoneNumber);
                phoneContact = "Phone Number: ";
                emailBodyStringData = emailBodyStringData + "\n" + phoneContact + formattedPhone;
            }
            emailBodyStringData = emailBodyStringData + "\n \n Sincerely, \n" + userNameEntry;

            //Send the intent request for an email creation
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "contact-us@hthackney.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Potential Customer Inquiry");
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailBodyStringData);
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        }

    }


}
