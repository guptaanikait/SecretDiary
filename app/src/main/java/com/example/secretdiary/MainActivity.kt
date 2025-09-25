package com.example.secretdiary

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import java.time.LocalDateTime

import android.app.AlertDialog
import java.util.Collections
import kotlinx.datetime.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edittext=findViewById<EditText>(R.id.etNewWriting)
        var button=findViewById<Button>(R.id.btnSave)
        var textview=findViewById<TextView>(R.id.tvDiary)
        var buttonundo=findViewById<Button>(R.id.btnUndo)
        //var listhelp = mutableListOf<String>()

        val PREF_DIARY = getSharedPreferences("PREF_DIARY", MODE_PRIVATE)
        var result = PREF_DIARY.getString("KEY_DIARY_TEXT", "null")
        textview.text=result

        button.setOnClickListener {
            if(TextUtils.isEmpty(edittext.getText().toString()) || edittext.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Empty or blank input cannot be saved",Toast.LENGTH_SHORT).show()
            }
            else{
                /*textview.text=edittext.text.toString()*/
                var tz1 = TimeZone.currentSystemDefault()
                var currentInstant = Clock.System.now()
                var local: kotlinx.datetime.LocalDateTime =currentInstant.toLocalDateTime(tz1)
                var localstring=local.toString()
                var localstringhelp=""
                var i=0
                while(i<19){
                    if(i>=localstring.length){
                        if(i==13 || i==16){
                            localstringhelp+=':'
                        }
                        else{
                            localstringhelp+='0'
                        }
                    }
                    else if(localstring[i]=='T'){
                        localstringhelp+=" "
                    }
                    else{
                        localstringhelp+=localstring[i]
                    }
                    i++
                }

                var stringhelp=edittext.text.toString()
                var stringhelp1=localstringhelp+"\n"+stringhelp
                //result = PREF_DIARY.getString("key", "null")
                var  listsplit=emptyList<String>()
                if(result=="null"){

                }
                else{
                    listsplit = result!!.split("\n\n")
                }
                var listhelp=listsplit.toMutableList()
                Collections.reverse(listhelp)
                listhelp.add(stringhelp1)
                //Collections.reverse(listhelp)

                //listhelp.add(stringhelp1)
                i=listhelp.size-1
                var stringans=""
                while(i>=0){
                    if(i>0){
                        stringans+=listhelp[i]
                        stringans+="\n"
                        stringans+="\n"
                    }
                    else if(i==0){
                        stringans+=listhelp[i]
                    }
                    i--
                }
                stringans.trimIndent()

                //val value = edittext.text.toString()
                PREF_DIARY.edit().putString("KEY_DIARY_TEXT", stringans).apply()

                result = PREF_DIARY.getString("KEY_DIARY_TEXT", "null")
                //retrievedValue.text = result
                textview.text=stringans


                edittext.setText("")
            }
        }
        buttonundo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Remove last note")
                .setMessage("Do you really want to remove the last writing? This operation cannot be undone!")
                .setPositiveButton("Yes"){_,_->
                    var  listsplit=emptyList<String>()
                    if(result=="null"){

                    }
                    else{
                        listsplit = result!!.split("\n\n")
                    }
                    var listhelp=listsplit.toMutableList()
                    Collections.reverse(listhelp)
                    if(listhelp.size>0){
                        listhelp.removeAt(listhelp.lastIndex)
                        var i=listhelp.size-1
                        var stringans=""
                        while(i>=0){
                            if(i>0){
                                stringans+=listhelp[i]
                                stringans+="\n"
                                stringans+="\n"
                            }
                            else if(i==0){
                                stringans+=listhelp[i]
                            }
                            i--
                        }
                        stringans.trimIndent()
                        //val value = edittext.text.toString()
                        PREF_DIARY.edit().putString("KEY_DIARY_TEXT", stringans).apply()

                        result = PREF_DIARY.getString("KEY_DIARY_TEXT", "null")
                        textview.text=stringans

                    }
                    else{
                        PREF_DIARY.edit().putString("KEY_DIARY_TEXT","null").apply()

                        result = PREF_DIARY.getString("KEY_DIARY_TEXT", "null")
                    }

                }
                .setNegativeButton("No",null)
                .show()

        }

        /*
            Tests for android can not guarantee the correctness of solutions that make use of
            mutation on "static" variables to keep state. You should avoid using those.
            Consider "static" as being anything on kotlin that is transpiled to java
            into a static variable. That includes global variables and variables inside
            singletons declared with keyword object, including companion object.
            This limitation is related to the use of JUnit on tests. JUnit re-instantiate all
            instance variable for each test method, but it does not re-instantiate static variables.
            The use of static variable to hold state can lead to state from one test to spill over
            to another test and cause unexpected results.
            Using mutation on static variables to keep state
            is considered a bad practice anyway and no measure
            attempting to give support to that pattern will be made.
         */
    }
}