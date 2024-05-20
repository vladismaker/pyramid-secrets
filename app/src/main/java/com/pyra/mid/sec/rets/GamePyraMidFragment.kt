package com.pyra.mid.sec.rets

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pyra.mid.sec.rets.databinding.FragmentGamePyraMidBinding
import kotlin.random.Random

class GamePyraMidFragment : Fragment() {
    private var _bindingPyraMid: FragmentGamePyraMidBinding? = null
    private val bindingPyraMid get() = _bindingPyraMid!!
    private var arrayCardsPyraMid:MutableList<Int> = mutableListOf(0, 0, 0, 0)
    private var arrayImagesPyraMid:MutableList<Int> = mutableListOf(R.drawable.card_pyra_mid_1, R.drawable.card_pyra_mid_2, R.drawable.card_pyra_mid_3, R.drawable.card_pyra_mid_4, R.drawable.card_pyra_mid_5, R.drawable.card_pyra_mid_6, R.drawable.card_pyra_mid_7, R.drawable.card_pyra_mid_8, R.drawable.card_pyra_mid_9, R.drawable.card_pyra_mid_10, R.drawable.card_pyra_mid_11, R.drawable.card_pyra_mid_12, R.drawable.card_pyra_mid_13)
    private var betPyraMid:Int =0
    private var countPyraMid:Int =0
    private var prefsPyraMid: SharedPreferences? = null

    override fun onCreateView(
        inflaterPyraMid: LayoutInflater, containerPyraMid: ViewGroup?,
        savedInstanceStatePyraMid: Bundle?
    ): View {
        _bindingPyraMid = FragmentGamePyraMidBinding.inflate(inflaterPyraMid, containerPyraMid, false)
        return bindingPyraMid.root
    }

    override fun onViewCreated(viewPyraMid: View, savedInstanceStatePyraMid: Bundle?) {
        super.onViewCreated(viewPyraMid, savedInstanceStatePyraMid)
        prefsPyraMid = requireActivity().getSharedPreferences("NameScore", Context.MODE_PRIVATE)
        setListenersPyraMid()
        generateCardsPyraMid()
        bindingPyraMid.btnPyraMidX2.isEnabled = false
        bindingPyraMid.btnPassPyraMid.isEnabled = false
    }

    private fun setListenersPyraMid(){
        bindingPyraMid.cardPyraMidMy1.setOnClickListener {
            if (bindingPyraMid.editPyraMidBet.text.toString() == ""){
                Toast.makeText(requireActivity(), "Enter bet!", Toast.LENGTH_SHORT).show()
            }else{
                countPyraMid++
                if (betPyraMid==0){
                    betPyraMid = bindingPyraMid.editPyraMidBet.text.toString().toInt()
                }

                bindingPyraMid.cardPyraMidMy1.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[0]])
                bindingPyraMid.cardPyraMidMy2.isEnabled=false
                bindingPyraMid.btnPyraMidX2.isEnabled = true
                bindingPyraMid.btnPassPyraMid.isEnabled = true
                bindingPyraMid.editPyraMidBet.isEnabled = false
            }
        }
        bindingPyraMid.cardPyraMidMy2.setOnClickListener {
            if (bindingPyraMid.editPyraMidBet.text.toString() == ""){
                Toast.makeText(requireActivity(), "Enter bet!", Toast.LENGTH_SHORT).show()
            }else{
                countPyraMid++
                if (betPyraMid==0){
                    betPyraMid = bindingPyraMid.editPyraMidBet.text.toString().toInt()
                }

                bindingPyraMid.cardPyraMidMy2.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[1]])
                bindingPyraMid.cardPyraMidMy1.isEnabled=false
                bindingPyraMid.btnPyraMidX2.isEnabled = true
                bindingPyraMid.btnPassPyraMid.isEnabled = true
                bindingPyraMid.editPyraMidBet.isEnabled = false
            }
        }

        bindingPyraMid.btnPyraMidX2.setOnClickListener {
            betPyraMid *= 2
            bindingPyraMid.editPyraMidBet.setText("$betPyraMid")
            bindingPyraMid.cardPyraMidMy1.isEnabled=true
            bindingPyraMid.cardPyraMidMy2.isEnabled=true
            bindingPyraMid.btnPyraMidX2.isEnabled = false
            bindingPyraMid.btnPassPyraMid.isEnabled = false
            if (countPyraMid==2){
                bindingPyraMid.cardRandom1.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[2]])
                bindingPyraMid.cardRandom2.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[3]])
                checkResultPyraMid()
            }
        }
        bindingPyraMid.btnPassPyraMid.setOnClickListener {
            bindingPyraMid.cardPyraMidMy1.isEnabled=true
            bindingPyraMid.cardPyraMidMy2.isEnabled=true
            bindingPyraMid.btnPyraMidX2.isEnabled = false
            bindingPyraMid.btnPassPyraMid.isEnabled = false
            if (countPyraMid==2){
                bindingPyraMid.cardRandom1.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[2]])
                bindingPyraMid.cardRandom2.setImageResource(arrayImagesPyraMid[arrayCardsPyraMid[3]])
                checkResultPyraMid()
            }
        }

        bindingPyraMid.btnPyraMidExit.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        bindingPyraMid.btnPyraMidSettings.setOnClickListener {
            if (!MainActivity.pauseSoundPyraMid){
                MainActivity.soundPyraMid?.pause()
                MainActivity.pauseSoundPyraMid=true
            }else{
                MainActivity.soundPyraMid?.start()
                MainActivity.pauseSoundPyraMid=false
            }
        }
        bindingPyraMid.btnMenuPyraMid.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun checkResultPyraMid(){
        if ((arrayCardsPyraMid[0]+arrayCardsPyraMid[1]) >= (arrayCardsPyraMid[2]+arrayCardsPyraMid[3])){
            bindingPyraMid.result.setImageResource(R.drawable.win_pyra_mid)
        }else{
            bindingPyraMid.result
            bindingPyraMid.result.setImageResource(R.drawable.lose_pyra_mid)
        }

        bindingPyraMid.btnPyraMidX2.visibility = View.GONE
        bindingPyraMid.btnPassPyraMid.visibility = View.GONE
        bindingPyraMid.editPyraMidBet.visibility = View.GONE
        bindingPyraMid.btnPyraMidExit.visibility = View.VISIBLE

        val scoreFromPrefPyraMid = prefsPyraMid?.getString("score", null)
        if (scoreFromPrefPyraMid==null){
            val edPrefsPyraMid = prefsPyraMid!!.edit()
            edPrefsPyraMid.putString("score", betPyraMid.toString())
            edPrefsPyraMid.apply()
        }else{
            if (betPyraMid>scoreFromPrefPyraMid.toInt()){
                val edPrefsPyraMid = prefsPyraMid!!.edit()
                edPrefsPyraMid.putString("score", betPyraMid.toString())
                edPrefsPyraMid.apply()
            }
        }
    }

    private fun generateCardsPyraMid(){
        for(iPyraMid in 0 until 4){
            var successPyraMid=true
            while (successPyraMid){
                val randomPyraMid = Random.nextInt(13)
                var bPyraMid=true
                for(jPyraMid in 0 until arrayCardsPyraMid.size){
                    if (arrayCardsPyraMid[jPyraMid]==randomPyraMid){
                        bPyraMid=false
                        break
                    }
                }
                if (bPyraMid){
                    arrayCardsPyraMid[iPyraMid]= randomPyraMid
                    successPyraMid = false
                }else{
                    successPyraMid = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _bindingPyraMid = null
    }
}