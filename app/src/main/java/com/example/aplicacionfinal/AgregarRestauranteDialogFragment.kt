package com.example.aplicacionfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aplicacionfinal.databinding.DialogNuevoRestauranteBinding

class AgregarRestauranteDialogFragment : DialogFragment() {

    private var _binding: DialogNuevoRestauranteBinding? = null
    private val binding get() = _binding!!

    private var onGuardarClickListener: ((String, String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogNuevoRestauranteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardar.setOnClickListener {
            val titulo = binding.edtTitulo.text.toString()
            val descripcion = binding.edtDescripcion.text.toString()

            if (titulo.isNotEmpty() && descripcion.isNotEmpty()) {
                onGuardarClickListener?.invoke(titulo, descripcion)
                dismiss() // Cierra el diálogo
            } else {
                Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setOnGuardarClickListener(listener: (String, String) -> Unit) {
        onGuardarClickListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
