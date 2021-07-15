package com.example.nofapmainactivity.ToDoList;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.example.nofapmainactivity.AppDefaultFragment;
import com.example.nofapmainactivity.MainPage.MainPageFragment;
import com.example.nofapmainactivity.Utility.Modals.ToDoModel;
import com.example.nofapmainactivity.R;
import com.example.nofapmainactivity.Utility.ToDoModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToDoListFragment extends AppDefaultFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Date mLastEdited;
    private EditText mToDoTextBodyEditText;
    private EditText mToDoTextBodyDescription;
    private SwitchCompat mToDoDateSwitch;
    private LinearLayout mUserDateSpinnerContainingLinearLayout;
    private TextView mReminderTextView;

    private String CombinationText;


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
                String toDoTextContainer = mToDoTextBodyEditText.getText().toString();
                String toDoTextBodyDescriptionContainer = mToDoTextBodyDescription.getText().toString();
                ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                CombinationText = "Title : " + toDoTextContainer + "\nDescription : " + toDoTextBodyDescriptionContainer + "\n -Copied From ToDoList";
                ClipData clip = ClipData.newPlainText("text", CombinationText);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Copied To Clipboard!", Toast.LENGTH_SHORT).show();
            }
        });
        mContainerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(mToDoTextBodyEditText);
                hideKeyboard(mToDoTextBodyDescription);
            }
        });
        if (mUserHasReminder && (mUserReminderDate != null)) {
            setReminderTextView();
            setEnterDateLayoutVisibleWithAnimations(true);
        }

        if (mUserReminderDate == null) {
            mToDoDateSwitch.setChecked(false);
            mReminderTextView.setVisibility(View.INVISIBLE);
        }

        mToDoTextBodyEditText.requestFocus();
        mToDoTextBodyEditText.setText(mUserEnteredText);
        mToDoTextBodyDescription.setText(mUserEnteredDescription);
        InputMethodManager imm = (InputMethodManager)this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        mToDoTextBodyEditText.setSelection(mToDoTextBodyEditText.length());

        mToDoTextBodyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUserEnteredText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mToDoTextBodyDescription.setText(mUserEnteredDescription);
        mToDoTextBodyDescription.setSelection(mToDoTextBodyDescription.length());
        mToDoTextBodyDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUserEnteredDescription = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        setEnterDateLayoutVisible(mToDoDateSwitch.isChecked());
        mToDoDateSwitch.setChecked(mUserHasReminder && (mUserReminderDate != null));
        mToDoDateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    app.send(this, "Action", "Reminder Set!");
                } else {
                    app.send(this, "Action", "Reminder Removed");
                }
                if (!isChecked) {
                    mUserReminderDate = null;
                }
                mUserHasReminder = isChecked;
                setDateAndTimeEditText();
                setEnterDateLayoutVisibleWithAnimations(isChecked);
                hideKeyboard(mToDoTextBodyEditText);
                hideKeyBoard(mToDoTextBodyDescription);
            }
        });
        mToDoSendFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToDoTextBodyEditText.length() <= 0) {
                    mToDoTextBodyEditText.setError(getString(R.string.todo_error));
                } else if (mUserReminderDate != null && mUserReminderDate.before(new Date())) {
                    app.send(this, "Action", "Date in the past");
                    makeResult(RESULT_CANCELED);
                } else {
                    app.send(this, "Action", "Make Todo");
                    makeResult(RESULT_OK);
                    getActivity().finish();
                }
                hideKeyboard(mToDoTextBodyEditText);
                hideKeyboard(mToDoTextBodyDescription);
            }
        });
        mDateEditText = (EditText)view.findViewById(R.id.newTodoDateEditText);
        mTimeEditText = (EditText)view.findViewById(R.id.newTodoTimeEditText);

        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                hideKeyboard(mToDoTextBodyEditText);
                if (mUserToDoItem.getToDoDate() != null) {
                    date = mUserReminderDate;
                } else {
                    date = new Date();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(ToDoListFragment.this, year, month, day);
                if (theme.equals(MainPageFragment.DARKTHEME)) {
                    datePickerDialog.setThemeDark(true);
                }
                datePickerDialog.show();
            }
        });
        mTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                hideKeyboard(mToDoTextBodyEditText);
                if (mUserToDoItem.getToDoDate() != null) {
                    date = mUserReminderDate;
                } else {
                    date = new Date();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(ToDoListFragment.this, hour, minute, DateFormat.is24HourFormat(getContext()));
                if (theme.equals(MainPageFragment.DARKTHEME)) {
                    timePickerDialog.setThemeDark(true);
                }
                timePickerDialog.show();
            }
        });
        setDateTimeEditText();
    }

    private void setDateTimeEditText() {
        if (mUserToDoItem.hasReminder() && mUserReminderDate != null) {
            String userDate = formatDate("d MMM, yyyy", mUserReminderDate);
            String formatToUse;
            if (DateFormat.is24HourFormat(getContext())) {
                formatToUse = "k:mm";
            } else {
                formatToUse = "h:mm a";
            }
            String userTime = formatDate(formatToUse, mUserReminderDate);
            mTimeEditText.setText(userTime);
            mDateEditText.setText(userDate);
        } else {
            mDateEditText.setText(getString(R.string.date_reminder_default));
            boolean time24 = DateFormat.is24HourFormat(getContext());
            Calendar cal = Calendar.getInstance();
            if (time24) {
                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
            } else {
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
            }
            cal.set(Calendar.MINUTE, 0);
            mUserReminderDate = cal.getTime();
            Log.d("NofapApp", "Imagined Date: " + mUserReminderDate);
            String timeString;

            if (time24) {
                timeString = formatDate("k:mm", mUserReminderDate);
            } else {
                timeString = formatDate("h:mm a", mUserReminderDate);
            }
            mTimeEditText.setText(timeString);
        }
    }

    private String getThemeSet() {
        return getActivity().getSharedPreferences(MainPageFragment.THEME_PREFERENCES, Context.MODE_PRIVATE).getString(MainPageFragment.THEME_SAVED, MainPageFragment.LIGHTTHEME);
    }
    public void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public void setDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int hour, minute;
        Calendar reminderCalender = Calendar.getInstance();
        reminderCalender.set(year, month, day);

        if (reminderCalender.before(calendar)) {
            return;
        }
        if (mUserReminderDate != null) {
            calendar.setTime(mUserReminderDate);
        }
        if (DateFormat.is24HourFormat(getContext())) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } else {
            hour = calendar.get(Calendar.MINUTE);
        }
        minute = calendar.get(Calendar.MINUTE);
        calendar.set(year, month, day, hour, minute);
        mUserReminderDate = calendar.getTime();
        setReminderTextView();
        setDateEditText();
    }

    public void setTime(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        if (mUserReminderDate != null) {
            calendar.setTime(mUserReminderDate);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("NofapApp", "Time set: " + hour);
        calendar.set(year, month, day, hour, minute, 0);
        mUserReminderDate = calendar.getTime();
        setReminderTextView();
        setTimeEditText();
    }

    public void setDateEditText() {
        String dateFormat = "d MMM, yyyy";
        mDateEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public void setTimeEditText() {
        String dateFormat;
        if (DateFormat.is24HourFormat(getContext())) {
            dateFormat = "k:mm";
        } else {
            dateFormat = "h:mm a";
        }

        mTimeEditText.setText(formatDate(dateFormat, mUserReminderDate));
    }

    public void setReminderTextView() {
       if (mUserReminderDate != null) {
           mReminderTextView.setVisibility(View.VISIBLE);
           if (mUserReminderDate.before(new Date())) {
               Log.d("NofapApp", "DATE is " + mUserReminderDate);
               mReminderTextView.setText(getString(R.string.date_error_check_again));
               mReminderTextView.setTextColor(Color.RED);
               return;
           }

           Date date = mUserReminderDate;
           String dateString = formatDate("d MMM, yyyy", date);
           String timeString;
           String amPmString = "";
           if (DateFormat.is24HourFormat(getContext())) {
               timeString = formatDate("k:mm", date);
           } else {
               timeString = formatDate("h:mm", date);
               amPmString = formatDate("a", date);
           }
           String finalString = String.format(getResources().getString(R.string.remind_date_and_time), dateString, timeString, amPmString);
           mReminderTextView.setTextColor(getResources().getColor(R.color.secondary_text));
           mReminderTextView.setText(finalString);
       } else {
           mReminderTextView.setVisibility(View.INVISIBLE);
       }
    }
    public void makeResult(int result) {
        Log.d(TAG, "makeResult - ok : in");
        Intent intent = new Intent();
        if (mUserEnteredText.length() > 0) {
            String capitalizedString = Character.toUpperCase(mUserEnteredText.charAt(0)) + mUserEnteredText.substring(1);
            mUserToDoItem.setToDoText(capitalizedString);

            Log.d(TAG, "Description: " + mUserEnteredDescription);
            mUserToDoItem.setToDoDescription(mUserEnteredDescription);
        } else {
            mUserToDoItem.setToDoText(mUserEnteredText);
            Log.d(TAG, "Description: " + mUserEnteredDescription);
            mUserToDoItem.setToDoDescription(mUserEnteredDescription);
        }
        if (mUserReminderDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mUserReminderDate);
            calendar.set(Calendar.SECOND, 0);
            mUserReminderDate = calendar.getTime();
        }
        mUserToDoItem.setToDoHasReminder(mUserHasReminder);
        mUserToDoItem.setToDoDate(mUserReminderDate);
        mUserToDoItem.setToDoColor(mUserColor);
        intent.putExtra(MainPageFragment.TODOITEM, mUserToDoItem);
        getActivity().setResult(result, intent);
    }

    public static String formatDate(String formatString, Date dateToFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(dateToFormat);
    }

    public static ToDoListFragment newInstance() {return new ToDoListFragment(); }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_to_do_list;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int dayOfMonth) {
        setDate(year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(RadicalPickerLayout radicalPickerLayout, int hour, int minute) {
        setTime(hour, minute);
    }
    public void setEnterDateLayoutVisible(boolean checked) {
        if (checked) {
            mUserDateSpinnerContainingLinearLayout.setVisibility(View.VISIBLE);
        } else {
            mUserDateSpinnerContainingLinearLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void setEnterDateLayoutVisibleWithAnimations(boolean checked) {
        if (checked) {
            setReminderTextView();
            mUserDateSpinnerContainingLinearLayout.animate().alpha(1.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    mUserDateSpinnerContainingLinearLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        } else {
            mUserDateSpinnerContainingLinearLayout.animate().alpha(0.0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mUserDateSpinnerContainingLinearLayout.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
    }


}
