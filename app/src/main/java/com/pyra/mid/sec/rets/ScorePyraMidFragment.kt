package com.pyra.mid.sec.rets

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pyra.mid.sec.rets.databinding.FragmentScorePyraMidBinding

class ScorePyraMidFragment : Fragment() {
    private var _bindingPyraMid: FragmentScorePyraMidBinding? = null
    private val bindingPyraMid get() = _bindingPyraMid!!
    private var prefsPyraMid: SharedPreferences? = null

    override fun onCreateView(
        inflaterPyraMid: LayoutInflater, containerPyraMid: ViewGroup?,
        savedInstanceStatePyraMid: Bundle?
    ): View {
        _bindingPyraMid = FragmentScorePyraMidBinding.inflate(inflaterPyraMid, containerPyraMid, false)
        return bindingPyraMid.root
    }

    override fun onViewCreated(viewPyraMid: View, savedInstanceStatePyraMid: Bundle?) {
        super.onViewCreated(viewPyraMid, savedInstanceStatePyraMid)

        prefsPyraMid = requireActivity().getSharedPreferences("NameScore", Context.MODE_PRIVATE)

        val scoreFromPrefPyraMid = prefsPyraMid?.getString("score", null)
        if (scoreFromPrefPyraMid==null){
            bindingPyraMid.textScore.text = "Empty for now"
        }else{
            bindingPyraMid.textScore.text = scoreFromPrefPyraMid
        }

        bindingPyraMid.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _bindingPyraMid = null
    }
}