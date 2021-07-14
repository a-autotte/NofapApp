package com.example.nofapmainactivity.ToDoList;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.SwitchCompat;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.MainPage.MainPageFragment;
import com.example.nofapmainactivity.Utility.Modals.ToDoModel;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.Utility.ToDoModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class ToDoListFragment extends AppDefaultFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Date mLastEdited;
    private EditText mToDoTextBodyEditText;
    private EditText mToDoTextDescription;
    private SwitchCompat mToDoDateSwitch;
    private LinearLayout mUserDateSpinnerContainingLinearLayout;
    private TextView mReminderTextView;

    private EditText mDateEditText;
    private EditText mTimeEditText;
    private String mDefaultTimeOptions12H[];
    private String mDefaultTimeOptions24H[];

    private Button mChooseDateButton;
    private Button mChooseTimeButton;
    private Button mCopyClipboard;
    private ToDoModal mUserToDoItem;
    private FloatingActionButton mToDoSendFloatingActionButton;
    public static final String DATE_FORMAT = "MMM d, yyyy";
    public static final String DATE_FORMAT_MONTH_DAY = "MMM d";
    public static final String DATE_FORMAT_TIME = "H:m";

    private String mUserEnteredText;
    private String mUserEnteredDescription;
    private boolean mUserHasReminder;
    private Date mUserReminderDate;
    private int mUserColor;
    private boolean setDateButtonClickedOnce = false;
    private boolean setTimeButtonClickedOnce = false;
    private LinearLayout mContainerLayout;
    private String theme;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        theme = getActivity().getSharedPreferences(MainPageFragment.THEME_PREFERENCES, Context.MODE_PRIVATE).getString(MainPageFragment.THEME_SAVED, MainPageFragment.LIGHTTHEME);
        if (theme.equals(MainPageFragment.LIGHTTHEME)) {
            getActivity().setTheme(R.style.CustomStyle_LightTheme);
        } else {
            getActivity().setTheme(R.style.CustomStyle.DarkTheme);
        }

        final Drawable xForm = getResources().getDrawable(R.drawable.ic_clear_white_24dp);
        if (xForm != null) {
            xForm.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_ATOP);
        }

        mUserToDoItem = (ToDoModal)getActivity().getIntent().getSerializableExtra(MainPageFragment.TODOITEM);
        mUserEnteredText = mUserToDoItem.getToDoText();
        mUserEnteredDescription = mUserToDoItem.getToDoDescription();
        mUserHasReminder = mUserToDoItem.hasReminder();
        mUserReminderDate = mUserToDoItem.getToDoDate();
        mUserColor = mUserToDoItem.getTodoColor();

        mCopyClipboard = (Button)view.findViewById(R.id.copyclipboard);
        mContainerLayout = (LinearLayout)view.findViewById(R.id.todoReminderAndDateContainerLayout);
        mUserDateSpinnerContainingLinearLayout = (LinearLayout)view.findViewById(R.id.toDoEnterDateLinearLayout);
        mToDoTextBodyEditText = (TextView)view.findViewById(R.id.userToDoEditText);
        mToDoTextDescription = (TextView)view.findViewById(R.id.userToDoDescription);
        mToDoDateSwitch = (SwitchCompat)view.findViewById(R.id.toDoHasDateSwitchCompat);
        mToDoSendFloatingActionButton = (FloatingActionButton)view.findViewById(R.id.makeToDoActionButton);
        mReminderTextView = (TextView)view.findViewById(R.id.newToDoDateTimeReminderTextView);

        mCopyClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                
            }
        });

    }

    public static ToDoListFragment newInstance() {return new ToDoListFragment(); }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_to_do_list;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
}
