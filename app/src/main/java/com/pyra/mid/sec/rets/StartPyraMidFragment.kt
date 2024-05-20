package com.pyra.mid.sec.rets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.pyra.mid.sec.rets.databinding.FragmentStartPyraMidBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartPyraMidFragment : Fragment() {
    private var _bindingPyraMid: FragmentStartPyraMidBinding? = null
    private val bindingPyraMid get() = _bindingPyraMid!!

    override fun onCreateView(
        inflaterPyraMid: LayoutInflater, containerPyraMid: ViewGroup?,
        savedInstanceStatePyraMid: Bundle?
    ): View {
        _bindingPyraMid = FragmentStartPyraMidBinding.inflate(inflaterPyraMid, containerPyraMid, false)
        return bindingPyraMid.root
    }

    override fun onViewCreated(viewPyraMid: View, savedInstanceStatePyraMid: Bundle?) {
        super.onViewCreated(viewPyraMid, savedInstanceStatePyraMid)

        startPyraMid()
    }

    private fun startPyraMid(){
        bindingPyraMid.progressBarPyraMid.startAnimation(
            AnimationUtils.loadAnimation(
                requireActivity(),
                R.anim.animation_pyra_mid
            )
        )

        lifecycleScope.launch(Dispatchers.Main) {
            delay(2250)

            parentFragmentManager.beginTransaction()
                .replace(R.id.container_for_fragments_pyra_mid, MenuPyraMidFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _bindingPyraMid = null
    }
}