package com.example.alertdialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import java.lang.ClassCastException

class ExampleDialog : AppCompatDialogFragment() { //미친다. 괄호도 안넣고, 상속하는데 : 콜론도 안넣고.... 쯧쯧..
    lateinit var builder : AlertDialog.Builder
    //now we have to set this listener to our activity
    //그러면 Activity와 연결하기 위해서 변수를 만들었다는거네..
    //그 해당 Acitivity가 ExampleDialogInterface를 상속하니깐 그걸 구현한 부분을 사용하기 위해서
    //이 안에서 해당 변수를 가지고 있어서 그걸로 여기서 외부를 조정한다는 거지..
    lateinit var listener : ExampleDialogListener //현재는 listener 변수만 만들어놓은 상태이다.
    val dialogValue : Int = 100
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            listener = activity as ExampleDialogListener
        } catch (e : ClassCastException) {
            throw ClassCastException(context.toString()+" must implement ExampleDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder  = AlertDialog.Builder(activity)
        builder.setTitle("Attention")
            .setMessage("Do you want to increase the counter by 1?")
            //들어오는거를 캔슬하겠다는거지.. dialogInterface들어온거를 .cancel()하겠다는거다.
            .setNegativeButton("cancel", DialogInterface.OnClickListener { dialogInterface, i -> Unit })
            //다이얼로가 떠있는 상태에서 yes 버턴을 누를때 listener.onYesClicked()가 실행된다는 거는 외부의 즉 상속받은 Activity의
                //onYesClicked가 실행된다는 거지
            .setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i -> listener.onYesClicked(dialogValue)  })
        return builder.create()
    }
    //이제부터 인터페이스를 만든단다.. 왜?? 어디든지 연결하겠다는게 목표이지...
    public interface ExampleDialogListener {
        //yes가 눌려졌을때
        //내가 여기에다가 nested Interface를 만들었지만 중요한 사실은 이 인터페이스를 상속받으면 반드시 onYesClicked()를 구현해야한다.
        //어디서? 그 외부에서(액티비티에서)
        fun onYesClicked(dialogValue : Int) {
        }
    }

}
