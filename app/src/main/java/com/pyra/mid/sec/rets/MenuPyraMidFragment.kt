package com.pyra.mid.sec.rets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pyra.mid.sec.rets.databinding.FragmentMenuPyraMidBinding

class MenuPyraMidFragment : Fragment() {
    private var _bindingPyraMid: FragmentMenuPyraMidBinding? = null
    private val bindingPyraMid get() = _bindingPyraMid!!

    override fun onCreateView(
        inflaterPyraMid: LayoutInflater, containerPyraMid: ViewGroup?,
        savedInstanceStatePyraMid: Bundle?
    ): View {
        _bindingPyraMid = FragmentMenuPyraMidBinding.inflate(inflaterPyraMid, containerPyraMid, false)
        return bindingPyraMid.root
    }

    override fun onViewCreated(viewPyraMid: View, savedInstanceStatePyraMid: Bundle?) {
        super.onViewCreated(viewPyraMid, savedInstanceStatePyraMid)

        setListenersForMenuPyraMid()
    }

    private fun setListenersForMenuPyraMid(){
        bindingPyraMid.btnStartPyraMidGame.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_for_fragments_pyra_mid, GamePyraMidFragment())
                .addToBackStack(null)
                .commit()
        }
        bindingPyraMid.btnPyraMidVolume.setOnClickListener {
            if (!MainActivity.pauseSoundPyraMid){
                MainActivity.soundPyraMid?.pause()
                MainActivity.pauseSoundPyraMid=true
                bindingPyraMid.volumeOnOff.visibility = View.GONE
            }else{
                MainActivity.soundPyraMid?.start()
                MainActivity.pauseSoundPyraMid=false
                bindingPyraMid.volumeOnOff.visibility = View.VISIBLE
            }
        }
        bindingPyraMid.btnPyraMidScore.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_for_fragments_pyra_mid, ScorePyraMidFragment())
                .addToBackStack(null)
                .commit()
        }
        bindingPyraMid.btnPyraMidExit.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onResume() {
        super.onResume()

        if (MainActivity.pauseSoundPyraMid){
            bindingPyraMid.volumeOnOff.visibility = View.GONE
        }else{
            bindingPyraMid.volumeOnOff.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _bindingPyraMid = null
    }
}