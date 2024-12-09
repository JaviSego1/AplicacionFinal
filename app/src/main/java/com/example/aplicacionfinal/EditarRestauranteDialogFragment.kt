package com.example.aplicacionfinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.aplicacionfinal.databinding.DialogEditarRestauranteBinding
import com.example.aplicacionfinal.modelos.Restaurante

class EditarRestauranteDialogFragment(private val restaurante: Restaurante) : DialogFragment() {

    private var _binding: DialogEditarRestauranteBinding? = null
    private val binding get() = _binding!!

    private var onGuardarClickListener: ((String, String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditarRestauranteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa los campos con los valores actuales del restaurante
        binding.etTitulo.setText(restaurante.titulo)
        binding.etDescripcion.setText(restaurante.descripcion)

        binding.btnGuardar.setOnClickListener {
            val titulo = binding.etTitulo.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

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
