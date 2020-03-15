package com.ashwin.android.jetpacknavigation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal

class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController

    // Arguments
    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipient = arguments?.getString("recipient") ?: "None"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        recipient_tv.text = "Sending money to $recipient"
        send_btn.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.send_btn -> {
                val amount = input_amount.text.toString()
                if (!TextUtils.isEmpty(amount)) {
                    val money = Money(BigDecimal(amount))
                    val bundle = bundleOf(
                        "recipient" to recipient,
                        "amount" to money
                    )
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
                } else {
                    Toast.makeText(activity, "Enter an amount", Toast.LENGTH_LONG).show()
                }
            }
            R.id.cancel_btn -> activity!!.onBackPressed()
        }
    }
}
